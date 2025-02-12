<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="./partials/header.jsp"%>

<style>
	.nav-link.userButton {
	  color: var(--accent);
	}
</style>

<%@ include file="./partials/middle.jsp"%>

<main class="container main-content">
    <div class="page-header">
        <h1>User Management</h1>
        <button class="btn btn-primary" onclick="openCreateModal()">Create New User</button>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>User Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${users}">
				    <!-- Edit User Modal for specific user -->
				    <div id="editModal${user.id}" class="modal-overlay" style="display: none;">
				        <div class="modal">
				            <div class="modal-header">
				                <h2>Edit User</h2>
				                <button class="btn btn-danger" onclick="closeEditModal(${user.id})">&times;</button>
				            </div>
				            <form id="editUserForm" action="user" method="get" enctype="multipart/form-data">
				                <input type="hidden" name="action" value="edit">
				                <input type="hidden" name="id" value="${user.id}">
				                <div class="form-group">
				                    <label class="form-label">First Name</label>
				                    <input type="text" class="form-input" name="firstName" value="${user.firstName}">
				                </div>
				                <div class="form-group">
				                    <label class="form-label">Last Name</label>
				                    <input type="text" class="form-input" name="lastName" value="${user.lastName}">
				                </div>
				                <div class="form-group">
				                    <label class="form-label">Email</label>
				                    <input type="email" class="form-input" name="email" value="${user.email}">
				                </div>
				                <div class="form-group">
				                    <label class="form-label">Phone Number</label>
				                    <input type="text" class="form-input" name="phoneNumber" value="${user.phoneNumber}">
				                </div>
				                <div class="form-group">
				                    <label class="form-label">User Type</label>
				                    <select class="form-input" name="userType">
				                        <option value="User" ${user.userType == 'User' ? 'selected' : ''}>User</option>
				                        <option value="Admin" ${user.userType == 'Admin' ? 'selected' : ''}>Admin</option>
				                    </select>
				                </div>
				                <div class="form-group">
				                    <label class="form-label">Password</label>
				                    <input type="password" class="form-input" name="password" value="${user.password}">
				                </div>
				                <button type="submit" class="btn btn-primary">Update User</button>
				            </form>
				        </div>
				    </div>
				
				    <tr>
				        <td>${user.id}</td>
				        <td>${user.firstName}</td>
				        <td>${user.lastName}</td>
				        <td>${user.email}</td>
				        <td>${user.phoneNumber}</td>
				        <td>${user.userType}</td>
				        <td class="actions">
				            <button class="btn btn-primary" onclick="openEditModal(${user.id})">Edit</button>
				            <button class="btn btn-danger" onclick="showDeleteModal(${user.id})">Delete</button>
				        </td>
				    </tr>
				</c:forEach>
            </tbody>
        </table>
    </div>
</main>

<!-- Create New User Modal -->
<div id="createModal" class="modal-overlay" style="display: none;">
    <div class="modal">
        <div class="modal-header">
            <h2>Create New User</h2>
            <button class="btn btn-danger" onclick="closeCreateModal()">&times;</button>
        </div>
        <form id="createUserForm" action="user" method="get" enctype="multipart/form-data">
        	<input type="hidden" name="action" value="create">
            <div class="form-group">
                <label class="form-label">First Name</label>
                <input type="text" class="form-input" name="firstName">
            </div>
            <div class="form-group">
                <label class="form-label">Last Name</label>
                <input type="text" class="form-input" name="lastName">
            </div>
            <div class="form-group">
                <label class="form-label">Email</label>
                <input type="email" class="form-input" name="email">
            </div>
            <div class="form-group">
                <label class="form-label">Phone Number</label>
                <input type="text" class="form-input" name="phoneNumber">
            </div>
            <div class="form-group">
                <label class="form-label">User Type</label>
                <select class="form-input" name="userType">
                    <option value="User">User</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password" class="form-input" name="password">
            </div>
            <button type="submit" class="btn btn-primary">Create User</button>
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
        <p>Are you sure you want to delete this user?</p>
        <div style="margin-top: 1rem; display: flex; gap: 1rem;">
            <button class="btn btn-danger" onclick="confirmDelete()">Delete</button>
            <button class="btn btn-primary" onclick="closeDeleteModal()">Cancel</button>
        </div>
    </div>
</div>

<%@ include file="./partials/footer.jsp"%>