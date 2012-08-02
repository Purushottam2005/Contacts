package org.sebrevel.contacts.beans;

import org.sebrevel.contacts.interfaces.IAddress;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@Entity
public class Address implements IAddress, Serializable {


    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressCategory category;

    @Column(length = 2000)
    private String street;

    private String city;

    @Column(name = "ZIP_CODE")
    private Integer zipCode;

    private String country;


    // ======================================
    // =            Constructors            =
    // ======================================

    public Address() {
    }

    public Address(String street, String city, Integer zipCode, String country, AddressCategory category) {
        this.category = category;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
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

    public AddressCategory getCategory() {
        return category;
    }

    public void setCategory(AddressCategory category) {
        this.category = category;
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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", category=" + category +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (category != address.category) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}