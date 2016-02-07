/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.api.rest;

import es.uva.gui.shadow.eao.UserPositionFacadeLocal;
import es.uva.gui.shadow.entities.UserPosition;
import es.uva.gui.shadow.entities.UserPositionPK;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author David Soler <aensoler@gmail.com>
 */
@ManagedBean
@RequestScoped
@Path("userpositions")
public class UserPositionFacadeREST implements IFacadeRest<UserPosition, PathSegment> {

  @EJB
  private UserPositionFacadeLocal userPositionFacade;

  private UserPositionPK getPrimaryKey(PathSegment pathSegment) {
    /*
     * pathSemgent represents a URI path segment and any associated matrix parameters.
     * URI path part is supposed to be in form of 'somePath;userId=userIdValue;positionId=positionIdValue'.
     * Here 'somePath' is a result of getPath() method invocation and
     * it is ignored in the following code.
     * Matrix parameters are used as field names to build a primary key instance.
     */
    UserPositionPK key = new UserPositionPK();
    MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
    List<String> userId = map.get("userId");
    if (userId != null && !userId.isEmpty()) {
      key.setUserId(userId.get(0));
    }
    List<String> positionId = map.get("positionId");
    if (positionId != null && !positionId.isEmpty()) {
      key.setPositionId(new java.lang.Integer(positionId.get(0)));
    }
    return key;
  }

  @POST
  @Override
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void create(UserPosition entity) {
    userPositionFacade.create(entity);
  }

  @PUT
  @Override
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void edit(@PathParam("id") PathSegment id, UserPosition entity) {
    userPositionFacade.edit(entity);
  }

  @DELETE
  @Override
  @Path("{id}")
  public void remove(@PathParam("id") PathSegment id) {
    UserPositionPK key = getPrimaryKey(id);
    userPositionFacade.remove(userPositionFacade.find(key));
  }

  @GET
  @Override
  @Path("{id}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public UserPosition find(@PathParam("id") PathSegment id) {
    UserPositionPK key = getPrimaryKey(id);
    return userPositionFacade.find(key);
  }

  @GET
  @Override
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<UserPosition> findAll() {
    return userPositionFacade.findAll();
  }

  @GET
  @Override
  @Path("{from}/{to}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<UserPosition> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
    return userPositionFacade.findRange(new int[]{from, to});
  }

  @GET
  @Override
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count() {
    return String.valueOf(userPositionFacade.count());
  }

}
