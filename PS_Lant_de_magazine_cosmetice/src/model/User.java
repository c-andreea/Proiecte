package model;

public class User {
    private String password;
    private int userId;
    private String name;
    private String email;
    private String role;
    private int storeId;
    public void setPassword(String password) {
        this.password = password;
    }



    public void setRole(String role) {
        this.role = role;
    }

    public User(int userId, String name, String email, String role, int storeId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.storeId = storeId;
    }
    public User(int userId, String name, String email, String role, int storeId, String password) {
        this.userId =userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.storeId = storeId;
        this.password=password;
    }

    // Getteri
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public int getStoreId() { return storeId; }
    public void setName(String name) { // Add setName method
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String mail) {
        this.email=mail;
    }

    public void setStoreId(int i) {
        this.storeId=i;
    }
}
