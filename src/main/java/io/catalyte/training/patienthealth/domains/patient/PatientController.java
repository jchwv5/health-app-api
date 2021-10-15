package io.catalyte.training.patienthealth.domains.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static io.catalyte.training.patienthealth.constants.Paths.PATIENTS_PATH;

/**
 * The PatientController exposes endpoints for patient related actions.
 */

@RestController
@RequestMapping(value = PATIENTS_PATH)
public class PatientController {

    Logger logger = LogManager.getLogger(PatientController.class);

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(
            PatientService patientService,
            PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    /**
     * retrieves all patients from the database
     * @return list of patients currently present in the database
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {
        logger.info("Request received for getPatients");

        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    /**
     * retrieves a patient from the database with the requested patient ID
     * @param id id to look for in the database
     * @return patient with associated ID
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        logger.info("Request received for getPatientsById: " + id);

        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    /**
     * saves a patient to the database
     *
     * @param patient - patient to be saved
     * @return - patient saved in the database
     */
    @PostMapping
    public ResponseEntity savePatient(@RequestBody Patient patient) {
        logger.info("Request received for savePatient");
        patientService.savePatient(patient);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    /**
     * Controller method for updating the Patient
     *
     * @param id - Id of the patient to update
     * @param patient - Patient to update
     * @return Patient - Updated patient
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient
    ) {
        logger.info("Request received for Update User");
        return new ResponseEntity<>(patientService.updatePatient(id, patient), HttpStatus.OK);
    }

    /**
     * Deletes a patient from the database with a patient ID matching the ID provided,
     * provided that they do not have any associated encounters
     * @param id - The ID of the patient in question to be deleted
     * @return
     */

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Long> deletePatientById(@PathVariable Long id) {
        var isRemoved = patientService.deletePatientById(id);

        if (isRemoved == null) {
            logger.info("Could not delete Patient  " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Patient with the ID of " + id + " was successfully deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
