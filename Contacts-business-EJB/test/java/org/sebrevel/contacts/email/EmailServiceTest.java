package org.sebrevel.contacts.email;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.service.email.EmailService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Sébastien Revel
 */
public class EmailServiceTest {

    private static EJBContainer ejbContainer;
    private static Context appContext;

    private static EmailService emailService = null;

    @BeforeClass
    public static void initEjbContainer() throws Exception {

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
        props.put("org.glassfish.service.embedded.glassfish.instance.root", "./src/test/resources"); // lien vers le fichier domain.xml
        ejbContainer = EJBContainer.createEJBContainer(props);
        appContext = ejbContainer.getContext();

        emailService = (EmailService) appContext.lookup("java:global/embedded-classes/EmailService!org.sebrevel.contacts.service.email.EmailService");
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
    public void findEmail() throws Exception {
        List<EmailDTO> all = emailService.findEmail("Sébastien", "REVEL");
        assertEquals(2, all.size());
    }


    @Test
    public void findContact() throws Exception {
        ContactDTO contact = emailService.findContact("fleur.bragard@gmail.com");
        assertEquals("Fleur", contact.getFirstName());
        assertEquals("REVEL", contact.getLastName());
    }
}
