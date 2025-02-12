package com.shop.servlet;

import com.shop.model.OrderItem;
import com.shop.service.OrderItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/orderItem")
public class OrderItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderItemService = new OrderItemService();
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
            // Display all order items
            request.setAttribute("orderItems", orderItemService.getAllOrderItems());
            request.getRequestDispatcher("manageOrderItem.jsp").forward(request, response);
        } else if (action.equals("create")) {
            // Create a new order item via GET request
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);

            if (orderItemService.createOrderItem(orderItem)) {
                response.sendRedirect("orderItem");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("edit")) {
            // Update an existing order item via GET request
            int id = Integer.parseInt(request.getParameter("id"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));

            OrderItem orderItem = new OrderItem();
            orderItem.setId(id);
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);

            if (orderItemService.updateOrderItem(orderItem)) {
                response.sendRedirect("orderItem");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("view")) {
            // View an order item via GET request
            int id = Integer.parseInt(request.getParameter("id"));
            OrderItem orderItem = orderItemService.getOrderItem(id);
            request.setAttribute("orderItem", orderItem);
            request.getRequestDispatcher("manageOrderItem.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            // Delete an order item via GET request
            int id = Integer.parseInt(request.getParameter("id"));
            if (orderItemService.deleteOrderItem(id)) {
                response.sendRedirect("orderItem");
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
