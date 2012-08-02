package org.sebrevel.contacts.rest.contact;

//These are Jersey jars

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.IdentityDTO;
import org.sebrevel.contacts.service.contact.ContactServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

@Path("/contact")
public class ContactRestService {

    private ContactServiceRemote contactService;

    public ContactRestService() {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", ContactServiceRemote.RMI_HOST);
        props.setProperty("org.omg.CORBA.ORBInitialPort", ContactServiceRemote.RMI_PORT);
        try {
            InitialContext appContext = new InitialContext(props);
            contactService = (ContactServiceRemote) appContext.lookup(ContactServiceRemote.JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{firstName}/{lastName}")
    public ContactDTO find(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        ContactDTO newPerson = contactService.find(firstName, lastName);

        return newPerson;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public ContactDTO create(ContactDTO newPerson) {
        newPerson = contactService.create(newPerson);

        return newPerson;
    }

    @PUT
    @Path("{firstName}/{lastName}")
    @Produces({MediaType.APPLICATION_JSON})
    public ContactDTO update(ContactDTO newPerson) {
        return contactService.update(newPerson);
    }

    @DELETE
    @Path("{firstName}/{lastName}")
    public void delete(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        ContactDTO person = contactService.find(firstName, lastName);
        contactService.delete(person);
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {

        List<ContactDTO> all = contactService.findAll();

        GenericEntity<List<ContactDTO>> entity =
                new GenericEntity<List<ContactDTO>>(all) {
                };

        return Response.ok(entity).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByCity/{city}")
    public List<IdentityDTO> findByCity(@PathParam("city") String city) {
        return contactService.findByCity(city);
    }
}
