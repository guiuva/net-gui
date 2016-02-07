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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Entity
@Table(name = "users_positions")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "UserPosition.findAll", query = "SELECT u FROM UserPosition u"),
  @NamedQuery(name = "UserPosition.findByUserId", query = "SELECT u FROM UserPosition u WHERE u.usersPositionsPK.userId = :userId"),
  @NamedQuery(name = "UserPosition.findByPositionId", query = "SELECT u FROM UserPosition u WHERE u.usersPositionsPK.positionId = :positionId"),
  @NamedQuery(name = "UserPosition.findByBeginning", query = "SELECT u FROM UserPosition u WHERE u.beginning = :beginning"),
  @NamedQuery(name = "UserPosition.findByFinalisation", query = "SELECT u FROM UserPosition u WHERE u.finalisation = :finalisation")})
public class UserPosition implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected UserPositionPK usersPositionsPK;
  @Basic(optional = false)
  @NotNull
  @Column(name = "beginning")
  @Temporal(TemporalType.DATE)
  private Date beginning;
  @Column(name = "finalisation")
  @Temporal(TemporalType.DATE)
  private Date finalisation;
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private User users;
  @JoinColumn(name = "position_id", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Position positions;

  public UserPosition() {
  }

  public UserPosition(UserPositionPK usersPositionsPK) {
    this.usersPositionsPK = usersPositionsPK;
  }

  public UserPosition(UserPositionPK usersPositionsPK, Date beginning) {
    this.usersPositionsPK = usersPositionsPK;
    this.beginning = beginning;
  }

  public UserPosition(String userId, int positionId) {
    this.usersPositionsPK = new UserPositionPK(userId, positionId);
  }

  public UserPositionPK getUsersPositionsPK() {
    return usersPositionsPK;
  }

  public void setUsersPositionsPK(UserPositionPK usersPositionsPK) {
    this.usersPositionsPK = usersPositionsPK;
  }

  public Date getBeginning() {
    return beginning;
  }

  public void setBeginning(Date beginning) {
    this.beginning = beginning;
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

  public Position getPositions() {
    return positions;
  }

  public void setPositions(Position positions) {
    this.positions = positions;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (usersPositionsPK != null ? usersPositionsPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserPosition)) {
      return false;
    }
    UserPosition other = (UserPosition) object;
    if ((this.usersPositionsPK == null && other.usersPositionsPK != null) || (this.usersPositionsPK != null && !this.usersPositionsPK.equals(other.usersPositionsPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.UsersPositions[ usersPositionsPK=" + usersPositionsPK + " ]";
  }

}
