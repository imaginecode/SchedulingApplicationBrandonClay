package Model;

import Data.DBQuery;
import Utilities.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

/** First thing to run in program makes connection to MSQL database and launches JavaFX runtime & JavaFX application */
    public static void main(String[] args) throws SQLException {
        //This bit of code was from what was provided however to complete tutorial a different setup is used
        JDBC.makeConnection();
        Connection conn = JDBC.getConnection();
        System.out.println(conn);
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement(); //Get Statement Reference
//        // Raw SQL insert statement
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES('US',now(),'admin',now(),'admin')";
//
//        //Execute SQL statement
        statement.executeUpdate(insertStatement);
//
//        //Confirm rows affected
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + " Rows affected");
       else
           System.out.println("No change");

        launch(args);
        JDBC.closeConnection();
    }
}
