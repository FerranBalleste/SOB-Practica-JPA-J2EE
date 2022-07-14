/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.entities.Product;

@Stateless
@Path("rest/api/v1/product")
public class ProductFacadeRest extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public ProductFacadeRest() {
        super(Product.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getProduct(@PathParam("id") Long id) {
        Product prod = em.find(Product.class, id);
        if(prod == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(prod).build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
