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
import java.time.LocalDateTime;

public class CustomersData {

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


}


