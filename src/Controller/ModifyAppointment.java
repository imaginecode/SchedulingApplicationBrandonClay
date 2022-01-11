package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyAppointment {

    public Button cancelUpdate;
    public Button saveUpdate;
    public TextField appointment_ID;
    public TextField description;
    public TextField place;
    public TextField contact;
    public TextField type;
    public TextField Customer_ID;
    public TextField User_ID;
    public TextField title;
    public DatePicker aptStartDate;
    public ComboBox startTime;
    public DatePicker aptEndDate;

    public void aptEndDateHandler(ActionEvent actionEvent) {
    }

    public void startTimeHandler(ActionEvent actionEvent) {
    }

    public void aptStartDateHandler(ActionEvent actionEvent) {
    }

    public void titleHandler(ActionEvent actionEvent) {
    }

    public void User_IDHandler(ActionEvent actionEvent) {
    }

    public void Customer_IDHandler(ActionEvent actionEvent) {
    }

    public void typeHandler(ActionEvent actionEvent) {
    }

    public void contactHandler(ActionEvent actionEvent) {
    }

    public void locationHandler(ActionEvent actionEvent) {
    }

    public void descriptionHandler(ActionEvent actionEvent) {
    }

    public void appointment_IDHandler(ActionEvent actionEvent) {
    }

    public void saveUpdateHandler(ActionEvent actionEvent) {
    }

    public void cancelUpdateHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
