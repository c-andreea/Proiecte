package model;

public class Store {
    private String storeID;
    private String name;
    private String address;

    public Store(String storeID, String name, String address) {
        this.storeID = storeID;
        this.name = name;
        this.address = address;
    }




    public String getName() {
        return name;
    }


}
