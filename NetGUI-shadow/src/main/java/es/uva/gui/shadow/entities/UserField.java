/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Entity
@Table(name = "users_fields")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "UserField.findAll", query = "SELECT u FROM UserField u"),
  @NamedQuery(name = "UserField.findById", query = "SELECT u FROM UserField u WHERE u.id = :id"),
  @NamedQuery(name = "UserField.findByText", query = "SELECT u FROM UserField u WHERE u.text = :text")})
public class UserField implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Size(max = 100)
  @Column(name = "text")
  private String text;
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User userId;
  @JoinColumn(name = "type_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Type typeId;

  public UserField() {
  }

  public UserField(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public Type getTypeId() {
    return typeId;
  }

  public void setTypeId(Type typeId) {
    this.typeId = typeId;
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
    if (!(object instanceof UserField)) {
      return false;
    }
    UserField other = (UserField) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.UsersFields[ id=" + id + " ]";
  }

}
