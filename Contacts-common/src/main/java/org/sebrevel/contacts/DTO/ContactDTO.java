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
public class ContactDTO extends PersonDTO implements IContact {

    private List<IEmail> emails;
    private List<IPhone> phones;
    private List<IAddress> addresses;

    public ContactDTO() {
    }

    public ContactDTO(String lastName, String firstName, Date birthdate, Gender gender, Status status, List<IActivity> activities, List<IEmail> emails, List<IPhone> phones, List<IAddress> addresses) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.status = status;
        this.activities = activities;
        this.emails = emails;
        this.phones = phones;
        this.addresses = addresses;
    }

    @Override
    @XmlElementWrapper(name = "emails")
    @XmlElement(type = EmailDTO.class)
    public List<IEmail> getEmails() {
        return emails;
    }

    @Override
    public void setEmails(List<IEmail> emails) {
        this.emails = emails;
    }

    @Override
    @XmlElementWrapper(name = "phones")
    @XmlElement(type = PhoneDTO.class)
    public List<IPhone> getPhones() {
        return phones;
    }

    @Override
    public void setPhones(List<IPhone> phones) {
        this.phones = phones;
    }

    @Override
    @XmlElementWrapper(name = "addresses")
    @XmlElement(type = AddressDTO.class)
    public List<IAddress> getAddresses() {
        return addresses;
    }

    @Override
    public void setAddresses(List<IAddress> addresses) {
        this.addresses = addresses;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactDTO personDTO = (ContactDTO) o;

        if (addresses != null ? !addresses.equals(personDTO.addresses) : personDTO.addresses != null) return false;
        if (emails != null ? !emails.equals(personDTO.emails) : personDTO.emails != null) return false;
        if (phones != null ? !phones.equals(personDTO.phones) : personDTO.phones != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = emails != null ? emails.hashCode() : 0;
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
                ", addresses=" + activities +
                ", addresses=" + gender +
                ", addresses=" + status +
                '}';
    }
}


