package org.sebrevel.contacts.beans;


import org.sebrevel.contacts.interfaces.IUser;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sébastien Revel
 */
@Entity
@Table(name = "USERSAPP") // Permet de donner un autre nom qu el enom par défaut à ma table
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_NAME, query = "select u From User u where u.firstName=:firstName and u.lastName=:lastName "),
        @NamedQuery(name = User.FIND_ALL, query = "select u From User u")
})
public class User implements IUser, Serializable {

    // ======================================
    // =             Constants             =
    // ======================================
    public static final String FIND_BY_NAME = "User.findByName";
    public static final String FIND_ALL = "User.findAll";

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    protected String login;

    protected String password;

    protected String lastName;

    protected String firstName;

    // ======================================
    // =            Constructors            =
    // ======================================

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}