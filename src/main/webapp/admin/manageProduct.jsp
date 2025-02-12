<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="./partials/header.jsp"%>

<style>
	.nav-link.productButton {
	  color: var(--accent);
	}
</style>

<%@ include file="./partials/middle.jsp"%>

<main class="container main-content">
    <div class="page-header">
        <h1>Product Management</h1>
        <button class="btn btn-primary" onclick="openCreateModal()">Create New Product</button>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock Quantity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <!-- Edit Product Modal for specific product -->
                    <div id="editModal${product.id}" class="modal-overlay" style="display: none;">
                        <div class="modal">
                            <div class="modal-header">
                                <h2>Edit Product</h2>
                                <button class="btn btn-danger" onclick="closeEditModal(${product.id})">&times;</button>
                            </div>
                            <form action="product" method="get">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="id" value="${product.id}">
                                <div class="form-group">
                                    <label class="form-label">Name</label>
                                    <input type="text" class="form-input" name="name" value="${product.name}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Description</label>
                                    <textarea class="form-input" name="description">${product.description}</textarea>
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Price</label>
                                    <input type="number" step="0.01" class="form-input" name="price" value="${product.price}">
                                </div>
                                <div class="form-group">
                                    <label class="form-label">Stock Quantity</label>
                                    <input type="number" class="form-input" name="stockQuantity" value="${product.stockQuantity}">
                                </div>
                                <button type="submit" class="btn btn-primary">Update Product</button>
                            </form>
                        </div>
                    </div>
                    
                    <tr>
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>${product.stockQuantity}</td>
                        <td class="actions">
                            <button class="btn btn-primary" onclick="openEditModal(${product.id})">Edit</button>
                            <button class="btn btn-danger" onclick="showDeleteModal(${product.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<!-- Create New Product Modal -->
<div id="createModal" class="modal-overlay" style="display: none;">
    <div class="modal">
        <div class="modal-header">
            <h2>Create New Product</h2>
            <button class="btn btn-danger" onclick="closeCreateModal()">&times;</button>
        </div>
        <form action="product" method="get">
            <input type="hidden" name="action" value="create">
            <div class="form-group">
                <label class="form-label">Name</label>
                <input type="text" class="form-input" name="name">
            </div>
            <div class="form-group">
                <label class="form-label">Description</label>
                <textarea class="form-input" name="description"></textarea>
            </div>
            <div class="form-group">
                <label class="form-label">Price</label>
                <input type="number" step="0.01" class="form-input" name="price">
            </div>
            <div class="form-group">
                <label class="form-label">Stock Quantity</label>
                <input type="number" class="form-input" name="stockQuantity">
            </div>
            <button type="submit" class="btn btn-primary">Create Product</button>
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
        <p>Are you sure you want to delete this product?</p>
        <div style="margin-top: 1rem; display: flex; gap: 1rem;">
            <button class="btn btn-danger" onclick="confirmDelete()">Delete</button>
            <button class="btn btn-primary" onclick="closeDeleteModal()">Cancel</button>
        </div>
    </div>
</div>

<%@ include file="./partials/footer.jsp"%>