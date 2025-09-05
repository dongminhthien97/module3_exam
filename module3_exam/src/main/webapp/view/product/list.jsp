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

<!-- Form tìm kiếm -->
<form action="/products" method="get" class="row g-3 mb-3">
    <input type="hidden" name="action" value="search">

    <div class="col-md-4">
        <input type="text" name="name" class="form-control"
               placeholder="Nhập tên sản phẩm"
               value="${searchName}">
    </div>

    <div class="col-md-4">
        <select name="categoryId" class="form-select">
            <option value="">-- Chọn loại hàng --</option>
            <c:forEach var="c" items="${categories}">
                <option value="${c.categoryId}"
                        <c:if test="${searchCategoryId == c.categoryId}">selected</c:if>>
                        ${c.categoryName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="col-md-4">
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        <a href="/products?action=create" class="btn btn-success">+ Thêm mới</a>
    </div>
</form>

<!-- Bảng sản phẩm -->
<table class="table table-bordered table-hover">
    <thead class="table-light">
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th>Đơn vị</th>
        <th>Giá (VNĐ)</th>
        <th>Loại</th>
        <th>Ngày thu hoạch</th>
        <th>Sửa</th>
        <th>Xóa</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.productId}</td>
            <td>${p.productName}</td>
            <td>${p.unit}</td>
            <td>${p.price}</td>
            <td>${p.categoryName}</td>
            <td>${p.harvestDate}</td>

            <!-- Cột Sửa -->
            <td>
                <a href="/products?action=edit&id=${p.productId}" class="btn btn-warning btn-sm">Sửa</a>
            </td>

            <!-- Cột Xóa -->
            <td>
                <!-- Nút mở modal -->
                <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                        data-bs-target="#deleteModal${p.productId}">
                    Xóa
                </button>

                <!-- Modal xác nhận xóa -->
                <div class="modal fade" id="deleteModal${p.productId}" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Xác nhận xóa</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                Bạn có chắc chắn muốn xóa sản phẩm
                                <b>${p.productName}</b> không?
                            </div>
                            <div class="modal-footer">
                                <a href="/products?action=delete&id=${p.productId}" class="btn btn-danger">Xóa</a>
                                <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Hủy
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Phân trang -->
<nav>
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <li class="page-item ${i == currentPage ? 'active' : ''}">
                <a class="page-link"
                   href="/products?action=${empty searchName and empty searchCategoryId ? 'list' : 'search'}
                   &page=${i}
                   &name=${searchName}
                   &categoryId=${searchCategoryId}">
                        ${i}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
