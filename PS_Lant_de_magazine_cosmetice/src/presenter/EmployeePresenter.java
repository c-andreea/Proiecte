package presenter;

import model.Product;
import model.User;
import model.repository.ProductService;
import model.repository.StoreService;
import view.IEmployeeView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePresenter {

    private final IEmployeeView view;
    private final ProductService productService;
    private final StoreService storeService;
    private final User loggedInUser;
    private Map<String, Integer> productNameToIdMap = new HashMap<>();
    public EmployeePresenter(IEmployeeView view, ProductService productService, StoreService storeService, User loggedInUser) {
        this.view = view;
        this.productService = productService;
        this.storeService = storeService;
        this.loggedInUser = loggedInUser;
        initialize();
    }

    private void initialize() {
        SwingUtilities.invokeLater(() -> {
            initStoreData();
            setManufacturers();
            loadInitialProductData();
            loadProductsForStore();
        });
    }
    public void loadInitialProductData() {
        List<Product> products = productService.getProductsByStoreId(loggedInUser.getStoreId());
        updateProductNameToIdMap(products);
        updateProductNamesDropdown();
    }


    public void loadProductsForStore() {
        List<Product> products = productService.getProductsByStoreId(loggedInUser.getStoreId());
        DefaultTableModel model = createProductTableModel(products);
        view.displayProducts(model);

    }
    public void onStoreSelected(int storeId) {
        if (storeId > 0) {
            List<Product> storeProducts = productService.getProductsByStoreId(storeId);
            DefaultTableModel model = createProductTableModel(storeProducts);
            view.displayStoreSpecificInfo(model);
        } else {

        }
    }


    public void loadFilteredProducts() {
        String manufacturer = view.getSelectedManufacturer().equals("All") ? null : view.getSelectedManufacturer();
        boolean availability = view.getSelectedAvailability();
        double maxPrice = parseMaxPrice(view.getMaxPriceText());

        List<Product> filteredProducts = productService.getFilteredProductsDynamic(loggedInUser.getStoreId(), manufacturer, availability, maxPrice);


        DefaultTableModel model = createProductTableModel(filteredProducts);
        view.displayProducts(model);


        updateProductNameToIdMap(filteredProducts);
        updateProductNamesDropdown();
    }
    private double parseMaxPrice(String maxPriceText) {
        try {
            return Double.parseDouble(maxPriceText);
        } catch (NumberFormatException e) {
            return Double.MAX_VALUE;
        }
    }
    private void updateProductNameToIdMap(List<Product> products) {
        productNameToIdMap.clear();
        for (Product product : products) {
            productNameToIdMap.put(product.getName(), product.getId());
        }
    }

    private void updateProductNamesDropdown() {
        SwingUtilities.invokeLater(() -> {
            view.clearProductNamesComboBox();
            view.addProductNameToComboBox("Select");
            productNameToIdMap.keySet().forEach(view::addProductNameToComboBox);
        });
    }


    public void initStoreData() {
        Map<Integer, String> stores = storeService.getStores();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        stores.forEach((id, name) -> model.addElement(id + " - " + name));
        view.setStoreComboBoxModel(model);
    }

    public void setManufacturers() {
        List<String> manufacturers = productService.getAllManufacturers();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("All");
        manufacturers.forEach(model::addElement);
        view.setManufacturersComboBoxModel(model);
    }

    private DefaultTableModel createProductTableModel(List<Product> products) {
        String[] columnNames = { "Name", "Manufacturer", "Price", "Availability"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        products.forEach(product -> model.addRow(new Object[]{
                //product.getId(),
                product.getName(),
                product.getManufacturer(),
                product.getPrice(),
                product.isAvailable() ? "Yes" : "No"
        }));
        return model;
    }
    public void handleStoreSelection() {
        String selectedItem = view.getSelectedStoreItem();
        int storeId = -1;

        try {
            if (!selectedItem.isEmpty()) {
                String[] parts = selectedItem.split(" - ");
                storeId = Integer.parseInt(parts[0]);
            }
        } catch (Exception e) {
            view.showError("Invalid store selection.");
            return;
        }

        if (storeId > 0) {
            onStoreSelected(storeId);
        } else {
            view.showError("Please select a store.");
        }
    }
    public void addProduct() {

        int storeId = loggedInUser.getStoreId();

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JCheckBox availableCheckBox = new JCheckBox();
        JTextField manufacturerField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Price:", priceField,
                "Quantity:", quantityField,
                "Available:", availableCheckBox,
                "Manufacturer:", manufacturerField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                double quantity = Double.parseDouble(quantityField.getText());
                boolean available = availableCheckBox.isSelected();
                String manufacturer = manufacturerField.getText();

                Product newProduct = new Product(name, price, quantity, available, manufacturer);

                productService.addProduct(newProduct, storeId);

                loadFilteredProducts();
            } catch (NumberFormatException e) {
                view.showError("Invalid input format.");
            }
        }
    }
    public void deleteSelectedProduct() {
        String selectedProductName = view.getSelectedProductName();
        if (selectedProductName != null && !selectedProductName.isEmpty()) {
            Integer productId = productNameToIdMap.get(selectedProductName);
            int storeId = loggedInUser.getStoreId();
            if (productId != null && productService.deleteProductById(productId, storeId)) {
                loadFilteredProducts();
            } else {
                view.showError("Failed to delete product.");
            }
        } else {
            view.showError("Please select a product to delete.");
        }
    }
    public void updateSelectedProduct() {
        String selectedProductName = view.getSelectedProductName();
        if (selectedProductName == null || selectedProductName.isEmpty() || selectedProductName.equals("Select")) {
            view.showError("Please select a product to update.");
            return;
        }

        Integer productId = productNameToIdMap.get(selectedProductName);
        if (productId == null) {
            view.showError("Product not found.");
            return;
        }

        double price = view.getProductPrice();
        int quantity = view.getProductQuantity();
        boolean isAvailable = view.getProductAvailability();
        String manufacturer = view.getProductManufacturer();


        int storeId = loggedInUser.getStoreId();


        boolean success = productService.updateProductInStore(productId, storeId, new Product(selectedProductName, price, quantity, isAvailable, manufacturer));

        if (success) {
            loadFilteredProducts();
        } else {
            view.showError("Failed to update product.");
        }
    }


    public void prepareUpdateFields(String selectedProductName) {
        SwingUtilities.invokeLater(() -> {
            try {
                Integer productId = productNameToIdMap.get(selectedProductName);
                if (productId == null) {
                    view.showError("Product not selected or not found.");
                    return;
                }


                Product productToUpdate = productService.getProductById(productId);
                if (productToUpdate == null) {
                    view.showError("Product not found.");
                    return;
                }


                view.setUpdatePriceField(String.valueOf(productToUpdate.getPrice()));
                view.setUpdateQuantityField(String.valueOf(productToUpdate.getQuantity()));
                view.setUpdateManufacturerField(productToUpdate.getManufacturer());
                view.setUpdateAvailabilityCheckBox(productToUpdate.isAvailable());

            } catch (Exception e) {
                view.showError("Failed to prepare update fields: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void reloadStoreProducts() {
        int storeId = loggedInUser.getStoreId();
        List<Product> products = productService.getProductsByStoreId(storeId);
        DefaultTableModel model = createProductTableModel(products);
        view.displayProducts(model);
    }

    public void productNameSelected(String selectedProductName) {

        if(selectedProductName != null && !selectedProductName.equals("Select")) {
            prepareUpdateFields(selectedProductName);
        } else {

            view.clearUpdateFields();
        }
    }


}