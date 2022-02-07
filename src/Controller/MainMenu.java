package Controller;

import Data.AppointmentsData;
import Data.CustomersData;
import Model.Appointment;
import Model.Customer;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main Menu Controller contains the navigation to the other views and also populates the data in customer and appointment
 * table view. MainMenu also posses the data to other controllers for actions such as modify customer and modify appointment*/
/** @author Brandon Clay */
public class MainMenu  implements Initializable {

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
    public ToggleGroup appointmentsToggle;
    public RadioButton allView;
    /** Non static Observable List of appointments*/
    public static ObservableList<Appointment> appointments;
    /** Observable List of customers*/
    public static ObservableList<Customer> customers;


    /**Reports Handler is the button for navigating to the reports menu
     * @param actionEvent button click */
    public void reportsHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Make appointment handler loads Add appointment menu
     * @param actionEvent button click */
    public void makeAppointmentHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentsAdd.fxml"));
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** Navigates to add customer form
     * @param actionEvent button click */
    public void addCustomerHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/CustomerAdd.fxml"));
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**Navigates to edit customer menu and passed data of selected customer
     * @param actionEvent button click */
    public void editCustomerHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/CustomerUpdate.fxml"));
        stage.setTitle("Update Customer");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** Deleted selected customer
     * @param actionEvent button click */
    public void deleteCustomerHandler(ActionEvent actionEvent) {
    }
    /** Navigates to edit appointment menu and passes data of selected appointment for editing
     * @param actionEvent button click */
    public void editAppointmentHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/AppointmentUpdate.fxml"));
        stage.setTitle("Edit Appointment");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Deletes selected appointment
     * @param actionEvent button */
    public void deleteAppointmentHandler(ActionEvent actionEvent) {
        appointmentsTable.getSelectionModel().getSelectedItem();
    }
    /**Changes tableview of appointments to a week time frame
     * @param actionEvent radio button selected */
    public void weekViewHandler(ActionEvent actionEvent) throws SQLException {
        try {
            appointments = AppointmentsData.getWeekAppointments();
            appointmentsTable.setItems(appointments);
            appointmentsTable.refresh();
        }

        catch(SQLException e){
            e.printStackTrace();
        }

    }
    /**Changes tableview of appointments to a month time frame
     * @param actionEvent radio button selected */
    public void monthViewHandler(ActionEvent actionEvent) {
        try {
            appointments = AppointmentsData.getMonthAppointments();
            appointmentsTable.setItems(appointments);
            appointmentsTable.refresh();
        }

        catch(SQLException e){
            e.printStackTrace();
        }
    }
    /**Changes tableview of appointments to all appointments
     * @param actionEvent radio button selected */
    public void allViewHandler(ActionEvent actionEvent) {
        try {
            appointments = AppointmentsData.getAllAppointments();
            appointmentsTable.setItems(appointments);
            appointmentsTable.refresh();
        }

        catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**Exit handler exit the program and confirm users action with dialog box
     * @param actionEvent exit button action */
    public void exitMainMenuHandler(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** Displays messages for empty login fields and invalid usernames and passwords
     * @param alertnum is for selecting which specific alert case is needed */
    public void errorMsgs(int alertnum) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            switch (alertnum) {
                case 1:
                    alert.setTitle("Confirm Delete");
                    alert.setHeaderText("Confirm Delete");
                    alert.setContentText("Confirm that you want Delete selected item");
                    alert.showAndWait();
                    break;
                case 2:
                    alert.setTitle("No item selected");
                    alert.setHeaderText("No item selected");
                    alert.setContentText("No item selected for deletion. Please click the item you want to delete.");
                    alert.showAndWait();
                    break;
                case 3:
                    alert.setTitle("Error deleting selected item");
                    alert.setHeaderText("Error deleting selected item");
                    alert.setContentText("Error deleting selected item");
                    alert.showAndWait();
                    break;
                case 4:
                    alert.setTitle("Selected item successfully deleted");
                    alert.setHeaderText("Selected item successfully deleted");
                    alert.setContentText("Selected item successfully deleted. Congrats!!");
                    alert.showAndWait();
                    break;


            }
    }

    /**Pulls data into tableview for customer and appointment tableviews
     * @param url resolve relative paths for the root object
     * @param resourceBundle resources used to localize the root object
     * catches SQLException prints stack trace */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointments = AppointmentsData.getAllAppointments();
            customers = CustomersData.getAllCustomers();


            //Initializing Appointment table values
            appointmentsTable.setItems(appointments);
            appointmentID.setCellValueFactory(new PropertyValueFactory<>("aptID"));
            description.setCellValueFactory(new PropertyValueFactory<>("aptDescription"));
            place.setCellValueFactory(new PropertyValueFactory<>("aptLocation"));
            contact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            start.setCellValueFactory(new PropertyValueFactory<>("start"));
            end.setCellValueFactory(new PropertyValueFactory<>("end"));
            customerIDAptTable.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            userID.setCellValueFactory(new PropertyValueFactory<>("userID"));

            //Initializing Customer table values
            customersTable.setItems(customers);
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            zip.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));



        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
