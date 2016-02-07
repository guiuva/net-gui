/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.gui.api.rest;

import es.uva.gui.shadow.eao.MembershipFacadeLocal;
import es.uva.gui.shadow.entities.Membership;
import es.uva.gui.shadow.entities.MembershipPK;
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
@Path("memberships")
public class MembershipFacadeREST implements IFacadeRest<Membership, PathSegment> {

  @EJB
  private MembershipFacadeLocal membershipFacade;

  private MembershipPK getPrimaryKey(PathSegment pathSegment) {
    /*
     * pathSemgent represents a URI path segment and any associated matrix parameters.
     * URI path part is supposed to be in form of 'somePath;userId=userIdValue;beginning=beginningValue'.
     * Here 'somePath' is a result of getPath() method invocation and
     * it is ignored in the following code.
     * Matrix parameters are used as field names to build a primary key instance.
     */
    MembershipPK key = new MembershipPK();
    MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
    List<String> userId = map.get("userId");
    if (userId != null && !userId.isEmpty()) {
      key.setUserId(userId.get(0));
    }
    List<String> beginning = map.get("beginning");
    if (beginning != null && !beginning.isEmpty()) {
      key.setBeginning(new java.util.Date(beginning.get(0)));
    }
    return key;
  }

  @POST
  @Override
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void create(Membership entity) {
    membershipFacade.create(entity);
  }

  @PUT
  @Override
  @Path("{id}")
  @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public void edit(@PathParam("id") PathSegment id, Membership entity) {
    membershipFacade.edit(entity);
  }

  @DELETE
  @Override
  @Path("{id}")
  public void remove(@PathParam("id") PathSegment id) {
    MembershipPK key = getPrimaryKey(id);
    membershipFacade.remove(membershipFacade.find(key));
  }

  @GET
  @Override
  @Path("{id}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public Membership find(@PathParam("id") PathSegment id) {
    MembershipPK key = getPrimaryKey(id);
    return membershipFacade.find(key);
  }

  @GET
  @Override
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<Membership> findAll() {
    return membershipFacade.findAll();
  }

  @GET
  @Override
  @Path("{from}/{to}")
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  public List<Membership> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
    return membershipFacade.findRange(new int[]{from, to});
  }

  @GET
  @Override
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String count() {
    return String.valueOf(membershipFacade.count());
  }

}
