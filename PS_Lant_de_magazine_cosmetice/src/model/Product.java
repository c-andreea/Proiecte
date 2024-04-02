package model;

public class Product {
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    private int id;
    private String name;
    private final double price;
    private final boolean available;
    private final int quantity;
    private final String manufacturer;


    public Product( int id,String name, Double price, double quantity, boolean available, String manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = (int) quantity;
        this.available = available;
        this.manufacturer = manufacturer;
    }

    public Product(String name, double price, double quantity, boolean available, String manufacturer) {

        this.name = name;
        this.price = price;
        this.available = available;
        this.quantity = (int)quantity;
        this.manufacturer = manufacturer;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public int getQuantity() { return quantity; }

    public String getManufacturer() {
        return manufacturer;
    }



}
