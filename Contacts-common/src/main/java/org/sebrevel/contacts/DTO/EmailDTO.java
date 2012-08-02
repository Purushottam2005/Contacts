package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IEmail;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDTO implements IEmail {

    private Long id;
    private String email;
    private EmailCategory category;

    public EmailDTO() {
    }

    public EmailDTO(String email, EmailCategory category) {
        this.email = email;
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
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public EmailCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(EmailCategory category) {
        this.category = category;
    }
}