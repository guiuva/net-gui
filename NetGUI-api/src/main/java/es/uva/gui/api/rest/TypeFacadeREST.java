/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.api.rest;

import es.uva.gui.shadow.eao.TypeFacadeLocal;
import es.uva.gui.shadow.entities.Type;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@ManagedBean
@RequestScoped
@Path("types")
public class TypeFacadeREST implements IFacadeRest<Type, Integer> {

  @EJB
  private TypeFacadeLocal typeFacade;

  @POST
  @Override
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void create(Type entity) {
    typeFacade.create(entity);
  }

  @PUT
  @Override
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void edit(@PathParam("id") Integer id, Type entity) {
    typeFacade.edit(entity);
  }

  @DELETE
  @Override
  @Path("{id}")
  public void remove(@PathParam("id") Integer id) {
    typeFacade.remove(typeFacade.find(id));
  }

  @GET
  @Override
  @Path("{id}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Type find(@PathParam("id") Integer id) {
    return typeFacade.find(id);
  }

  @GET
  @Override
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<Type> findAll() {
    return typeFacade.findAll();
  }

  @GET
  @Override
  @Path("{from}/{to}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<Type> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
    return typeFacade.findRange(new int[]{from, to});
  }

  @GET
  @Override
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count() {
    return String.valueOf(typeFacade.count());
  }

}
