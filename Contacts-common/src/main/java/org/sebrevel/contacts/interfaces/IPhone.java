package org.sebrevel.contacts.interfaces;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
public interface IPhone extends Serializable {
    Long getId();

    void setId(Long id);

    String getPhone();

    void setPhone(String phone);

    PhoneCategory getCategory();

    void setCategory(PhoneCategory category);


    public enum PhoneCategory {
        WORK,
        HOME,
        MOBILE
    }
}
