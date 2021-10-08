package io.catalyte.training.patienthealth.domains.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;

public interface PatientRepository extends JpaRepository<Patient, Long>,
        JpaSpecificationExecutor<Patient> {

    Patient getPatientById(Long id);

    Patient findPatientByEmail(String email);

    Patient findByEmail (String email);

    @Transactional
    Long deletePatientById(Long id);

}
