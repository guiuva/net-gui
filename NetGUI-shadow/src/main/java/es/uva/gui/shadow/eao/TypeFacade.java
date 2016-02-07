/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.shadow.eao;

import es.uva.gui.shadow.Config;
import es.uva.gui.shadow.entities.Type;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@Stateless
public class TypeFacade extends AbstractFacade<Type> implements TypeFacadeLocal {

  @PersistenceContext(unitName = Config.ACTIVE_PU)
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public TypeFacade() {
    super(Type.class);
  }

}
