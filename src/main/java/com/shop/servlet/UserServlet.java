package com.shop.servlet;

import com.shop.model.User;
import com.shop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect("login");
            return;
        }

        request.setAttribute("firstName", session.getAttribute("firstName"));
        request.setAttribute("lastName", session.getAttribute("lastName"));
        
        String action = request.getParameter("action");

        if (action == null) {
            request.setAttribute("users", userService.getAllUsers());
            request.getRequestDispatcher("manageUser.jsp").forward(request, response);
        } else if (action.equals("create")) {
            // Create a new user via GET request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String userType = request.getParameter("userType");

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setUserType(userType);

            if (userService.createUser(user)) {
                response.sendRedirect("user");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("edit")) {
            // Update an existing user via GET request
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String userType = request.getParameter("userType");

            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhoneNumber(phoneNumber);
            user.setUserType(userType);

            if (userService.updateUser(user)) {
                response.sendRedirect("user");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUser(id);
            request.setAttribute("user", user);
            request.getRequestDispatcher("manageUser.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (userService.deleteUser(id)) {
                response.sendRedirect("user");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("logout")) {
            // Logout the user
            session.invalidate(); // Invalidate the session to log the user out
            response.sendRedirect("login"); // Redirect to the login page
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Redirect POST requests to GET handler
    }
}