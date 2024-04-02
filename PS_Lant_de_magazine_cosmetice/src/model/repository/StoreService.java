package model.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StoreService {

    public Map<Integer, String> getStores() {
        Map<Integer, String> stores = new LinkedHashMap<>();
        String sql = "SELECT id, name FROM Store";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                stores.put(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }
}
