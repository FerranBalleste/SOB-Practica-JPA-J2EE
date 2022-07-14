package cat.urv.deim.sob.command;

import cat.urv.deim.sob.model.Customer;
import cat.urv.deim.sob.service.CustomerService;
import cat.urv.deim.sob.service.Security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

public class logInFormCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get input
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        userName = Security.stripEvilChars(userName);
        password = Security.stripEvilChars(password);
        
        // Verificate customer throug Customer Service
        CustomerService service = new CustomerService();
        Customer customer = service.verifyCustomer(userName, password);
        //Customer customer = service.getCustomer(1L);

        String view;
        if (customer != null) {
            request.getSession().setAttribute("userId", customer.getId());
            request.getSession().setAttribute("userName", customer.getUserName());
            request.getSession().setAttribute("userEmail", customer.getEmail());
            view = "views/index.jsp";
        } else {
            // Log in failed
            view = "views/login.jsp";
            request.setAttribute("message", "Wrong username or password");
        }
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
