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
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

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

    /**
     * Retrieves all encounters from the database
     * @return List of all encounters present in the database
     */
    public List<Encounter> getEncounters() {
        try {
            return encounterRepository.findAll();
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }

    /**
     * Finds a list of encounters in the database by patient ID
     * @param patientId - ID to search the database for
     * @return List of encounters with associated patientId
     */
    public List<Encounter> getEncountersByPatientId(Long patientId) {
        try {
            return encounterRepository.findByPatientId(patientId);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }

    /**
     * Finds an encounter in the database by ID
     * @param id - ID to search the database for
     * @return - encounter with associated EncounterId
     */
    public Encounter getEncounterById(Long id) {
        try {
            return encounterRepository.getEncounterById(id);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }


    /**
     * Insert and save a new Encounter to database.
     *
     * @param encounter saved patient
     */
    @Override
    public Encounter saveEncounter(Encounter encounter, Long id) {
        Patient patient = patientRepository.getPatientById(encounter.getPatientId());
        BigDecimal checkCost = encounter.getTotalCost();
        if (patient == null) {throw new ResourceNotFound("Patient ID " + encounter.getPatientId() + " does not exist.");}
        if (checkCost.compareTo(BigDecimal.ZERO) == 0) {
            encounter.setTotalCost(BigDecimal.valueOf(0.00));
        }
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

    /**
     * Updates an Encounter given they are given the right credentials
     *
     * @param id - id of the encounter to update
     * @param encounter - encounter to update
     * @return Encounter - Updated encounter
     */
    @Override
    public Encounter updateEncounter(Long patientId, Long id, Encounter encounter) {
        Encounter existingEncounter;
        BigDecimal checkCost = encounter.getTotalCost();
        try {
            existingEncounter = encounterRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
        if (existingEncounter == null) {
            logger.error("Encounter with id: " + id + " does not exist");
            throw new ResourceNotFound("Encounter with id: " + id + " does not exist");
        }
        if (patientId != encounter.getPatientId()) {throw new BadRequest("Invalid Patient ID provided for path");}
        if (id != encounter.getId()){throw new BadRequest("Invalid Encounter ID provided for path");}
        encounterValidation.validateEncounter(encounter);
        //CHECKS IF PATIENT EXISTS IN THE DATABASE
        // GIVE THE ENCOUNTER ID IF NOT SPECIFIED IN BODY TO AVOID DUPLICATE USERS
        if (encounter.getId() == null) {
            encounter.setId(id);
        }
        if (checkCost.compareTo(BigDecimal.ZERO) == 0) {
            encounter.setTotalCost(BigDecimal.valueOf(0.00));
        }
        try {
            logger.info("Updated encounter ID: " + encounter.getId());
            return encounterRepository.save(encounter);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }

    }
}
