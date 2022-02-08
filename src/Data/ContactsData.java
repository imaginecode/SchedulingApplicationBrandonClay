package Data;

import Model.Appointment;
import Model.Contact;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * This class contains MYSQL queries for getting contacts and contact information
 /** @author Brandon Clay */

public class ContactsData {

    /** Query for all contacts
     * @return contactResultSet a result set of all contacts
     * @throws SQLException and prints error message
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> contactResultSet = FXCollections.observableArrayList();
        String query = "SELECT * FROM contacts";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            //Going through result set
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");


//                //Creating new contact object
                Contact newContact = new Contact(contactID,contactName,contactEmail);
                contactResultSet.add(newContact);

            }
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace());
            return null;
        }
        return contactResultSet;

    }
}
