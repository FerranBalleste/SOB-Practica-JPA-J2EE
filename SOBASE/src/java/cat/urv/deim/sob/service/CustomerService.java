package cat.urv.deim.sob.service;

import cat.urv.deim.sob.model.Customer;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
        
public class CustomerService {
    private WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources/rest/api/v1";
    
    public CustomerService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("customer");
    }
    
    public Customer getCustomer(Long id){
        Response response = webTarget.path(Long.toString(id))
                .request(MediaType.APPLICATION_JSON)
                .get();
        //System.out.println(webTarget.path(Long.toString(id)).toString());
        if (response.getStatus() == 200) {
            //System.out.println(response.getEntity().toString());
            return response.readEntity(Customer.class);
        }
        return null;
    }
    
    public Customer verifyCustomer(String userName, String password) {
        Response response = webTarget.path("login").queryParam("userName", userName).queryParam("password", password)
                .request(MediaType.APPLICATION_JSON)
                .get();
        System.out.println(webTarget.path("login").queryParam("userName", userName).queryParam("password", password).toString());
        if (response.getStatus() == 200) {
            return response.readEntity(Customer.class);
        }
        return null;
    }
    
    public List<Customer> getAllCustomers(){
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            return response.readEntity(List.class);
        }
        return null;
    }

}
