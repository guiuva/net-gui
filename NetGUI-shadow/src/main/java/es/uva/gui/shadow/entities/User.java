/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
  @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
  @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
  @NamedQuery(name = "User.findByDni", query = "SELECT u FROM User u WHERE u.dni = :dni"),
  @NamedQuery(name = "User.findByNia", query = "SELECT u FROM User u WHERE u.nia = :nia"),
  @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
  @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
  @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
  @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
  @NamedQuery(name = "User.findByState", query = "SELECT u FROM User u WHERE u.state = :state")})
public class User implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "id")
  private String id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "password")
  private String password;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "dni")
  private String dni;
  @Size(max = 20)
  @Column(name = "nia")
  private String nia;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "first_name")
  private String firstName;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "last_name")
  private String lastName;
  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "email")
  private String email;
  // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
  @Size(max = 15)
  @Column(name = "phone")
  private String phone;
  @Size(max = 100)
  @Column(name = "ustate")
  private String state;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<UserFile> usersFilesCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
  private Collection<UserPosition> usersPositionsCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<UserField> usersFieldsCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
  private Collection<Membership> membershipsCollection;

  public User() {
  }

  public User(String id) {
    this.id = id;
  }

  public User(String id, String password, String dni, String firstName, String lastName, String email) {
    this.id = id;
    this.password = password;
    this.dni = dni;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getNia() {
    return nia;
  }

  public void setNia(String nia) {
    this.nia = nia;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @XmlTransient
  public Collection<UserFile> getUsersFilesCollection() {
    return usersFilesCollection;
  }

  public void setUsersFilesCollection(Collection<UserFile> usersFilesCollection) {
    this.usersFilesCollection = usersFilesCollection;
  }

  @XmlTransient
  public Collection<UserPosition> getUsersPositionsCollection() {
    return usersPositionsCollection;
  }

  public void setUsersPositionsCollection(Collection<UserPosition> usersPositionsCollection) {
    this.usersPositionsCollection = usersPositionsCollection;
  }

  @XmlTransient
  public Collection<UserField> getUsersFieldsCollection() {
    return usersFieldsCollection;
  }

  public void setUsersFieldsCollection(Collection<UserField> usersFieldsCollection) {
    this.usersFieldsCollection = usersFieldsCollection;
  }

  @XmlTransient
  public Collection<Membership> getMembershipsCollection() {
    return membershipsCollection;
  }

  public void setMembershipsCollection(Collection<Membership> membershipsCollection) {
    this.membershipsCollection = membershipsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.Users[ id=" + id + " ]";
  }

}
