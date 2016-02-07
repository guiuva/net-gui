/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.api.rest;

import java.util.List;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 * @param <T>
 * @param <K>
 */
public interface IFacadeRest<T, K> {

  void create(T entity);

  void edit(K id, T entity);

  void remove(K id);

  T find(K id);

  List<T> findAll();

  List<T> findRange(Integer from, Integer to);

  String count();

}
