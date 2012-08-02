package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IPhone;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDTO implements IPhone {

    private Long id;
    private String phone;
    private PhoneCategory category;

    public PhoneDTO() {
    }

    public PhoneDTO(String phone, PhoneCategory category) {
        this.phone = phone;
        this.category = category;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public PhoneCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(PhoneCategory category) {
        this.category = category;
    }
}