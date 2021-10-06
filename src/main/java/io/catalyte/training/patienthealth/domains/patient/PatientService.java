package io.catalyte.training.patienthealth.domains.patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients(Patient patient);
}
