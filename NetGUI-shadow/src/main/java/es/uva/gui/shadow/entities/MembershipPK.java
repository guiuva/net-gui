/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Embeddable
public class MembershipPK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "user_id")
  private String userId;
  @Basic(optional = false)
  @NotNull
  @Column(name = "beginning")
  @Temporal(TemporalType.DATE)
  private Date beginning;

  public MembershipPK() {
  }

  public MembershipPK(String userId, Date beginning) {
    this.userId = userId;
    this.beginning = beginning;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getBeginning() {
    return beginning;
  }

  public void setBeginning(Date beginning) {
    this.beginning = beginning;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (userId != null ? userId.hashCode() : 0);
    hash += (beginning != null ? beginning.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof MembershipPK)) {
      return false;
    }
    MembershipPK other = (MembershipPK) object;
    if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
      return false;
    }
    if ((this.beginning == null && other.beginning != null) || (this.beginning != null && !this.beginning.equals(other.beginning))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.MembershipsPK[ userId=" + userId + ", beginning=" + beginning + " ]";
  }

}
