package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IAddress;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO implements IAddress {

    private Long id;
    private String street;
    private String city;
    private Integer zipCode;
    private String country;
    private AddressCategory category;


    public AddressDTO() {
    }

    public AddressDTO(String street, String city, Integer zipCode, String country, AddressCategory category) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.category = category;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getStreet() {
        return street;
    }

    @Override
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public Integer getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public AddressCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(AddressCategory category) {
        this.category = category;
    }
}