package io.catalyte.training.patienthealth.domains.encounter;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.catalyte.training.patienthealth.constants.Paths.ENCOUNTERS_PATH;


@RestController
@RequestMapping(value = ENCOUNTERS_PATH)
public class EncounterController {
    Logger logger = LogManager.getLogger(EncounterController.class);
    @Autowired
    private EncounterService encounterService;

    @PostMapping
    public ResponseEntity saveEncounter(@RequestBody Encounter encounter) {

        encounterService.saveEncounter(encounter);

        return new ResponseEntity<>(encounter, HttpStatus.CREATED);
    }

    /**
     * Controller method for updating the Encounter
     *
     * @param id - Id of the encounter to update
     * @param encounter - Encounter to update
     * @return Patient - Updated encounter
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Encounter> updateEncounter(
            @PathVariable Long id,
            @PathVariable Long patientId,
            @RequestBody Encounter encounter
    ) {
        logger.info("Request received for Update User");
        return new ResponseEntity<>(encounterService.updateEncounter(patientId, id, encounter), HttpStatus.OK);
    }

    /**
     * Retrieves a list of encounters from the database associated with the provided Patient ID
     *
     * @param patientId ID to search the database for
     * @return List of encounters with associated patientId
     */
    @GetMapping
    public ResponseEntity<List<Encounter>> getEncountersByPatientId(@PathVariable Long patientId)
        throws Exception {
        return new ResponseEntity<>(encounterService.getEncountersByPatientId(patientId), HttpStatus.OK);
    }

    /**
     * Retrieves an encounter from the databse with associated ID
     *
     * @param id - ID to search the database for
     * @return Encounter with matching ID
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Encounter> getEncounterId(@PathVariable Long id)
            throws Exception {
        return new ResponseEntity<>(encounterService.getEncounterById(id), HttpStatus.OK);
    }







}
