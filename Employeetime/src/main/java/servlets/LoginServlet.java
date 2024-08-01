package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO;
import models.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAO dao = new DAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users validate = dao.validate(username, password);
        
        try {
            if (validate != null) {
                HttpSession session = request.getSession();
                if ("Admin".equals(validate.getRole())) {
                    session.setAttribute("Admin", validate);
                    request.setAttribute("Username", username);
                    request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
                } else if ("Associate".equals(validate.getRole())) {
                    session.setAttribute("Associate", validate);
                    request.setAttribute("Username", username);
                    response.sendRedirect("AssociateDashboard.jsp");
                }
            } else {
                response.sendRedirect("Login.jsp?error=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Login.jsp?error=error");
        }
    }
}