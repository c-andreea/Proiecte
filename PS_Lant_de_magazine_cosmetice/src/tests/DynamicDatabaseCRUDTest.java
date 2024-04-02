package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class) // Ensure tests are run in order
public class DynamicDatabaseCRUDTest {
    private static final String DATABASE_NAME = "test_db_" + System.currentTimeMillis(); // Unique database name
    private static Connection conn;
    private static int lastInsertedId; // Declare lastInsertedId

    @BeforeAll
    public static void setup() throws Exception {
        // Connect to MySQL server without specifying a database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=");
        try (Statement stmt = conn.createStatement()) {
            // Create a new database for testing
            stmt.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
        }
        // Reconnect to the specific test database
        conn.close();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?user=root&password=");
        try (Statement stmt = conn.createStatement()) {
            // Create a table in the new test database
            String sqlCreate = "CREATE TABLE IF NOT EXISTS test_users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT)";
            stmt.executeUpdate(sqlCreate);
        }
    }

    @Test
    @Order(1)
    public void testCreate() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate("INSERT INTO test_users (name, age) VALUES ('Test User', 30)", Statement.RETURN_GENERATED_KEYS);
            assertTrue(affectedRows > 0, "No rows affected, item not inserted.");
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lastInsertedId = generatedKeys.getInt(1);
                } else {
                    fail("Failed to retrieve ID of inserted item.");
                }
            }
        }
    }

    @Test
    @Order(2)
    public void testRead() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT name, age FROM test_users WHERE id = " + lastInsertedId);
            if (rs.next()) {
                assertEquals("Test User", rs.getString("name"));
                assertEquals(30, rs.getInt("age"));
            } else {
                fail("Inserted item not found.");
            }
        }
    }

    // Update Test: Modifies the data of the inserted record
    @Test
    @Order(3)
    public void testUpdate() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate("UPDATE test_users SET name = 'Updated User', age = 35 WHERE id = " + lastInsertedId);
            assertTrue(affectedRows > 0, "No rows affected, item not updated.");

            // Verify update
            ResultSet rs = stmt.executeQuery("SELECT name, age FROM test_users WHERE id = " + lastInsertedId);
            if (rs.next()) {
                assertEquals("Updated User", rs.getString("name"));
                assertEquals(35, rs.getInt("age"));
            } else {
                fail("Updated item not found.");
            }
        }
    }

    // Delete Test: Removes the inserted record from the database
    @Test
    @Order(4)
    public void testDelete() throws Exception {
        try (Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate("DELETE FROM test_users WHERE id = " + lastInsertedId);
            assertTrue(affectedRows > 0, "No rows affected, item not deleted.");

            // Verify deletion
            ResultSet rs = stmt.executeQuery("SELECT name, age FROM test_users WHERE id = " + lastInsertedId);
            assertFalse(rs.next(), "Item was not deleted.");
        }
    }



    @AfterAll
    public static void cleanup() throws Exception {
        // Close the connection to the test database
        if (conn != null) {
            conn.close();
        }

        // Connect to MySQL server without specifying a database to be able to drop the test database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=");
             Statement stmt = conn.createStatement()) {
            // Drop the test database
            stmt.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE_NAME);
        }
    }
}
