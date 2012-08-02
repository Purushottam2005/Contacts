package org.sebrevel.contacts.service;

import org.sebrevel.contacts.DTO.UserDTO;
import org.sebrevel.contacts.service.user.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 07/06/12
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton(name = "InitUsers")
public class InitUsers {


    @Inject
    UserService userService;


    @PostConstruct
    private void dataInit() {

        UserDTO fleur = new UserDTO("FLR", "pwd");
        fleur.setFirstName("Fleur");
        fleur.setLastName("REVEL");
        userService.create(fleur);

        UserDTO olivier = new UserDTO("OLC", "pwd");
        olivier.setFirstName("Olivier");
        olivier.setLastName("CHAUMONT");
        userService.create(olivier);

    }
 }
