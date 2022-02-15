package Controller;

import Data.ContactsData;
import Data.CustomersData;
import Model.Contact;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/** This class shows 3 reports that can be generated and displays areas where users can input information to generate these reports
 * @author Brandon Clay */
public class Reports implements Initializable {
    public Button exitReports;
    public Button zipReports;
    public Button customerAppointmentsReport;
    public ComboBox pickContact;
    public Button contactScheduleBtn;

    /** Passes data after the generate button is passed for selected contact
     * @param actionEvent button click of generate */
    public void contactScheduleHandler(ActionEvent actionEvent) throws SQLException, IOException {

        try{String selectedContact;
            selectedContact = pickContact.getSelectionModel().getSelectedItem().toString();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/contactSchedule.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ContactScheduleReport controller = loader.getController();
            controller.dataHandoff(selectedContact);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();}

        catch (NullPointerException e){
            e.getMessage();
            Errors(1);
        }





    }

    /**Allows user to enter zip code for a report that shows appointments by zip code of customer
     * @param actionEvent button click of generate  */
    public void searchPostalCodeHandler(ActionEvent actionEvent) throws IOException {

    }

    public void zipReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/ReportByZip.fxml"));
        stage.setTitle("ReportByZip");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Exits schedule report menu and goes to main menu
     * @param actionEvent exit button click */
    public void ExitReportsHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Goes to contact appointment handler controller and view and passes data from selected contact object
     * @param actionEvent button click of Generate */
    public void customerAppointmentsReportHandler(ActionEvent actionEvent) throws IOException, SQLException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/appointmentReport.fxml"));
        stage.setTitle("Appointment Report");
        stage.setScene(new Scene(scene));
        stage.show();



    }

    /** initializes contact combo  */
    public void initContactCombo(){
        ObservableList<String> contactList = FXCollections.observableArrayList();

        try{
            if (ContactsData.getAllContacts() != null){
                for (Contact contact: ContactsData.getAllContacts()) {
                    contactList.add(contact.getContactName());
                }
            }
            pickContact.setItems(contactList);
        }

        catch (SQLException e){
            System.out.println(e.getStackTrace());
        }
    }

    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void Errors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("A contact must be selected");
                alert.setHeaderText("A contact must be selected");
                alert.setContentText("A contact must be selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("A country must be selected");
                alert.setHeaderText("A country must be selected");
                alert.setContentText("A country must be selected before getting first level division");
                break;


        }
    }

    /** Calls methods that set default data in combo boxes for creating new appointments
     * @param url resolve relative paths for the root object
     * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initContactCombo();
    }

}
