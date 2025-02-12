<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <link rel="shortcut icon" href="./assets/cogwheel.png" type="image/x-icon">
    <link rel="stylesheet" href="./css/styles.css">
</head>
<body>
    <div class="login-container">
        <form class="login-form" method="POST" action="<%= request.getContextPath() + "/admin/login" %>">
            <h1 style="margin-bottom: 1.5rem;">Admin Login</h1>
            <div class="form-group">
                <label class="form-label">Email</label>
                <input type="email" class="form-input" name="email" required>
            </div>
            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password" class="form-input" name="password" required>
            </div>
            <!-- Error Message Display -->
            <c:if test="${not empty error}">
                <div class="error-message" style="color: red; margin-bottom: 1rem;">
                    ${error}
                </div>
            </c:if>
            <button type="submit" class="btn btn-primary" style="width: 100%;">Login</button>
        </form>
    </div>

    <script src="./script/script.js"></script>
</body>
</html>