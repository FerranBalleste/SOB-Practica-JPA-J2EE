/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.entities.Customer;
import authn.Secured;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import model.entities.Product;
import model.entities.ProductSale;
import model.entities.Sale;
import model.entities.Store;
import service.request.SaleRequest;
import service.request.SaleRequestUnit;

@Stateless
@Path("rest/api/v1/order")
public class SaleFacadeRest extends AbstractFacade<Sale> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public SaleFacadeRest() {
        super(Sale.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    @Path("{id}")
    public Response getSale(@PathParam("id") Long id) {
        Sale sl = em.find(Sale.class, id);
        if(sl == null) return Response.status(Response.Status.NOT_FOUND).build();
        Customer tempCust = sl.getCust();
        sl.setCust(tempCust);
        
        return Response.ok().entity(sl).build();
    }
    
    //Fa una comanda dels diferents productes a l'establiment ${store}. 
    //Per cada producte, caldrà indicar la quantitat i la mida de l'envàs i si fa servir algun tipus d'edulcorant
    
    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSale(@QueryParam("store") String store, SaleRequest saleReq){
        Sale newSale = new Sale();
        Customer cust = em.find(Customer.class, saleReq.getCustomerId());
        if(cust == null) return Response.status(Response.Status.NOT_FOUND).entity("Customer not found").build();
        Store store2 = findStore(store);
        if(store2 == null) return Response.status(Response.Status.NOT_FOUND).entity("Store not found").build();
        
        Collection<ProductSale> auxProductList = new ArrayList<>();
        for(SaleRequestUnit saleReqUnit : saleReq.getProducts()){
            Product auxProduct = store2.findProduct(saleReqUnit.getProductId());
            if(auxProduct == null) return Response.status(Response.Status.NOT_FOUND).entity("Product not found in store").build();
            ProductSale auxProductSale = 
                    new ProductSale(auxProduct, newSale, saleReqUnit.getQuantity(), saleReqUnit.getSize(), saleReqUnit.getSweetener());
            auxProductList.add(auxProductSale);
            em.persist(auxProductSale);
        }
        
        newSale.setCust(cust);
        newSale.setStore(store2);
        newSale.setProducts(auxProductList);

        em.persist(newSale);
        return Response.status(Response.Status.CREATED).build();
    }
    
    public Store findStore(String cityName){
        try{
            Store s = (Store) em.createNamedQuery("Store.findStoreByName").setParameter("cityName", cityName).getSingleResult();
            return s;
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
