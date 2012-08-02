package org.sebrevel.contacts.service.contact;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.IdentityDTO;
import org.sebrevel.contacts.DTO.PersonDTO;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 23/05/12
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface ContactServiceRemote {

    public static String JNDI_NAME = "java:global/contacts-business-ear-1.0/contacts-business-EJB-1.0/ContactService!org.sebrevel.contacts.service.contact.ContactServiceRemote";
    public static String RMI_HOST = "localhost";
    public static String RMI_PORT = "3700";

    ContactDTO find(String firstName, String lastName);

    ContactDTO create(ContactDTO contact);

    void delete(ContactDTO contact);

    ContactDTO update(ContactDTO contact);

    List<ContactDTO> findAll();

    List<IdentityDTO> findByCity(String city);
}
