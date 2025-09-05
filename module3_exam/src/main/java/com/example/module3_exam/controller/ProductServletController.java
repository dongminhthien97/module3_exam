package com.example.module3_exam.controller;

import com.example.module3_exam.dto.ProductDTO;
import com.example.module3_exam.model.Category;
import com.example.module3_exam.model.Product;
import com.example.module3_exam.service.CategoryService;
import com.example.module3_exam.service.ICategoryService;
import com.example.module3_exam.service.IProductService;
import com.example.module3_exam.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServletController extends HttpServlet {
    private final IProductService productService = new ProductService();
    private final ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    // ====== CREATE FORM ======
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/view/product/create.jsp").forward(request, response);
    }

    // ====== EDIT FORM ======
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        Product product = productService.findById(productId);
        List<Category> categories = categoryService.findAll();

        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/view/product/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    // ====== CREATE ======
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("productId");
        String name = request.getParameter("productName");
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String harvestDateStr = request.getParameter("harvestDate");

        LocalDate harvestDate = null;
        if (harvestDateStr != null && !harvestDateStr.isEmpty()) {
            harvestDate = LocalDate.parse(harvestDateStr);
        }

        Category category = categoryService.findById(categoryId);
        Product product = new Product(id, name, unit, price, category, harvestDate);

        productService.save(product);
        response.sendRedirect("/products");
    }

    // ====== UPDATE ======
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("productId");
        String name = request.getParameter("productName");
        String unit = request.getParameter("unit");
        int price = Integer.parseInt(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        LocalDate harvestDate = LocalDate.parse(request.getParameter("harvestDate"));

        Category category = categoryService.findById(categoryId);
        Product product = new Product(id, name, unit, price, category, harvestDate);

        productService.update(id, product);
        response.sendRedirect("/products");
    }

    // ====== DELETE ======
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String productId = request.getParameter("id");
        productService.delete(productId);
        response.sendRedirect("/products");
    }

    // ====== LIST (có phân trang) ======
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int size = 3;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam.trim()); // fix lỗi khoảng trắng
            } catch (NumberFormatException e) {
                page = 1; // fallback về page 1 nếu parse lỗi
            }
        }

        List<ProductDTO> products = productService.findPaginatedDTO(page, size);
        List<Category> categories = categoryService.findAll();

        int totalItems = productService.countAll();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/view/product/list.jsp").forward(request, response);
    }

    // ====== SEARCH (có phân trang) ======
    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String categoryId = request.getParameter("categoryId");

        int page = 1;
        int size = 3;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam.trim());
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<ProductDTO> products = productService.searchPaginatedDTO(name, categoryId, page, size);
        List<Category> categories = categoryService.findAll();

        int totalItems = productService.countSearchDTO(name, categoryId);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // giữ lại giá trị đã nhập khi search
        request.setAttribute("searchName", name);
        request.setAttribute("searchCategoryId", categoryId);

        request.getRequestDispatcher("/view/product/list.jsp").forward(request, response);
    }
}
