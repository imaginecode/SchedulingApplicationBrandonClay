package Data;
/**
 * This class contains MYSQL queries for appointments*/

import Controller.MainMenu;
import Model.Appointment;
import Utilities.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.ScatterChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** @author Brandon Clay */

public class AppointmentsData {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> aptResultSet = FXCollections.observableArrayList();
        String query = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            //Going through result set
            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");

                // times and dates converted to local time of user
                LocalDate startDate = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
                LocalDate endDate = rs.getTimestamp("End").toLocalDateTime().toLocalDate();
                LocalTime startTime = rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
                LocalTime endTime = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

 //               System.out.println(appointmentTitle + appointmentDescription+appointmentLocation+appointmentType+startDate+endDate+startTime+endTime+customerID+userID+contactID);
//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, startDate, endDate, startTime, endTime, customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
            catch(SQLException e){
                return null;
            }
        return aptResultSet;

    }









}
