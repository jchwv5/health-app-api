package io.catalyte.training.patienthealth.domains.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long>,
        JpaSpecificationExecutor<Patient> {

    Patient getPatientById(Long id);

}
