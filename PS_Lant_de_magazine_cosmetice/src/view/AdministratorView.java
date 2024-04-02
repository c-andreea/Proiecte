package view;

import presenter.AdministratorPresenter;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class AdministratorView extends JFrame implements IAdministratorView {
    private AdministratorPresenter presenter;
    private JTable usersTable;
    private JButton addButton, updateButton, deleteButton;
    private JButton reloadButton;


    public AdministratorView() {
        initializeUI();
        setupButtons();
    }

    private void initializeUI() {
        setTitle("List of Users");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(237, 247, 236));
        usersTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);


        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");


        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


        updateButton.setBackground(new Color(76, 175, 80));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


        deleteButton.setBackground(new Color(76, 175, 80)); // Vibrant green
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


        reloadButton = new JButton("Reload Users");
        reloadButton.setBackground(new Color(76, 175, 80)); // Green color
        reloadButton.setForeground(Color.WHITE);
        reloadButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        reloadButton.setFocusPainted(false);
        reloadButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(237, 247, 236));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 230, 201)));
        buttonPanel.add(reloadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void setupButtons() {
        addButton.addActionListener(e -> presenter.showAddUserDialog());
        updateButton.addActionListener(e -> presenter.showUpdateUserDialog(getSelectedUserId()));
        deleteButton.addActionListener(e -> presenter.deleteSelectedUser(getSelectedUserId()));
        reloadButton.addActionListener(e -> presenter.displayAllUsers());


    }

    private int getSelectedUserId() {
        int row = usersTable.getSelectedRow();
        return (int) usersTable.getModel().getValueAt(row, 0); // Assuming the ID is in the first column

    }

    @Override
    public void setPresenter(AdministratorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setTableModel(TableModel model) {
        SwingUtilities.invokeLater(() -> {
            usersTable.setModel(model);
        });
    }

}
