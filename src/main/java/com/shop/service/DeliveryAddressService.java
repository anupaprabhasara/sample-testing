package com.shop.service;

import com.shop.model.DeliveryAddress;
import com.shop.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressService {

    // Create Delivery Address
    public boolean createDeliveryAddress(DeliveryAddress deliveryAddress) {
        String query = "INSERT INTO delivery_addresses (user_id, address_line1, address_line2, city, state, postal_code, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, deliveryAddress.getUserId());
            stmt.setString(2, deliveryAddress.getAddressLine1());
            stmt.setString(3, deliveryAddress.getAddressLine2());
            stmt.setString(4, deliveryAddress.getCity());
            stmt.setString(5, deliveryAddress.getState());
            stmt.setString(6, deliveryAddress.getPostalCode());
            stmt.setString(7, deliveryAddress.getPhoneNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Delivery Address by ID
    public DeliveryAddress getDeliveryAddress(int id) {
        String query = "SELECT * FROM delivery_addresses WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setId(rs.getInt("id"));
                deliveryAddress.setUserId(rs.getInt("user_id"));
                deliveryAddress.setAddressLine1(rs.getString("address_line1"));
                deliveryAddress.setAddressLine2(rs.getString("address_line2"));
                deliveryAddress.setCity(rs.getString("city"));
                deliveryAddress.setState(rs.getString("state"));
                deliveryAddress.setPostalCode(rs.getString("postal_code"));
                deliveryAddress.setPhoneNumber(rs.getString("phone_number"));
                return deliveryAddress;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Delivery Addresses
    public List<DeliveryAddress> getAllDeliveryAddresses() {
        List<DeliveryAddress> deliveryAddresses = new ArrayList<>();
        String query = "SELECT * FROM delivery_addresses";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DeliveryAddress deliveryAddress = new DeliveryAddress();
                deliveryAddress.setId(rs.getInt("id"));
                deliveryAddress.setUserId(rs.getInt("user_id"));
                deliveryAddress.setAddressLine1(rs.getString("address_line1"));
                deliveryAddress.setAddressLine2(rs.getString("address_line2"));
                deliveryAddress.setCity(rs.getString("city"));
                deliveryAddress.setState(rs.getString("state"));
                deliveryAddress.setPostalCode(rs.getString("postal_code"));
                deliveryAddress.setPhoneNumber(rs.getString("phone_number"));
                deliveryAddresses.add(deliveryAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryAddresses;
    }

    // Update Delivery Address
    public boolean updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        String query = "UPDATE delivery_addresses SET user_id = ?, address_line1 = ?, address_line2 = ?, city = ?, state = ?, postal_code = ?, phone_number = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, deliveryAddress.getUserId());
            stmt.setString(2, deliveryAddress.getAddressLine1());
            stmt.setString(3, deliveryAddress.getAddressLine2());
            stmt.setString(4, deliveryAddress.getCity());
            stmt.setString(5, deliveryAddress.getState());
            stmt.setString(6, deliveryAddress.getPostalCode());
            stmt.setString(7, deliveryAddress.getPhoneNumber());
            stmt.setInt(8, deliveryAddress.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Delivery Address
    public boolean deleteDeliveryAddress(int id) {
        String query = "DELETE FROM delivery_addresses WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}