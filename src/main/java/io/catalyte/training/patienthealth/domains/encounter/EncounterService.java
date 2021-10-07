package io.catalyte.training.patienthealth.domains.encounter;

import java.util.List;

public interface EncounterService {

    List<Encounter> getEncounters(Encounter encounter);

    Encounter saveEncounter(Encounter encounter, Long id);

}
