package org.sebrevel.contacts.service.person;

import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.DTO.IdentityDTO;
import org.sebrevel.contacts.DTO.PersonDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;

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
public interface PersonServiceRemote {

    public static String JNDI_NAME = "java:global/contacts-business-ear-1.0/contacts-business-EJB-1.0/PersonService!org.sebrevel.contacts.service.person.PersonServiceRemote";
    public static String RMI_HOST = "localhost";
    public static String RMI_PORT = "3700";

    PersonDTO find(String firstName, String lastName);

    PersonDTO create(PersonDTO person);

    void delete(PersonDTO person);

    PersonDTO update(PersonDTO person);

    List<PersonDTO> findAll();

}
