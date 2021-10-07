package io.catalyte.training.patienthealth.domains.encounter;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EncounterRepository extends JpaRepository<Encounter, Long>,
        JpaSpecificationExecutor<Encounter> {

    Encounter getEncounterById(Long id);

    Encounter updateEncounter(Long id, Encounter encounter);

}