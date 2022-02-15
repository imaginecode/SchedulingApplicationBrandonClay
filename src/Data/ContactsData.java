package Data;

import Model.Contact;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        PreparedStatement ps = DBQuery.getPreparedStatement();


        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
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
  /** Getting contactID by  contact name */
    public static Contact getContactByID(String nameOfContact) throws SQLException {
        String query = "SELECT * FROM contacts WHERE Contact_Name =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, nameOfContact);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");


//                //Creating new contact object
                Contact newContact = new Contact(contactID,contactName,contactEmail);
                return newContact;

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /** Getting contactID by name
     * @param CID Customer ID  */
    public static Contact getContactNameByID(int CID) throws SQLException {
        String query = "SELECT * FROM contacts WHERE Contact_ID =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, String.valueOf(CID));

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");


//                //Creating new contact object
                Contact newContact = new Contact(contactID,contactName,contactEmail);
                return newContact;

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
