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

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/** @author Brandon Clay */

public class AppointmentsData {

    /** Query for all appointments.
     * @return aptResultSet a result set of all appointments
     * @throws SQLException and prints error message
     */
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
                System.out.println(e.getStackTrace());
                return null;
            }
        return aptResultSet;

    }
    /**Queries appointments for a one week period starting at current date and showing a week into the future
     * @return aptResultSet result set from query
     * @throws SQLException and prints stack trace*/
    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {
        ObservableList<Appointment> aptResultSet = FXCollections.observableArrayList();
        LocalDate today = LocalDateTime.now().toLocalDate();
        LocalDate weekTimeFrame = today.plusDays(7);
        String query = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setDate(1, Date.valueOf(weekTimeFrame));
        preparedStatement.setDate(2, Date.valueOf(today));

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

//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, start, end , customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace());
            return null;
        }
        return aptResultSet;
    }


        /** Gets appointments by month starting from current day and going 30 days forward
         * @return aptResultSet result set from query
         * @throws SQLException and prints stack trace*/
    public static ObservableList<Appointment> getMonthAppointments() throws SQLException {
        ObservableList<Appointment> aptResultSet = FXCollections.observableArrayList();
        LocalDate today = LocalDateTime.now().toLocalDate();
        LocalDate monthTimeFrame = today.plusDays(30);
        String query = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setDate(1, Date.valueOf(monthTimeFrame));
        preparedStatement.setDate(2, Date.valueOf(today));

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

//                //Creating new appointment object
                Appointment newApt = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation,
                        appointmentType, start, end , customerID, userID, contactID);
                aptResultSet.add(newApt);

            }
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace());
            return null;
        }
        return aptResultSet;
    }
    /** Creating new appointment.
     * @param userID userID ID of user
     * @param contactID ID of contact
     * @param customerId ID of customer
     * @param description written description of appointment
     * @param start beginning of appointment and start date of appointment
     * @param end ending of appointment in a timestamp
     * @param location where the appointment is going to be
     * @param title Title of appointment
     * @param type type of appointment
     * @return boolean true if it properly executes query and false if query doesn't get executed
     * @throws SQLException and prints stack trace
     * */
    public static boolean newAppointment (String title, String description, String location, String type,
                                          LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID, Integer contactID) throws SQLException {


        String query = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID)" +
                "       VALUES (?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userID);
        ps.setInt(9, contactID);

        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows(s) affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No rows affected by INSERT");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    /** Creating new appointment.
     * @param userID userID ID of user
     * @param Appointment_ID id of appointment being updated
     * @param contactID ID of contact
     * @param customerId ID of customer
     * @param description written description of appointment
     * @param start beginning of appointment and start date of appointment
     * @param end ending of appointment in a timestamp
     * @param location where the appointment is going to be
     * @param title Title of appointment
     * @param type type of appointment
     * @return boolean true if it properly executes query and false if query doesn't get executed
     * @throws SQLException and prints stack trace
     * */
    public static boolean editAppointment (Integer Appointment_ID, String title, String description, String location, String type,
                                           LocalDateTime start, LocalDateTime end, Integer customerId, Integer userID, Integer contactID) throws SQLException {

        String query = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();


        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setInt(7, customerId);
        ps.setInt(8, userID);
        ps.setInt(9, contactID);
        ps.setInt(10, Appointment_ID);

        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows(s) affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No rows affected by INSERT");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    /** Deletes an appointment by Appointment_ID
     * @param appointmentID ID of appointment being deleted
     * @return true if appointment query was successfully done false if it throws an error
     * @throws SQLException and prints stacktrace
     * */
    public static boolean deleteAppointment(Integer appointmentID) throws SQLException {
        String query = "DELETE from appointments WHERE Appointment_ID=?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, appointmentID);

        try {
            ps.execute();
            if (ps.getUpdateCount() > 0) {
                System.out.println("Rows(s) affected: " + ps.getUpdateCount());
            } else {
                System.out.println("No rows affected by INSERT");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }


    }






}
