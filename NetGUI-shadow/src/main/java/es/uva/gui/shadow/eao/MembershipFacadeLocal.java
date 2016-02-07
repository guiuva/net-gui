/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.entities.Membership;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Local
public interface MembershipFacadeLocal {

  void create(Membership membership);

  void edit(Membership membership);

  void remove(Membership membership);

  Membership find(Object id);

  List<Membership> findAll();

  List<Membership> findRange(int[] range);

  int count();

}
