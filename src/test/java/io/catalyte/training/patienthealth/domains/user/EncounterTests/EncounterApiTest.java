package io.catalyte.training.patienthealth.domains.user.EncounterTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.patienthealth.domains.encounter.EncounterRepository;
import io.catalyte.training.patienthealth.domains.encounter.EncounterService;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import org.junit.Assert;
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
    EncounterRepository encounterRepository;

    @Autowired
    PatientRepository patientRepository;

    @MockBean
    EncounterService encounterService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEncountersByPatientIdReturns200() throws Exception {
        String type =
        mockMvc.perform(get("/patients/1/encounters"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentType();
        Assert.assertEquals("application/json", type);

    }

    @Test
    public void getEncounterIdReturns200() throws Exception {
        mockMvc.perform(get("/patients/1/encounters/1"))
                .andExpect(status().isOk());
    }


}

