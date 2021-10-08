package io.catalyte.training.patienthealth.domains.user.EncounterTests;

import io.catalyte.training.patienthealth.domains.encounter.Encounter;
import io.catalyte.training.patienthealth.domains.encounter.EncounterRepository;
import io.catalyte.training.patienthealth.domains.encounter.EncounterServiceImpl;
import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import io.catalyte.training.patienthealth.domains.patient.PatientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EncounterServiceTests {

    @Mock
    EncounterRepository mockEncounterRepository;

    @Mock
    PatientRepository mockPatientRepository;

    @Mock
    PatientServiceImpl mockPatientServiceImpl;

    @InjectMocks
    EncounterServiceImpl mockEncounterServiceImpl;

    List<Encounter> encounterList = new ArrayList<>();
    List<Patient> patientList = new ArrayList<>();

    Patient patient = new Patient();
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
        patient.setAge(55);
        patient.setHeight(72);
        patient.setWeight(201);
        patient.setInsurance("Blue Cross");
        patient.setGender("Male");

        patientList.add(patient);

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

        when(mockEncounterRepository.findAll()).thenReturn(encounterList);

        when(mockEncounterRepository.findById(any(Long.class))).thenReturn(Optional.of(encounter));

        when(mockEncounterRepository.findByPatientId(any(Long.class))).thenReturn(encounterList);

        when(mockEncounterRepository.existsById(any(Long.class))).thenReturn(true);

        when(mockEncounterRepository.save(any(Encounter.class))).thenReturn(encounter);

        when(mockPatientRepository.existsById(any(Long.class))).thenReturn(true);

        when(mockPatientRepository.getPatientById(any(Long.class))).thenReturn(patient);

        when(this.mockPatientServiceImpl.getPatients(any(Patient.class))).thenReturn(patientList);


    }

    @Test
    public void testGetEncountersReturnsEncounter() throws Exception {

        List<Encounter> result = mockEncounterServiceImpl.getEncounters();

        Assert.assertEquals(encounterList, result);

    }

    @Test
    public void testGetEncountersByPatientIdReturnsEncounter() throws Exception {

        List<Encounter> result = mockEncounterServiceImpl.getEncountersByPatientId(patient.getId());

        Assert.assertEquals(encounterList, result);

    }

    @Test(expected = HttpServerErrorException.ServiceUnavailable.class)
    public void testDBError() throws Exception {

        when(mockEncounterServiceImpl.getEncountersByPatientId(patient.getId())).thenThrow(HttpServerErrorException.ServiceUnavailable.class);

        List<Encounter> result = mockEncounterServiceImpl.getEncountersByPatientId(patient.getId());
    }

    @Test
    public void saveEncounterReturnsEncounter() throws Exception {

        Encounter result = mockEncounterServiceImpl.saveEncounter(encounter, 1L);

        Assert.assertEquals(encounter, result);
    }

    @Test
    public void updateEncounterReturnsEncounter() throws Exception {

        Encounter result = mockEncounterServiceImpl.updateEncounter(1L, 1L, encounter);

        Assert.assertEquals(encounter, result);
    }







}
