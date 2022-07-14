package cat.urv.deim.sob.service;

import cat.urv.deim.sob.model.SaleRequest;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
        
public class SaleService {
    private WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources/rest/api/v1";
    
    public SaleService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("order");
    }
    
    public Boolean postSale(SaleRequest saleRequest, String cityName){
        System.out.println(webTarget.queryParam("store", cityName));
        Response response = webTarget.queryParam("store", cityName).request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic c29iOnNvYg==")
                .post(Entity.entity(saleRequest, MediaType.APPLICATION_JSON), Response.class);
        System.out.println("Post Sale Status: " + response.getStatus());
        return response.getStatus() == 201;
    }

}
