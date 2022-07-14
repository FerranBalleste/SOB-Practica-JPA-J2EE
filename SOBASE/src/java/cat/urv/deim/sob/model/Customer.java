/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ferran Balleste Solsona
 * @author Sergi Alier Vinuesa
 */
@XmlRootElement
public class Customer implements Serializable{
    private Long id;
    private String userName, email;
 
    public Customer(){}
    
    public Customer(Long id, String userName, String email){
        this.id = id;
        this.userName = userName;
        this.email = email;
    }
    
    public Customer(String id, String userName, String email){
        this.id = Long.parseLong(id);
        this.userName = userName;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }
    
    public void setUserName(String usrn){
        this.userName = usrn;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
