package org.sebrevel.contacts.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public interface IIdentity {
    String getLastName();

    void setLastName(String lastName);

    String getFirstName();

    void setFirstName(String firstName);
}
