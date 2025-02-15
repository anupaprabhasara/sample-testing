package com.shop.service;

import com.shop.model.DeliveryAddress;
import com.shop.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressService {

    // Create Delivery Address
    public boolean createAddress(DeliveryAddress address) {
        String query = "INSERT INTO delivery_addresses (user_id, address_line_1, address_line_2, city, state, postal_code, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getAddressLine1());
            stmt.setString(3, address.getAddressLine2());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getState());
            stmt.setString(6, address.getPostaleCode());
            stmt.setString(7, address.getPhone());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Address by ID
    public DeliveryAddress getAddress(int id) {
        String query = "SELECT * FROM delivery_addresses WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                DeliveryAddress address = new DeliveryAddress();
                address.setId(rs.getInt("id"));
                address.setUserId(rs.getInt("user_id"));
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setPostaleCode(rs.getString("postal_code"));
                address.setPhone(rs.getString("phone_number"));
                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get All Delivery Addresses
    public List<DeliveryAddress> getAllAddresses() {
        List<DeliveryAddress> addresses = new ArrayList<>();
        String query = "SELECT * FROM delivery_addresses";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DeliveryAddress address = new DeliveryAddress();
                address.setId(rs.getInt("id"));
                address.setUserId(rs.getInt("user_id"));
                address.setAddressLine1(rs.getString("address_line_1"));
                address.setAddressLine2(rs.getString("address_line_2"));
                address.setCity(rs.getString("city"));
                address.setState(rs.getString("state"));
                address.setPostaleCode(rs.getString("postal_code"));
                address.setPhone(rs.getString("phone_number"));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    // Update Delivery Address
    public boolean updateAddress(DeliveryAddress address) {
        String query = "UPDATE delivery_addresses SET user_id = ?, address_line_1 = ?, address_line_2 = ?, city = ?, state = ?, postal_code = ?, phone_number = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getAddressLine1());
            stmt.setString(3, address.getAddressLine2());
            stmt.setString(4, address.getCity());
            stmt.setString(5, address.getState());
            stmt.setString(6, address.getPostaleCode());
            stmt.setString(7, address.getPhone());
            stmt.setInt(8, address.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete Delivery Address
    public boolean deleteAddress(int id) {
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
