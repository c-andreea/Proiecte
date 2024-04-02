package tests;

import model.Product;
import model.repository.ProductService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/test_db_1711695765102";
    private static final String DATABASE_NAME = "test_db_1711695656649"; // Extract the database name
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    private static ProductService productService;
    private static Connection conn;
    private static int testStoreId = 1; // Assuming this store ID exists in your test database
    private static int lastInsertedProductId;

    @BeforeAll
    public static void setup() throws Exception {
        // Only one connection and ProductService initialization
        conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        productService = new ProductService();

        // Create the Store table if it doesn't exist and insert a test store
        try (Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Store (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL)";
            stmt.executeUpdate(createTableSQL); // Ensure the table is created

            stmt.executeUpdate("INSERT IGNORE INTO Store (id, name) VALUES (" + testStoreId + ", 'Test Store')");
        }
    }



    @Test
    @Order(1)
    public void testAddProduct() throws SQLException {
        Product newProduct = new Product("Test Product", 10.99, 5, true, "Test Manufacturer");
        boolean isAdded = productService.addProduct(newProduct, testStoreId);
        assertTrue(isAdded, "Product should be added successfully");

        // Assuming ProductService.addProduct sets the ID of the product after successful insertion
        lastInsertedProductId = newProduct.getId();
        assertTrue(lastInsertedProductId > 0, "Product ID should be set");
    }

    @Test
    @Order(2)
    public void testGetProductsByStoreId() {
        List<Product> products = productService.getProductsByStoreId(testStoreId);
        assertFalse(products.isEmpty(), "Should retrieve at least one product for the test store");
        assertTrue(products.stream().anyMatch(p -> p.getId() == lastInsertedProductId), "The newly added product should be among the retrieved products");
    }

    @Test
    @Order(3)
    public void testUpdateProductInStore() {
        Product updatedProduct = new Product(lastInsertedProductId, "Updated Product", 12.99, 10, false, "Updated Manufacturer");
        boolean isUpdated = productService.updateProductInStore(lastInsertedProductId, testStoreId, updatedProduct);
        assertTrue(isUpdated, "Product should be updated successfully");
    }

    @Test
    @Order(4)
    public void testDeleteProductById() {
        boolean isDeleted = productService.deleteProductById(lastInsertedProductId, testStoreId);
        assertTrue(isDeleted, "Product should be deleted successfully");
    }

    @AfterAll
    public static void cleanupAndDropDatabase() throws SQLException {
        // Attempt to close the current connection to the test database
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Connect to the MySQL server without specifying a database
        String serverUrl = "jdbc:mysql://localhost:3306?user=root&password=";
        try (Connection serverConn = DriverManager.getConnection(serverUrl);
             Statement stmt = serverConn.createStatement()) {
            // Drop the test database
            stmt.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE_NAME);
        }
    }
}
