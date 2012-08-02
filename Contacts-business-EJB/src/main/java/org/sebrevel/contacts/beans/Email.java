package org.sebrevel.contacts.beans;

import org.sebrevel.contacts.interfaces.IEmail;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Email.FIND_EMAIL, query = "select distinct p.email From Contact u JOIN u.emails p where u.firstName=:firstName and u.lastName=:lastName"),
        @NamedQuery(name = Email.FIND_CONTACT, query = "select distinct u From Contact u JOIN u.emails p where p.email=:email")
})
public class Email implements IEmail, Serializable {


    public static final String FIND_EMAIL = "Email.findEmail";
    public static final String FIND_CONTACT = "Email.findContact";

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private EmailCategory category;

    private String email;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Email() {
    }

    public Email(String email, EmailCategory category) {
        this.category = category;
        this.email = email;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmailCategory getCategory() {
        return category;
    }

    public void setCategory(EmailCategory category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // ======================================
    // =         hash, equals, toString     =
    // ======================================


    @Override
    public String toString() {
        return "EmailDTO{" +
                "id=" + id +
                ", category=" + category +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (category != email1.category) return false;
        if (email != null ? !email.equals(email1.email) : email1.email != null) return false;
        if (id != null ? !id.equals(email1.id) : email1.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}