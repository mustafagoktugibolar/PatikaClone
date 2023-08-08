package com.goktugibolar.helper;

import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.*;

public class Helper {
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

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
}
