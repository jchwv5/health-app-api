package io.catalyte.training.patienthealth.domains.patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<Patient>> getProducts(Patient patient) {
        logger.info("Request received for getPatients");

        return new ResponseEntity<>(patientService.getPatients(patient), HttpStatus.OK);
    }

}
