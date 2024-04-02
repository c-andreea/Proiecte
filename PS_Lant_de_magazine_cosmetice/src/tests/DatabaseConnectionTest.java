package tests;

import model.repository.DatabaseHelper;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    public void testConnection() {
        try {
            Connection conn = DatabaseHelper.getConnection();
            assertNotNull(conn, "The database connection should not be null");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Failed to connect to the database";
        }
    }


}
