package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface IEmployeeView {
    String getSelectedStoreItem();

    void displayStoreSpecificInfo(DefaultTableModel model);

    void setStoreComboBoxModel(DefaultComboBoxModel<String> model);

    void setManufacturersComboBoxModel(DefaultComboBoxModel<String> model);

    void displayProducts(DefaultTableModel model);

    void showError(String message);

    String getSelectedManufacturer();

    boolean getSelectedAvailability();

    String getMaxPriceText();

    String getSelectedProductName();
    void clearProductNamesComboBox();
    void addProductNameToComboBox(String name);

    double getProductPrice();
    int getProductQuantity();
    boolean getProductAvailability();
    String getProductManufacturer();
    void setUpdatePriceField(String price);
    void setUpdateQuantityField(String quantity);
    void setUpdateAvailabilityCheckBox(boolean available);
    void setUpdateManufacturerField(String manufacturer);


    void clearUpdateFields();

}
