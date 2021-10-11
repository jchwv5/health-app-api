package io.catalyte.training.patienthealth.domains.user.EncounterTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.patienthealth.domains.encounter.Encounter;
import io.catalyte.training.patienthealth.domains.encounter.EncounterRepository;
import io.catalyte.training.patienthealth.domains.encounter.EncounterService;
import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import io.catalyte.training.patienthealth.domains.patient.PatientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static io.catalyte.training.patienthealth.constants.Paths.ENCOUNTERS_PATH;
import static io.catalyte.training.patienthealth.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncounterApiTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    EncounterRepository encounterRepository;

    @MockBean
    EncounterService encounterService;

    @MockBean
    PatientService patientService;



    Encounter encounter = new Encounter();
    Encounter failEncounter = new Encounter();

    Patient patient = new Patient();
    Patient failPatient = new Patient();

    List<Encounter> encounterList = new ArrayList<>();
    List<Patient> patientList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
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

        encounter.setId(15L);
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

    }

    @Test
    public void getEncountersByPatientIdReturns200() throws Exception {
        String type =
        mockMvc.perform(get(ENCOUNTERS_PATH, "1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentType();
        Assert.assertEquals("application/json", type);

    }

    @Test
    public void getEncounterIdReturns200() throws Exception {
        String type =
        mockMvc.perform(get(ENCOUNTERS_PATH, "1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentType();
        Assert.assertEquals("application/json", type);
    }

    @Test
    public void postEncounterReturns201() throws Exception {
        String encounterAsJson = mapper.writeValueAsString(encounter);
        mockMvc.perform(post(ENCOUNTERS_PATH, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(encounterAsJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


}

