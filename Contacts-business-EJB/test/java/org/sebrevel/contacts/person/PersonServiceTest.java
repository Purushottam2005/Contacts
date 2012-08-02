package org.sebrevel.contacts.person;

import org.sebrevel.contacts.DTO.PersonDTO;
import org.sebrevel.contacts.interfaces.IPerson;
import org.sebrevel.contacts.service.person.PersonService;
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
public class PersonServiceTest {

    private static EJBContainer ejbContainer;
    private static Context appContext;

    private static PersonService personService = null;

    @BeforeClass
    public static void initEjbContainer() throws Exception {

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
        props.put("org.glassfish.service.embedded.glassfish.instance.root", "./src/test/resources"); // lien vers le fichier domain.xml
        ejbContainer = EJBContainer.createEJBContainer(props);
        appContext = ejbContainer.getContext();

        personService = (PersonService) appContext.lookup("java:global/embedded-classes/PersonService!org.sebrevel.contacts.service.person.PersonService");
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
        List<PersonDTO> all = personService.findAll();
        assertEquals(4, all.size());

        //read
        PersonDTO person = personService.find("REVEL", "Nell");
        assertNull(person);

        //create
        PersonDTO nell = new PersonDTO("REVEL", "Nell", new GregorianCalendar(2007, 6, 28).getTime(),IPerson.Gender.FEMALE, IPerson.Status.SINGLE, null);
        personService.create(nell);

        //read
        all = personService.findAll();
        assertEquals(5, all.size());
        person = personService.find("Nell", "REVEL");
        assertNotNull(person);
        assertEquals(28, person.getBirthdate().getDate());

        //update
        nell.setBirthdate(new GregorianCalendar(2007, 6, 29).getTime());
        person = personService.update(nell);

        //read
        person = personService.find("Nell", "REVEL");
        assertNotNull(person);
        assertEquals(29, person.getBirthdate().getDate());

        //delete
        personService.delete(nell);

        //read
        all = personService.findAll();
        assertEquals(4, all.size());
        person = personService.find("Nell", "REVEL");
        assertNull(person);
    }

    @Test
    public void findAll() throws Exception {
        List<PersonDTO> all = personService.findAll();
        assertEquals(4, all.size());
    }

}
