package org.sebrevel.contacts.service.user;

import org.sebrevel.contacts.DTO.UserDTO;
import org.sebrevel.contacts.MapperUtil;
import org.sebrevel.contacts.beans.User;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * PersonDTO: Sébastien Revel
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote {

    private DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        super();
        mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
    }

    @PersistenceContext(unitName = "contactsPU")
    private EntityManager em;


    public UserDTO find(String login) {
        logger.info("Recheche du user {}, {}", login);

        User user = em.find(User.class, login);

        UserDTO userDTO = null;
        if (user != null) {
            userDTO = mapper.map(user, UserDTO.class);
        }
        logger.info("Résultat de la recherche : {}", userDTO);

        return userDTO;
    }

    public UserDTO create(UserDTO userDTO) {
        logger.info("Création du user {}, {}", userDTO.getFirstName(), userDTO.getLastName());

        User user = mapper.map(userDTO, User.class);
        em.persist(user);
        em.flush();

        userDTO = mapper.map(user, UserDTO.class);

        logger.info("Création du user {} effectuée !", userDTO);

        return userDTO;
    }

    public void delete(UserDTO userDTO) {
        logger.info("Suppression du user {}, {}", userDTO.getFirstName(), userDTO.getLastName());

        User user = mapper.map(userDTO, User.class);

        em.remove(em.merge(user));

        logger.info("Suppression du user : {} effectuée !", user);
    }

    public UserDTO update(UserDTO userDTO) {
        logger.info("Mise à jour du user {}, {}", userDTO.getFirstName(), userDTO.getLastName());

        User user = mapper.map(userDTO, User.class);


        user = em.merge(user);

        userDTO = mapper.map(user, UserDTO.class);

        logger.info("Mise à jour du contact {} effectuée !", user);

        return userDTO;
    }

    public List<UserDTO> findAll() {
        logger.info("Recherche toutes les users !");
        List<UserDTO> all = em.createNamedQuery(User.FIND_ALL).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<UserDTO> allDTO = MapperUtil.map(mapper, all, UserDTO.class, UserDTO.class);

        return allDTO;
    }

    public List<UserDTO> findByName(String firstName, String lastName) {
        logger.info("Recheche du user {}, {}", firstName, lastName);

        List<UserDTO> all = em.createNamedQuery(User.FIND_BY_NAME).setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<UserDTO> allDTO = MapperUtil.map(mapper, all, UserDTO.class, UserDTO.class);

        return allDTO;
    }



}
