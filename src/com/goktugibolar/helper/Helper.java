package com.goktugibolar.helper;

import com.goktugibolar.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static javax.swing.UIManager.*;

public class Helper {
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    //GET SCREEN DIMESIONS
    public static int getScreenCenter(String dim, Dimension size){
        int point;
        switch (dim){
            case "x" -> point = (screenWidth - size.width) / 2;
            case "y" -> point = (screenHeight - size.height) / 2;
            default -> point = 0;
        }
        return point;
    }
    public static void setLayout(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
    //CHECK IS THE FIELD EMPTY
    public static boolean isFieldEmpty(JTextField... fields){
        for (JTextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return true; // At least one field is empty
            }
        }
        return false; // All fields are non-empty
    }

    public static void showMessage(String input){
        String message;
        String title;
        switch (input){
            case "fill" ->{
                message = "Fill All The Fields!";
                title = "Error!";
            }
            case "done" ->{
                message = "Process Successful";
                title = "Success!";
            }
            default -> {
                message = input;
                title = "Something Wrong!";
            }

        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
        //CHANGE POPUPS TEXT TO ANY LANGUAGE(SET THE LANGUAGE!)
//      public static void optionPageTR(){
//          UIManager.put("OptionPane.okButtonText", "Tamam");
//      }
    //SHOW TABLE ROWS FOR OPERATOR VIEW
    public static void showRowForOV(DefaultTableModel tbl, Object[] row) {
        tbl.setRowCount(0);
        //Object[] row = new Object[object.length];
        for (User obj : User.getList()) {
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUsername();
            row[3] = obj.getPass();
            row[4] = obj.getType();
            tbl.addRow(row);

        }
    }
}
