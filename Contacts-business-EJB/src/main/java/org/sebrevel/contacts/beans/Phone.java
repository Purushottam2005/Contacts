package org.sebrevel.contacts.beans;

import org.sebrevel.contacts.interfaces.IPhone;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@Entity
@NamedQueries({
        @NamedQuery(name = Phone.FIND_PHONE, query = "select distinct p.phone From Contact u JOIN u.phones p where u.firstName=:firstName and u.lastName=:lastName"),
        @NamedQuery(name = Phone.FIND_CONTACT, query = "select distinct u From Contact u JOIN u.phones p where p.phone=:phone")
})
public class Phone implements IPhone, Serializable {

    public static final String FIND_PHONE = "Phone.findPhone";
    public static final String FIND_CONTACT = "Phone.findContact";

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneCategory category;

    private String phone;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Phone() {
    }

    public Phone(String phone, PhoneCategory category) {
        this.category = category;
        this.phone = phone;
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

    public PhoneCategory getCategory() {
        return category;
    }

    public void setCategory(PhoneCategory category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone1 = (Phone) o;

        if (category != phone1.category) return false;
        if (id != null ? !id.equals(phone1.id) : phone1.id != null) return false;
        if (phone != null ? !phone.equals(phone1.phone) : phone1.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + id +
                ", category=" + category +
                ", phone='" + phone + '\'' +
                '}';
    }
}