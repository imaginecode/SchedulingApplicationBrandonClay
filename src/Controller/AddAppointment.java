package Controller;

import Data.UserNamePassQuery;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAppointment implements Initializable {
    public ComboBox contactCombo;
    public ComboBox endTime;
    public ComboBox startTime;
    public TextField title;
    public TextField User_ID;
    public TextField Customer_ID;
    public TextField type;
    public TextField aptLocation;
    public TextField aptDescription;
    public TextField appointment_ID;
    public Button SaveAptAdd;
    public Button cancelAptAdd;
    public DatePicker aptStartDate;
    public DatePicker aptEndDate;
    private Customer selectedCustomer;


    public void cancelAptAddHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void SaveAptAddHandler(ActionEvent actionEvent) {
        if (appointmentChecks()){

        }
        else{

        }


    }
/**Checks to make sure appointment is valid fields are not empty as well as checking for overlapping appointments,
 * valid dates, and that appointment is within business hours
 * @return boolean of t/f for if the appointment is valid if (f) then alerts are displayed*/
    private boolean appointmentChecks() {

        //Checking to make sure text fields aren't empty

        //Checking to make sure end date is after start date

        //Checking for overlapping appointments

        //Checks too see if appointment is with business hours defined as 8:00 a.m. to 10:00 p.m. EST, including weekends



    }

    public void appointment_IDHandler(ActionEvent actionEvent) {
    }

    public void aptDescriptionHandler(ActionEvent actionEvent) {
    }

    public void AptLocationHandler(ActionEvent actionEvent) {
    }

    public void handler(ActionEvent actionEvent) {
    }

    public void Customer_IDHandler(ActionEvent actionEvent) {
    }

    public void User_IDHandler(ActionEvent actionEvent) {
    }

    public void titleHandler(ActionEvent actionEvent) {
    }

    public void startTimeHandler(ActionEvent actionEvent) {
    }

    public void endTimeHandler(ActionEvent actionEvent) {
    }

    public void contactComboHandler(ActionEvent actionEvent) {
    }

    public void aptEndDateHandler(ActionEvent actionEvent) {
    }

    public void aptStartDateHandler(ActionEvent actionEvent) {

    }

//    public void dataHandoff(Customer controller){
//        Customer_ID.setText(String.valueOf(controller.getCustomerID()));
//    }

    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void addErrors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("All fields  and combo boxes must be filled selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Confirm Save");
                alert.setHeaderText("Confirm Save");
                alert.setContentText("Confirm Save");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Confirm Cancel");
                alert.setHeaderText("Confirm Cancel");
                alert.setContentText("Confirm Cancel");
                alert.showAndWait();
                break;





        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customer_ID.setText(String.valueOf(MainMenu.CID));
        try {
            User_ID.setText(String.valueOf(UserNamePassQuery.getCurrentUser()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
