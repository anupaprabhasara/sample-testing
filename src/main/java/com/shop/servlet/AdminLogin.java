package com.shop.servlet;

import com.shop.model.User;
import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/login")
public class AdminLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifier = request.getParameter("email"); // Can be email or username
        String password = request.getParameter("password");

        // Check if session already exists
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            response.sendRedirect("user");
            return;
        }

        // Authenticate admin
        User admin = authenticateAdmin(identifier, password);

        if (admin != null) {
            // Create a new session for the authenticated admin
            session = request.getSession(true);
            session.setAttribute("admin", admin);
            session.setAttribute("firstName", admin.getFirstName());
            session.setAttribute("lastName", admin.getLastName());
            session.setMaxInactiveInterval(30 * 60); // Session expires after 30 minutes

            // Redirect to the dashboard
            response.sendRedirect("user");
        } else {
            // Authentication failed, redirect back to login page with error
            request.setAttribute("error", "Invalid email/username or password");
            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
        }
    }

    private User authenticateAdmin(String identifier, String password) {
        for (User user : userService.getAllUsers()) {
            if ((user.getEmail().equalsIgnoreCase(identifier) || user.getFirstName().equalsIgnoreCase(identifier))
                    && user.getPassword().equals(password)
                    && user.getUserType().equals("Admin")) {
                return user;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check for existing error message (e.g., from invalid login attempt)
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            // Redirect authenticated user to the dashboard
            response.sendRedirect("user");
        } else {
            // Redirect to login page
            request.getRequestDispatcher("loginPage.jsp").forward(request, response);
        }
    }
}