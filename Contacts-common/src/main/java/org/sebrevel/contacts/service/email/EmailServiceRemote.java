package org.sebrevel.contacts.service.email;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
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
public interface EmailServiceRemote {

    public static String JNDI_NAME = "java:global/contacts-business-ear-1.0/contacts-business-EJB-1.0/EmailService!org.sebrevel.contacts.service.email.EmailServiceRemote";
    public static String RMI_HOST = "localhost";
    public static String RMI_PORT = "3700";

    List<EmailDTO> findEmail(String firstName, String lastName);

    ContactDTO findContact(String email);

}
