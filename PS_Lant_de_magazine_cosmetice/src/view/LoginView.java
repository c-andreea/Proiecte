package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ILoginView {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen


        Color mainPanelColor = new Color(232, 245, 233);
        Color btnPanelColor = new Color(200, 230, 201);
        Color btnColor = new Color(76, 175, 80);


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(mainPanelColor);


        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        gridPanel.setBackground(mainPanelColor);

        txtEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
        btnLogin.setBackground(btnColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));


        gridPanel.add(new JLabel("Email:"));
        gridPanel.add(txtEmail);
        gridPanel.add(new JLabel("Password:"));
        gridPanel.add(txtPassword);


        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(btnPanelColor);
        btnPanel.add(btnLogin);


        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);


        add(mainPanel);
    }
    @Override
    public String getEmail() {
        return txtEmail.getText().trim();
    }

    @Override
    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    @Override
    public void addLoginListener(ActionListener loginListener) {
        btnLogin.addActionListener(loginListener);
    }

    @Override
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void closeView() {
        dispose();
    }
}
