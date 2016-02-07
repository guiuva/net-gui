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
@Table(name = "positions")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
  @NamedQuery(name = "Position.findById", query = "SELECT p FROM Position p WHERE p.id = :id"),
  @NamedQuery(name = "Position.findByTitle", query = "SELECT p FROM Position p WHERE p.title = :title"),
  @NamedQuery(name = "Position.findBySubtitle", query = "SELECT p FROM Position p WHERE p.subtitle = :subtitle")})
public class Position implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "title")
  private String title;
  @Size(max = 100)
  @Column(name = "subtitle")
  private String subtitle;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "positions")
  private Collection<UserPosition> usersPositionsCollection;

  public Position() {
  }

  public Position(Integer id) {
    this.id = id;
  }

  public Position(Integer id, String title) {
    this.id = id;
    this.title = title;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  @XmlTransient
  public Collection<UserPosition> getUsersPositionsCollection() {
    return usersPositionsCollection;
  }

  public void setUsersPositionsCollection(Collection<UserPosition> usersPositionsCollection) {
    this.usersPositionsCollection = usersPositionsCollection;
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
    if (!(object instanceof Position)) {
      return false;
    }
    Position other = (Position) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.Positions[ id=" + id + " ]";
  }

}
