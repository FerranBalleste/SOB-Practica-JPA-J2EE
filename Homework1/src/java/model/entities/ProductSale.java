/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Entity
public class ProductSale implements Serializable {

    @SequenceGenerator(name="ProductSale_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductSale_Gen") 
    @Id
    private Long id;
 
    @ManyToOne
    private Product product;
 
    @ManyToOne
    private Sale sale;
    
    int quantity;
    String productSize;
    String sweetener;

    public ProductSale() {}
    
    public ProductSale(Product prod, Sale sale){
        this.product = prod;
        this.sale = sale;
    }
    
    public ProductSale(Product prod, Sale sale, int quantity, String productSize, String sweetener){
        this.product = prod;
        this.sale = sale;
        this.quantity = quantity;
        this.productSize = productSize;
        this.sweetener = sweetener;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }
        
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return productSize;
    }

    public void setSize(String size) {
        this.productSize = size;
    }

    public String getSweetener() {
        return sweetener;
    }

    public void setSweetener(String sweetener) {
        this.sweetener = sweetener;
    }
}
