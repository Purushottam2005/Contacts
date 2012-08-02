package org.sebrevel.contacts.interfaces;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public interface IEmail extends Serializable {
    Long getId();

    void setId(Long id);

    String getEmail();

    void setEmail(String email);

    EmailCategory getCategory();

    void setCategory(EmailCategory category);


    public enum EmailCategory {
        WORK,
        HOME
    }
}
