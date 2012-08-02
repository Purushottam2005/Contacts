package org.sebrevel.contacts.user;

import org.sebrevel.contacts.DTO.UserDTO;
import org.sebrevel.contacts.service.user.UserService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Sébastien Revel
 */
public class UserServiceTest {

    private static EJBContainer ejbContainer;
    private static Context appContext;

    private static UserService userService = null;

    @BeforeClass
    public static void initEjbContainer() throws Exception {

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
        props.put("org.glassfish.service.embedded.glassfish.instance.root", "./src/test/resources"); // lien vers le fichier domain.xml
        ejbContainer = EJBContainer.createEJBContainer(props);
        appContext = ejbContainer.getContext();

        userService = (UserService) appContext.lookup("java:global/embedded-classes/UserService!org.sebrevel.contacts.service.user.UserService");
    }

    @AfterClass
    public static void closeEjbContainer() throws Exception {
        appContext.close();
        ejbContainer.close();
    }


    // ======================================
    // =              Unit tests            =
    // ======================================


    @Test
    public void crud() throws Exception {
        List<UserDTO> all = userService.findAll();
        assertEquals(2, all.size());

        //read
        UserDTO user = userService.find("SRL");
        assertNull(user);

        //create
        UserDTO seb = new UserDTO("SRL", "pwd");
        seb.setFirstName("Seb");
        seb.setLastName("REVEL");
        userService.create(seb);

        //read
        all = userService.findAll();
        assertEquals(5, all.size());

        List<UserDTO> users = userService.findByName("Seb", "REVEL");
        assertNotNull(users);
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).getFirstName(), "Seb");

        //update
        seb.setFirstName("Sébastien");
        seb = userService.update(seb);

        //read
        seb = userService.find("SRL");
        assertNotNull(seb);
        assertEquals("REVEL", seb.getFirstName());

        //delete
        userService.delete(seb);

        //read
        all = userService.findAll();
        assertEquals(2, all.size());
        seb = userService.find("SRL");
        assertNull(seb);
    }

    @Test
    public void findAll() throws Exception {
        List<UserDTO> all = userService.findAll();
        assertEquals(2, all.size());
    }
}