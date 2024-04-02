package view;

import presenter.EmployeePresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmployeeView1 extends JFrame implements IEmployeeView {
    private JTable productsTable;
    private JTable storeSpecificTable;
    private JComboBox<String> storeComboBox;
    private JComboBox<String> manufacturerComboBox;
    private JComboBox<String> productNamesComboBox;
    private JTextField maxPriceField;
    private JCheckBox availabilityCheckBox;
    private JButton applyFiltersButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextField updatePriceField;
    private JTextField updateQuantityField;
    private JTextField updateManufacturerField;
    private JCheckBox updateAvailabilityCheckBox;
    private JButton reloadButton;

    private EmployeePresenter presenter;

    public EmployeeView1() {
        initializeUI();

    }

    private void initializeUI() {
        setTitle("Product Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();

        pack();
        setVisible(true);
    }

    private void initComponents() {
        storeComboBox = new JComboBox<>();
        manufacturerComboBox = new JComboBox<>();
        productsTable = new JTable();
        storeSpecificTable = new JTable();
        productNamesComboBox = new JComboBox<>();
        maxPriceField = new JTextField();
        availabilityCheckBox = new JCheckBox("Available Only", false);
        applyFiltersButton = new JButton("Apply Filters");
        addButton = new JButton("Add Product");
        deleteButton = new JButton("Delete Product");



        updatePriceField = new JTextField();
        updateQuantityField = new JTextField();
        updateManufacturerField = new JTextField();
        updateAvailabilityCheckBox = new JCheckBox();
        updateButton = new JButton("Update Product");

        // Defining a green color for the buttons and checkboxes
        Color greenColor = new Color(76, 175, 80);
        applyFiltersButton.setBackground(greenColor);
        addButton.setBackground(greenColor);
        updateButton.setBackground(greenColor);
        deleteButton.setBackground(greenColor);
        applyFiltersButton.setForeground(Color.WHITE);
        addButton.setForeground(Color.WHITE);
        updateButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        availabilityCheckBox.setForeground(greenColor);
        updateAvailabilityCheckBox.setForeground(greenColor);

        Font labelFont = new Font("SansSerif", Font.BOLD, 12);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 12);

        applyFiltersButton.setFont(buttonFont);
        addButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        availabilityCheckBox.setFont(labelFont);
        maxPriceField.setColumns(10); // Ensure uniform size

        Dimension preferredSize = new Dimension(200, 25);
        storeComboBox.setPreferredSize(preferredSize);
        manufacturerComboBox.setPreferredSize(preferredSize);


        reloadButton = new JButton("Reload Table");
        reloadButton.setBackground(new Color(76, 175, 80)); // Green color
        reloadButton.setForeground(Color.WHITE);
        reloadButton.setFont(new Font("SansSerif", Font.BOLD, 12));

        setupActionListeners();
    }

    private void setupActionListeners() {
        applyFiltersButton.addActionListener(e -> presenter.loadFilteredProducts());
        storeComboBox.addActionListener(e -> presenter.handleStoreSelection());
        addButton.addActionListener(e -> presenter.addProduct());
        deleteButton.addActionListener(e -> presenter.deleteSelectedProduct());
        updateButton.addActionListener(e -> presenter.updateSelectedProduct());
        productNamesComboBox.addActionListener(e -> {
            presenter.productNameSelected((String) productNamesComboBox.getSelectedItem());
        });
        reloadButton.addActionListener(e -> presenter.reloadStoreProducts());
    }

    private void layoutComponents() {
        JPanel filtersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filtersPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        filtersPanel.setBackground(new Color(236, 249, 241)); // Light green background
        filtersPanel.add(new JLabel("Manufacturer:"));
        filtersPanel.add(manufacturerComboBox);
        filtersPanel.add(new JLabel("Max Price:"));
        filtersPanel.add(maxPriceField);
        filtersPanel.add(availabilityCheckBox);
        filtersPanel.add(applyFiltersButton);

        JPanel storePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        storePanel.setBackground(new Color(236, 249, 241)); // Light green background
        storePanel.add(new JLabel("Select Store:"));
        storePanel.add(storeComboBox);

        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        tablesPanel.setBackground(new Color(236, 249, 241)); // Light green background
        tablesPanel.add(new JScrollPane(productsTable));
        tablesPanel.add(new JScrollPane(storeSpecificTable));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        bottomPanel.setBackground(new Color(236, 249, 241)); // Light green background
        bottomPanel.add(new JLabel("Select Product:"));
        bottomPanel.add(productNamesComboBox);
        bottomPanel.add(deleteButton);
        bottomPanel.add(addButton);

        JPanel updatePanel = new JPanel(new GridLayout(0, 2, 10, 5));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Product"));
        updatePanel.setBackground(new Color(236, 249, 241)); // Light green background
        updatePanel.add(new JLabel("Price:"));
        updatePanel.add(updatePriceField);
        updatePanel.add(new JLabel("Quantity:"));
        updatePanel.add(updateQuantityField);
        updatePanel.add(new JLabel("Manufacturer:"));
        updatePanel.add(updateManufacturerField);
        updatePanel.add(new JLabel("Availability:"));
        updatePanel.add(updateAvailabilityCheckBox);
        updatePanel.add(updateButton);
        bottomPanel.add(reloadButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(236, 249, 241)); // Light green background
        mainPanel.add(filtersPanel, BorderLayout.NORTH);
        mainPanel.add(tablesPanel, BorderLayout.CENTER);
        mainPanel.add(storePanel, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(updatePanel, BorderLayout.EAST);

        pack();
    }
        public void setPresenter(EmployeePresenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public String getSelectedStoreItem() {
        return storeComboBox.getSelectedItem().toString();
    }
    @Override
    public void displayStoreSpecificInfo(DefaultTableModel model) {
        SwingUtilities.invokeLater(() -> storeSpecificTable.setModel(model));
    }

    @Override
    public void setStoreComboBoxModel(DefaultComboBoxModel<String> model) {
        SwingUtilities.invokeLater(() -> storeComboBox.setModel(model));
    }

    @Override
    public void setManufacturersComboBoxModel(DefaultComboBoxModel<String> model) {
        SwingUtilities.invokeLater(() -> manufacturerComboBox.setModel(model));
    }

    @Override
    public void displayProducts(DefaultTableModel model) {
        SwingUtilities.invokeLater(() -> productsTable.setModel(model));
    }

    @Override
    public void showError(String message) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE));
    }

    @Override
    public String getSelectedManufacturer() {
        return (String) manufacturerComboBox.getSelectedItem();
    }

    @Override
    public boolean getSelectedAvailability() {
        return availabilityCheckBox.isSelected();
    }

    @Override
    public String getMaxPriceText() {
        return maxPriceField.getText();
    }

    @Override
    public String getSelectedProductName() {
        return (String) productNamesComboBox.getSelectedItem();
    }
    @Override
    public void clearProductNamesComboBox() {
        productNamesComboBox.removeAllItems();
    }

    @Override
    public void addProductNameToComboBox(String name) {
        productNamesComboBox.addItem(name);
    }

    @Override
    public double getProductPrice() {
            return Double.parseDouble(updatePriceField.getText());

    }

    @Override
    public int getProductQuantity() {

            return Integer.parseInt(updateQuantityField.getText());

    }

    @Override
    public boolean getProductAvailability() {
        return updateAvailabilityCheckBox.isSelected();
    }

    @Override
    public String getProductManufacturer() {
        return updateManufacturerField.getText();
    }

    @Override
    public void setUpdatePriceField(String price) {
        updatePriceField.setText(price);
    }

    @Override
    public void setUpdateQuantityField(String quantity) {
        updateQuantityField.setText(quantity);
    }

    @Override
    public void setUpdateAvailabilityCheckBox(boolean available) {
        updateAvailabilityCheckBox.setSelected(available);
    }

    @Override
    public void setUpdateManufacturerField(String manufacturer) {
        updateManufacturerField.setText(manufacturer);
    }
// In EmployeeView1.java

    @Override
    public void clearUpdateFields() {
        SwingUtilities.invokeLater(() -> {
            setUpdatePriceField("");
            setUpdateQuantityField("");
            setUpdateManufacturerField("");
            setUpdateAvailabilityCheckBox(false);
        });
    }


}
