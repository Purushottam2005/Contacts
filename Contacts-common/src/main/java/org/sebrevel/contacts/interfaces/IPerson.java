package org.sebrevel.contacts.interfaces;

import org.sebrevel.contacts.DTO.ActivityDTO;
import org.sebrevel.contacts.DTO.AddressDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public interface IPerson extends Serializable {
    String getLastName();

    void setLastName(String lastName);

    String getFirstName();

    void setFirstName(String firstName);

    Date getBirthdate();

    void setBirthdate(Date birthdate);


    Gender getGender();

    void setGender(Gender gender);


    Status getStatus();

    void setStatus(Status status);

    @XmlElementWrapper(name = "activities")
    @XmlElement(type = ActivityDTO.class)
    List<IActivity> getActivities();

    void setActivities(List<IActivity> activities);

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public enum Gender {
        MALE, FEMALE
    }

    @XmlRootElement
    @JsonIgnoreProperties(ignoreUnknown = true)
    public enum Status {
        COUPLE, DIVORCED, ENGAGED , SINGLE
    }

}
