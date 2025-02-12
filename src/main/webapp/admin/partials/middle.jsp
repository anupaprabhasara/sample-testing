</head>
<body>
    <nav class="navbar">
        <div class="container navbar-content">
            <div class="nav-links">
                <p class="nav-link userButton" onclick="window.location.href='user';">Dashboard</p>
                <p class="nav-link productButton" onclick="window.location.href='product';">Product</p>
                <p class="nav-link orderitemButton" onclick="window.location.href='orderItem';">Order Item</p>
            </div>
            <div class="user-profile">
                <span class="admin-name"><%= firstName %> <%= lastName %></span>
                <button class="btn btn-danger" onclick="window.location.href='?action=logout';">Logout</button>
            </div>
        </div>
    </nav>