package cat.urv.deim.sob.command;

import cat.urv.deim.sob.model.Product;
import cat.urv.deim.sob.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

public class ProductInfoCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        String view = "views/index.jsp";
        Long productId = Long.parseLong(request.getParameter("product"));

        ProductService ps = new ProductService();
        Product p = ps.getProduct(productId);
        
        //test
        /*
        if(p == null){
            p = new Product(5L);
            p.setName("Product1");
            p.addSize("L",6.9f);
            p.addSize("M",3.5f);
        }
        */
        
        // 2. Set attributes on the request
        if(p != null){
            //request.setAttribute("prodInfo", p);
            request.getSession().setAttribute("prodInfo", p);
        }
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
