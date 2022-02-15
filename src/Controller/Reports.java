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
/** This class shows 3 reports that can be generated and displays areas where users can input information to generate these reports */
public class Reports implements Initializable {
    public Button genCustomerSchedule;
    public TextField searchPostalCode;
    public Button exitReports;
    public ComboBox pickCustomerSchedule;
    public Button zipReports;
    public Button customerAppointmentsReport;
    public ComboBox pickContact;

    /** Generates customer schedule based on selected customer
     * @param actionEvent button click of Generate*/
    public void genCustomerScheduleHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/customerScheduleReport.fxml"));
        stage.setTitle("Customer Schedule Report");
        stage.setScene(new Scene(scene));
        stage.show();
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

    /**Goes to customer appointment handler controller and view and passes data from selected customer object
     * @param actionEvent button click of Generate */
    public void customerAppointmentsReportHandler(ActionEvent actionEvent) throws IOException, SQLException {
        if(pickCustomerSchedule.getSelectionModel().getSelectedItem() != null){

            Contact selectedContact;
            selectedContact = (Contact) pickCustomerSchedule.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/customerScheduleReport.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            CustomerScheduleReport controller = loader.getController();
            controller.dataHandoff(selectedContact);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }

        else  {
            errorMsgs(1);
        }

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/appointmentReport.fxml"));
        stage.setTitle("Appointments Report");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**Populates all customer names from query in combo box */
    public void customerNameCombo() {
        ObservableList<String> customerNameList = FXCollections.observableArrayList();

        try {
            if (CustomersData.getAllCustomers() != null) {
                for (Customer customer : CustomersData.getAllCustomers()) {
                    customerNameList.add(customer.getCustomerName());
                }
            }
            pickCustomerSchedule.setItems(customerNameList);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
    }

    /** Displays messages for empty login fields and invalid usernames and passwords
     * @param alertnum is for selecting which specific alert case is needed */
    public void errorMsgs(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {

            case 1:
                alert.setTitle("Please Select a customer");
                alert.setHeaderText("Please Select a customer");
                alert.setContentText("Please Select a customer you wish to make an appointment for.");
                alert.showAndWait();
                break;



        }
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

    /** Calls methods that set default data in combo boxes for creating new appointments
     * @param url resolve relative paths for the root object
     * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initContactCombo();
    }

}
