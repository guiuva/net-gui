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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "types")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
  @NamedQuery(name = "Type.findById", query = "SELECT t FROM Type t WHERE t.id = :id"),
  @NamedQuery(name = "Type.findByLiteral", query = "SELECT t FROM Type t WHERE t.literal = :literal"),
  @NamedQuery(name = "Type.findByCategory", query = "SELECT t FROM Type t WHERE t.category = :category")})
public class Type implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "literal")
  private String literal;
  @Size(max = 100)
  @Column(name = "category")
  private String category;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
  private Collection<UserFile> usersFilesCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeId")
  private Collection<UserField> usersFieldsCollection;

  public Type() {
  }

  public Type(Integer id) {
    this.id = id;
  }

  public Type(Integer id, String literal) {
    this.id = id;
    this.literal = literal;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @XmlTransient
  public Collection<UserFile> getUsersFilesCollection() {
    return usersFilesCollection;
  }

  public void setUsersFilesCollection(Collection<UserFile> usersFilesCollection) {
    this.usersFilesCollection = usersFilesCollection;
  }

  @XmlTransient
  public Collection<UserField> getUsersFieldsCollection() {
    return usersFieldsCollection;
  }

  public void setUsersFieldsCollection(Collection<UserField> usersFieldsCollection) {
    this.usersFieldsCollection = usersFieldsCollection;
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
    if (!(object instanceof Type)) {
      return false;
    }
    Type other = (Type) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.Types[ id=" + id + " ]";
  }

}
