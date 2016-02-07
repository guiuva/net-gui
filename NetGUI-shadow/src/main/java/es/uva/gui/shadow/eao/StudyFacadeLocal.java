/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.Study;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface StudyFacadeLocal {

  void create(Study study);

  void edit(Study study);

  void remove(Study study);

  Study find(Object id);

  List<Study> findAll();

  List<Study> findRange(int[] range);

  int count();

}
