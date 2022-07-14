/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.entities.Customer;
import authn.Secured;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Stateless
@Path("rest/api/v1/customer")
public class CustomerFacadeRest extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CustomerFacadeRest() {
        super(Customer.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers() {
        List<Customer> custList = em.createNamedQuery("Customer.allCustomers").getResultList();
        GenericEntity<List<Customer>> gc = new GenericEntity<List<Customer>>(custList){};
        return Response.ok().entity(gc).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCustomer(@PathParam("id") Long id) {
        Customer cust = em.find(Customer.class, id);
        if(cust == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().entity(cust).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name")
    public Response getCustomerName(@QueryParam("userName") String userName) {
        try{
            Customer cust = (Customer) em.createNamedQuery("Customer.findCustomerByName").setParameter("userName", userName).getSingleResult();
            if(cust == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok().entity(cust).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response verifyCustomer(@QueryParam("userName") String userName, @QueryParam("password") String passw) {
        try{
            Customer cust = (Customer) em.createNamedQuery("Customer.findCustomerByName").setParameter("userName", userName).getSingleResult();
            if(cust == null) return Response.status(Response.Status.NOT_FOUND).build();
            if(cust.obtainPassword().equals(passw))
                return Response.ok().entity(cust).build();
            return Response.status(Response.Status.CONFLICT).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Path("{id}")
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateCustomer(@PathParam("id") Long id, Customer newCustData) {
        Customer cust = em.find(Customer.class, id);
        if(cust == null) return Response.status(Response.Status.NOT_FOUND).build();
        
        String userName = newCustData.getUserName();
        String email = newCustData.getEmail();
        String password = newCustData.obtainPassword();
        
        if(userName == null || email == null || password == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("Parameter/s missing.").build();
        if(userName.isEmpty() || email.isEmpty() || password.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot have empty fields.").build();
        
        cust.setUserName(userName);
        cust.setEmail(email);
        cust.setPassword(password);
        
        em.persist(cust);
        return Response.noContent().build();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
