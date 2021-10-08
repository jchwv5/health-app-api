package io.catalyte.training.patienthealth.domains.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import io.catalyte.training.patienthealth.domains.patient.PatientService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.catalyte.training.patienthealth.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PatientApiTest {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    PatientRepository patientRepository;

    @MockBean
    PatientService patientService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPatientsReturns200() throws Exception {
        mockMvc.perform(get(PATIENTS_PATH))
                .andExpect(status().isOk());
    }

    @Test
    public void getPatientsByIdReturns200() throws Exception {
        mockMvc.perform(get(PATIENTS_PATH + "/1" ))
                .andExpect(status().isOk());
    }

    @Test
    public void savePatientReturns201() throws Exception {

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Smith");
        patient.setSsn("123-45-6789");
        patient.setEmail("john.smith@gmail.com");
        patient.setStreet("123 Test Street");
        patient.setCity("Denver");
        patient.setState("CO");
        patient.setPostal("80011");
        patient.setAge(55);
        patient.setHeight(72);
        patient.setWeight(201);
        patient.setInsurance("Blue Cross");
        patient.setGender("Male");

        String patientAsJson = mapper.writeValueAsString(patient);

        mockMvc.perform(post(PATIENTS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientAsJson))
                .andExpect(status().isCreated());
    }

}
