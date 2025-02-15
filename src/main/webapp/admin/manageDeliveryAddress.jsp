<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="./partials/header.jsp"%>

<style>
	.nav-link.addressButton {
	  color: var(--accent);
	}
</style>

<%@ include file="./partials/middle.jsp"%>

<main class="container main-content">
    <div class="page-header">
        <h1>Manage Delivery Addresses</h1>
        <button class="btn btn-primary" onclick="openCreateModal()">Add New Address</button>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Address Line 1</th>
                    <th>Address Line 2</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Postal Code</th>
                    <th>Phone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="address" items="${addresses}">
                
                 <!-- Edit Order Item Modal for specific order item -->
                    <div id="editModal${address.id}" class="modal-overlay" style="display: none;">
                        <div class="modal">
                            <div class="modal-header">
                                <h2>Edit Delivery Address</h2>
                                <button class="btn btn-danger" onclick="closeEditModal(${address.id})">&times;</button>
                            </div>
                            <form id="editdeliveryAddressForm" action="deliveryAddress" method="get" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="id" value="${address.id}">
                                <div class="form-group">
                                    <label class="form-label">User ID</label>
                                    <input type="number" class="form-input" name="userId" value="${address.userId}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Address Line 1</label>
                                    <input type="text" class="form-input" name="addressLine1" value="${address.addressLine1}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Address Line 2</label>
                                    <input type="text" class="form-input" name="addressLine2" value="${address.addressLine2}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">City</label>
                                    <input type="text"  class="form-input" name="city" value="${address.city}">
                                </div>
                                <div class="form-group">
                					<label class="form-label">State</label>
               						 <input type="text" class="form-input" name="state"  value="${address.state}" required>
            					</div>
            					<div class="form-group">
                					<label class="form-label">Postal Code</label>
               						 <input type="text" class="form-input" name="postaleCode" value="${address.postaleCode}" required>
           					    </div>
            					<div class="form-group">
                					<label class="form-label">Phone</label>
               						 <input type="text" class="form-input" name="phone" value="${address.phone}" required>
            					</div>
                                <button type="submit" class="btn btn-primary">Update Delivery Address</button>
                            </form>
                        </div>
                    </div>
        
                    <tr>
                        <td>${address.id}</td>
                        <td>${address.userId}</td>
                        <td>${address.addressLine1}</td>
                        <td>${address.addressLine2}</td>
                        <td>${address.city}</td>
                        <td>${address.state}</td>
                        <td>${address.postaleCode}</td>
                        <td>${address.phone}</td>
                        <td class="actions">
                            <button class="btn btn-primary" onclick="openEditModal(${address.id})">Edit</button>
                            <button class="btn btn-danger" onclick="showDeleteModal(${address.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<!-- Create New Address Modal -->
<div id="createModal" class="modal-overlay" style="display: none;">
    <div class="modal">
        <div class="modal-header">
            <h2>Add New Address</h2>
            <button class="btn btn-danger" onclick="closeCreateModal()">&times;</button>
        </div>
        <form action="deliveryAddress" method="post">
            <input type="hidden" name="action" value="create">
            <div class="form-group">
                <label class="form-label">User ID</label>
                <input type="number" class="form-input" name="userId" required>
            </div>
            <div class="form-group">
                <label class="form-label">Address Line 1</label>
                <input type="text" class="form-input" name="addressLine1" required>
            </div>
            <div class="form-group">
                <label class="form-label">Address Line 2</label>
                <input type="text" class="form-input" name="addressLine2">
            </div>
            <div class="form-group">
                <label class="form-label">City</label>
                <input type="text" class="form-input" name="city" required>
            </div>
            <div class="form-group">
                <label class="form-label">State</label>
                <input type="text" class="form-input" name="state" required>
            </div>
            <div class="form-group">
                <label class="form-label">Postal Code</label>
                <input type="text" class="form-input" name="postaleCode" required>
            </div>
            <div class="form-group">
                <label class="form-label">Phone</label>
                <input type="text" class="form-input" name="phone" required>
            </div>
            <button type="submit" class="btn btn-primary">Save Address</button>
        </form>
    </div>
</div>

<!-- Delete Confirmation Modal -->
<div id="deleteModal" class="modal-overlay" style="display: none;">
    <div class="modal">
        <div class="modal-header">
            <h2>Confirm Delete</h2>
            <button class="btn btn-danger" onclick="closeDeleteModal()">&times;</button>
        </div>
        <p>Are you sure you want to delete this address?</p>
        <div style="margin-top: 1rem; display: flex; gap: 1rem;">
            <button class="btn btn-danger" onclick="confirmDelete()">Delete</button>
            <button class="btn btn-primary" onclick="closeDeleteModal()">Cancel</button>
        </div>
    </div>
</div>

<%@ include file="./partials/footer.jsp"%>
