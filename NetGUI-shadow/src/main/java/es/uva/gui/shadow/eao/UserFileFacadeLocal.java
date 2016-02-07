/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.UserFile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface UserFileFacadeLocal {

  void create(UserFile userFile);

  void edit(UserFile userFile);

  void remove(UserFile userFile);

  UserFile find(Object id);

  List<UserFile> findAll();

  List<UserFile> findRange(int[] range);

  int count();

}
