package io.catalyte.training.patienthealth.domains.patient;

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

@Service
public class PatientServiceImpl implements PatientService{

    private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

    @Autowired
    PatientRepository patientRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository,
                              EntityManager entityManager) {
        this.patientRepository = patientRepository;
        this.entityManager = entityManager;
    }

    public PatientServiceImpl(PatientRepository patientRepository) {
    }

    public List<Patient> getPatients(Patient patient) {
        try {
            return patientRepository.findAll(Example.of(patient));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }
}
