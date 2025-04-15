package com.bridgelabz.userregisteration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        description = "Login Servlet ",
        urlPatterns = {"/com.bridgelabz.userregisteration.LoginServlet"},
        initParams = {
                @WebInitParam(name = "user", value = "User"),
                @WebInitParam(name = "password", value = "Bridgelabz@1")
        }
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        System.out.println("user: "+user);
        System.out.println("password: "+pwd);

        if (!isValidName(user)) {
            out.println("Invalid name: Must start with a capital and be at least 3 characters long.<br>");
            request.getRequestDispatcher("login.html").include(request, response);
            return;
        }

        if (!isValidPassword(pwd)) {
            out.println("Invalid password: Minimum 8 characters, 1 uppercase, 1 digit, and exactly 1 special character.<br>");
            request.getRequestDispatcher("login.html").include(request, response);
            return;
        }

        if (userID.equals(user) && password.equals(pwd)) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            out.println("Either username or password is incorrect.<br>");
            request.getRequestDispatcher("login.html").include(request, response);
        }
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("^[A-Z][a-zA-Z0-9]{2,}$");
    }

    private boolean isValidPassword(String password) {

        return password!= null && password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
