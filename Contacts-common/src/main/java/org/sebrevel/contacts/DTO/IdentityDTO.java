package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IIdentity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
public class IdentityDTO implements Serializable, IIdentity {

    private String lastName;
    private String firstName;

    public IdentityDTO() {
    }

    public IdentityDTO(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
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
}