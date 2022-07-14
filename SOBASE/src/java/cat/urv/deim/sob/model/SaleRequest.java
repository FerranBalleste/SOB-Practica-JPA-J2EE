/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
public class SaleRequest {
    Long customerId;
    List<SaleRequestUnit> products;

    public SaleRequest() {}
    
    public SaleRequest(Long customerId, List<SaleRequestUnit> products) {
        this.customerId = customerId;
        this.products = products;
    }
    
    public SaleRequest(Long customerId, Long prodId, String sweetener, String size, int quantity) {
        this.customerId = customerId;
        SaleRequestUnit sru = new SaleRequestUnit(prodId, sweetener, size, quantity);
        products = new ArrayList();
        products.add(sru);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<SaleRequestUnit> getProducts() {
        return products;
    }

    public void setProducts(List<SaleRequestUnit> products) {
        this.products = products;
    }
    
    public SaleRequestUnit obtainFirst() {
        return products.get(0);
    }
}
