package io.catalyte.training.patienthealth.domains.encounter;

import io.catalyte.training.patienthealth.domains.patient.Patient;
import io.catalyte.training.patienthealth.domains.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class EncounterValidation {

    /**
     * This function validates an encounter's email field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateVisitCode(ArrayList<String> errors, Encounter encounter) {
        String visitCode = encounter.getVisitCode();
        validateRequired(errors, visitCode, "Visit Code");
        if (visitCode == null || !Pattern.matches("^[A-Z]\\d[A-Z]\\s\\d[A-Z]\\d$", visitCode)) {
            errors.add("Invalid visit code. Visit code must be in LDL DLD format, where L represents an uppercase letter and D" +
                    " represents a digit.");
        }
    }

    /**
     * This function validates an encounter's provider field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateProvider(ArrayList<String> errors, Encounter encounter) {
        String provider = encounter.getProvider();
        validateRequired(errors, provider, "Provider");
    }

    /**
     * This function validates an encounter's billingCode field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateBillingCode(ArrayList<String> errors, Encounter encounter) {
        String billingCode = encounter.getBillingCode();
        validateRequired(errors, billingCode, "Billing Code");
        if (billingCode == null || !Pattern.matches("^\\d{3}+\\.\\d{3}+\\.\\d{3}+\\-\\d{2}$", billingCode)) {
            errors.add("Invalid billing code. Billing code must be in DDD.DDD.DDD-DD format, where D represents a digit.");
        }
    }

    /**
     * This function validates an encounter's icd10 field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateIcd10(ArrayList<String> errors, Encounter encounter) {
        String icd10 = encounter.getIcd10();
        validateRequired(errors, icd10, "ICD10");
        if (icd10 == null || !Pattern.matches("^[A-Z]\\d{2}$", icd10)) {
            errors.add("Invalid ICD10. ICD10 must be in LDD format, where L represents an uppercase letter and D" +
                    " represents a digit.");
        }
    }

    /**
     * This function validates an encounter's totalCost field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateTotalCost(ArrayList<String> errors, Encounter encounter) {
        String totalCost = String.valueOf(encounter.getTotalCost());
        validateRequired(errors, totalCost, "Total Cost");
        if (totalCost == null || !Pattern.matches("^(0|[1-9][0-9]{0,2})(,\\d{3})*(\\.\\d{1,2})?$", totalCost)) {
            errors.add("Invalid total cost. Total cost must be in USD format.");
        }
    }

    /**
     * This function validates an encounter's copay field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateCopay(ArrayList<String> errors, Encounter encounter) {
        String copay = String.valueOf(encounter.getCopay());
        validateRequired(errors, copay, "Copay");
        if (copay == null || !Pattern.matches("^(0|[1-9][0-9]{0,2})(,\\d{3})*(\\.\\d{1,2})?$", copay)) {
            errors.add("Invalid copay. Copay must be in USD format.");
        }
    }

    /**
     * This function validates an encounter's chiefComplaint field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateChiefComplaint(ArrayList<String> errors, Encounter encounter) {
        String chiefComplaint = encounter.getChiefComplaint();
        validateRequired(errors, chiefComplaint, "Chief Complaint");
    }

    /**
     * This function validates an encounter's pulse field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validatePulse(ArrayList<String> errors, Encounter encounter) {
        String pulse = String.valueOf(encounter.getPulse());
        if (pulse != "null") {
            validateNumeric(errors, pulse, "Pulse");
        }
    }

    /**
     * This function validates an encounter's systolic field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateSystolic(ArrayList<String> errors, Encounter encounter) {
        String systolic = String.valueOf(encounter.getSystolic());
        if (systolic != "null") {
            validateNumeric(errors, systolic, "Systolic");
        }
    }

    /**
     * This function validates an encounter's diastolic field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateDiastolic(ArrayList<String> errors, Encounter encounter) {
        String diastolic = String.valueOf(encounter.getDiastolic());
        if (diastolic != "null") {
            validateNumeric(errors, diastolic, "Diastolic");
        };
    }

    /**
     * This function validates an encounter's date field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param encounter Encounter being validated
     */
    public void validateDate(ArrayList<String> errors, Encounter encounter) {
        String date = String.valueOf(encounter.getDate());
        validateRequired(errors, date, "Date");
        if (date == "null" || !Pattern.matches("^\\d{4}+\\-\\d{2}+\\-\\d{2}$", date)) {
            errors.add("Invalid date. Date must be in YYYY-MM-DD format.");
        }
    }

    /**
     * Validates numeric encounter fields.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param requiredInputToValidate String that will be validated
     * @param inputNameForErrorMessage Name of the input to be used for the dynamic error message.
     */
    public void validateNumeric(ArrayList<String> errors, String requiredInputToValidate,
                                String inputNameForErrorMessage) {
        if (requiredInputToValidate != null && !Pattern.matches("^(0|[1-9][0-9]*)$", requiredInputToValidate)) {
            errors.add(String
                    .format("Invalid Input. %s may only allow numeric values",
                            inputNameForErrorMessage));
        }
    }

    /**
     * Validates required encounter fields.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param requiredInputToValidate String that will be validated
     * @param inputNameForErrorMessage Name of the input to be used for the dynamic error message.
     */
    public void validateRequired(ArrayList<String> errors, String requiredInputToValidate,
                                 String inputNameForErrorMessage) {
        if (requiredInputToValidate == null || requiredInputToValidate.trim().equals("")) {
            errors.add(String.format("%s may not be blank, empty or null", inputNameForErrorMessage));
        }
    }

    /**
     * Validates all encounter fields
     *
     * @param encounter Encounter being validated
     */
    public void validateEncounter(Encounter encounter) {
        ArrayList<String> errors = new ArrayList<>();
        if (encounter == null) {
            errors.add("No encounter provided");
            processBadEncounterValidation(errors);
        }
        validateVisitCode(errors, encounter);
        validateProvider(errors, encounter);
        validateBillingCode(errors, encounter);
        validateIcd10(errors, encounter);
        validateTotalCost(errors, encounter);
        validateCopay(errors, encounter);
        validateChiefComplaint(errors, encounter);
        validatePulse(errors, encounter);
        validateSystolic(errors, encounter);
        validateDiastolic(errors, encounter);
        validateDate(errors, encounter);


        if (!errors.isEmpty()) {
            processBadEncounterValidation(errors);
        }
    }

    /**
     * Validation helper method to throw exception for creating encounter with appropriate message
     *
     * @param errors ArrayList of error messages detailing what caused validation to fail
     */
    public void processBadEncounterValidation(ArrayList<String> errors) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            message.append(errors.get(i));
            if (i < errors.size() - 1) {
                message.append("; ");
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Encounter could not be created - " + message);
    }
}
