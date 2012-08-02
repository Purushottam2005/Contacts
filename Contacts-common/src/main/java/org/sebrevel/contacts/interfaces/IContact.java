package org.sebrevel.contacts.interfaces;

import org.sebrevel.contacts.DTO.ActivityDTO;
import org.sebrevel.contacts.DTO.AddressDTO;
import org.sebrevel.contacts.DTO.EmailDTO;
import org.sebrevel.contacts.DTO.PhoneDTO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public interface IContact extends  IPerson, Serializable {

    @XmlElementWrapper(name = "emails")
    @XmlElement(type = EmailDTO.class)
    List<IEmail> getEmails();

    void setEmails(List<IEmail> emails);

    @XmlElementWrapper(name = "phones")
    @XmlElement(type = PhoneDTO.class)
    List<IPhone> getPhones();

    void setPhones(List<IPhone> phones);

    @XmlElementWrapper(name = "addresses")
    @XmlElement(type = AddressDTO.class)
    List<IAddress> getAddresses();

    void setAddresses(List<IAddress> addresses);


}
