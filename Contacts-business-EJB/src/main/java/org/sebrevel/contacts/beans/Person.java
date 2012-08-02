package org.sebrevel.contacts.beans;


import org.sebrevel.contacts.interfaces.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Sébastien Revel
 */
@Entity
@Table(name = "PERSONS") // Permet de donner un autre nom qu el enom par défaut à ma table
@IdClass(Identity.class) // définit la classe qui constitu ma clé multiple (firstName, LastName)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.CHAR) // cree une colonne pour différencier les Person des Contact
@DiscriminatorValue("P")  // Indique dans la table qu'il s'agit d'une personne
@NamedQueries({
        @NamedQuery(name = Person.FIND, query = "select u From Person u where u.firstName=:firstName and u.lastName=:lastName"),
        @NamedQuery(name = Person.FIND_ALL, query = "select u From Person u")
})
public class Person implements IPerson, Serializable {

    // ======================================
    // =             Constants             =
    // ======================================
    public static final String FIND_ALL = "Person.findAll";
    public static final String FIND_BY_CITY = "Person.findByCity";
    public static final String FIND = "Person.find";

    // ======================================
    // =             Attributes             =
    // ======================================
    @Id
    @Column(name = "lastName")
    protected String lastName;

    @Id
    @Column(name = "firstName")
    protected String firstName;

    @Column(name = "type")
    private String type;        // colonne due à l'héritage entre contact et person

    @Temporal(TemporalType.DATE)
    protected Date birthdate;

    @Enumerated(EnumType.STRING)
    protected Gender gender;

    @Enumerated(EnumType.STRING)
    protected Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Activity.class)
    @JoinTable(
            name = "JOIN_USER_ACTIVITY",
            joinColumns = {
                    @JoinColumn(name = "personfirstname_fk", referencedColumnName = "firstName"),
                    @JoinColumn(name = "personlastname_fk", referencedColumnName = "lastName")
            }
    )
    protected List<IActivity> activities;



    // ======================================
    // =            Constructors            =
    // ======================================

    public Person() {
    }

    public Person(String lastName, String firstName, Date birthdate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================


    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = type;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public List<IActivity> getActivities() {
        return this.activities;
    }

    @Override
    public void setActivities(List<IActivity> activities) {
        this.activities = activities;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================


    @Override
    public String toString() {
        return "PersonDTO{" +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate=" + birthdate +
               '}';
    }
}