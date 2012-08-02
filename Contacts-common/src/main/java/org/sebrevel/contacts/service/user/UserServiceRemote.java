package org.sebrevel.contacts.service.user;

import org.sebrevel.contacts.DTO.UserDTO;

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
public interface UserServiceRemote {

    public static String JNDI_NAME = "java:global/contacts-business-ear-1.0/contacts-business-EJB-1.0/UserService!org.sebrevel.contacts.service.user.UserServiceRemote";
    public static String RMI_HOST = "localhost";
    public static String RMI_PORT = "3700";

    UserDTO find(String login);

    List<UserDTO> findByName(String firstName, String lastName);

    UserDTO create(UserDTO user);

    void delete(UserDTO user);

    UserDTO update(UserDTO user);

    List<UserDTO> findAll();

}
