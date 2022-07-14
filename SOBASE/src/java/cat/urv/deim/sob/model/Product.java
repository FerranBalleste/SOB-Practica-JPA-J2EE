/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@XmlRootElement
public class Product implements Serializable{
    private Long id;
    
    private String name;
    private String type;
    private Integer serviceTime;
    
    private String nutritionalInformation;

    private String sweetener;
    private Integer quantity;
   
    private List<SizePrice> pricesList;
    
    public Product(){}
    
    public Product(Long id){
        this.id = id;
    }
    
    public Product(String name, SizePrice priceSize, String sweetener, Integer quantity){
        this.name = name;
        this.pricesList = new ArrayList<>();
        pricesList.add(priceSize);
        this.sweetener = sweetener;
        this.quantity = quantity;
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
        if(pricesList == null) pricesList = new ArrayList<>();
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
