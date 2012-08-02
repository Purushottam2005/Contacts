package org.sebrevel.contacts.service.phone;

import org.sebrevel.contacts.DTO.ContactDTO;
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
public interface PhoneServiceRemote {

    public static String JNDI_NAME = "java:global/contacts-business-ear-1.0/contacts-business-EJB-1.0/PhoneService!org.sebrevel.contacts.service.phone.PhoneServiceRemote";
    public static String RMI_HOST = "localhost";
    public static String RMI_PORT = "3700";

    List<PhoneDTO> findPhone(String firstName, String lastName);


    List<ContactDTO> findContact (String email);
}
