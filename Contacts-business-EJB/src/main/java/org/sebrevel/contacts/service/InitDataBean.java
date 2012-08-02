package org.sebrevel.contacts.service;

import org.sebrevel.contacts.DTO.*;
import org.sebrevel.contacts.init.AbstractInitDataSource;
import org.sebrevel.contacts.interfaces.*;
import org.sebrevel.contacts.service.contact.ContactService;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 07/06/12
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton(name = "InitDataEJB")
@DependsOn("InitUsers")
public class InitDataBean extends AbstractInitDataSource {


    @Inject
    ContactService contactService;


    @PostConstruct
    private void dataInit() {

        List<IAddress> addressesSeb = new ArrayList<IAddress>();
        addressesSeb.add(new AddressDTO("9 rue d'en haut", "Yèbles", 77390, "France", AddressDTO.AddressCategory.HOME));
        addressesSeb.add(new AddressDTO("124 rue de Verdun", "Puteaux", 92600, "France", AddressDTO.AddressCategory.WORK));

        List<IEmail> emailsSeb = new ArrayList<IEmail>() {{
            add(new EmailDTO("sebastien.revel@gmailllll.com", EmailDTO.EmailCategory.HOME));
            add(new EmailDTO("s.revel@yahoo.com", EmailDTO.EmailCategory.WORK));
        }};

        List<IPhone> phonesSeb = new ArrayList<IPhone>() {{
            add(new PhoneDTO("0616910907", PhoneDTO.PhoneCategory.MOBILE));
            add(new PhoneDTO("0146250972", PhoneDTO.PhoneCategory.WORK));
        }};
        ContactDTO seb = new ContactDTO("REVEL", "Sebastien", new GregorianCalendar(1976, 3, 22).getTime(), IPerson.Gender.MALE, IPerson.Status.COUPLE, null, emailsSeb, phonesSeb, addressesSeb);
        seb.setGender(IPerson.Gender.MALE);
        seb.setStatus(IPerson.Status.ENGAGED);
        List<IActivity> activitiesSeb = new ArrayList<IActivity>() {{
            add(new ActivityDTO(IActivity.ActivityName.SPORT));
            add(new ActivityDTO(IActivity.ActivityName.TV));
        }};
        seb.setActivities(activitiesSeb);
        contactService.create(seb);

        List<IAddress> addressesFleur = new ArrayList<IAddress>();
        addressesFleur.add(new AddressDTO("7 rue d'en haut", "Yèbles", 77390, "France", AddressDTO.AddressCategory.HOME));
        addressesFleur.add(new AddressDTO("124 rue de Verdun", "Puteaux", 92600, "France", AddressDTO.AddressCategory.WORK));

        List<IEmail> emailsFleur = new ArrayList<IEmail>() {{
            add(new EmailDTO("fleur.bragard@gmail.com", EmailDTO.EmailCategory.HOME));
            add(new EmailDTO("f.revel@yahoo.com", EmailDTO.EmailCategory.WORK));
        }};

        List<IPhone> phonesFleur = new ArrayList<IPhone>() {{
            add(new PhoneDTO("0603458315", PhoneDTO.PhoneCategory.MOBILE));
            add(new PhoneDTO("0146250972", PhoneDTO.PhoneCategory.WORK));
        }};
        List<IActivity> activitiesFleur = new ArrayList<IActivity>() {{
            add(new ActivityDTO(IActivity.ActivityName.COMPUTER));
        }};
        ContactDTO fleur = new ContactDTO("REVEL", "Fleur", new GregorianCalendar(1976, 11, 11).getTime(), IPerson.Gender.FEMALE, IPerson.Status.COUPLE, activitiesFleur, emailsFleur, phonesFleur, addressesFleur);
        contactService.create(fleur);

        List<IAddress> addressesPatricia = new ArrayList<IAddress>();
        addressesPatricia.add(new AddressDTO("18  Square Colonel Remy", "Rennes", 35700, "France", AddressDTO.AddressCategory.HOME));
        addressesPatricia.add(new AddressDTO("18 place des Lices", "Rennes", 35000, "France", AddressDTO.AddressCategory.WORK));
        ContactDTO patricia = new ContactDTO("REVEL", "Patricia", new GregorianCalendar(1971, 6, 18).getTime(), IPerson.Gender.FEMALE, IPerson.Status.SINGLE, null, null, null, addressesPatricia);
        contactService.create(patricia);

        List<IAddress> addressesMarieFrance = new ArrayList<IAddress>();
        addressesMarieFrance.add(new AddressDTO("2 villa de Moravie", "Rennes", 35200, "France", AddressDTO.AddressCategory.HOME));
        ContactDTO mariefrance = new ContactDTO("REVEL", "Marie-France", new GregorianCalendar(1949, 3, 9).getTime(),IPerson.Gender.FEMALE, IPerson.Status.SINGLE, null, null, null, addressesMarieFrance);
        contactService.create(mariefrance);

    }
 }
