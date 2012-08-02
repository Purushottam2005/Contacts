package org.sebrevel.contacts.rest.person;

//These are Jersey jars

import org.sebrevel.contacts.DTO.PersonDTO;
import org.sebrevel.contacts.service.person.PersonServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

@Path("/person")
public class PersonRestService {

    private PersonServiceRemote personService;

    public PersonRestService() {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", PersonServiceRemote.RMI_HOST);
        props.setProperty("org.omg.CORBA.ORBInitialPort", PersonServiceRemote.RMI_PORT);
        try {
            InitialContext appContext = new InitialContext(props);
            personService = (PersonServiceRemote) appContext.lookup(PersonServiceRemote.JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{firstName}/{lastName}")
    public PersonDTO find(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        PersonDTO newPerson = personService.find(firstName, lastName);

        return newPerson;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO create(PersonDTO newPerson) {
        newPerson = personService.create(newPerson);

        return newPerson;
    }

    @PUT
    @Path("{firstName}/{lastName}")
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO update(PersonDTO newPerson) {
        return personService.update(newPerson);
    }

    @DELETE
    @Path("{firstName}/{lastName}")
    public void delete(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        PersonDTO person = personService.find(firstName, lastName);
        personService.delete(person);
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {

        List<PersonDTO> all = personService.findAll();

        GenericEntity<List<PersonDTO>> entity =
                new GenericEntity<List<PersonDTO>>(all) {
                };

        return Response.ok(entity).build();
    }


}
