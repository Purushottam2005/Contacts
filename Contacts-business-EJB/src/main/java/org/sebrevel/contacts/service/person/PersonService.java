package org.sebrevel.contacts.service.person;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.sebrevel.contacts.DTO.PersonDTO;
import org.sebrevel.contacts.MapperUtil;
import org.sebrevel.contacts.beans.Identity;
import org.sebrevel.contacts.beans.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * PersonDTO: Sébastien Revel
 */
@Stateless
public class PersonService implements PersonServiceRemote {

    private DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService() {
        super();
        mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
    }

    @PersistenceContext(unitName = "contactsPU")
    private EntityManager em;

    public PersonDTO find(String firstName, String lastName) {
        logger.info("Recheche de la personne {}, {}", firstName, lastName);

        Person person = em.find(Person.class, new Identity(firstName, lastName));

        PersonDTO personDTO = null;
        if (person != null) {
            personDTO = mapper.map(person, PersonDTO.class);
        }
        logger.info("Résultat de la recherche : {}", personDTO);

        return personDTO;
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Création de la personne {}, {}", personDTO.getFirstName(), personDTO.getLastName());

        Person person = mapper.map(personDTO, Person.class);
        em.persist(person);
        em.flush();

        personDTO = mapper.map(person, PersonDTO.class);

        logger.info("Création de la personne {} effectuée !", personDTO);

        return personDTO;
    }

    public void delete(PersonDTO personDTO) {
        logger.info("Suppression de la personne {}, {}", personDTO.getFirstName(), personDTO.getLastName());

        Person person = mapper.map(personDTO, Person.class);

        em.remove(em.merge(person));

        logger.info("Suppression de la personne : {} effectuée !", person);
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Mise à jour de la personne {}, {}", personDTO.getFirstName(), personDTO.getLastName());

        Person person = mapper.map(personDTO, Person.class);

        person = em.merge(person);

        personDTO = mapper.map(person, PersonDTO.class);

        logger.info("Mise à jour de la personne {} effectuée !", person);

        return personDTO;
    }

    public List<PersonDTO> findAll() {
        logger.info("Recherche toutes les personnes !");
        List<PersonDTO> all = em.createNamedQuery(Person.FIND_ALL).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<PersonDTO> allDTO = MapperUtil.map(mapper, all, PersonDTO.class, PersonDTO.class);

        return allDTO;
    }


}
