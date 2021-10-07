package io.catalyte.training.patienthealth.domains.encounter;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import io.catalyte.training.patienthealth.exceptions.BadRequest;
import io.catalyte.training.patienthealth.exceptions.ResourceNotFound;
import io.catalyte.training.patienthealth.exceptions.ServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterServiceImpl implements EncounterService{
    private final Logger logger = LogManager.getLogger(EncounterServiceImpl.class);

    @Autowired
    EncounterRepository encounterRepository;
    @Autowired
    PatientRepository patientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private final EncounterValidation encounterValidation = new EncounterValidation();

    @Autowired
    public EncounterServiceImpl(EncounterRepository encounterRepository,
                              EntityManager entityManager) {
        this.encounterRepository = encounterRepository;
        this.patientRepository = patientRepository;
        this.entityManager = entityManager;
    }

    public EncounterServiceImpl(EncounterRepository encounterRepository) {
    }

    public List<Encounter> getEncounters(Encounter encounter) {
        try {
            return encounterRepository.findAll(Example.of(encounter));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }

    /**
     * Insert and save a new Patient to database.
     *
     * @param encounter saved patient
     */
    @Override
    public Encounter saveEncounter(Encounter encounter, Long id) {
        Patient patient = patientRepository.getPatientById(encounter.getPatientId());
        if (patient == null) {throw new ResourceNotFound("Patient ID " + encounter.getPatientId() + " does not exist.");}
        if (id != encounter.getPatientId()) {throw new BadRequest("Invalid Patient ID provided for path");}
            encounterValidation.validateEncounter(encounter);
        try {
            encounterRepository.save(encounter);
            logger.info("Saved encounter");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
        return encounter;
    }
}
