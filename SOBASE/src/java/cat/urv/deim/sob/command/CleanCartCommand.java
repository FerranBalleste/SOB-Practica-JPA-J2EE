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

public class CleanCartCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        HttpSession session = request.getSession();
   
        String view = "views/shoppingcart.jsp";
        request.setAttribute("orderMessage", "Shopping cart has been cleaned.");
        session.setAttribute("shoppingCart", null);
        session.setAttribute("cartCity", null);
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
