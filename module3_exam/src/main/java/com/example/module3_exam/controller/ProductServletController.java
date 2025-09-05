package com.example.module3_exam.controller;

import com.example.module3_exam.model.Product;
import com.example.module3_exam.service.IProductService;
import com.example.module3_exam.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServletController extends HttpServlet {
    private final IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            default:
                listProducts(request, response);
        }
    }

    // ====== CREATE FORM ======
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/product/create.jsp").forward(request, response);
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
            default:
                listProducts(request, response);
        }
    }

    // ====== CREATE ======
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            return;
        }
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        BigDecimal discount = new BigDecimal(request.getParameter("discount"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        Product product = new Product(Integer.parseInt(id), name, price, discount, stock);
        productService.save(product);
        response.sendRedirect("/products");
    }


    // ====== LIST ======
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/view/product/list.jsp").forward(request, response);
    }

}
