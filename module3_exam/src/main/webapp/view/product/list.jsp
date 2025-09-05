<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">
<h2 class="mb-3">Danh sách sản phẩm</h2>

<a href="/products?action=create" class="btn btn-primary mb-3">Thêm mới</a>

<!-- Bảng sản phẩm -->
<table class="table table-bordered table-hover">
    <thead class="table-light">
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Price</th>
        <th>Discount</th>
        <th>Stock</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.discount}</td>
            <td>${p.stock}</td>
    </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
