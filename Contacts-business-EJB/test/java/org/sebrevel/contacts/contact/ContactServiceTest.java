package org.sebrevel.contacts.contact;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.IdentityDTO;
import org.sebrevel.contacts.interfaces.IContact;
import org.sebrevel.contacts.service.contact.ContactService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Sébastien Revel
 */
public class ContactServiceTest {
    private static EJBContainer ejbContainer;
    private static Context appContext;

    private static ContactService contactService = null;

    @BeforeClass
    public static void initEjbContainer() throws Exception {

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
        props.put("org.glassfish.service.embedded.glassfish.instance.root", "./src/test/resources"); // lien vers le fichier domain.xml
        ejbContainer = EJBContainer.createEJBContainer(props);
        appContext = ejbContainer.getContext();

        contactService = (ContactService) appContext.lookup("java:global/embedded-classes/ContactService!org.sebrevel.contacts.service.contact.ContactService");
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
        List<ContactDTO> all = contactService.findAll();
        assertEquals(4, all.size());

        //read
        ContactDTO person = contactService.find("REVEL", "Nell");
        assertNull(person);

        //create
        ContactDTO nell = new ContactDTO("REVEL", "Nell", new GregorianCalendar(2007, 6, 28).getTime(),IContact.Gender.FEMALE, IContact.Status.SINGLE, null, null, null, null);
        contactService.create(nell);

        //read
        all = contactService.findAll();
        assertEquals(5, all.size());
        person = contactService.find("Nell", "REVEL");
        assertNotNull(person);
        assertEquals(28, person.getBirthdate().getDate());

        //update
        nell.setBirthdate(new GregorianCalendar(2007, 6, 29).getTime());
        person = contactService.update(nell);

        //read
        person = contactService.find("Nell", "REVEL");
        assertNotNull(person);
        assertEquals(29, person.getBirthdate().getDate());

        //delete
        contactService.delete(nell);

        //read
        all = contactService.findAll();
        assertEquals(4, all.size());
        person = contactService.find("Nell", "REVEL");
        assertNull(person);
    }

    @Test
    public void findAll() throws Exception {
        List<ContactDTO> all = contactService.findAll();
        assertEquals(4, all.size());
    }

    @Test
    public void findRennesInhabitants() throws Exception {
        List<IdentityDTO> rennesInhabitants = contactService.findByCity("Rennes");
        assertEquals(2, rennesInhabitants.size());
    }

    @Test
    public void findYeblesInhabitants() throws Exception {
        List<IdentityDTO> yeblesInhabitants = contactService.findByCity("Yèbles");
        assertEquals(2, yeblesInhabitants.size());
    }

}
