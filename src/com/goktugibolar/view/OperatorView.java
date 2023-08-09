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
    private JPanel pnl_userform;
    private JTextField fld_add_name;
    private JTextField fld_add_usersname;
    private JLabel lbl_add_username;
    private JPasswordField fld_add_password;
    private JComboBox cmb_usertype;
    private JButton btn_add;
    private JLabel lbl_add_password;
    private JLabel lbl_add_usertype;
    private final Operator operator;
    private DefaultTableModel mdl_userlist;
    private Object[] row_userlist;

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

        top_operator.setTabPlacement(JTabbedPane.TOP);

        //CREATE OBJECT[] TO SET COLUMNS

        mdl_userlist.setColumnIdentifiers(Config.COL_USERLIST);
        row_userlist = new Object[Config.COL_USERLIST.length];

        //PUT MODEL INTO TABEL
        tbl_users.setModel(mdl_userlist);
        tbl_users.setRowHeight(30);


        //ADDING ROWS
        //Object[] firstRow = {"1", "Mustafa Goktug Ibolar", "goktugibolar", "123123qwe", "operator"};
        //mdl_userlist.addRow(firstRow);
        Helper.showRowForOV(mdl_userlist, row_userlist);


        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Logout clicked!");
            }

        });
        btn_add.addActionListener(e ->  {
            if(Helper.isFieldEmpty(fld_add_name, fld_add_usersname, fld_add_password)){
                Helper.showMessage("fill");
            }
            else {
                Helper.showMessage("done");

                String name = fld_add_name.getText();
                String username = fld_add_usersname.getText();
                String pass = fld_add_password.getText();
                String usertype = cmb_usertype.getSelectedItem().toString();
                User.add(name, username, pass, usertype);
                Helper.showRowForOV(mdl_userlist, row_userlist);
            }

        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Operator o = new Operator();
            o.setId(1);
            o.setName("goktug ibolar");
            o.setUsername("goktugibolar");
            o.setPass("123123qwe");
            o.setType("operator");
            new OperatorView(o);
        });

    }

}
