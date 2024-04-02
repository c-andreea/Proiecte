package presenter;

import model.Product;
import model.repository.ProductService;
import view.IManagerView;

import java.util.List;
import java.util.Map;

public class ManagerPresenter {
    private final IManagerView view;
    private final ProductService productService;

    public ManagerPresenter(IManagerView view, ProductService productService) {
        this.view = view;
        this.productService = productService;
        this.view.setPresenter(this);
        displayProductsSortedByNameAndPrice();
    }
    public void displayProductsSortedByNameAndPrice() {
        List<Product> products = productService.getAllProductsSortedByNameAndPrice();
        Map<Integer, String> productIdToStoreName = productService.fetchProductIdToStoreNameMap();
        view.clearProductsTable();
        for (Product product : products) {
            String storeName = productIdToStoreName.getOrDefault(product.getId(), "No Store");
            view.setProductToTableWithStoreName(product.getName(), String.format("%.2f", product.getPrice()),
                    product.isAvailable() ? "Yes" : "No", product.getManufacturer(), storeName);
        }
    }

    public void loadFilteredProducts(String manufacturer, Boolean availability, Double maxPrice) {
        // If "All" is selected, pass null to fetch all manufacturers
        manufacturer = "All".equals(manufacturer) ? null : manufacturer;
        List<Product> filteredProducts = productService.getFilteredProductsDynamic(manufacturer, availability, maxPrice);
        Map<Integer, String> productIdToStoreName = productService.fetchProductIdToStoreNameMap();

        view.clearProductsTable();
        for (Product product : filteredProducts) {
            String storeName = productIdToStoreName.getOrDefault(product.getId(), "No Store");
            view.setProductToTableWithStoreName(product.getName(), String.format("%.2f", product.getPrice()),
                    product.isAvailable() ? "Yes" : "No", product.getManufacturer(), storeName);
        }
    }


    public void loadManufacturers() {
        List<String> manufacturers = productService.getAllManufacturers();
        manufacturers.add(0, "All");
        view.populateManufacturers(manufacturers);
    }
    public void reloadProducts() {
        displayProductsSortedByNameAndPrice();
        // If you have filters implemented, apply them here before or after reloading products
    }



}
