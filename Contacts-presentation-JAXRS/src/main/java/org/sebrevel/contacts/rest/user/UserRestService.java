package org.sebrevel.contacts.rest.user;

//These are Jersey jars

import org.sebrevel.contacts.DTO.UserDTO;
import org.sebrevel.contacts.service.user.UserServiceRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

@Path("/user")
public class UserRestService {

    private UserServiceRemote userService;

    public UserRestService() {
        Properties props = new Properties();
        props.setProperty("org.omg.CORBA.ORBInitialHost", UserServiceRemote.RMI_HOST);
        props.setProperty("org.omg.CORBA.ORBInitialPort", UserServiceRemote.RMI_PORT);
        try {
            InitialContext appContext = new InitialContext(props);
            userService = (UserServiceRemote) appContext.lookup(UserServiceRemote.JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{login}")
    public UserDTO find(@PathParam("login") String login) {
        UserDTO user = userService.find(login);
        return user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public UserDTO create(UserDTO newUser) {
        newUser = userService.create(newUser);

        return newUser;
    }

    @PUT
    @Path("{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public UserDTO update(UserDTO newUser) {
        return userService.update(newUser);
    }

    @DELETE
    @Path("{login}")
    public void delete(@PathParam("login") String login) {
        UserDTO user = userService.find(login);
        userService.delete(user);
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll() {

        List<UserDTO> all = userService.findAll();

        GenericEntity<List<UserDTO>> entity =
                new GenericEntity<List<UserDTO>>(all) {
                };

        return Response.ok(entity).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    @Path("findByName/{firstName}/{lastName}")
    public List<UserDTO> findByCity(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
        return userService.findByName(firstName, lastName);
    }
}
