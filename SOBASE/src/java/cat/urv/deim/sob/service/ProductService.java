package cat.urv.deim.sob.service;

import cat.urv.deim.sob.model.Product;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
        
public class ProductService {
    private WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/Homework1/webresources/rest/api/v1";
    
    public ProductService() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("product");
    }
    
    public Product getProduct(Long id){
        Response response = webTarget.path(Long.toString(id))
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(Product.class);
        }
        return null;
    }
    
    public List<Product> findProductsByStore(String store){
        Response response = webTarget.queryParam("store", store)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<Product>>() {});
        }
        return null;
    }
}
