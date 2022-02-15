package Controller;

import Data.AppointmentsData;
import Data.ContactsData;
import Data.CustomersData;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/** This class generates a schedule for the selected contact */
public class ContactScheduleReport {
    public Button exitReport;
    public TableColumn end;
    public TableColumn start;
    public TableColumn title;
    public TableColumn appointmentID;
    public TableView appointmentsTable;
    public TableColumn userID;
    public TableColumn description;
    public TableColumn place;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn customerIDAptTable;
    /** Non static Observable List of appointments*/
    public static ObservableList<Appointment> appointments;

/**Exits customer schedule report and goes back to reports selection menu
 * @param actionEvent exit button click */
    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Hands off the selected Contact so that the tableview can be populated
     * @param selectedContact*/
    public void dataHandoff(String selectedContact) throws SQLException {

        int CID = ContactsData.getContactByID(selectedContact).getContactID();
        appointments = AppointmentsData.appointmentsByContactID(CID);




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
    }

}
