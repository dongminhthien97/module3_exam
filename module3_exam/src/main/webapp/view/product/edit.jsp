<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chỉnh sửa sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

<h2 class="mb-3">Chỉnh sửa sản phẩm</h2>

<form action="/products" method="post" class="row g-3" onsubmit="return validateForm()">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="productId" value="${product.productId}">

    <div class="col-md-6">
        <label class="form-label">Tên hàng hoá</label>
        <input type="text" name="productName" class="form-control" value="${product.productName}" required>
    </div>

    <div class="col-md-3">
        <label class="form-label">Đơn vị tính</label>
        <input type="text" name="unit" class="form-control" value="${product.unit}" required>
    </div>

    <div class="col-md-3">
        <label class="form-label">Giá</label>
        <input type="number" name="price" class="form-control" value="${product.price}" min="1000" required>
        <div class="form-text text-danger">Giá phải ≥ 1000 VNĐ</div>
    </div>

    <div class="col-md-4">
        <label class="form-label">Loại hàng hoá</label>
        <select name="categoryId" class="form-select" required>
            <c:forEach var="c" items="${categories}">
                <option value="${c.categoryId}"
                        <c:if test="${c.categoryId == product.category.categoryId}">selected</c:if>>
                        ${c.categoryName}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="col-md-4">
        <label class="form-label">Ngày thu hoạch</label>
        <input type="date" name="harvestDate" class="form-control"
               value="${product.harvestDate}" required>
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="/products" class="btn btn-secondary">Quay lại</a>
    </div>
</form>

<script>
    function validateForm() {
        let productName = document.querySelector("input[name='productName']").value.trim();
        let unit = document.querySelector("input[name='unit']").value.trim();
        let price = document.querySelector("input[name='price']").value.trim();
        let categoryId = document.querySelector("select[name='categoryId']").value;
        let harvestDate = document.querySelector("input[name='harvestDate']").value;

        // Validate tên
        if (productName === "" || productName.length < 2) {
            alert("Tên hàng hóa không được để trống và phải có ít nhất 2 ký tự.");
            return false;
        }

        // Validate đơn vị tính
        if (unit === "") {
            alert("Đơn vị tính không được để trống.");
            return false;
        }

        // Validate giá
        if (price === "" || isNaN(price) || parseInt(price) < 1000) {
            alert("Giá phải là số hợp lệ và ≥ 1000 VNĐ.");
            return false;
        }

        // Validate loại hàng hóa
        if (categoryId === "") {
            alert("Vui lòng chọn loại hàng hóa.");
            return false;
        }

        // Validate ngày thu hoạch
        if (harvestDate === "") {
            alert("Vui lòng chọn ngày thu hoạch.");
            return false;
        } else {
            let today = new Date().toISOString().split("T")[0];
            if (harvestDate > today) {
                alert("Ngày thu hoạch không được lớn hơn ngày hiện tại.");
                return false;
            }
        }

        return true;
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
