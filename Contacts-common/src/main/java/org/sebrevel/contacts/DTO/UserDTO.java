package org.sebrevel.contacts.DTO;

import org.sebrevel.contacts.interfaces.IUser;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sébastien Revel
 */
@XmlRootElement
public class UserDTO implements IUser {

    protected String login;
    protected String password;

    protected String lastName;
    protected String firstName;

    public UserDTO() {
    }

    public UserDTO(String login, String password) {
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

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO personDTO = (UserDTO) o;

        if (login != null ? !login.equals(personDTO.login) : personDTO.login!= null) return false;
        if (password != null ? !password.equals(personDTO.password) : personDTO.password != null) return false;
        if (firstName != null ? !firstName.equals(personDTO.firstName) : personDTO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(personDTO.lastName) : personDTO.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastName != null ? lastName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "login='" + login + '\'' +
                "password='" + password + '\'' +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}


