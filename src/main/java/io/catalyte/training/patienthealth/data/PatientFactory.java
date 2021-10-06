package io.catalyte.training.patienthealth.data;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
/**
 * This class provides tools for random generation of patients.
 */
public class PatientFactory {

    private static final String[] firstNames = {
            "Jack",
            "Jill",
            "Mike",
            "Jeff",
            "Andrew",
            "Mary",
            "Luke",
            "Greg",
            "Eric",
            "Tom",
            "Nancy",
            "Michelle",
            "Karen",
            "Cody",
            "Ryan"
    };
    private static final String[] lastNames = {
            "Smith",
            "Jones",
            "Martinez",
            "Anderson",
            "Connors",
            "McGregor",
            "Lopez",
            "Simms",
            "Robertson",
            "Carpenter",
            "Jeffers",
            "Lorne",
            "Gardner",
            "Moore",
            "O'Neil"
    };
    private static final String[] ssns = {
            "123-45-6789",
            "234-56-7891",
            "345-67-8912",
            "456-78-9123",
            "567-89-1234",
            "678-91-2345",
            "789-12-3456",
            "891-23-4567",
            "912-23-4567",
            "112-34-5678",
            "113-45-5678",
            "589-54-7856",
            "859-56-5487",
            "582-65-9852",
            "859-65-7451"
    };
    private static final String[] emails = {
            "@gmail.com",
            "@msn.com",
            "@yahoo.com",
            "@aol.com"
    };
    private static final String[] streets = {
            "High Street",
            "Havana Way",
            "Ohio Avenue",
            "Congress Parkway",
            "Mexico Street",
            "Gulf Avenue",
            "Louisiana Parkway",
            "Grant Street",
            "Park Avenue",
            "Market Street"
    };
    private static final String[] cities ={
            "Kansas City",
            "New York",
            "Boston",
            "Chicago",
            "Baltimore",
            "Salt Lake City"
    };
    private static final String[] states = {
            "MO",
            "CO",
            "AK",
            "AZ",
            "MD",
            "NY",
            "OH",
            "KS"
    };
    private static final String[] insurances = {
            "Blue Cross",
            "Kaiser",
            "Heartland",
            "USAA",
            "Aetna"
    };
    private static final String[] genders = {
            "male",
            "female",
            "other"
    };
    private static Random randomGenerator = new Random();
    /**
     * Returns a random first name from the list of first names
     * @return - a firstName string
     */
    public static String getFirstName() {
        return firstNames[randomGenerator.nextInt(firstNames.length)];
    };

    /**
     * Returns a random last name from the list of last names
     * @return - a lastName string
     */
    public static String getLastName() {
        return lastNames[randomGenerator.nextInt(lastNames.length)];
    };

    /**
     * Returns a random ssn from the list of ssns
     * @return - a ssn string
     */
    public static String getSsn() {
        return ssns[randomGenerator.nextInt(ssns.length)];
    };
    /**
     * Returns a random email from the list of emails
     * @return - a email string
     */
    public static String getEmail() {
        return emails[randomGenerator.nextInt(emails.length)];
    };
    /**
     * Returns a random street from the list of streets
     * @return - a street string
     */
    public static String getStreet() {
        String street = streets[randomGenerator.nextInt(streets.length)];
        String streetNumber = RandomStringUtils.random(3, false, true);
        return streetNumber + " " + street;
    };
    /**
     * Returns a random city from the list of cities
     * @return - a city string
     */
    public static String getCity() {
        return cities[randomGenerator.nextInt(cities.length)];
    };
    /**
     * Returns a random state from the list of states
     * @return - a state string
     */
    public static String getState() {
        return states[randomGenerator.nextInt(states.length)];
    };
    /**
     * Returns a randomly generated postal code
     * @return - a postal code integer
     */
    public static String getPostal() {
        return String.valueOf(randomGenerator.nextInt(99999));
    };
    /**
     * Returns a randomly generated age
     * @return - an age integer
     */
    public static Integer getAge() {
        return randomGenerator.nextInt(99);
    };
    /**
     * Returns a randomly generated height
     * @return - a height BigDecimal
     */
    public static BigDecimal getHeight() {
        BigDecimal max = new BigDecimal(6.6);
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        return randFromDouble.divide(max,BigDecimal.ROUND_DOWN);
    };
    /**
     * Returns a randomly generated weight
     * @return - a weight BigDecimal
     */
    public static BigDecimal getWeight() {
        BigDecimal max = new BigDecimal(250);
        BigDecimal randFromDouble = new BigDecimal(Math.random());
        return randFromDouble.divide(max,BigDecimal.ROUND_DOWN);
    };
    /**
     * Returns a random insurance from the list of insurances
     * @return - an insurance string
     */
    public static String getInsurance() {
        return insurances[randomGenerator.nextInt(insurances.length)];
    };
    /**
     * Returns a random gender from the list of genders
     * @return - a gender string
     */
    public static String getGender() {
        return genders[randomGenerator.nextInt(genders.length)];
    };

    /**
     * Generates a number of random products based on input.
     *
     * @param numberOfPatients - the number of random patients to generate
     * @return - a list of random patients
     */
    public List<Patient> generateRandomPatients(Integer numberOfPatients) {

        List<Patient> patientList = new ArrayList<>();

        for (int i = 0; i < numberOfPatients; i++) {
            patientList.add(createRandomPatient());
        }
        return patientList;
    }

    public Patient createRandomPatient() {
        Patient patient = new Patient();
        String firstName = PatientFactory.getFirstName();
        String lastName = PatientFactory.getLastName();
        String ssn = PatientFactory.getSsn();
        String email = PatientFactory.getEmail();
        String street = PatientFactory.getStreet();
        String city = PatientFactory.getCity();
        String state = PatientFactory.getState();
        String postal = PatientFactory.getPostal();
        Integer age = PatientFactory.getAge();
        BigDecimal height = PatientFactory.getHeight();
        BigDecimal weight = PatientFactory.getWeight();
        String insurance = PatientFactory.getInsurance();
        String gender = PatientFactory.getGender();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setSsn(ssn);
        patient.setEmail(firstName.toLowerCase() + email);
        patient.setStreet(street);
        patient.setCity(city);
        patient.setState(state);
        patient.setPostal(postal);
        patient.setAge(age);
        patient.setHeight(height);
        patient.setWeight(weight);
        patient.setInsurance(insurance);
        patient.setGender(gender);
        return patient;
    };


}
