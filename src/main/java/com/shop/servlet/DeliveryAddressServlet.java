package com.shop.servlet;

import com.shop.model.DeliveryAddress;
import com.shop.service.DeliveryAddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/deliveryAddress")
public class DeliveryAddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryAddressService deliveryAddressService;

    @Override
    public void init() throws ServletException {
        super.init();
        deliveryAddressService = new DeliveryAddressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
        
        if (action == null) {
            List<DeliveryAddress> addresses = deliveryAddressService.getAllAddresses();
            request.setAttribute("addresses", addresses);
            request.getRequestDispatcher("manageDeliveryAddress.jsp").forward(request, response);
        } else if (action.equals("create")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postaleCode = request.getParameter("postaleCode");
            String phone = request.getParameter("phone");

            DeliveryAddress address = new DeliveryAddress();
            address.setUserId(userId);
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setCity(city);
            address.setState(state);
            address.setPostaleCode(postaleCode);
            address.setPhone(phone);

            if (deliveryAddressService.createAddress(address)) {
                response.sendRedirect("deliveryAddress");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String postaleCode = request.getParameter("postaleCode");
            String phone = request.getParameter("phone");

            DeliveryAddress address = new DeliveryAddress();
            address.setId(id);
            address.setUserId(userId);
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setCity(city);
            address.setState(state);
            address.setPostaleCode(postaleCode);
            address.setPhone(phone);

            if (deliveryAddressService.updateAddress(address)) {
                response.sendRedirect("deliveryAddress");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("view")) {
            int id = Integer.parseInt(request.getParameter("id"));
            DeliveryAddress address = deliveryAddressService.getAddress(id);
            request.setAttribute("address", address);
            request.getRequestDispatcher("manageDeliveryAddress.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (deliveryAddressService.deleteAddress(id)) {
                response.sendRedirect("deliveryAddress");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else if (action.equals("logout")) {
            session.invalidate();
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

