package org.sebrevel.contacts.interfaces;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Sébastien Revel
 * Date: 31/05/12
 * Time: 17:18
 * To change this template use File | Settings | File Templates.
 */
public interface IActivity extends Serializable {
    Long getId();

    void setId(Long id);

    ActivityName getName();

    void setName(ActivityName name);

    public enum ActivityName {
        SPORT, COMPUTER, TV, GAME
    }

}