package com.example.module3_exam.repository;


import com.example.module3_exam.model.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection conn = ConnectionDB.getConnectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getBigDecimal("discount"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public List<ProductDTO> findAllDTO() {
//        List<ProductDTO> list = new ArrayList<>();
//        String sql = "SELECT p.product_id, p.product_name, p.unit, p.price, p.harvest_date, " +
//                "c.category_id, c.category_name " +
//                "FROM product p " +
//                "JOIN category c ON p.category_id = c.category_id";
//
//        try (Connection conn = ConnectionDB.getConnectDB();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                ProductDTO dto = new ProductDTO(
//                        rs.getString("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("unit"),
//                        rs.getInt("price"),
//                        rs.getDate("harvest_date").toLocalDate(),
//                        rs.getInt("category_id"),
//                        rs.getString("category_name")
//                );
//                list.add(dto);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO Product (ID, Name, Price, Discount, Stock) VALUES (?,?,?,?,?)";

        try (Connection conn = ConnectionDB.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setBigDecimal(4, product.getDiscount());
            ps.setInt(5,product.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void delete(String productId) {
//        String sql = "DELETE FROM product WHERE product_id = ?";
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, productId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Product findById(String productId) {
//        String sql = "SELECT * FROM product WHERE product_id = ?";
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, productId);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Category c = categoryRepo.findById(rs.getInt("category_id"));
//                return new Product(
//                        rs.getString("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("unit"),
//                        rs.getInt("price"),
//                        c,
//                        rs.getDate("harvest_date").toLocalDate()
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void update(String id, Product product) {
//        String sql = "UPDATE product SET product_name=?, unit=?, price=?, category_id=?, harvest_date=? WHERE product_id=?";
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, product.getProductName());
//            ps.setString(2, product.getUnit());
//            ps.setInt(3, product.getPrice());
//            ps.setInt(4, product.getCategory().getCategoryId());
//            ps.setDate(5, Date.valueOf(product.getHarvestDate()));
//            ps.setString(6, id);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<ProductDTO> searchDTO(String name, String categoryId) {
//        List<ProductDTO> list = new ArrayList<>();
//        StringBuilder sql = new StringBuilder(
//                "SELECT p.product_id, p.product_name, p.unit, p.price, p.harvest_date, " +
//                        "c.category_id, c.category_name " +
//                        "FROM product p JOIN category c ON p.category_id = c.category_id WHERE 1=1"
//        );
//
//        if (name != null && !name.trim().isEmpty()) {
//            sql.append(" AND p.product_name LIKE ?");
//        }
//        if (categoryId != null && !categoryId.trim().isEmpty()) {
//            sql.append(" AND c.category_id = ?");
//        }
//
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
//
//            int idx = 1;
//            if (name != null && !name.trim().isEmpty()) {
//                ps.setString(idx++, "%" + name + "%");
//            }
//            if (categoryId != null && !categoryId.trim().isEmpty()) {
//                ps.setInt(idx, Integer.parseInt(categoryId));
//            }
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                ProductDTO dto = new ProductDTO(
//                        rs.getString("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("unit"),
//                        rs.getInt("price"),
//                        rs.getDate("harvest_date").toLocalDate(),
//                        rs.getInt("category_id"),
//                        rs.getString("category_name")
//                );
//                list.add(dto);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    @Override
//    public List<ProductDTO> findPaginatedDTO(int page, int size) {
//        List<ProductDTO> list = new ArrayList<>();
//        String sql = "SELECT p.product_id, p.product_name, p.unit, p.price, p.harvest_date, " +
//                "c.category_id, c.category_name " +
//                "FROM product p JOIN category c ON p.category_id = c.category_id " +
//                "LIMIT ? OFFSET ?";
//
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, size);
//            ps.setInt(2, (page - 1) * size);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new ProductDTO(
//                        rs.getString("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("unit"),
//                        rs.getInt("price"),
//                        rs.getDate("harvest_date").toLocalDate(),
//                        rs.getInt("category_id"),
//                        rs.getString("category_name")
//                ));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    @Override
//    public int countAll() {
//        String sql = "SELECT COUNT(*) FROM product";
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//
//    @Override
//    public List<ProductDTO> searchPaginatedDTO(String name, String categoryId, int page, int size) {
//        List<ProductDTO> list = new ArrayList<>();
//        StringBuilder sql = new StringBuilder(
//                "SELECT p.product_id, p.product_name, p.unit, p.price, p.harvest_date, " +
//                        "c.category_id, c.category_name " +
//                        "FROM product p JOIN category c ON p.category_id = c.category_id WHERE 1=1"
//        );
//
//        if (name != null && !name.trim().isEmpty()) {
//            sql.append(" AND p.product_name LIKE ?");
//        }
//        if (categoryId != null && !categoryId.trim().isEmpty()) {
//            sql.append(" AND c.category_id = ?");
//        }
//        sql.append(" LIMIT ? OFFSET ?");
//
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
//
//            int idx = 1;
//            if (name != null && !name.trim().isEmpty()) {
//                ps.setString(idx++, "%" + name + "%");
//            }
//            if (categoryId != null && !categoryId.trim().isEmpty()) {
//                ps.setInt(idx++, Integer.parseInt(categoryId));
//            }
//            ps.setInt(idx++, size);
//            ps.setInt(idx, (page - 1) * size);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new ProductDTO(
//                        rs.getString("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("unit"),
//                        rs.getInt("price"),
//                        rs.getDate("harvest_date").toLocalDate(),
//                        rs.getInt("category_id"),
//                        rs.getString("category_name")
//                ));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    @Override
//    public int countSearchDTO(String name, String categoryId) {
//        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product p JOIN category c ON p.category_id = c.category_id WHERE 1=1");
//
//        if (name != null && !name.trim().isEmpty()) {
//            sql.append(" AND p.product_name LIKE ?");
//        }
//        if (categoryId != null && !categoryId.trim().isEmpty()) {
//            sql.append(" AND c.category_id = ?");
//        }
//
//        try (Connection conn = ConnectionDB.getConnectDB();
//             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
//
//            int idx = 1;
//            if (name != null && !name.trim().isEmpty()) {
//                ps.setString(idx++, "%" + name + "%");
//            }
//            if (categoryId != null && !categoryId.trim().isEmpty()) {
//                ps.setInt(idx, Integer.parseInt(categoryId));
//            }
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
}
