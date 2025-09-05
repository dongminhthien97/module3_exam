<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm sản phẩm</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-4">

<h2 class="mb-3">Thêm sản phẩm mới</h2>

<form action="/products?action=create" method="post" onsubmit="return validateForm()">
    <div class="mb-3">
        <label class="form-label">Mã hàng hóa</label>
        <input type="text" name="productId" class="form-control"
               placeholder="ID" required>
        <div class="form-text text-danger">ID không được để trống hoặc bé hơn 0</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Tên hàng hóa</label>
        <input type="text" name="productName" class="form-control" required>
        <div class="form-text text-danger">Vui lòng nhập tên sản phẩm</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Price</label>
        <input type="number" name="price" class="form-control"
               min="100" step="1" required>
        <div class="form-text text-danger">Vui lòng nhập giá</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Discount</label>
        <select name="discount" class="form-select" required>
            <option value="">----</option>
            <option>5%</option>
            <option>10%</option>
            <option>15%</option>
            <option>20%</option>
        </select>
        <div class="form-text text-danger">Vui lòng chọn giá trị giảm giá</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Stock</label>
        <input type="number" name="stock" class="form-control"
               min="10" step="1" required>
        <div class="form-text text-danger">Giá trị phải lớn hơn 10</div>
    </div>
    <button type="submit" class="btn btn-primary">Thêm mới</button>
    <a href="/products" class="btn btn-secondary">Hủy</a>
</form>

<script>
    function validateForm() {
        // Lấy dữ liệu từ form
        let productId = document.querySelector("input[name='productId']").value.trim();
        let productName = document.querySelector("input[name='productName']").value.trim();
        let price = document.querySelector("input[name='price']").value;
        let discount = document.querySelector("select[name='discount']").value;
        let stock = document.querySelector("input[name='stock']").value;

        // Validate Mã hàng hóa
        if (productId === "" || productId.length < 0) {
            alert("Mã hàng hóa không được để trống");
            return false;
        }


        // Validate Tên hàng hóa
        if (productName === "" || productName.length < 2) {
            alert("Tên hàng hóa không được để trống và phải có ít nhất 2 ký tự.");
            return false;
        }

        // Validate Giá
        if (price === "" || isNaN(price) || parseInt(price) <
            100) {
            alert("Giá phải là số hợp lệ và ≥ 100.");
            return false;
        }

        // Validate Giảm giá
        if (discount === "") {
            alert("Vui lòng chọn giá trị giảm giá.");
            return false;
        }


        // Validate Stock
        if (stock === "" || isNaN(stock) || parseInt(stock) <
            10) {
            alert("Stock phải là số hợp lệ và ≥ 10.");
            return false;
        }
        return true; // nếu tất cả đều OK
    }
</script>
</body>
</html>
