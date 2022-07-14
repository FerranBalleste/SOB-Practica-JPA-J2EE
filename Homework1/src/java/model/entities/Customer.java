/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@Entity
@NamedQuery(name="Customer.allCustomers", query = "SELECT e FROM Customer e" )
@NamedQuery(name="Customer.findCustomerByName", query = "SELECT c FROM Customer c WHERE c.userName = :userName")
public class Customer implements Serializable{
    @SequenceGenerator(name="Customer_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Gen")
    @Id
    private Long id;
    private String userName, password, email;

    @OneToMany(mappedBy = "cust")
    private Collection<Sale> orders;
 
    public Customer(){}
    
    public Customer(Long id, String userName, String password, String email){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    public Long getId(){
        return id;
    }
    
    public String getUserName(){
        return userName;
    }
    /*
    @XmlTransient
    public String getPassword(){
        return password;
    }
    */
    public String obtainPassword(){
        return password;
    }
  
    public String getEmail(){
        return email;
    }
    
    public void setUserName(String usrn){
        this.userName = usrn;
    }
    
    public void setPassword(String passwd){
        this.password = passwd;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
}
