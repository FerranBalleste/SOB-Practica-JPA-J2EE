/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import model.entities.Store;
import model.entities.Product;

@Stateless
@Path("rest/api/v1/product")
public class StoreFacadeRest extends AbstractFacade<Store> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public StoreFacadeRest() {
        super(Store.class);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreProducts(@QueryParam("store") String store) {
        if(store == null)   return Response.status(Response.Status.BAD_REQUEST).entity("Falta parametre store").build();
        String[] parts = store.split(",");
        Store sto;
        switch (parts.length) {
            case 1:
                sto = findStore(parts[0]);
                break;
            case 2:
                sto = findStore(parts[0], parts[1]);
                break;
            default:
                return Response.status(Response.Status.BAD_REQUEST).entity("Parametres Incorrectes").build();
        }
        if(sto == null)   return Response.status(Response.Status.NOT_FOUND).build();
        //Practica 2
        return Response.ok().entity(removeInfo(sto.listProducts())).build();
        //return Response.ok().entity(sto.listProducts()).build();
    }
    
    private Store findStore(String cityName){
        try{
            Store s = (Store) em.createNamedQuery("Store.findStoreByName").setParameter("cityName", cityName).getSingleResult();
            return s;
        }
        catch(Exception e){
            return null;
        }
    }
    
    private Store findStore(String latitude, String longitude){
        try{
            Store s = (Store) em.createNamedQuery("Store.findStoreByCoordinates")
                    .setParameter("latitude", latitude)
                    .setParameter("longitude", longitude)
                    .getSingleResult();
            return s;
        }
        catch(Exception e){
            return null;
        }
    }
    
    private Collection<Product> removeInfo(Collection<Product> pList){
        Collection<Product> newList = new ArrayList<>();
        pList.forEach((p) -> {
            newList.add(new Product(p.getId(), p.getName(), p.getType(), p.getServiceTime()));
        });
        return newList;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
