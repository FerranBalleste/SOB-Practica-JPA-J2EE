package cat.urv.deim.sob.command;

import cat.urv.deim.sob.model.Product;
import cat.urv.deim.sob.service.ProductService;
import cat.urv.deim.sob.service.Security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class ProductListCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        String view = "views/index.jsp";
        String cityName = request.getParameter("cityName");
        cityName = Security.stripEvilChars(cityName);
        
        ProductService service = new ProductService();
        List<Product> productList = service.findProductsByStore(cityName);
        /*Test*/
        /*
        if(productList == null){
            productList = new ArrayList<>();
            Product p;
            p = new Product(5L);
            p.setName("Product1");
            p.addSize("L",6.9f);
            p.addSize("M",3.5f);
            productList.add(p);
            p = new Product();
            p.setName("Product2");
            productList.add(p);
            p = new Product();
            p.setName("Product3");
            productList.add(p);
        }
        */
        // 2. Set attributes on the request
        //request.setAttribute("store", store);
        HttpSession session = request.getSession();
        session.setAttribute("productList", productList);
        session.setAttribute("cityName", cityName);
        if(session.getAttribute("prodInfo") != null){
            session.setAttribute("prodInfo", null);
        }
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
