package io.catalyte.training.patienthealth.domains.patient;

import io.catalyte.training.patienthealth.exceptions.BadRequest;
import io.catalyte.training.patienthealth.exceptions.ConflictException;
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

@Service
public class PatientServiceImpl implements PatientService{

    private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    private final PatientValidation patientValidation = new PatientValidation();

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
    @Override
    public List<Patient> getPatients(Patient patient) {
        try {
            return patientRepository.findAll(Example.of(patient));
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }

    /**
     * Finds a patient in the database by ID
     * @param id - ID to search the database for
     * @return patient - patient with associated patientId
     */
    @Override
    public Patient getPatientById(Long id) {
        Patient patient;

        try {
            patient = patientRepository.findById(id).orElse(null);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }

        if (patient != null) {
            return patient;
        } else {
            logger.info("Get by id failed, patient does not exist in the database: " + id);
            throw new ResourceNotFound("Get by id failed, patient does not exist in the database: " + id);
        }
    }

    /**
     * Insert and save a new Patient to database.
     *
     * @param patient saved patient
     */
    @Override
    public Patient savePatient(Patient patient) {
        patientValidation.validatePatient(patient);
        Patient emailCheck = patientRepository.findPatientByEmail(patient.getEmail());
        if (emailCheck != null) {
            logger.info("Add new patient failed, email already exists in the database: " + patient.getEmail());
            throw new ConflictException("Email already exists: " + patient.getEmail());
        }
        try {
            patientRepository.save(patient);
            logger.info("Saved patient");
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
        return patient;
    }

    /**
     * Updates a User given they are given the right credentials
     *
     * @param id - id of the patient to update
     * @param patient - patient to update
     * @return Patient - Updated patient
     */
    @Override
    public Patient updatePatient(Long id, Patient patient) {

        Patient existingPatient;

        if (id != patient.getId()) {throw new BadRequest("Invalid Patient ID provided for path");}
        patientValidation.validatePatient(patient);
        //CHECKS IF PATIENT EXISTS IN THE DATABASE
        try {
            existingPatient = patientRepository.findById(id).orElse(null);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }

        if (existingPatient == null) {
            logger.error("Patient with id: " + id + " does not exist");
            throw new ResourceNotFound("Patient with id: " + id + " does not exist");
        }
        //CHECKS IF EMAIL IS USED BY A PATIENT OTHER THAN THE PATIENT BEING UPDATED
        Patient emailCheck = patientRepository.findPatientByEmail(patient.getEmail());

        if (emailCheck != null && (emailCheck.getId() != patient.getId())) {
            logger.info("Add new patient failed, email already exists in the database: " + patient.getEmail());
            throw new ConflictException("Email already exists: " + patient.getEmail());
        }

        // GIVE THE PATIENT ID IF NOT SPECIFIED IN BODY TO AVOID DUPLICATE USERS
        if (patient.getId() == null) {
            patient.setId(id);
        }

        try {
            logger.info("Updated user ID: " + patient.getId());
            return patientRepository.save(patient);
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }

    }
    /**
     * Retrieves the patient info with the matching email from the database.
     * @param email - patient email to find
     * @return patient
     */
    public Patient findPatientByEmail(String email) {
        Patient patient;
        try {
            patient = patientRepository.findByEmail(email);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
        if (patient != null) {
            return patient;
        } else {
            logger.info("Get by email failed, email does not exist in the database" + email);
            throw new ResourceNotFound(
                    "Get by email failed. email " + email + "does not exist in the database: "
            );
        }
    }


}
