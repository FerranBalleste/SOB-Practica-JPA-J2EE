package cat.urv.deim.sob.command;

import cat.urv.deim.sob.model.SaleRequest;
import cat.urv.deim.sob.model.SaleRequestUnit;
import cat.urv.deim.sob.service.SaleService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class OrderProductCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        List<SaleRequestUnit> shopList = (List<SaleRequestUnit>) session.getAttribute("shoppingCart");
        String cartCity = (String) session.getAttribute("cartCity");
        String view = "views/shoppingcart.jsp";
        if(cartCity != null && shopList != null){
            SaleRequest saleRequest = new SaleRequest(userId,shopList);
            
            SaleService service = new SaleService();
            if(service.postSale(saleRequest, cartCity)){
                request.setAttribute("orderMessage", "Order has been posted correctly.");
                session.setAttribute("shoppingCart", null);
                session.setAttribute("cartCity", null);
            }
            else{
                request.setAttribute("orderMessage", "Error.");
            }
        }
        else{
            request.setAttribute("orderMessage", "Empty cart, cannot order.");
        }
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
