package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

/** Makes connection to MSQL database and launch JavaFX runtime & JavaFX application */
    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
    }
}
