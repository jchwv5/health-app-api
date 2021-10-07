package io.catalyte.training.patienthealth.domains.patient;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class PatientValidation {
    /**
     * Validates a patient's first name field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient patient being validated
     */
    public void validateFirstName(ArrayList<String> errors, Patient patient) {
        String firstName = patient.getFirstName();
        validateRequired(errors, firstName, "First name");
        validateAlphabetic(errors, firstName, "First name");
    }

    /**
     * Validates a patient's first name field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateLastName(ArrayList<String> errors, Patient patient) {
        String lastName = patient.getLastName();
        validateRequired(errors, lastName, "First name");
        validateAlphabetic(errors, lastName, "First name");
    }

    /**
     * Validates a patient's ssn field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateSsn (ArrayList<String> errors, Patient patient) {
        String ssn = patient.getSsn();
        validateRequired(errors, ssn, "SSN");
        if (ssn == null || !Pattern.matches("^(?!666|000|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$", ssn)) {
            errors.add(
                    "Invalid SSN. SSN must be in xxx-xx-xxxx format, where 'x' represents a digit.");
        }
    }

    /**
     * This function validates a patient's email field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateEmail(ArrayList<String> errors, Patient patient) {
        String email = patient.getEmail();
        validateRequired(errors, email, "Email");
        if (email == null || !Pattern.matches("^(\\S+)@(\\S+)$", email)) {

            errors.add("Invalid email. Email must be in either x@x.x or x@x format.");

        }
    }

    /**
     * Validates a patient's street field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateStreet(ArrayList<String> errors, Patient patient) {
        String streetAddress = patient.getStreet();
        validateRequired(errors, streetAddress, "Street address");
    }

    /**
     * Validates a patient's street address field.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateCity(ArrayList<String> errors, Patient patient) {
        String city = patient.getCity();
        validateRequired(errors, city, "City");
        validateAlphabetic(errors, city, "City");
    }

    /**
     * Validates a patient's state field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateState(ArrayList<String> errors, Patient patient) {
        String state = patient.getState();
        validateRequired(errors, state, "State");
        if (state == null || !Pattern.matches(
                "^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$",
                state)) {
            errors.add("Invalid State. Please enter a valid two-letter US state abbreviation.");
        }
    }

    /**
     * Validates a patient's zip code field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validatePostal(ArrayList<String> errors, Patient patient) {
        String postal = patient.getPostal();
        validateRequired(errors, postal, "Zip code");
        if (postal == null || !Pattern.matches("^[0-9]{5}(?:-[0-9]{4})?$", postal)) {
            errors.add(
                    "Invalid Zip Code. Zip Code must be in either the xxxxx or xxxxx-xxxx format, where 'x' represents a digit.");
        }
    }

    /**
     * Validates a patient's age field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateAge(ArrayList<String> errors, Patient patient) {
        String age = String.valueOf(patient.getAge());
        validateRequired(errors, age, "Age");
        validateNumeric(errors, age, "Age");
    }

    /**
     * Validates a patient's height field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateHeight(ArrayList<String> errors, Patient patient) {
        String height = String.valueOf(patient.getHeight());
        validateRequired(errors, height, "Height");
        validateNumeric(errors, height, "Height");
    }

    /**
     * Validates a patient's weight field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateWeight(ArrayList<String> errors, Patient patient) {
        String weight = String.valueOf(patient.getWeight());
        validateRequired(errors, weight, "Weight");
        validateNumeric(errors, weight, "Weight");
    }

    /**
     * Validates a patient's insurance field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateInsurance(ArrayList<String> errors, Patient patient) {
        String insurance = patient.getInsurance();
        validateRequired(errors, insurance, "Insurance");
    }

    /**
     * Validates a patient's gender field
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param patient Patient being validated
     */
    public void validateGender(ArrayList<String> errors, Patient patient) {
        String gender = patient.getGender();
        validateRequired(errors, gender, "Age");
        if (gender == null || !isValidGender(gender)) {
            errors.add(
                    "Invalid gender. Please enter male, female, or other.");
        }
    }



    /**
     * Validates required patient fields.
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
     * Validates alphabetic patient fields.
     *
     * @param errors An ArrayList of Strings containing the error messages.
     * @param requiredInputToValidate String that will be validated
     * @param inputNameForErrorMessage Name of the input to be used for the dynamic error message.
     */
    public void validateAlphabetic(ArrayList<String> errors, String requiredInputToValidate,
                                   String inputNameForErrorMessage) {
        if (requiredInputToValidate == null || !Pattern.matches("^([A-Za-z])[A-Za-z '-]*$", requiredInputToValidate)) {
            errors.add(String
                    .format("Invalid Input. %s may only allow letters, apostrophes, spaces, hyphens (-)",
                            inputNameForErrorMessage));
        }
    }

    /**
     * Validates numeric patient fields.
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

    public boolean isValidGender (String args) {
        String gender = args.toLowerCase(Locale.ROOT);
        return gender.equals("male") || gender.equals("female") || gender.equals("other");
    }



    /**
     * Validates all patient fields
     *
     * @param patient Patient being validated
     */
    public void validatePatient(Patient patient) {
        ArrayList<String> errors = new ArrayList<>();
        if (patient == null) {
            errors.add("No patient provided");
            processBadPatientValidation(errors);
        }

        validateFirstName(errors, patient);
        validateLastName(errors, patient);
        validateSsn(errors, patient);
        validateEmail(errors, patient);
        validateStreet(errors, patient);
        validateCity(errors, patient);
        validateState(errors, patient);
        validatePostal(errors, patient);
        validateAge(errors, patient);
        validateHeight(errors, patient);
        validateWeight(errors, patient);
        validateInsurance(errors, patient);
        validateGender(errors, patient);

        if (!errors.isEmpty()) {
            processBadPatientValidation(errors);
        }
    }

    /**
     * Validation helper method to throw exception for creating patient with appropriate message
     *
     * @param errors ArrayList of error messages detailing what caused validation to fail
     */
    public void processBadPatientValidation(ArrayList<String> errors) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            message.append(errors.get(i));
            if (i < errors.size() - 1) {
                message.append("; ");
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Patient could not be created - " + message);
    }

}