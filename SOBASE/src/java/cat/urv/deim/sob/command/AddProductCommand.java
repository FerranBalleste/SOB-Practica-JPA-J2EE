package cat.urv.deim.sob.command;

import cat.urv.deim.sob.model.SaleRequestUnit;
import cat.urv.deim.sob.service.Security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class AddProductCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        // Sale Request Unit: productId, sweetener, size, quantity
        String view = "views/index.jsp";
        Long productId = Long.parseLong(request.getParameter("productId"));
        String sweetener = request.getParameter("edulcorant");
        sweetener = Security.stripEvilChars(sweetener);
        String size = request.getParameter("prodSize");
        String[] aux = size.split(",");
        size = Security.stripEvilChars(aux[0]);
        float price = Float.parseFloat(aux[1]);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        //Extra info, not needed to order
        String prodName = request.getParameter("productName");

        //Shopping cart list
        HttpSession session = request.getSession();
        String cityName = (String) session.getAttribute("cityName");
        List<SaleRequestUnit> shoppingCart = (List<SaleRequestUnit>) session.getAttribute("shoppingCart");
        if(shoppingCart == null){
            shoppingCart = new ArrayList<>();
            session.setAttribute("cartCity", cityName);
        }
        
        //Products in a shopping cart can only be from one city
        String cartCity = (String) session.getAttribute("cartCity");
        if(cartCity.equals(cityName)){
            shoppingCart.add(new SaleRequestUnit(productId, prodName, sweetener, size, quantity, price));
            session.setAttribute("shoppingCart", shoppingCart);
            request.setAttribute("addMessage", "Product has been added to cart.");
        }
        else{
            request.setAttribute("addMessage", "Product could not be added. Different store.");
        }
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
