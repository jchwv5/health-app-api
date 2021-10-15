package io.catalyte.training.patienthealth.data;

import io.catalyte.training.patienthealth.domains.encounter.Encounter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EncounterFactory {

    private static final String[] notes = {
            "has cancer.",
            "doesn't have cancer.",
            "is alive.",
            "has disappeared.",
            "is a bird.",
            "is dead.",
            "makes wonderful cakes"
    };

    private static final String[] providers = {
            "Dr. Nick",
            "Dr. Crusher",
            "Dr. Strange",
            "Dr. Dentist",
            "Dr. Lazarus"
    };

    private static final String[] complaints = {
            "Hurts.",
            "Has too many toes.",
            "Doesn't have enough toes",
            "Heartburn",
            "Heartbreak",
            "Headache",
            "Dog-tongue",
            "Left side has switched with right side",
            "Inside-out",
            "Main-character syndrome"
    };

    private static Random randomGenerator = new Random();

    public static Long getPatientId(int patientRepositoryLength) {
        return (long) randomGenerator.nextInt(patientRepositoryLength - 5) + 1;
    }

    public static String getNotes() { return "Patient " + notes[randomGenerator.nextInt(notes.length)];
    }

    public static String getVisitCode() { return RandomStringUtils.random(1, true, false) +
            RandomStringUtils.random(1, false, true) +
            RandomStringUtils.random(1, true, false) + " " +
            RandomStringUtils.random(1, false, true) +
            RandomStringUtils.random(1, true, false) +
            RandomStringUtils.random(1, false, true); }

    public static String getProvider() { return providers[randomGenerator.nextInt(providers.length)];
    }

    public static String getBillingCode() { return randomGenerator.nextInt(999) + "." +
            randomGenerator.nextInt(999) + "." + randomGenerator.nextInt(999) +
                    "-" + randomGenerator.nextInt(99); }

    public static String getIcd10() { return RandomStringUtils.random(1, true, false) + randomGenerator.nextInt(99); }

    public static BigDecimal getCost() {
        double min = 100.00;
        double max = 10000.00;
        double diff = max- min;
        double randomValue = min + Math.random( ) * diff;
        double temp = Math.floor(randomValue * 10);
        double finalCost = temp/10;
        return BigDecimal.valueOf(finalCost);
    }

    public static BigDecimal getCopay() {
        double min = 10.00;
        double max = 500.00;
        double diff = max- min;
        double randomValue = min + Math.random( ) * diff;
        double temp = Math.floor(randomValue * 10);
        double finalCost = temp/10;
        return BigDecimal.valueOf(finalCost);
    }

    public static String getChiefComplaint() { return complaints[randomGenerator.nextInt(complaints.length)]; }

    public static Integer getPulse() { return randomGenerator.nextInt(150); }

    public static Integer getSystolic() { return randomGenerator.nextInt(130); }

    public static Integer getDiastolic() { return randomGenerator.nextInt(80);}

    /**
     * Finds a random date between two date bounds.
     *
     * @param startInclusive the beginning bound
     * @param endExclusive   the ending bound
     * @return a random date as a LocalDate
     */
    private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate getDate() {
        LocalDate start = LocalDate.of(2017, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        return between(start, end);
    }

    /**
     * Generates a number of random encounters based on input.
     *
     * @param numberOfEncounters - the number of random encounter to generate
     * @return - a list of random patients
     */
    public List<Encounter> generateRandomEncounters(Integer numberOfEncounters) {

        List<Encounter> encountersList = new ArrayList<>();

        for (int i = 0; i < numberOfEncounters; i++) {
            encountersList.add(createRandomEncounter());
        }
        return encountersList;
    }

    public Encounter createRandomEncounter() {
        Encounter encounter = new Encounter();
        Long patientId = EncounterFactory.getPatientId(10);
        String notes = EncounterFactory.getNotes();
        String visitCode = EncounterFactory.getVisitCode();
        String provider = EncounterFactory.getProvider();
        String billingCode = EncounterFactory.getBillingCode();
        String icd10 = EncounterFactory.getIcd10();
        BigDecimal totalCost = EncounterFactory.getCost();
        BigDecimal copay = EncounterFactory.getCopay();
        String chiefComplaint = EncounterFactory.getChiefComplaint();
        Integer pulse = EncounterFactory.getPulse();
        Integer systolic = EncounterFactory.getSystolic();
        Integer diastolic = EncounterFactory.getDiastolic();
        LocalDate date = EncounterFactory.getDate();
        encounter.setPatientId(patientId);
        encounter.setNotes(notes);
        encounter.setVisitCode(visitCode);
        encounter.setProvider(provider);
        encounter.setBillingCode(billingCode);
        encounter.setIcd10(icd10);
        encounter.setTotalCost(totalCost);
        encounter.setCopay(copay);
        encounter.setChiefComplaint(chiefComplaint);
        encounter.setPulse(pulse);
        encounter.setSystolic(systolic);
        encounter.setDiastolic(diastolic);
        encounter.setDate(date);
        return encounter;
    }

}
