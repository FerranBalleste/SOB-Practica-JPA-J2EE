/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Entity
public class Product implements Serializable{
    @SequenceGenerator(name="Product_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Product_Gen") 
    @Id
    private Long id;
    
    private String name;
    private String type;
    private Integer serviceTime;
    
    private String nutritionalInformation;
    
    @Transient
    private String sweetener;
    
    @Transient
    private Integer quantity;
    
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "product_id"))
    private List<SizePrice> pricesList;
    
    @OneToMany(mappedBy = "product")
    private Collection<ProductSale> sales;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_store",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "store_id")}
    )
    private Collection<Store> stores;
    
    public Product(){}
    
    public Product(String name, SizePrice priceSize, String sweetener, Integer quantity){
        this.name = name;
        this.pricesList = new ArrayList<>();
        pricesList.add(priceSize);
        this.sweetener = sweetener;
        this.quantity = quantity;
    }
    
    public Product(Long id, String name, String type, Integer servicetime){
        this.id = id;
        this.name = name;
        this.type = type;
        this.serviceTime = servicetime;
    }
    
    public Product(String name, List<SizePrice> pricesList){
        this.name = name;
        this.pricesList = pricesList;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
       this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getNutritionalInformation() {
        return nutritionalInformation;
    }

    public void setNutritionalInformation(String nutritionalInformation) {
        this.nutritionalInformation = nutritionalInformation;
    }

    public String getSweetener() {
        return sweetener;
    }

    public void setSweetener(String sweetener) {
        this.sweetener = sweetener;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public List<SizePrice> getPricesList() {
        return this.pricesList;
    }

    public void setPricesList(List<SizePrice> pricesList) {
        this.pricesList = pricesList;
    }

    public void addSize(String size, float price) {
        this.pricesList.add(new SizePrice(size, price));
    }
    
    public float findPrice(String size2){
        SizePrice sizePrice = findSizePrice(size2);
        if(sizePrice == null) return 0;
        return sizePrice.getPrice();
    }
    
    public SizePrice findSizePrice(String size2){
        for(SizePrice s : pricesList){
            String size1 = s.getSize();
            if(size1.equals(size2))
                return s;
        }
        return null;
    }
    
}
