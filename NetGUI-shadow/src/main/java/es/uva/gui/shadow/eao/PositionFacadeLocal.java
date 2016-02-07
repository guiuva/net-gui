/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.Position;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface PositionFacadeLocal {

  void create(Position position);

  void edit(Position position);

  void remove(Position position);

  Position find(Object id);

  List<Position> findAll();

  List<Position> findRange(int[] range);

  int count();

}
