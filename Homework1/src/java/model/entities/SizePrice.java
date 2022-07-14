/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Embeddable
public class SizePrice implements Serializable {
    private String size;
    private float price;
    
    public SizePrice(){}
    
    public SizePrice(String size, float price) {
        this.size = size;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
