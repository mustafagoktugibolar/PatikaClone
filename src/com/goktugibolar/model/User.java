package com.goktugibolar.model;

import com.goktugibolar.connection.DBConnection;
import com.goktugibolar.helper.Helper;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String username;
    private String pass;
    private  String type;

    public User() {
    }

    public User(int id, String name, String username, String pass, String type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.pass = pass;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        User obj;
        try {
            Statement st = DBConnection.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("usertype"));
                userList.add(obj);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userList;
    }
    public static boolean add(String name, String username, String password, String usertype){
        String query = "INSERT INTO users (name, username, pass, usertype) VALUES (?, ?, ?, ? :: usertype)";
        User findUser = User.getFetch(username, "SELECT * FROM users WHERE username = ?", 1);

        if(findUser != null){
            Helper.showMessage("Choose Another Username!");
            return false;
        }
        else {
            try {
                PreparedStatement ps = DBConnection.getInstance().prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setString(4, usertype);
                Helper.showMessage("done");
                return ps.executeUpdate() != -1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public static User getFetch(String check, String query, int columnNum){
        User obj = null;
        try{
            PreparedStatement pr = DBConnection.getInstance().prepareStatement(query);
            pr.setString(columnNum, check);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("usertype"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }
    public static User getFetch(int check, String query, int columnNum){
        User obj = null;
        try{
            PreparedStatement pr = DBConnection.getInstance().prepareStatement(query);
            pr.setInt(columnNum, check);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPass(rs.getString("pass"));
                obj.setType(rs.getString("usertype"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }
    public static boolean removeUserFromID(int id){
        String query = "DELETE FROM users WHERE id = ?";
        User findUser = User.getFetch(id, "SELECT * FROM users WHERE id = ?", 1);

        if(findUser != null){
            try {
                PreparedStatement ps = DBConnection.getInstance().prepareStatement(query);
                ps.setInt(1, id);
                Helper.showMessage("User Deleted!");
                return ps.executeUpdate() != -1;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            Helper.showMessage("User Does Not Exits!");
            return false;
        }
        return true;
    }


}
