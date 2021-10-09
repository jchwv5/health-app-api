package io.catalyte.training.patienthealth.domains.encounter;

import java.util.List;

public interface EncounterService {

    List<Encounter> getEncounters();

    Encounter saveEncounter(Encounter encounter);

    Encounter updateEncounter(Long patientId, Long id, Encounter encounter);

    List<Encounter> getEncountersByPatientId(Long patientId);

    Encounter getEncounterById(Long id);

}
