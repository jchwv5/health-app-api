package io.catalyte.training.patienthealth.domains.user.PatientTests;

import io.catalyte.training.patienthealth.domains.encounter.Encounter;
import io.catalyte.training.patienthealth.domains.encounter.EncounterRepository;
import io.catalyte.training.patienthealth.domains.encounter.EncounterServiceImpl;
import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import io.catalyte.training.patienthealth.domains.patient.PatientServiceImpl;
import io.catalyte.training.patienthealth.exceptions.BadRequest;
import io.catalyte.training.patienthealth.exceptions.ConflictException;
import io.catalyte.training.patienthealth.exceptions.ResourceNotFound;
import io.catalyte.training.patienthealth.exceptions.ServerError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PatientServiceTests {

    @Mock
    EncounterRepository mockEncounterRepository;

    @Mock
    PatientRepository mockPatientRepository;

    @InjectMocks
    PatientServiceImpl mockPatientServiceImpl;

    @Mock
    EncounterServiceImpl mockEncounterServiceImpl;

    List<Encounter> encounterList = new ArrayList<>();
    List<Patient> patientList = new ArrayList<>();

    Patient patient = new Patient();
    Patient failPatient = new Patient();
    Encounter encounter = new Encounter();

    @Before
    public void setUp() throws ParseException {



        MockitoAnnotations.initMocks(this);

        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Smith");
        patient.setSsn("555-55-5555");
        patient.setEmail("john.smith@gmail.com");
        patient.setStreet("123 Test Street");
        patient.setCity("Denver");
        patient.setState("CO");
        patient.setPostal("55555");
        patient.setAge(55);
        patient.setHeight(72);
        patient.setWeight(201);
        patient.setInsurance("Blue Cross");
        patient.setGender("Male");
        patient.setEncounters(encounterList);

        patientList.add(patient);

    }

    @Test
    public void testGetPatientsReturnsPatient() throws Exception {

        when(mockPatientRepository.findAll()).thenReturn(patientList);

        List<Patient> result = mockPatientServiceImpl.getPatients();

        Assert.assertEquals(patientList, result);
    }

    @Test(expected = ServerError.class)
    public void testGetPatientsReturnsAccessException() throws Exception {

        when(mockPatientRepository.findAll()).thenThrow(new DataAccessException("..."){ });

        List<Patient> result = mockPatientServiceImpl.getPatients();

    }

    @Test
    public void testGetPatientByIdReturnsPatient() throws Exception {

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Patient result = mockPatientServiceImpl.getPatientById(patient.getId());

        Assert.assertEquals(patient, result);
    }

    @Test (expected = ResourceNotFound.class)
    public void testGetPatientByIdReturns404() throws Exception {

        Patient result = mockPatientServiceImpl.getPatientById(patient.getId());

    }

    @Test(expected = ServerError.class)
    public void testGetPatientByIdReturnsAccessException() throws Exception {

        when(mockPatientRepository.findById(any(Long.class))).thenThrow(new DataAccessException("..."){ });

        Patient result = mockPatientServiceImpl.getPatientById(patient.getId());

    }

    @Test
    public void testFindPatientByEmailReturnsPatient() throws Exception {

        when(mockPatientRepository.findByEmail(any(String.class))).thenReturn(patient);

        Patient result = mockPatientServiceImpl.findPatientByEmail(patient.getEmail());

        Assert.assertEquals(patient, result);
    }

    @Test (expected = ResourceNotFound.class)
    public void testFindPatientByEmailReturns404() throws Exception {

        Patient result = mockPatientServiceImpl.findPatientByEmail("fail@gmail.com");

    }

    @Test(expected = ServerError.class)
    public void testFindPatientByEmailReturnsAccessException() throws Exception {

        when(mockPatientRepository.findByEmail(any(String.class))).thenThrow(new DataAccessException("..."){ });

        Patient result = mockPatientServiceImpl.findPatientByEmail(patient.getEmail());

    }

    @Test
    public void testSavePatientReturnsPatient() throws Exception {

        Patient result = mockPatientServiceImpl.savePatient(patient);

        Assert.assertEquals(patient, result);

    }

    @Test(expected = ConflictException.class)
    public void testSavePatientWillNotDuplicateEmail() throws Exception {

        when(mockPatientRepository.findPatientByEmail(any(String.class))).thenReturn(patient);

        Patient result = mockPatientServiceImpl.savePatient(patient);

    }

    @Test(expected = ResponseStatusException.class)
    public void testSavePatientWillNotSavePatientWithErrors() throws Exception {

        Patient result = mockPatientServiceImpl.savePatient(failPatient);

    }

    @Test(expected = ServerError.class)
    public void testSavePatientReturnsAccessException() throws Exception {

        when(mockPatientRepository.save(any(Patient.class))).thenThrow(new DataAccessException("..."){ });

        Patient result = mockPatientServiceImpl.savePatient(patient);

    }


    @Test
    public void testUpdatePatientReturnsPatient() throws Exception {
        when(mockPatientRepository.save(any(Patient.class))).thenReturn(patient);

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Patient result = mockPatientServiceImpl.updatePatient(1L, patient);

        Assert.assertEquals(patient, result);
    }

    @Test(expected = BadRequest.class)
    public void testUpdatePatientWillNotPutPatientWithErrors() throws Exception {
        when(mockPatientRepository.save(any(Patient.class))).thenReturn(patient);

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Patient result = mockPatientServiceImpl.updatePatient(1L, failPatient);
    }

    @Test(expected = ServerError.class)
    public void testUpdatePatientReturnsAccessException() throws Exception {

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        when(mockPatientRepository.save(any(Patient.class))).thenThrow(new DataAccessException("..."){ });

        Patient result = mockPatientServiceImpl.updatePatient(1L, patient);
    }

    @Test
    public void testDeletePatientByIdDeletesPatient() throws Exception {

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Long result = mockPatientServiceImpl.deletePatientById(1L);

        verify(mockPatientRepository).deletePatientById(any());

    }

    @Test(expected = ConflictException.class)
    public void testDeletePatientByIdWontDeletePatientWithEncounters() throws Exception {
        encounter.setId(1L);
        encounter.setPatientId(patient.getId());
        encounter.setNotes("no notes");
        encounter.setVisitCode("H1H 1H1");
        encounter.setProvider("New Hospital");
        encounter.setBillingCode("123.456.789-00");
        encounter.setIcd10("A77");
        encounter.setTotalCost(BigDecimal.valueOf(100.00));
        encounter.setCopay(BigDecimal.valueOf(10.00));
        encounter.setChiefComplaint("Owwwwwwww");
        encounter.setPulse(100);
        encounter.setSystolic(80);
        encounter.setDiastolic(80);
        encounter.setDate(LocalDate.of(2020, 5, 13));

        encounterList.add(encounter);

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        Long result = mockPatientServiceImpl.deletePatientById(1L);

    }

    @Test(expected = ServerError.class)
    public void testDeletePatientByIdReturnsAccessError() throws Exception {

        when(mockPatientRepository.findById(any(Long.class))).thenReturn(Optional.of(patient));

        when(mockPatientRepository.deletePatientById(any(Long.class))).thenThrow(new DataAccessException("..."){ });

        Long result = mockPatientServiceImpl.deletePatientById(1L);

    }

}
