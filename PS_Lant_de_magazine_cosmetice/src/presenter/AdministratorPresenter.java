package presenter;

import model.User;
import model.repository.UserService;
import view.IAdministratorView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdministratorPresenter {
    private IAdministratorView view;
    private UserService userService;

    public AdministratorPresenter(IAdministratorView view, UserService userService) {
        this.view = view;
        this.userService = userService;
        view.setPresenter(this);
        displayAllUsers();
    }

    public void displayAllUsers() {
        List<User> users = userService.getAllUsers();
        String[] columnNames = {"ID", "Name", "Email", "Role", "Store ID"};
        Object[][] data = new Object[users.size()][5];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = user.getUserId();
            data[i][1] = user.getName();
            data[i][2] = user.getEmail();
            data[i][3] = user.getRole();
            data[i][4] = user.getStoreId();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        view.setTableModel(model);
    }


    public void showAddUserDialog() {

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField storeIdField = new JTextField();
        JTextField password = new JTextField();
        Object[] message = {
                "Name:", nameField,
                "Email:", emailField,
                "Role:", roleField,
                "Store ID:", storeIdField,
                "Password",password
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            User user = new User(0, nameField.getText(), emailField.getText(), roleField.getText(), Integer.parseInt(storeIdField.getText()),password.getText());
            if (userService.createUser(user)) {
                JOptionPane.showMessageDialog(null, "User successfully added.");
                displayAllUsers();
            } else {
                JOptionPane.showMessageDialog(null, "Error adding user.");
            }
        }
    }

    public void showUpdateUserDialog(int userId) {
        User userToUpdate = userService.getUserById(userId);
        if (userToUpdate != null) {
            JTextField nameField = new JTextField(userToUpdate.getName());
            JTextField emailField = new JTextField(userToUpdate.getEmail());
            JTextField roleField = new JTextField(userToUpdate.getRole());
            JTextField storeIdField = new JTextField(String.valueOf(userToUpdate.getStoreId()));
            JTextField passwordField = new JTextField(); // Assuming you want to allow updating the password
            Object[] message = {
                    "Name:", nameField,
                    "Email:", emailField,
                    "Role:", roleField,
                    "Store ID:", storeIdField,
                    "Password:", passwordField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Update User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                userToUpdate.setName(nameField.getText());
                userToUpdate.setEmail(emailField.getText());
                userToUpdate.setRole(roleField.getText());
                userToUpdate.setStoreId(Integer.parseInt(storeIdField.getText()));
                userToUpdate.setPassword(passwordField.getText());

                if (userService.updateUser(userToUpdate)) {
                    JOptionPane.showMessageDialog(null, "User successfully updated.");
                    displayAllUsers();
                } else {
                    JOptionPane.showMessageDialog(null, "Error updating user.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User not found.");
        }
    }

    public void deleteSelectedUser(int userId) {
        if (userService.deleteUser(userId)) {
            JOptionPane.showMessageDialog(null, "User successfully deleted.");
            displayAllUsers();
        } else {
            JOptionPane.showMessageDialog(null, "Error deleting user.");
        }
    }
}
