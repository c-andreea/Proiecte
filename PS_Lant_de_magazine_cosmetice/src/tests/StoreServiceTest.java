package tests;

import model.repository.StoreService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreServiceTest {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/test_db_1711695765102";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    private static Connection conn;
    private static StoreService storeService;

    @BeforeAll
    public static void setupClass() throws Exception {
        // Establish connection
        conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        // Initialize StoreService
        storeService = new StoreService();

        // Create Store table only once before all tests
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Store (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))");
        }
    }

    @BeforeEach
    public void setupTest() throws Exception {
        // Insert test data before each test
        try (Statement stmt = conn.createStatement()) {
            // Clear the table to ensure a clean state
            stmt.executeUpdate("DELETE FROM Store");
            // Insert test data
            stmt.executeUpdate("INSERT INTO Store (name) VALUES ('Test Store 1'), ('Test Store 2')");
        }
    }

    @Test
    @Order(1)
    public void testGetStores() {
        Map<Integer, String> stores = storeService.getStores();
        System.out.println("Stores: " + stores); // Debug print
        assertNotNull(stores, "Stores map should not be null");
        assertFalse(stores.isEmpty(), "Stores map should not be empty");
        assertTrue(stores.containsValue("Magazinul Central"), "Stores map should contain 'Test Store 1'");
    }


    @AfterAll
    public static void cleanupClass() throws Exception {
        // Drop the Store table and close connection after all tests
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS Store");
        }

        if (conn != null) {
            conn.close();
        }
    }
}
