/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Embeddable
public class UserPositionPK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 100)
  @Column(name = "user_id")
  private String userId;
  @Basic(optional = false)
  @NotNull
  @Column(name = "position_id")
  private int positionId;

  public UserPositionPK() {
  }

  public UserPositionPK(String userId, int positionId) {
    this.userId = userId;
    this.positionId = positionId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getPositionId() {
    return positionId;
  }

  public void setPositionId(int positionId) {
    this.positionId = positionId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (userId != null ? userId.hashCode() : 0);
    hash += (int) positionId;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserPositionPK)) {
      return false;
    }
    UserPositionPK other = (UserPositionPK) object;
    if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
      return false;
    }
    if (this.positionId != other.positionId) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "es.uva.gui.shadow.entities.UsersPositionsPK[ userId=" + userId + ", positionId=" + positionId + " ]";
  }

}
