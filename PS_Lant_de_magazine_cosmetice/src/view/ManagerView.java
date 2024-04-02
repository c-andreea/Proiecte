package view;

import presenter.ManagerPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
public class ManagerView extends JFrame implements IManagerView {
    private ManagerPresenter presenter;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> manufacturerComboBox;
    private JCheckBox availabilityCheckBox;
    private JTextField maxPriceTextField;
    private JButton filterButton;
    private JButton reloadButton; // Added for completeness

    public ManagerView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Manager View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        productTable = new JTable();
        // Inside the initializeUI method of ManagerView
        tableModel = new DefaultTableModel(new String[]{"Name", "Price", "Availability", "Manufacturer", "Store Name"}, 0);
        productTable.setModel(tableModel);
        productTable.setFont(new Font("Serif", Font.PLAIN, 14));
        productTable.setRowHeight(25);
        productTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        productTable.getTableHeader().setBackground(new Color(0, 153, 76));
        productTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        setupFilterPanel();

        setVisible(true);
    }

    private void setupFilterPanel() {
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setBackground(new Color(232, 245, 233));

        manufacturerComboBox = new JComboBox<>(new String[]{"Loading manufacturers..."});
        manufacturerComboBox.setBackground(Color.WHITE);

        availabilityCheckBox = new JCheckBox("Available Only");
        availabilityCheckBox.setBackground(new Color(232, 245, 233));

        maxPriceTextField = new JTextField(10);
        maxPriceTextField.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 76), 2));

        filterButton = new JButton("Filter");
        reloadButton = new JButton("Reload"); // Assuming you want a reload functionality
        styleButton(filterButton, new Color(0, 153, 76), Color.WHITE);
        styleButton(reloadButton, new Color(0, 153, 76), Color.WHITE);

        filterPanel.add(new JLabel("Manufacturer:"));
        filterPanel.add(manufacturerComboBox);
        filterPanel.add(availabilityCheckBox);
        filterPanel.add(new JLabel("Max Price:"));
        filterPanel.add(maxPriceTextField);
        filterPanel.add(filterButton);
        filterPanel.add(reloadButton); // Adding the reload button
        filterButton.addActionListener(e -> onFilterButtonClicked());
        reloadButton.addActionListener(e -> presenter.reloadProducts());

        add(filterPanel, BorderLayout.NORTH);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
    }
    private void onFilterButtonClicked() {
        String manufacturer = manufacturerComboBox.getSelectedItem().toString();
        boolean availableOnly = availabilityCheckBox.isSelected();
        String maxPriceStr = maxPriceTextField.getText();
        double maxPrice = maxPriceStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceStr);

        presenter.loadFilteredProducts(manufacturer, availableOnly, maxPrice);
    }


    @Override
    public void setPresenter(ManagerPresenter presenter) {
        this.presenter = presenter;
        presenter.loadManufacturers();

    }

    @Override
    public void clearProductsTable() {
        tableModel.setRowCount(0);
    }

    @Override
    public void setProductToTableWithStoreName(String name, String price, String availability, String manufacturer, String storeName) {
        tableModel.addRow(new Object[]{name, price, availability, manufacturer, storeName});
    }


    @Override
    public void populateManufacturers(java.util.List<String> manufacturers) {
        manufacturerComboBox.setModel(new DefaultComboBoxModel<>(manufacturers.toArray(new String[0])));
    }



}
