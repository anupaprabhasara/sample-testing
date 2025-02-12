<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="./partials/header.jsp"%>

<style>
    .nav-link.orderitemButton {
        color: var(--accent);
    }
</style>

<%@ include file="./partials/middle.jsp"%>

<main class="container main-content">
    <div class="page-header">
        <h1>Order Item Management</h1>
        <button class="btn btn-primary" onclick="openCreateModal()">Create New Order Item</button>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Order ID</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderItem" items="${orderItems}">
                    <!-- Edit Order Item Modal for specific order item -->
                    <div id="editModal${orderItem.id}" class="modal-overlay" style="display: none;">
                        <div class="modal">
                            <div class="modal-header">
                                <h2>Edit Order Item</h2>
                                <button class="btn btn-danger" onclick="closeEditModal(${orderItem.id})">&times;</button>
                            </div>
                            <form id="editOrderItemForm" action="orderItem" method="get" enctype="multipart/form-data">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="id" value="${orderItem.id}">
                                <div class="form-group">
                                    <label class="form-label">Order ID</label>
                                    <input type="text" class="form-input" name="orderId" value="${orderItem.orderId}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Product ID</label>
                                    <input type="text" class="form-input" name="productId" value="${orderItem.productId}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Quantity</label>
                                    <input type="number" class="form-input" name="quantity" value="${orderItem.quantity}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Price</label>
                                    <input type="number" step="0.01" class="form-input" name="price" value="${orderItem.price}">
                                </div>
                                <button type="submit" class="btn btn-primary">Update Order Item</button>
                            </form>
                        </div>
                    </div>

                    <tr>
                        <td>${orderItem.id}</td>
                        <td>${orderItem.orderId}</td>
                        <td>${orderItem.productId}</td>
                        <td>${orderItem.quantity}</td>
                        <td>${orderItem.price}</td>
                        <td class="actions">
                            <button class="btn btn-primary" onclick="openEditModal(${orderItem.id})">Edit</button>
                            <button class="btn btn-danger" onclick="showDeleteModal(${orderItem.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<!-- Create New Order Item Modal -->
<div id="createModal" class="modal-overlay" style="display: none;">
    <div class="modal">
        <div class="modal-header">
            <h2>Create New Order Item</h2>
            <button class="btn btn-danger" onclick="closeCreateModal()">&times;</button>
        </div>
        <form id="createOrderItemForm" action="orderItem" method="get" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create">
            <div class="form-group">
                <label class="form-label">Order ID</label>
                <input type="text" class="form-input" name="orderId">
            </div>
            <div class="form-group">
                <label class="form-label">Product ID</label>
                <input type="text" class="form-input" name="productId">
            </div>
            <div class="form-group">
                <label class="form-label">Quantity</label>
                <input type="number" class="form-input" name="quantity">
            </div>
            <div class="form-group">
                <label class="form-label">Price</label>
                <input type="number" step="0.01" class="form-input" name="price">
            </div>
            <button type="submit" class="btn btn-primary">Create Order Item</button>
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
        <p>Are you sure you want to delete this order item?</p>
        <div style="margin-top: 1rem; display: flex; gap: 1rem;">
            <button class="btn btn-danger" onclick="confirmDelete()">Delete</button>
            <button class="btn btn-primary" onclick="closeDeleteModal()">Cancel</button>
        </div>
    </div>
</div>

<%@ include file="./partials/footer.jsp"%>