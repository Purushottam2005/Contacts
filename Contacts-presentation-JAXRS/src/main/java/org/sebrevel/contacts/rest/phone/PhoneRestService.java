package org.sebrevel.contacts.rest.phone;

//These are Jersey jars

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;
import org.sebrevel.contacts.service.phone.PhoneServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Properties;

@Path("/phone")
public class PhoneRestService {

    private PhoneServiceRemote phoneService;

    public PhoneRestService() {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", PhoneServiceRemote.RMI_HOST);
        props.setProperty("org.omg.CORBA.ORBInitialPort", PhoneServiceRemote.RMI_PORT);
        try {
            InitialContext appContext = new InitialContext(props);
            phoneService = (PhoneServiceRemote) appContext.lookup(PhoneServiceRemote.JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{firstName}/{lastName}")
    public List<PhoneDTO>  findPhone(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        List<PhoneDTO> phones = phoneService.findPhone(firstName, lastName);

        return phones;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{phone}")
    public List<ContactDTO> findContact(@PathParam("phone") String phone) {
        List<ContactDTO> contacts = phoneService.findContact(phone);

        return contacts;
    }
}
