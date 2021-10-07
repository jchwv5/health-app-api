package io.catalyte.training.patienthealth.domains.encounter;

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

    private final EncounterService encounterService;
    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterController(
            EncounterService encounterService,
            EncounterRepository encounterRepository) {
        this.encounterService = encounterService;
        this.encounterRepository = encounterRepository;
    }

    @PostMapping
    public ResponseEntity saveEncounter(@RequestBody Encounter encounter, @PathVariable Long patientId) {

        encounterService.saveEncounter(encounter, patientId);

        return new ResponseEntity<>(encounter, HttpStatus.CREATED);
    }



}
