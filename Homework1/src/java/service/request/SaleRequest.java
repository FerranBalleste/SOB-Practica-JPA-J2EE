/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.request;

import java.util.List;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
public class SaleRequest {
    Long customerId;
    List<SaleRequestUnit> products;

    public SaleRequest() {}
    
    public SaleRequest(Long customer_id, List<SaleRequestUnit> products) {
        this.products = products;
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
}
