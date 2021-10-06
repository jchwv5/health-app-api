package io.catalyte.training.patienthealth.domains.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String ssn;

    private String email;

    private String street;

    private String city;

    private String state;

    private String postal;

    private Integer age;

    private Integer height;

    private Integer weight;

    private String insurance;

    private String gender;

    public Patient() {
    }

    public Patient(Long id,
                   String firstName,
                   String lastName,
                   String ssn,
                   String email,
                   String street,
                   String city,
                   String state,
                   String postal,
                   Integer age,
                   Integer height,
                   Integer weight,
                   String insurance,
                   String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.insurance = insurance;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Patient product = (Patient) o;

        if (!Objects.equals(firstName, product.firstName)) {
            return false;
        }
        if (!Objects.equals(lastName, product.lastName)) {
            return false;
        }
        if (!Objects.equals(ssn, product.ssn)) {
            return false;
        }
        if (!Objects.equals(email, product.email)) {
            return false;
        }
        if (!Objects.equals(street, product.street)) {
            return false;
        }
        if (!Objects.equals(city, product.city)) {
            return false;
        }
        if (!Objects.equals(state, product.state)) {
            return false;
        }
        if (!Objects.equals(postal, product.postal)) {
            return false;
        }
        if (!Objects.equals(age, product.age)) {
            return false;
        }
        if (!Objects.equals(height, product.height)) {
            return false;
        }
        if (!Objects.equals(weight, product.weight)) {
            return false;
        }
        if (!Objects.equals(insurance, product.insurance)) {
            return false;
        }
        return !Objects.equals(gender, product.gender);
    }
    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (postal != null ? postal.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (insurance != null ? insurance.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal='" + postal + '\'' +
                ", age='" + age + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", insurance='" + insurance + '\'' +
                ", gender=" + gender +
                '}';
    }
}
