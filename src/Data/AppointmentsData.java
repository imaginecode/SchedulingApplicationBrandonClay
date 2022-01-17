package Data;
/**
 * This class contains MYSQL queries for appointments*/

import Model.Appointment;
import Utilities.JDBC;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** @author Brandon Clay */

//Work on consolidating code and figuring out how to reference observable lists without having to instantiate
    //Currently working on building executeAndResultSet method need to finishing making new appointment object filled with result set code
public class AppointmentsData {
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private String query;

    public static ObservableList<Appointment> getAllAppointments() {


        String query;

        return appointments;
    }
//Not sure if I need this or not
    public ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    private ObservableList<Appointment> executeAndResultSet() throws SQLException {
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();

            while (rs.next()) {
                Appointment newAppointment = new Appointment(rs.getInt("Appointment_ID"));

                appointments.add(newAppointment);
            }
            return appointments;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
