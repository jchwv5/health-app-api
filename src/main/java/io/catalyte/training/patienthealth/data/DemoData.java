package io.catalyte.training.patienthealth.data;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoData implements CommandLineRunner {
    private final Logger logger = LogManager.getLogger(DemoData.class);
    PatientFactory patientFactory = new PatientFactory();
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private Environment env;

    @Override
    public void run(String... strings) {
        // Retrieve the value of custom property in application.yml
        boolean loadData = Boolean.parseBoolean(env.getProperty("patients.load"));
        if (loadData) {
            seedDatabase();
        }
    }
    private void seedDatabase() {
        int numberOfPatients;

        try {
            // Retrieve the value of custom property in application.yml
            numberOfPatients = Integer.parseInt(env.getProperty("patients.number"));
        } catch (NumberFormatException nfe) {
            // If it's not a string, set it to be a default value
            numberOfPatients = 500;
        }

        // Generate products
        List<Patient> productList = patientFactory.generateRandomPatients(numberOfPatients);

        // Persist them to the database
        logger.info("Loading " + numberOfPatients + " patients...");
        patientRepository.saveAll(productList);

        logger.info("Data load completed.You can make requests now.");

}

}
