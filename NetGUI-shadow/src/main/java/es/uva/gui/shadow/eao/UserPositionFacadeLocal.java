/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.UserPosition;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface UserPositionFacadeLocal {

  void create(UserPosition userPosition);

  void edit(UserPosition userPosition);

  void remove(UserPosition userPosition);

  UserPosition find(Object id);

  List<UserPosition> findAll();

  List<UserPosition> findRange(int[] range);

  int count();

}
