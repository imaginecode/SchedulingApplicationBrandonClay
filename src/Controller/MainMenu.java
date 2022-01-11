package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    public Button makeAppointment;
    public Button Reports;
    public TableView appointmentsTable;
    public TableView customersTable;
    public Button deleteAppointment;
    public Button editAppointment;
    public Button deleteCustomer;
    public Button editCustomer;
    public Button exit;
    public Button addCustomer;
    public TableColumn customerID;
    public RadioButton weekView;
    public RadioButton monthView;
    public TableColumn name;
    public TableColumn zip;
    public TableColumn phone;
    public TableColumn address;
    public TableColumn appointmentID;
    public AnchorPane customerTable;
    public TableColumn description;
    public TableColumn place;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customerIDAptTable;
    public TableColumn userID;


    public void reportsHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void makeAppointmentHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentsAdd.fxml"));
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void addCustomerHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/CustomerAdd.fxml"));
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Exit handler exit the program and confirm users action with dialog box
     * @param actionEvent exit button action */
    public void exitMainMenuHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void editCustomerHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/CustomerUpdate.fxml"));
        stage.setTitle("Update Customer");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void deleteCustomerHandler(ActionEvent actionEvent) {
    }

    public void editAppointmentHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentUpdate.fxml"));
        stage.setTitle("Edit Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void deleteAppointmentHandler(ActionEvent actionEvent) {
    }

    public void weekViewHandler(ActionEvent actionEvent) {
    }

    public void monthViewHandler(ActionEvent actionEvent) {
    }
}
