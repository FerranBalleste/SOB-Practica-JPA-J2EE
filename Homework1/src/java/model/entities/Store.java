/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Entity
@NamedQuery(name="Store.findStoreByName", query = "SELECT s FROM Store s WHERE s.cityName = :cityName")
@NamedQuery(name="Store.findStoreByCoordinates", query = "SELECT s FROM Store s WHERE "
        + "(s.latitude = :latitude) AND (s.longitude = :longitude)")
public class Store implements Serializable {
    @SequenceGenerator(name="Store_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Store_Gen")
    @Id
    private Long id;
    private String cityName;
    private String latitude, longitude;
    //
    @OneToMany(mappedBy = "store")
    Collection<Sale> sales;
    
    @ManyToMany(mappedBy = "stores")
    private Collection<Product> products;
    
    /*
    public Long getId() {
        return id;
    }
    */
    
    public String getCityName() {
        return cityName;
    }
 
    public Collection<Product> listProducts(){
        return products;
    }
    
    public Product findProduct(Long lon){
        for(Product p : products){
            if(p.getId() == lon)
                return p;
        }
        return null;
    }
}
