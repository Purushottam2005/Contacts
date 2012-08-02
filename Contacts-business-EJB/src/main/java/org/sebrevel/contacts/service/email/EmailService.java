package org.sebrevel.contacts.service.email;

import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.MapperUtil;
import org.sebrevel.contacts.beans.Contact;
import org.sebrevel.contacts.beans.Email;
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
public class EmailService implements EmailServiceRemote {

    private DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService() {
        super();
        mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
    }

    @PersistenceContext(unitName = "contactsPU")
    private EntityManager em;

    public List<EmailDTO> findEmail(String firstName, String lastName) {
        logger.info("Recherche tous les emails de "+firstName+" "+lastName);
        List<Email> all = em.createNamedQuery(Email.FIND_EMAIL).setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<EmailDTO> allDTO = MapperUtil.map(mapper, all, EmailDTO.class, EmailDTO.class);

        return allDTO;
    }

    @Override
    public ContactDTO findContact(String email) {
        logger.info("Recherche le contact qui pour email "+email);

        Contact contact = (Contact) em.createNamedQuery(Email.FIND_CONTACT).setParameter("email", email).getSingleResult();

        ContactDTO contactDTO = null;
        if (contact != null) {
            contactDTO = mapper.map(contact, ContactDTO.class);
        }

        logger.info("Résultat de la recherche : {}", contactDTO);

        return contactDTO;


    }


}
