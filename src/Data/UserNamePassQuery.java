package Data;

import Controller.LoginScreen;
import Model.Contact;
import Model.User;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserName and password query that is used in the login screen
 */

/**@author Brandon Clay */

public class UserNamePassQuery {
    public static int currentUserID;

    /** Queries username and password entered in the login screen
 * @param username username checked
 * @param password  password checked
 * @throws SQLException stack trace is printed for reference */
    public static boolean validateUser(String username, String password) throws SQLException {
        //Select Statement
        String  findUserStatement = "SELECT * FROM users WHERE User_Name=? AND Password=?";


        DBQuery.setPreparedStatement(JDBC.getConnection(), findUserStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //Key value mapping for username and password
        ps.setString(1,username);
        ps.setString(2,password);
        //Execute Statement
        try
        {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            return rs.next();
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
            System.out.println("Invalid Credentials entered this is on the query page");
            System.out.println(username + password);
            return false;
        }



    }
//    /**Upgrade for the future that queries user that is logged in instead of having user select from a combo box
//     * specifying what user to use when making and updating appointments */
//    public static int getCurrentUser() throws SQLException {
//        ObservableList<User> currentUser = FXCollections.observableArrayList();
//
//        String  findUserStatement = "SELECT * FROM users WHERE User_Name=?";
//
//
//        DBQuery.setPreparedStatement(JDBC.getConnection(), findUserStatement);
//        PreparedStatement ps = DBQuery.getPreparedStatement();
//        //Key value mapping for username
//        ps.setString(1, String.valueOf(LoginScreen.currentUser));
//        //Execute Statement
//        try
//        {
//            System.out.println(LoginScreen.currentUser);
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//
//            while (rs.next()) {
//                currentUserID = rs.getInt("User_ID");
//            }
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getStackTrace());
//
//        }
//        return currentUserID;
//    }

    /**Queries for all users
     * @return  result set off all users as an Observable list */
    public static ObservableList<User> allUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        //Select Statement
        String findUserStatement = "SELECT * FROM users";

        DBQuery.setPreparedStatement(JDBC.getConnection(), findUserStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //Execute Statement
        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //Going through result set
            while (rs.next()) {
                int UserID = rs.getInt("User_ID");
                String password = rs.getString("Password");
                String userName = rs.getString("User_Name");


//                //Creating new contact object
                User newUser = new User(UserID, userName, password);
                allUsers.add(newUser);

            }
            return allUsers;
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

}

