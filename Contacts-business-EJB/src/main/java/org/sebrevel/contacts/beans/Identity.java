package org.sebrevel.contacts.beans;


import org.sebrevel.contacts.interfaces.IIdentity;

import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
public class Identity implements IIdentity, Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String lastName;
    private String firstName;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Identity() {
    }

    public Identity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================


    @Override
    public String toString() {
        return "PersonDTO{" +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identity identity = (Identity) o;

        if (firstName != null ? !firstName.equals(identity.firstName) : identity.firstName != null) return false;
        if (lastName != null ? !lastName.equals(identity.lastName) : identity.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}