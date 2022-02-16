package Controller;

import Data.AppointmentsData;
import Data.CustomersData;
import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**This class is customer appointment report that filters appointments by type and gets a count of how many appointments are of that type
 * @author Brandon Clay */
public class CustomerAppointmentReport implements Initializable {


    public Button exitReport;
    public Label countVal;
    public TableView appointmentsTable;
    public TableColumn appointmentID;
    public TableColumn description;
    public TableColumn place;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customerIDAptTable;
    public TableColumn userID;
    public ComboBox aptTypes;
    /** Non static Observable List of appointments*/
    public static ObservableList<Appointment> appointments;

/** Exits to main reports menu
 * @param actionEvent button click of exit */
    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Second Lambda expression used to simplify loop for outputting data
     * Combo box handler that takes selected type and populates tableview also gets a count of how many of that type of appointment*/
    public void aptTypesHandler(ActionEvent actionEvent) throws SQLException {
        int count = 0;
        if(!aptTypes.getSelectionModel().isEmpty()){
            appointments = AppointmentsData.appointmentsByType(aptTypes.getSelectionModel().getSelectedItem().toString());
            //Initializing Appointment table values
            appointmentsTable.setItems(appointments);
            for (Appointment appointment: appointments){
                ++count;
                List<String> pointList = new ArrayList();

                pointList.add("There are " + count + " of this appointment type");
                pointList.add(appointment.getType());

                pointList.forEach( p ->  { System.out.println(p); } );

            }
            countVal.setText(String.valueOf(count));

        }
    }
/**Initialized type combo with approved types of appointments that are used in the add and edit appointment */
    private void typeComboInit() {
        ObservableList<String> types = FXCollections.observableArrayList();

        types.addAll("Sprint","Demo","Breakout","Kick off");
        aptTypes.setItems(types);
    }

    /** Calls methods that set default data in combo boxes for creating new appointments
     * @param url resolve relative paths for the root object
     * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeComboInit();


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
