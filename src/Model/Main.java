package Model;

import Data.DBQuery;
import Utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * Main class.
 * This is a scheduling application  and contains methods for contact  and appointment management and reports */
/** @author Brandon Clay */

/** Java docs location \SchedulingApplicationBrandonClay\JavaDocs*/

public class Main extends Application {

    /** Sets up the java FX stage and sets the first scene for user login
     * @param primaryStage
     * @throws Exception*/
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginScreen.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 650, 600));
        primaryStage.show();

    }

/** First thing to run in program makes connection to MSQL database and launches JavaFX runtime & JavaFX application
 * @param args  */
    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        Connection conn = JDBC.getConnection();
        System.out.println(conn);
        //Printing zone ID for user

//    //Testing SQL statement need to remove before submission
//        String query = "SELECT * FROM appointments";
//        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
//        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
//
//        try {
//            preparedStatement.execute();
//            ResultSet rs = preparedStatement.getResultSet();
//            //Going through result set
//            while (rs.next()) {
//                int appointmentID = rs.getInt("Appointment_ID");
//                String appointmentTitle = rs.getString("Title");
//                String appointmentDescription = rs.getString("Description");
//                String appointmentLocation = rs.getString("Location");
//                String appointmentType = rs.getString("Type");
//
//                // times converted to local time of user
//                LocalDate startDate = rs.getTimestamp("Start").toLocalDateTime().toLocalDate();
//                LocalDate endDate = rs.getTimestamp("End").toLocalDateTime().toLocalDate();
//                LocalTime startTime =  rs.getTimestamp("Start").toLocalDateTime().toLocalTime();
//                LocalTime endTime = rs.getTimestamp("End").toLocalDateTime().toLocalTime();
//                int customerID = rs.getInt("Customer_ID");
//                int userID = rs.getInt("User_ID");
//                int contactID = rs.getInt("Contact_ID");
//                System.out.println(appointmentTitle + appointmentDescription+appointmentLocation+appointmentType+startDate+endDate+startTime+endTime+customerID+userID+contactID);
//            }
//
//
//
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Query not returning a result");
//
//        }
        //Sets Location on login screen
        System.out.println(ZoneId.systemDefault());





        launch(args);
        JDBC.closeConnection();
    }
}
