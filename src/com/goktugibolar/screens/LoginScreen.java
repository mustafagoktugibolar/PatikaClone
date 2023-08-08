package com.goktugibolar.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginScreen{
    int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    JFrame frame;
    JLabel topTitle;
    JPanel centerPanel;
    JLabel usernameLabel;
    JTextField usernameField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton loginButton;
    JLabel bottomTitle;

    public LoginScreen() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Patika.dev");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getWidth()) / 2);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        // Create and add the top title
        topTitle = new JLabel("Login Screen");
        topTitle.setHorizontalAlignment(JLabel.CENTER);
        topTitle.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(topTitle, BorderLayout.NORTH);

        // Create and add the center panel with login components
        centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(e ->  {
            if(usernameField.getText().length() == 0 || passwordField.getText().length() == 0){
                JOptionPane.showMessageDialog(null, "Fill All Fields!");
            }
        });


        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(new JLabel()); // Empty label for spacing
        centerPanel.add(loginButton);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Create and add the bottom title
        bottomTitle = new JLabel("Please log in to continue");
        bottomTitle.setHorizontalAlignment(JLabel.CENTER);
        bottomTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(bottomTitle, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
