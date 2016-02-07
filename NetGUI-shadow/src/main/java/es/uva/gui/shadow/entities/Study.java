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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Entity
@Table(name = "studies")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Study.findAll", query = "SELECT s FROM Study s"),
  @NamedQuery(name = "Study.findById", query = "SELECT s FROM Study s WHERE s.id = :id"),
  @NamedQuery(name = "Study.findByAbbr", query = "SELECT s FROM Study s WHERE s.abbr = :abbr"),
  @NamedQuery(name = "Study.findByLiteral", query = "SELECT s FROM Study s WHERE s.literal = :literal"),
  @NamedQuery(name = "Study.findByCode", query = "SELECT s FROM Study s WHERE s.code = :code")})
public class Study implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 10)
  @Column(name = "abbr")
  private String abbr;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "literal")
  private String literal;
  @Column(name = "code")
  private Integer code;

  public Study() {
  }

  public Study(Integer id) {
    this.id = id;
  }

  public Study(Integer id, String abbr, String literal) {
    this.id = id;
    this.abbr = abbr;
    this.literal = literal;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAbbr() {
    return abbr;
  }

  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }

  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
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
    if (!(object instanceof Study)) {
      return false;
    }
    Study other = (Study) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.Studies[ id=" + id + " ]";
  }

}
