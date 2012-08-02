package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IActivity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityDTO implements IActivity {

    private Long id;
    private ActivityName name;


    public ActivityDTO() {
    }

    public ActivityDTO(ActivityName name) {
        this.name = name;
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
    public ActivityName getName() {
        return name;
    }

    @Override
    public void setName(ActivityName name) {
        this.name = name;
    }

}