package cat.urv.deim.sob.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

public class logOutCommand implements Command {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("userEmail");
        String view = "views/index.jsp";
        
        // 3. produce the view with the web result
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
