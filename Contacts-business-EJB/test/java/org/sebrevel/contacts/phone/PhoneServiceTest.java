package org.sebrevel.contacts.phone;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;
import org.sebrevel.contacts.service.phone.PhoneService;
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
public class PhoneServiceTest {

    private static EJBContainer ejbContainer;
    private static Context appContext;

    private static PhoneService phoneService = null;

    @BeforeClass
    public static void initEjbContainer() throws Exception {

        Map<String, Object> props = new HashMap<String, Object>();
        props.put(EJBContainer.MODULES, new File("target/embedded-classes"));
        props.put("org.glassfish.service.embedded.glassfish.instance.root", "./src/test/resources"); // lien vers le fichier domain.xml
        ejbContainer = EJBContainer.createEJBContainer(props);
        appContext = ejbContainer.getContext();

        phoneService = (PhoneService) appContext.lookup("java:global/embedded-classes/PhoneService!org.sebrevel.contacts.service.phone.PhoneService");
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
    public void findPhone() throws Exception {
        List<PhoneDTO> all = phoneService.findPhone("Sébastien", "REVEL");
        assertEquals(2, all.size());
    }


    @Test
    public void findContact() throws Exception {
        List <ContactDTO> all = phoneService.findContact("0146250972");
        assertEquals(2, all.size());
    }
}
