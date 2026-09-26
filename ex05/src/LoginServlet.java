import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {
    
    // Handle GET request - Display login page
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Login Page</h2>");
        out.println("<form method='post' action='LoginServlet'>");
        out.println("Username: <input type='text' name='username'><br><br>");
        out.println("Password: <input type='password' name='password'><br><br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");
    }

    // Handle POST request - Authentication
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        
        if("admin".equals(uname) && "12345".equals(pwd)) {
            out.println("<h3>Login Successful! Welcome, " + uname + ".</h3>");
        } else {
            out.println("<h3>Login Failed! Invalid username or password.</h3>");
            out.println("<a href='login.html'>Try Again</a>");
        }
    }
}