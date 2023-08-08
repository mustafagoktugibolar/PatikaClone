package com.goktugibolar.view;

import com.goktugibolar.helper.Config;
import com.goktugibolar.helper.Helper;
import com.goktugibolar.model.Operator;
import com.goktugibolar.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorView extends JFrame {
    private JPanel wrapper;
    private JTabbedPane top_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JScrollPane scrll_userlist;
    private JTable tbl_users;
    private JPanel pnl_userlist;
    private final Operator operator;
    private DefaultTableModel mdl_userlist;
    private Object[] col_userlist = {"ID", "Name", "Username", "Password", "Type"};

    public OperatorView(Operator operator) {
        this.operator = operator;
        Helper.setLayout();
        add(wrapper);

        //SET FRAME'S SETTINGS
        setSize(900,600);
        setLocation(Helper.getScreenCenter("x", getSize()), Helper.getScreenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle(Config.PROJECT_TITLE);
        lbl_welcome.setText("Welcome " + operator.getUsername());
        setVisible(true);

        //SET MODEL

        // CREATE MODEL
        mdl_userlist = new DefaultTableModel();

        //CREATE OBJECT[] TO SET COLUMNS

        mdl_userlist.setColumnIdentifiers(col_userlist);

        //PUT MODEL INTO TABEL
        tbl_users.setModel(mdl_userlist);
        tbl_users.setRowHeight(30);


        //ADDING ROWS
        //Object[] firstRow = {"1", "Mustafa Goktug Ibolar", "goktugibolar", "123123qwe", "operator"};
        //mdl_userlist.addRow(firstRow);
        showRow();


        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mdl_userlist.setRowCount(0);
                showRow();
            }
        });
    }
    private void showRow() {
        for (User obj : User.getList()) {
            Object[] row = new Object[col_userlist.length];
            row[0] = obj.getId();
            row[1] = obj.getName();
            row[2] = obj.getUsername();
            row[3] = obj.getPass();
            row[4] = obj.getType();
            mdl_userlist.addRow(row);
        }
    }

    public static void main(String[] args) {
        Operator o = new Operator();
        o.setId(1);
        o.setName("goktug ibolar");
        o.setUsername("goktugibolar");
        o.setPass("123123qwe");
        o.setType("operator");
        new OperatorView(o);
    }


}
