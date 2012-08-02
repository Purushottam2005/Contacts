package org.sebrevel.contacts.interfaces;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public interface IUser extends Serializable {

    String getLogin();

    void setLogin(String id);

    String getPassword();

    void setPassword(String password);

    String getLastName();

    void setLastName(String lastName);

    String getFirstName();

    void setFirstName(String firstName);


}
