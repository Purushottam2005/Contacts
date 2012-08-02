package org.sebrevel.contacts.beans;


import org.sebrevel.contacts.interfaces.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Sébastien Revel
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Contact.FIND_BY_CITY, query = "select distinct u.firstName, u.lastName From Contact u JOIN u.addresses a where a.city=:city"),
        @NamedQuery(name = Contact.FIND, query = "select u From Contact u where u.firstName=:firstName and u.lastName=:lastName"),
        @NamedQuery(name = Contact.FIND_ALL, query = "select u From Contact u")
})
@DiscriminatorValue("C") // Indique dans la table qu'il s'agit d'un contact et pas d'une personne
public class Contact extends Person implements IContact, Serializable {

    // ======================================
    // =             Constants             =
    // ======================================
    public static final String FIND_ALL = "Contact.findAll";
    public static final String FIND_BY_CITY = "Contact.findByCity";
    public static final String FIND = "Contact.find";


    // ======================================
    // =             Attributes             =
    // ======================================

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Email.class)
    private List<IEmail> emails;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Phone.class)
    private List<IPhone> phones;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Address.class)
    @JoinTable(
            name = "JOIN_USER_ADDRESS",
            joinColumns = {
                    @JoinColumn(name = "personfirstname_fk", referencedColumnName = "firstName"),
                    @JoinColumn(name = "personlastname_fk", referencedColumnName = "lastName")
            }
    )
    private List<IAddress> addresses;


    // ======================================
    // =            Constructors            =
    // ======================================

    public Contact() {
    }

    public Contact(String lastName, String firstName, Date birthdate, List<IEmail> emails, List<IPhone> phones, List<IAddress> addresses) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;

        this.emails = emails;
        this.phones = phones;
        this.addresses = addresses;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public List<IEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<IEmail> emails) {
        this.emails = emails;
    }

    public List<IPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<IPhone> phones) {
        this.phones = phones;
    }

    public List<IAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<IAddress> addresses) {
        this.addresses = addresses;
    }


    // ======================================
    // =         hash, equals, toString     =
    // ======================================


    @Override
    public String toString() {
        return "Contact{" +
                ", emails=" + emails +
                ", phones=" + phones +
                ", addresses=" + addresses +
                '}';
    }
}