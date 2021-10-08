package io.catalyte.training.patienthealth.domains.patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients(Patient patient);

    Patient getPatientById(Long id);

    Patient savePatient(Patient patient);

    Patient updatePatient(Long id, Patient patient);

    Patient findPatientByEmail(String email);

    Long deletePatientById(Long id);

}
