/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
public class SaleRequestUnit {
    private Long productId;
    private String sweetener, size;
    private int quantity;
    private String name;        //Not needed to post an order
    private float price;        //Not needed to post an order
    
    public SaleRequestUnit(){}
    
    public SaleRequestUnit(Long productId, String sweetener, String size, int quantity) {
        this.productId = productId;
        this.sweetener = sweetener;
        this.size = size;
        this.quantity = quantity;
    }
    
    public SaleRequestUnit(Long productId, String name, String sweetener, String size, int quantity, float unitPrice) {
        this.productId = productId;
        this.name = name;
        this.sweetener = sweetener;
        this.size = size;
        this.quantity = quantity;
        this.price = unitPrice*quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    

    public String getSweetener() {
        return sweetener;
    }

    public void setSweetener(String sweetener) {
        this.sweetener = sweetener;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
