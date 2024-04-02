package tests;

import model.User;
import model.repository.UserService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/test_db_1711695765102";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    private static Connection conn;
    private static UserService userService;

    @BeforeAll
    public static void setup() throws Exception {
        conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        userService = new UserService();

        // Setup the Users table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Users");
            stmt.execute("CREATE TABLE Users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "role VARCHAR(50), " +
                    "storeId INT, " +
                    "passwordHash VARCHAR(255))");

            // Insert a user with a 'hashed' password for testing. In a real scenario, use an actual hash function.
            String[] insertStatements = new String[]{
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Andrei Popescu', 'andrei.popescu@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Ioana Ionescu', 'ioana.ionescu@example.com', 'Manager', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Mihai Stoica', 'mihai.stoica@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Elena Dumitru', 'elena.dumitru@example.com', 'Administrator', NULL, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Alexandru Stan', 'alexandru.stan@example.com', 'Manager', 3, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Gabriela Dobre', 'gabriela.dobre@example.com', 'Employee', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Radu Marin', 'radu.marin@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Maria Popescu', 'maria.popescu@example.com', 'Manager', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Bogdan Ionescu', 'bogdan.ionescu@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Cristina Stoica', 'cristina.stoica@example.com', 'Administrator', NULL, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Andrei Dumitru', 'andrei.dumitru@example.com', 'Manager', 3, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Ioana Stan', 'ioana.stan@example.com', 'Employee', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Mihai Dobre', 'mihai.dobre@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Elena Marin', 'elena.marin@example.com', 'Manager', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Alexandru Popescu', 'alexandru.popescu@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Gabriela Ionescu', 'gabriela.ionescu@example.com', 'Administrator', NULL, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Radu Stoica', 'radu.stoica@example.com', 'Manager', 3, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Maria Dumitru', 'maria.dumitru@example.com', 'Employee', 2, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Bogdan Stan', 'bogdan.stan@example.com', 'Employee', 1, 'abcd1234')",
                    "INSERT INTO Users (name, email, role, storeId, passwordHash) VALUES ('Cristina Dobre', 'cristina.dobre@example.com', 'Manager', 2, 'abcd1234')"
            };}
    }

    @Test
    @Order(1)
    public void testCreateUser() {
        User newUser = new User(3, "Jane Doe", "aaajae@example.com", "Employee", 1, "hashed_password456");
        assertTrue(userService.createUser(newUser), "User should be successfully created");
    }

    @Test
    @Order(2)
    public void testGetAllUsers() {
        assertFalse(userService.getAllUsers().isEmpty(), "User list should not be empty");
    }

    @Test
    @Order(3)
    public void testAuthenticate() {
        // Ensure to use the correct password hash. In a real test, compare against an actual hash.
        User user = userService.authenticate("john@example.com", "hashed_password123");
        assertNotNull(user, "Authentication should be successful");
        assertEquals("John Doe", user.getName(), "Authenticated user should have the correct name");
    }

    @Test
    @Order(4)
    public void testUpdateUser() {
        // Fetch a user to update. Assuming there's at least one user present.
        User userToUpdate = userService.getAllUsers().get(0);
        userToUpdate.setName("Updated Name");
        assertTrue(userService.updateUser(userToUpdate), "User update should be successful");
    }

    @Test
    @Order(5)
    public void testDeleteUser() {
        // Assuming there's at least one user to delete.
        User userToDelete = userService.getAllUsers().get(0);
        assertTrue(userService.deleteUser(userToDelete.getUserId()), "User deletion should be successful");
    }

    @AfterAll
    public static void cleanup() throws Exception {
        // Clean up the Users table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS Users");
        }
        if (conn != null) {
            conn.close();
        }
    }
}
