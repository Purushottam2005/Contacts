package org.sebrevel.contacts.interfaces;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public interface IAddress extends Serializable {
    Long getId();

    void setId(Long id);

    String getStreet();

    void setStreet(String street);

    String getCity();

    void setCity(String city);

    Integer getZipCode();

    void setZipCode(Integer zipCode);

    String getCountry();

    void setCountry(String country);

    AddressCategory getCategory();

    void setCategory(AddressCategory category);

    public enum AddressCategory {
        WORK,
        HOME
    }
}