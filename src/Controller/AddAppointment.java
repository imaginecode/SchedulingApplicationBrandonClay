package Controller;

import Data.UserNamePassQuery;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
