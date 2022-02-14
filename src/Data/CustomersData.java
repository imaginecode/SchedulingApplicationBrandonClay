package Data;

import Model.Appointment;
import Model.Country;
import Model.Customer;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * This class contains MYSQL queries for getting customer data and inserting updating and deleting that data
 /** @author Brandon Clay */
public class CustomersData {
/** Gets all customers
 * @return customer objects in a Observable list*/
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> customerResultSet = FXCollections.observableArrayList();
        String query = "SELECT * FROM customers";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            //Going through result set
            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String zip = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divID = rs.getInt("Division_ID");

//                System.out.println(customerID + customerName + address + zip + phone + divID);


//                //Creating new customer object
                Customer newCustomer = new Customer(customerID, customerName, address, zip, phone, divID);
                customerResultSet.add(newCustomer);
            }
        } catch (SQLException e) {
            return null;
        }
        return customerResultSet;

    }

/** Method performs an insert statement to add a new customer to database
 * @param name name of customer
 * @param address address of customer
 * @param divID division ID of customer
 * @param phone phone number of customer
 * @param zip Postal code for customer*/
    public static void newCustomer(String name,String address,String zip,String phone,int divID) throws SQLException {
        String query = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                "       VALUES (?,?,?,?,?)";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, zip);
        ps.setString(4, phone);
        ps.setInt(5, divID);


        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows(s) affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No rows affected by INSERT");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


