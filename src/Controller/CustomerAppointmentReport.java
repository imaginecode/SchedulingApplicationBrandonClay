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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
    /**
     * Non static Observable List of appointments
     */
    public static ObservableList<Appointment> appointments;
    public ComboBox monthCombo;
    public Text monthTotal;
    // Month
    public static ObservableList<Integer> january = FXCollections.observableArrayList();
    public static ObservableList<Integer> february = FXCollections.observableArrayList();
    public static ObservableList<Integer> march = FXCollections.observableArrayList();
    public static ObservableList<Integer> april = FXCollections.observableArrayList();
    public static ObservableList<Integer> may = FXCollections.observableArrayList();
    public static ObservableList<Integer> june = FXCollections.observableArrayList();
    public static ObservableList<Integer> july = FXCollections.observableArrayList();
    public static ObservableList<Integer> august = FXCollections.observableArrayList();
    public static ObservableList<Integer> september = FXCollections.observableArrayList();
    public static ObservableList<Integer> october = FXCollections.observableArrayList();
    public static ObservableList<Integer> november = FXCollections.observableArrayList();
    public static ObservableList<Integer> december = FXCollections.observableArrayList();

    /**
     * Exits to main reports menu
     *
     * @param actionEvent button click of exit
     */
    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Second Lambda expression used to simplify loop for outputting data
     * Combo box handler that takes selected type and populates tableview also gets a count of how many of that type of appointment
     */
    public void aptTypesHandler(ActionEvent actionEvent) throws SQLException {
        int count = 0;
        if (!aptTypes.getSelectionModel().isEmpty()) {
            appointments = AppointmentsData.appointmentsByType(aptTypes.getSelectionModel().getSelectedItem().toString());
            //Initializing Appointment table values
            appointmentsTable.setItems(appointments);
            for (Appointment appointment : appointments) {
                ++count;
                List<String> pointList = new ArrayList();

                pointList.add("There are " + count + " of this appointment type");
                pointList.add(appointment.getType());

                pointList.forEach(p -> {
                    System.out.println(p);
                });

            }
            countVal.setText(String.valueOf(count));

        }
    }

    /**
     * Initialized type combo with approved types of appointments that are used in the add and edit appointment
     */
    private void typeComboInit() {
        ObservableList<String> types = FXCollections.observableArrayList();

        types.addAll("Sprint", "Demo", "Breakout", "Kick off");
        aptTypes.setItems(types);
    }

    /**
     * Calls methods that set default data in combo boxes for creating new appointments
     *
     * @param url            resolve relative paths for the root object
     * @param resourceBundle resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        january.clear();
        february.clear();
        march.clear();
        april.clear();
        may.clear();
        june.clear();
        july.clear();
        august.clear();
        september.clear();
        october.clear();
        november.clear();
        december.clear();
        typeComboInit();
        monthInitCombo();
        monthQuerySetter();


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

    /** Initializing combo box with month names to be selected by user to get a total amount for that month */
    public void monthInitCombo() {
        ObservableList<String> monthNames = FXCollections.observableArrayList();
        monthNames.addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

        monthCombo.setItems(monthNames);
    }

/** MonthCombo gets users selection and populates total text*/
    public void monthCombo(ActionEvent actionEvent) {
        if(monthCombo.getSelectionModel().getSelectedItem().equals("January"))
        {
            monthTotal.setText(String.valueOf(january.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("February"))
        {
            monthTotal.setText(String.valueOf(february.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("March"))
        {
            monthTotal.setText(String.valueOf(march.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("April"))
        {
            monthTotal.setText(String.valueOf(april.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("May"))
        {
            monthTotal.setText(String.valueOf(may.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("June"))
        {
            monthTotal.setText(String.valueOf(june.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("July"))
        {
            monthTotal.setText(String.valueOf(july.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("August"))
        {
            monthTotal.setText(String.valueOf(august.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("September"))
        {
            monthTotal.setText(String.valueOf(september.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("October"))
        {
            monthTotal.setText(String.valueOf(october.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("November"))
        {
            monthTotal.setText(String.valueOf(november.size()));}
        if(monthCombo.getSelectionModel().getSelectedItem().equals("December"))
        {
            monthTotal.setText(String.valueOf(december.size()));}



    }

/** */
    public void monthQuerySetter() {

        try {
            ObservableList<Appointment> appointments = AppointmentsData.getAllAppointments();

            if (appointments != null) {
                for (Appointment appointment : appointments) {
                    LocalDateTime date = appointment.getStart();
                    //Gets selection from combo box and if the appointment month matches it adds it to an observable list for that month
                    if (date.getMonth().equals(Month.of(1))) {
                        january.add(date.getMonthValue());
                    }
                    //feb
                    if (date.getMonth().equals(Month.of(2))) {
                        february.add(date.getMonthValue());
                    }
                    //mar
                    if (date.getMonth().equals(Month.of(3))) {
                        march.add(date.getMonthValue());
                    }
                    //apr
                    if (date.getMonth().equals(Month.of(4))) {
                        april.add(date.getMonthValue());
                    }
                    //may
                    if (date.getMonth().equals(Month.of(5))) {
                        may.add(date.getMonthValue());
                    }
                    //jun
                    if (date.getMonth().equals(Month.of(6))) {
                        june.add(date.getMonthValue());
                    }
                    //jul
                    if (date.getMonth().equals(Month.of(7))) {
                        july.add(date.getMonthValue());
                    }
                    //aug
                    if (date.getMonth().equals(Month.of(8))) {
                        august.add(date.getMonthValue());
                    }
                    //September
                    if (date.getMonth().equals(Month.of(9))) {
                        september.add(date.getMonthValue());
                    }
                    //October
                    if (date.getMonth().equals(Month.of(10))) {
                        october.add(date.getMonthValue());
                    }
                    //November
                    if (date.getMonth().equals(Month.of(11))) {
                        november.add(date.getMonthValue());
                    }
                    //December
                    if (date.getMonth().equals(Month.of(12))) {
                        december.add(date.getMonthValue());
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
