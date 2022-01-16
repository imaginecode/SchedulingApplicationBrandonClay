package Model;

import Data.DBQuery;
import Utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

/** First thing to run in program makes connection to MSQL database and launches JavaFX runtime & JavaFX application */
    public static void main(String[] args) {
        JDBC.makeConnection();
        Connection conn = JDBC.getConnection();
        System.out.println(conn);
        //Printing zone ID for user
        System.out.println(ZoneId.systemDefault());





        launch(args);
        JDBC.closeConnection();
    }
}
