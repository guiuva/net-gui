/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.UserField;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface UserFieldFacadeLocal {

  void create(UserField userField);

  void edit(UserField userField);

  void remove(UserField userField);

  UserField find(Object id);

  List<UserField> findAll();

  List<UserField> findRange(int[] range);

  int count();

}
