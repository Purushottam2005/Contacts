package org.sebrevel.contacts.beans;

import org.sebrevel.contacts.interfaces.IActivity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@Entity
public class Activity implements IActivity, Serializable {


    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityName name;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Activity() {
    }

    public Activity(ActivityName name) {
        this.name = name;
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

    public ActivityName getName() {
        return name;
    }

    public void setName(ActivityName name) {
        this.name = name;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity address = (Activity) o;

        if (name != null ? !name.equals(address.name) : address.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}