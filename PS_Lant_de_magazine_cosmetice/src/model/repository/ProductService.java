package model.repository;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {

    public List<Product> getProductsByStoreId(int storeId) {
        List<Product> products = new ArrayList<>();
        // Adjust the query to include ORDER BY clauses for name and price
        String sql = "SELECT p.id, p.name, p.manufacturer, p.price, sp.quantity, sp.availability " +
                "FROM Product p INNER JOIN StoreProduct sp ON p.id = sp.productId " +
                "WHERE sp.storeId = ? ORDER BY p.name ASC, p.price ASC";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, storeId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getBoolean("availability"),
                        rs.getString("manufacturer"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return products;
    }
    public List<Product> getFilteredProductsDynamic(int storeId, String manufacturer, boolean availability, double maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.id, p.name, p.manufacturer, p.price, sp.quantity, sp.availability FROM Product p INNER JOIN StoreProduct sp ON p.id = sp.productId WHERE sp.storeId = ?");

        // Dynamically append conditions based on the criteria
        List<Object> parameters = new ArrayList<>();
        parameters.add(storeId);

        if (manufacturer != null) {
            sql.append(" AND p.manufacturer = ?");
            parameters.add(manufacturer);
        }

        if (availability) {
            sql.append(" AND sp.availability = true");
        }

        if (maxPrice != Double.MAX_VALUE) {
            sql.append(" AND p.price <= ?");
            parameters.add(maxPrice);
        }

        // Add ORDER BY clause here before executing the query
        sql.append(" ORDER BY p.name ASC, p.price ASC");

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getBoolean("availability"),
                        rs.getString("manufacturer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }

        return products;
    }




    public boolean addProduct(Product product, int storeId) {
        String insertProductSql = "INSERT INTO Product (name, price, manufacturer) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertProductSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getManufacturer());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return false; // Insert failed
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    product.setId(productId);
                    return associateProductWithStore(productId, storeId, product);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean associateProductWithStore(int productId, int storeId, Product product) {
        String insertStoreProductSql = "INSERT INTO StoreProduct (storeId, productId, quantity, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertStoreProductSql)) {

            pstmt.setInt(1, storeId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, product.getQuantity());
            pstmt.setBoolean(4, product.isAvailable());
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProductInStore(int productId, int storeId, Product product) {
        String updateProductSql = "UPDATE Product SET price = ?, manufacturer = ? WHERE id = ?";
        String updateStoreProductSql = "UPDATE StoreProduct SET quantity = ?, availability = ? WHERE productId = ? AND storeId = ?";

        Connection conn = null;
        PreparedStatement pstmtUpdateProduct = null;
        PreparedStatement pstmtUpdateStoreProduct = null;

        try {
            conn = DatabaseHelper.getConnection();

            conn.setAutoCommit(false);


            pstmtUpdateProduct = conn.prepareStatement(updateProductSql);
            pstmtUpdateProduct.setDouble(1, product.getPrice());
            pstmtUpdateProduct.setString(2, product.getManufacturer());
            pstmtUpdateProduct.setInt(3, productId);
            int productUpdateCount = pstmtUpdateProduct.executeUpdate();


            pstmtUpdateStoreProduct = conn.prepareStatement(updateStoreProductSql);
            pstmtUpdateStoreProduct.setInt(1, product.getQuantity());
            pstmtUpdateStoreProduct.setBoolean(2, product.isAvailable());
            pstmtUpdateStoreProduct.setInt(3, productId);
            pstmtUpdateStoreProduct.setInt(4, storeId);
            int storeProductUpdateCount = pstmtUpdateStoreProduct.executeUpdate();


            conn.commit();

            return productUpdateCount > 0 && storeProductUpdateCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {

                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {

            try {
                if (pstmtUpdateProduct != null) pstmtUpdateProduct.close();
                if (pstmtUpdateStoreProduct != null) pstmtUpdateStoreProduct.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteProductById(int productId, int storeId) {
        String sql = "DELETE FROM StoreProduct WHERE productId = ? AND storeId = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, storeId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            // This will print the entire stack trace, which includes the SQL error
            e.printStackTrace();
            return false;
        }
    }


    public List<String> getAllManufacturers() {
        List<String> manufacturers = new ArrayList<>();
        String sql = "SELECT DISTINCT manufacturer FROM Product ORDER BY manufacturer ASC";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String manufacturer = rs.getString("manufacturer");
                if (manufacturer != null && !manufacturer.trim().isEmpty()) {
                    manufacturers.add(manufacturer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT p.id, p.name, p.manufacturer, p.price, sp.quantity, sp.availability FROM Product p INNER JOIN StoreProduct sp ON p.id = sp.productId WHERE p.id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getBoolean("availability"),
                        rs.getString("manufacturer")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public List<Product> getAllProductsSortedByNameAndPrice() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.manufacturer, p.price, sp.quantity, sp.availability " +
                "FROM Product p INNER JOIN StoreProduct sp ON p.id = sp.productId " +
                "ORDER BY p.name ASC, p.price ASC";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getBoolean("availability"),
                        rs.getString("manufacturer"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions or throw a custom exception
        }
        return products;
    }

    public List<Product> getFilteredProductsDynamic(String manufacturer, Boolean availability, Double maxPrice) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT p.id, p.name, p.manufacturer, p.price, sp.quantity, sp.availability, s.name AS storeName FROM Product p INNER JOIN StoreProduct sp ON p.id = sp.productId INNER JOIN Store s ON sp.storeId = s.id");

        List<Object> parameters = new ArrayList<>();

        if (manufacturer != null && !manufacturer.equals("All")) {
            sql.append(" AND p.manufacturer = ?");
            parameters.add(manufacturer);
        }

        if (availability != null) {
            sql.append(" AND sp.availability = ?");
            parameters.add(availability ? 1 : 0); // Assuming your DB uses 1 for true
        }

        if (maxPrice != null && maxPrice != Double.MAX_VALUE) {
            sql.append(" AND p.price <= ?");
            parameters.add(maxPrice);
        }

        sql.append(" ORDER BY p.name ASC, p.price ASC");

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getBoolean("availability"),
                            rs.getString("manufacturer")
                            // Not including storeName here since Product doesn't support it
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
    public Map<Integer, String> fetchProductIdToStoreNameMap() {
        Map<Integer, String> productIdToStoreName = new HashMap<>();
        String query = """
                   SELECT sp.productId, s.name AS storeName
                   FROM StoreProduct sp
                   JOIN Store s ON sp.storeId = s.id
                   """;
        // Assuming DatabaseHelper.getConnection() statically provides a connection
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("productId");
                String storeName = rs.getString("storeName");
                productIdToStoreName.put(productId, storeName);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching product-store mapping: " + e.getMessage());
            e.printStackTrace();
        }
        return productIdToStoreName;
    }

}





