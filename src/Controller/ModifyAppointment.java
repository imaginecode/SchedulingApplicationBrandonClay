package Controller;


import Data.ContactsData;
import Model.Appointment;
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
import java.sql.SQLException;

public class ModifyAppointment {

    public Button cancelUpdate;
    public Button saveUpdate;
    public TextField appointment_ID;
    public TextField description;
    public DatePicker aptStartDate;
    public ComboBox startTime;
    public ComboBox endAptTime;
    public ComboBox aptLocationCombo;
    public ComboBox contactCombo;
    public ComboBox typeCombo;
    public ComboBox customerCombo;
    public ComboBox userIDCombo;
    public TextField title;


    public void saveUpdateHandler(ActionEvent actionEvent) {
    }

    public void cancelUpdateHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }
        public void dataHandoff(Appointment selectedAppointment){
        try {appointment_ID.setText(String.valueOf(selectedAppointment.getAptID()));
            description.setText(selectedAppointment.getAptDescription());
            aptStartDate.setValue(selectedAppointment.getEnd().toLocalDate());
            startTime.setValue(selectedAppointment.getStart().toLocalTime());
            endAptTime.setValue(selectedAppointment.getEnd().toLocalTime());
            aptLocationCombo.setValue(selectedAppointment.getAptLocation());
            contactCombo.setValue(ContactsData.getContactNameByID(selectedAppointment.getContactID()));
            contactCombo.setValue(selectedAppointment.getAptLocation());
            typeCombo.setValue(selectedAppointment.getType());
            customerCombo.setValue(selectedAppointment.getCustomerID());
            userIDCombo.setValue(selectedAppointment.getUserID());
            title.setText(selectedAppointment.getAptTitle());

        }


        catch(SQLException e){
                e.getStackTrace();
            }

    }
}
