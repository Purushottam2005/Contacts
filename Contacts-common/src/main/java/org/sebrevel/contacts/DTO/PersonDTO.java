package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO implements IPerson {

    protected String lastName;
    protected String firstName;

    protected Date birthdate;

    protected Gender gender;
    protected Status status;

    protected List<IActivity> activities;

    public PersonDTO() {
    }

    public PersonDTO(String lastName, String firstName, Date birthdate, Gender gender, Status status, List<IActivity> activities) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.status = status;
        this.activities = activities;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    @XmlElementWrapper(name = "activities")
    @XmlElement(type = ActivityDTO.class)
    public List<IActivity> getActivities() {
        return activities;
    }

    @Override
    public void setActivities(List<IActivity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDTO personDTO = (PersonDTO) o;

        if (birthdate != null ? !birthdate.equals(personDTO.birthdate) : personDTO.birthdate != null) return false;
        if (firstName != null ? !firstName.equals(personDTO.firstName) : personDTO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(personDTO.lastName) : personDTO.lastName != null) return false;
        if (activities != null ? !activities.equals(personDTO.activities) : personDTO.activities != null) return false;
        if (gender != null ? !gender.equals(personDTO.gender) : personDTO.gender != null) return false;
        if (status != null ? !status.equals(personDTO.status) : personDTO.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (activities != null ? activities.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
                ", addresses=" + activities +
                ", addresses=" + gender +
                ", addresses=" + status +
                '}';
    }
}


