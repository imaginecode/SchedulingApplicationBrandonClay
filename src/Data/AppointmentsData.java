package Data;
/**
 * This class contains MYSQL queries for appointments*/

import Controller.MainMenu;
import Model.Appointment;
import Utilities.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** @author Brandon Clay */

public class AppointmentsData {
    public static ObservableList<Appointment> aptResultSet;

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
       try{String query = "SELECT * FROM appointments";
           executeAndResultSet(query);


           return aptResultSet;}
       catch (SQLException e){
           e.printStackTrace();
           return null;
       }
    }

    /** Prepare and execute statement that creates new appointment object
     * @param query Unique query string that can be passed in*/
    private static void executeAndResultSet(String query) throws SQLException {
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            //Going through result set
            while (rs.next()) {
                Appointment newApt = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"), rs.getString("Description"),
                                                            rs.getString("Location"), rs.getString("Type"), rs.getDate("Start").toLocalDate(),
                                                            rs.getDate("End").toLocalDate(), rs.getTimestamp("Start").toLocalDateTime().toLocalTime(),
                                                            rs.getTimestamp("End").toLocalDateTime().toLocalTime(), rs.getInt("Customer_ID"),
                                                            rs.getInt("User_ID"),rs.getInt("Contact_ID"));

                aptResultSet.add(newApt);
            }
        } catch (Exception e) {
            System.out.println(" SQL Error: " + e.getMessage());
        }
    }
}
