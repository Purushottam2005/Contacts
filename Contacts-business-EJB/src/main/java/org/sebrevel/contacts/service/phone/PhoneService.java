package org.sebrevel.contacts.service.phone;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.sebrevel.contacts.DTO.ContactDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;
import org.sebrevel.contacts.MapperUtil;
import org.sebrevel.contacts.beans.Contact;
import org.sebrevel.contacts.beans.Phone;
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
public class PhoneService implements PhoneServiceRemote {

    private DozerBeanMapper mapper;

    Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public PhoneService() {
        super();
        mapper = (DozerBeanMapper) DozerBeanMapperSingletonWrapper.getInstance();
    }

    @PersistenceContext(unitName = "contactsPU")
    private EntityManager em;

    public List<PhoneDTO> findPhone(String firstName, String lastName) {
        logger.info("Recherche tous les phones de "+firstName+" "+lastName);
        List<Phone> all = em.createNamedQuery(Phone.FIND_PHONE).setParameter("firstName", firstName).setParameter("lastName", lastName).getResultList();
        logger.info("Résultat de la recherche : {}", all.size());

        List<PhoneDTO> allDTO = MapperUtil.map(mapper, all, PhoneDTO.class, PhoneDTO.class);

        return allDTO;
    }

    @Override
    public List<ContactDTO> findContact(String phone) {
        logger.info("Recherche le contact qui pour email "+phone);

        List<Contact> all = em.createNamedQuery(Phone.FIND_CONTACT).setParameter("phone", phone).getResultList();

        List<ContactDTO> allDTO = MapperUtil.map(mapper, all, ContactDTO.class, ContactDTO.class);

        logger.info("Résultat de la recherche : {}", allDTO);

        return allDTO;


    }

}
