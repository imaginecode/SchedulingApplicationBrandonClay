package Data;
/**
 * This class contains MYSQL queries for appointments
 /** @author Brandon Clay */

import Controller.MainMenu;
import Model.Appointment;
import Utilities.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.ScatterChart;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/** @author Brandon Clay */

public class AppointmentsData {

    /** Query for all appointments.
     * @return aptResultSet a result set of all appointments */
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

                // times and dates converted to local time and date of user
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

//                System.out.println(appointmentTitle + appointmentDescription+appointmentLocation + appointmentType+start+end+
  //                                  " " +customerID + " " +userID+ " " +contactID);
//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, start, end , customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
            catch(SQLException e){
                return null;
            }
        return aptResultSet;

    }
    /**Queries appointments for a one week period starting at current date and showing a week into the future
     * @return aptResultSet result set from query */
    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {
        ObservableList<Appointment> aptResultSet = FXCollections.observableArrayList();
        LocalDate today = LocalDateTime.now().toLocalDate();
        LocalDate weekTimeFrame = today.plusDays(7);
        String query = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setDate(1, Date.valueOf(today));
        preparedStatement.setDate(2, Date.valueOf(weekTimeFrame));

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

                // times and dates converted to local time and date of user
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                System.out.println(appointmentTitle + appointmentDescription+appointmentLocation + appointmentType+start+end+
                        " " +customerID + " " +userID+ " " +contactID);
//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, start, end , customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
        catch(SQLException e){
            return null;
        }
        return aptResultSet;
    }


        /** Gets appointments by month starting from current day and going 30 days forward
         * @return aptResultSet result set from query*/
    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {
        ObservableList<Appointment> aptResultSet = FXCollections.observableArrayList();
        LocalDate today = LocalDateTime.now().toLocalDate();
        LocalDate monthTimeFrame = today.plusDays(30);
        String query = "SELECT * FROM AppointmentsView WHERE Start < ? AND Start > ?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setDate(1, Date.valueOf(today));
        preparedStatement.setDate(2, Date.valueOf(monthTimeFrame));

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

                // times and dates converted to local time and date of user
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();

                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                System.out.println(appointmentTitle + appointmentDescription+appointmentLocation + appointmentType+start+end+
                        " " +customerID + " " +userID+ " " +contactID);
//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, start, end , customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
        catch(SQLException e){
            return null;
        }
        return aptResultSet;
    }











}
