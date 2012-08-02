package org.sebrevel.contacts.service.contact;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.IdentityDTO;
import org.sebrevel.contacts.MapperUtil;
import org.sebrevel.contacts.beans.Identity;
import org.sebrevel.contacts.beans.Contact;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


/**
 * Sébastien Revel
 */
@Stateless
@LocalBean
public class ContactService implements ContactServiceRemote {

    private DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(ContactService.class);

    public ContactService() {
        super();
        mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
    }

    @PersistenceContext(unitName = "contactsPU")
    private EntityManager em;

    public ContactDTO find(String firstName, String lastName) {
        logger.info("Recheche du contact {}, {}", firstName, lastName);

        Contact contact = em.find(Contact.class, new Identity(firstName, lastName));

        ContactDTO contactDTO = null;
        if (contact != null) {
            contactDTO = mapper.map(contact, ContactDTO.class);
        }
        logger.info("Résultat de la recherche : {}", contactDTO);

        return contactDTO;
    }

    public ContactDTO create(ContactDTO contactDTO) {
        logger.info("Création du contact {}, {}", contactDTO.getFirstName(), contactDTO.getLastName());

        Contact contact = mapper.map(contactDTO, Contact.class);
        em.persist(contact);
        em.flush();

        contactDTO = mapper.map(contact, ContactDTO.class);

        logger.info("Création du contact {} effectuée !", contactDTO);

        return contactDTO;
    }

    public void delete(ContactDTO contactDTO) {
        logger.info("Suppression du contact {}, {}", contactDTO.getFirstName(), contactDTO.getLastName());

        Contact contact = mapper.map(contactDTO, Contact.class);

        em.remove(em.merge(contact));

        logger.info("Suppressiondu contact : {} effectuée !", contact);
    }

    public ContactDTO update(ContactDTO contactDTO) {
        logger.info("Mise à jour du contact {}, {}", contactDTO.getFirstName(), contactDTO.getLastName());

        Contact contact = mapper.map(contactDTO, Contact.class);

        contact = em.merge(contact);

        contactDTO = mapper.map(contact, ContactDTO.class);

        logger.info("Mise à jour du contact {} effectuée !", contact);

        return contactDTO;
    }

    public List<ContactDTO> findAll() {
        logger.info("Recherche toutes les contacts !");
        List<ContactDTO> all = em.createNamedQuery(Contact.FIND_ALL).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<ContactDTO> allDTO = MapperUtil.map(mapper, all, ContactDTO.class, ContactDTO.class);

        return allDTO;
    }

    public List<IdentityDTO> findByCity(String city) {
        logger.info("Recherche toutes les habitant de {} !", city);

        List<Object[]> cityInhabitants = (List<Object[]>) em.createNamedQuery(Contact.FIND_BY_CITY).setParameter("city", city).getResultList();
        logger.info("Résultat de la recherche : {}", cityInhabitants.size());

        final List<IdentityDTO> cityInhabitantsDTO = new ArrayList();
        for (Object[] names: cityInhabitants ) {
            IdentityDTO identity = new IdentityDTO((String)names[1], (String)names[0]);
            cityInhabitantsDTO.add(identity);
        }

        return cityInhabitantsDTO;
    }


}
