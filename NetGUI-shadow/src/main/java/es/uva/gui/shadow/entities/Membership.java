/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Entity
@Table(name = "memberships")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m"),
  @NamedQuery(name = "Membership.findByUserId", query = "SELECT m FROM Membership m WHERE m.membershipsPK.userId = :userId"),
  @NamedQuery(name = "Membership.findByBeginning", query = "SELECT m FROM Membership m WHERE m.membershipsPK.beginning = :beginning"),
  @NamedQuery(name = "Membership.findByFinalisation", query = "SELECT m FROM Membership m WHERE m.finalisation = :finalisation")})
public class Membership implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected MembershipPK membershipsPK;
  @Column(name = "finalisation")
  @Temporal(TemporalType.DATE)
  private Date finalisation;
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private User users;

  public Membership() {
  }

  public Membership(MembershipPK membershipsPK) {
    this.membershipsPK = membershipsPK;
  }

  public Membership(String userId, Date beginning) {
    this.membershipsPK = new MembershipPK(userId, beginning);
  }

  public MembershipPK getMembershipsPK() {
    return membershipsPK;
  }

  public void setMembershipsPK(MembershipPK membershipsPK) {
    this.membershipsPK = membershipsPK;
  }

  public Date getFinalisation() {
    return finalisation;
  }

  public void setFinalisation(Date finalisation) {
    this.finalisation = finalisation;
  }

  public User getUsers() {
    return users;
  }

  public void setUsers(User users) {
    this.users = users;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (membershipsPK != null ? membershipsPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Membership)) {
      return false;
    }
    Membership other = (Membership) object;
    if ((this.membershipsPK == null && other.membershipsPK != null) || (this.membershipsPK != null && !this.membershipsPK.equals(other.membershipsPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.Memberships[ membershipsPK=" + membershipsPK + " ]";
  }

}
