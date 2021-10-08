package io.catalyte.training.patienthealth.domains.encounter;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EncounterRepository extends JpaRepository<Encounter, Long>,
        JpaSpecificationExecutor<Encounter> {

    Encounter getEncounterById(Long id);

    List<Encounter> findByPatientId(Long patientId);

}