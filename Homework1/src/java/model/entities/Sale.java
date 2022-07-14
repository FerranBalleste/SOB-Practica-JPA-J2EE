/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.ws.rs.Path;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Entity
public class Sale implements Serializable{
    @SequenceGenerator(name="Sale_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sale_Gen")
    @Id
    private Long id;
    
    @ManyToOne
    private Customer cust;
    
    @ManyToOne
    private Store store;
    
    @OneToMany(mappedBy = "sale")
    private Collection<ProductSale> products;
    
    private static final DecimalFormat DEF = new DecimalFormat("0.00");

    public Sale(){}

    public Sale(Customer cust, Store store, Collection<ProductSale> products) {
        this.cust = cust;
        this.store = store;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public Customer getCust() {
        return cust;
    }
  
    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Collection<Product> getProducts() {
        Collection<Product> newProducts = new ArrayList<>();
        for(ProductSale p: products){
            SizePrice sizeP = p.getProduct().findSizePrice(p.getSize());
            Product tempProduct = new Product(p.getProduct().getName(), sizeP, p.getSweetener(), p.getQuantity());
            newProducts.add(tempProduct);
        }
        return newProducts;
    }

    public void setProducts(Collection<ProductSale> products) {
        this.products = products;
    }
    
    public String getTotalPrice(){
        double total = 0.0;
        for(ProductSale p: products){
            String size = p.getSize();
            total = total + p.getProduct().findPrice(size)*p.getQuantity();
        }
        return DEF.format(total);
    }
    
    
}
