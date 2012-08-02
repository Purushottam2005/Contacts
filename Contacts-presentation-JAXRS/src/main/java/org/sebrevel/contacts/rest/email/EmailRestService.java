package org.sebrevel.contacts.rest.email;

//These are Jersey jars

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.service.email.EmailServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

@Path("/email")
public class EmailRestService {

    private EmailServiceRemote emailService;

    public EmailRestService() {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", EmailServiceRemote.RMI_HOST);
        props.setProperty("org.omg.CORBA.ORBInitialPort", EmailServiceRemote.RMI_PORT);
        try {
            InitialContext appContext = new InitialContext(props);
            emailService = (EmailServiceRemote) appContext.lookup(EmailServiceRemote.JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{firstName}/{lastName}")
    public List<EmailDTO>  findEmail(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        List<EmailDTO> emails = emailService.findEmail(firstName, lastName);

        return emails;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{email}")
    public ContactDTO findContact(@PathParam("email") String email) {
        ContactDTO contact = emailService.findContact(email);

        return contact;
    }
}
