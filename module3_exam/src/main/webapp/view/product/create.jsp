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
               placeholder="MHH-XXXX" required
               pattern="MHH-[A-Z0-9]{4}">
        <div class="form-text text-danger">"Tên hàng hóa không được để trống và phải có ít nhất 2 ký tự."</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Tên hàng hóa</label>
        <input type="text" name="productName" class="form-control" required>
        <div class="form-text text-danger">Định dạng: MHH-XXXX (X là số hoặc chữ in hoa)</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Đơn vị tính</label>
        <select name="unit" class="form-select" required>
            <option value="">-- Chọn đơn vị --</option>
            <option value="Kg">Kg</option>
            <option value="Túi">Túi</option>
            <option value="bó">Bó</option>
            <option value="Hộp">Hộp</option>
        </select>
        <div class="form-text text-danger">Vui lòng chọn đơn vị tính.</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Giá (VNĐ)</label>
        <input type="number" name="price" class="form-control"
               min="1000" step="1" required>
        <div class="form-text text-danger">Giá phải ≥ 1000 VNĐ</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Loại hàng hóa</label>
        <select name="categoryId" class="form-select" required>
            <option value="">-- Chọn loại --</option>
            <c:forEach var="c" items="${categories}">
                <option value="${c.categoryId}">${c.categoryName}</option>
            </c:forEach>
        </select>
        <div class="form-text text-danger">Vui lòng chọn loại hàng hóa.</div>
    </div>

    <div class="mb-3">
        <label class="form-label">Ngày thu hoạch</label>
        <input type="date" name="harvestDate" class="form-control" required>
        <div class="form-text text-danger">Vui lòng chọn ngày thu hoạch.</div>
    </div>

    <button type="submit" class="btn btn-primary">Lưu</button>
    <a href="/products" class="btn btn-secondary">Hủy</a>
</form>

<script>
    function validateForm() {
        // Lấy dữ liệu từ form
        let productId = document.querySelector("input[name='productId']").value.trim();
        let productName = document.querySelector("input[name='productName']").value.trim();
        let unit = document.querySelector("select[name='unit']").value;
        let price = document.querySelector("input[name='price']").value.trim();
        let categoryId = document.querySelector("select[name='categoryId']").value;
        let harvestDate = document.querySelector("input[name='harvestDate']").value;

        // Regex kiểm tra mã hàng hóa: MHH-XXXX (X là số hoặc chữ in hoa)
        let productIdPattern = /^MHH-[A-Z0-9]{4}$/;

        // Validate Mã hàng hóa
        if (productId === "" || !productIdPattern.test(productId)) {
            alert("Mã hàng hóa không hợp lệ! Định dạng: MHH-XXXX (X là số hoặc chữ in hoa).");
            return false;
        }

        // Validate Tên hàng hóa
        if (productName === "" || productName.length < 2) {
            alert("Tên hàng hóa không được để trống và phải có ít nhất 2 ký tự.");
            return false;
        }

        // Validate Đơn vị tính
        if (unit === "") {
            alert("Vui lòng chọn đơn vị tính.");
            return false;
        }

        // Validate Giá
        if (price === "" || isNaN(price) || parseInt(price) < 1000) {
            alert("Giá phải là số hợp lệ và ≥ 1000 VNĐ.");
            return false;
        }

        // Validate Loại hàng hóa
        if (categoryId === "") {
            alert("Vui lòng chọn loại hàng hóa.");
            return false;
        }

        // Validate Ngày thu hoạch
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

        return true; // nếu tất cả đều OK
    }
</script>


</body>
</html>
