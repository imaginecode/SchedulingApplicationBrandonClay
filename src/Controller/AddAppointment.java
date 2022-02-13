package Controller;

import Data.AppointmentsData;
import Data.ContactsData;
import Data.CustomersData;
import Data.UserNamePassQuery;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This class contains Adds appointments to the database and performs checks to make sure it is a valid appointment before insert query
 /** @author Brandon Clay */
public class AddAppointment implements Initializable{
    public ComboBox<String> contactCombo;
    public ComboBox <String> endTime;
    public ComboBox <String> startTime;
    public TextField title;
    public TextField aptDescription;
    public TextField appointment_ID;
    public Button SaveAptAdd;
    public Button cancelAptAdd;
    public DatePicker aptStartDate;
    public ComboBox <Integer> customerCombo;
    public ComboBox <Integer> userIDCombo;
    public ComboBox <String> typeCombo;
    public ComboBox <String> aptLocationCombo;

/** Cancels and returns to MainMenu and confirms users action with a dialog box
 * @param actionEvent button click of cancel button
 * @throws IOException thrown while accessing information using streams, files and directories*/
    public void cancelAptAddHandler(ActionEvent actionEvent) throws IOException {




        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && (result.get() == ButtonType.OK)) {

            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setTitle("Home Screen");
            stage.setScene(new Scene(scene));
            stage.show();
        }

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home Screen");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** */
    public void SaveAptAddHandler(ActionEvent actionEvent) throws IOException, SQLException {

        LocalDateTime startAptTime = LocalDateTime.of(aptStartDate.getValue(), LocalTime.parse(startTime.getSelectionModel().getSelectedItem()));
        LocalDateTime endAptTime = LocalDateTime.of(aptStartDate.getValue(), LocalTime.parse(endTime.getSelectionModel().getSelectedItem()));
        Contact contactID = ContactsData.getContactByID(contactCombo.getSelectionModel().getSelectedItem());
        //ContactID to pass into new appointment insert query
        Integer contactToPass = contactID.getContactID();


      try{

          if (appointmentChecks()){
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Save");
              Optional<ButtonType> result = alert.showAndWait();
              if(result.isPresent() && (result.get() == ButtonType.OK)) {
                  AppointmentsData.newAppointment(title.getText(), aptDescription.getText(), aptLocationCombo.getSelectionModel().getSelectedItem(),
                          typeCombo.getSelectionModel().getSelectedItem(), startAptTime, endAptTime, customerCombo.getSelectionModel().getSelectedItem(),
                          userIDCombo.getSelectionModel().getSelectedItem(), contactID.getContactID());

                  Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                  Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
                  stage.setTitle("Home Screen");
                  stage.setScene(new Scene(scene));
                  stage.show();
              }
          }

      }
      catch(SQLException e){
          e.getStackTrace();
      }



    }
/**Checks to make sure appointment is valid fields are not empty as well as checking for overlapping appointments,
 * valid dates and times
 * @return boolean of t/f for if the appointment is valid if (f) then alerts are displayed*/
    private boolean appointmentChecks() {

        //Checking to make sure text fields aren't empty
        if(contactCombo.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(endTime.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(startTime.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(title.getText().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(aptDescription.getText().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(aptStartDate.getValue() == null) {
            addErrors(1);
            return false;
        }
        if(customerCombo.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(userIDCombo.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(typeCombo.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }
        if(aptLocationCombo.getSelectionModel().isEmpty()) {
            addErrors(1);
            return false;
        }

        //Checking to make sure end time is after start time
        LocalTime start = LocalTime.parse(startTime.getSelectionModel().getSelectedItem());
        LocalTime end = LocalTime.parse(endTime.getSelectionModel().getSelectedItem());
        long diffBetweenAptTimes = ChronoUnit.MINUTES.between(end,start);
//        System.out.println(diffBetweenAptTimes);
        // Appointments are min 30 minutes and appointments can't start at the same time so difference must be less than 0 and !=
        if(diffBetweenAptTimes >= 30 || diffBetweenAptTimes == 0){
            addErrors(6);
            return false;
        }

        //Checks that appointment date is in the future
        if (aptStartDate.getValue().isBefore(LocalDateTime.now().toLocalDate())) {
            addErrors(5);
            return false;
        }


        //Checking for overlapping appointments
        try{
            ObservableList<Appointment> existingAppointments = FXCollections.observableArrayList();
            existingAppointments = AppointmentsData.getAllAppointments();



            for(Appointment appointment : existingAppointments){
                LocalDate existingDate = appointment.getStart().toLocalDate();

                if(aptStartDate.getValue().isEqual(existingDate)){
                    LocalTime existingStart = appointment.getStart().toLocalTime();
                    LocalTime existingEnd = appointment.getEnd().toLocalTime();
                    LocalTime potentialStart = LocalTime.parse(startTime.getSelectionModel().getSelectedItem());
                    LocalTime potentialEnd = LocalTime.parse(endTime.getSelectionModel().getSelectedItem());

                    if(potentialStart.isBefore(existingStart) & potentialEnd.isAfter(existingEnd))
                    {
                        addErrors(7);
                        return false;
                    }
                    if(potentialStart.isBefore(existingEnd) & potentialEnd.isAfter(existingEnd)) {
                        addErrors(7);
                        return false;
                    }
                    if((potentialStart.isAfter(existingStart) || potentialStart.equals(existingStart)) & (potentialEnd.isBefore(existingEnd) || potentialEnd.equals(existingEnd))){
                        addErrors(7);
                        return false;
                    }

                }


            }



        }
        catch(SQLException e){
            e.printStackTrace();
        }


        return true;
    }



    /**Populates type combo with default data*/
    public void aptTypeCombo() {
        ObservableList<String> aptTypes = FXCollections.observableArrayList();

        aptTypes.addAll("Sprint","Demo","Breakout","Kick off");
        typeCombo.setItems(aptTypes);
    }

    /**Populates location combo with default data*/
    public void aptLocationCombo(){
        ObservableList<String> aptLocations = FXCollections.observableArrayList();
        aptLocations.addAll("ConferenceRoom1", "ConferenceRoom2", "ConferenceRoom3");

        aptLocationCombo.setItems(aptLocations);
    }

    /**Populates type combo with data from query of all contacts*/
    public void contactCombo(){
        ObservableList<String> contactList = FXCollections.observableArrayList();
        try {

            if (ContactsData.getAllContacts() != null){
                for (Contact contact: ContactsData.getAllContacts()) {
                    contactList.add(contact.getContactName());
                }
            }
            contactCombo.setItems(contactList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Populates all customer ID from query in combo box*/
    public void customerIDCombo(){
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();

        try{
            if (CustomersData.getAllCustomers() != null){
                for (Customer customer: CustomersData.getAllCustomers()) {
                    customerIDList.add(customer.getCustomerID());
                }
            }
            customerCombo.setItems(customerIDList);
        }

        catch (SQLException e){
        System.out.println(e.getStackTrace());
        }
    }

    /**Populates all user IDs in combo box from query of users */
    public void userIDCombo(){
        ObservableList<Integer> userList = FXCollections.observableArrayList();
        try {

            if (UserNamePassQuery.allUsers() != null){
                for (User user: UserNamePassQuery.allUsers()) {
                    userList.add(user.getUserID());
                }
            }
            userIDCombo.setItems(userList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** Populates time combos in 30 min increments for both start and end combos. Start and end times are shown in local times that are between 8am-10pm EST
     *  to make it impossible for a user to schedule an appointment outside of business hours*/
    public void timeCombo(){
        ObservableList<String> aptSlotsStart = FXCollections.observableArrayList();
        ObservableList<String> aptSlotsEnd = FXCollections.observableArrayList();

        LocalTime one = LocalTime.now();
        ZoneId zone2 = ZoneId.of("America/New_York");
        LocalTime two = LocalTime.now(zone2);
// Finding the difference between local time and EST
        int diff = (int) ChronoUnit.HOURS.between(two, one);

//        ZoneId zone1 = ZoneId.of(String.valueOf(ZoneId.systemDefault()));

        //Second way of calculating offset in an int format. Not the preferred method.

////        ZoneId zone2 = ZoneId.of("America/New_York");
//        //Arbitrary date passed in so you can see the how much time in hours in an INT format is between two zones which is then passed into startHour
//        LocalDateTime dateTime = LocalDateTime.of(2022, 2, 9, 8, 0);
//        ZonedDateTime localDateTime = ZonedDateTime.of(dateTime, zone1);
//        ZonedDateTime ESTDateTime = localDateTime.withZoneSameInstant(zone2);
////      System.out.println("Difference between two time zones in hours = "+ESTDateTime.getOffset().getTotalSeconds());
////      System.out.println("Difference between two time zones in hours = "+localDateTime.getOffset());
//
//        //Time difference between local time and EST / 3600 to put it into hours
//        int timeDiffInHours = (localDateTime.getOffset().getTotalSeconds() - ESTDateTime.getOffset().getTotalSeconds()) / 3600;



        //Business starts  at 8am and then difference is added to convert  starttime from EST to local

        LocalTime Hour = LocalTime.of(8 + diff, 0);
        if(diff > 0) {
            diff = diff + 1;
        }


        int i = 0;
        aptSlotsStart.add(Hour.toString());
        while(i < 28){

            Hour = Hour.plusMinutes(30);
            if(i <27){
                aptSlotsStart.add(Hour.toString());
            }

            aptSlotsEnd.add(Hour.toString());
            ++i;

        }
        startTime.setItems(aptSlotsStart);

        endTime.setItems(aptSlotsEnd);
    }




    /** Displays messages for add appointment errors such as empty fields
     * @param alertnum is for selecting which specific alert case is needed */
    public void addErrors(int alertnum) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        switch (alertnum) {
            case 1:
                alert.setTitle("All fields must be filled");
                alert.setHeaderText("All fields must be filled");
                alert.setContentText("All fields  and combo boxes must be filled or selected");
                alert.showAndWait();
                break;
            case 2:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Save");
                alert.setHeaderText("Confirm Save");
                alert.setContentText("Confirm Save");
                Optional<ButtonType> yesSave = alert.showAndWait();
                break;
            case 3:
                alert.setAlertType(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Cancel");
                alert.setHeaderText("Confirm Cancel");
                alert.setContentText("Confirm Cancel");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Date must be in the future");
                alert.setHeaderText("Date must be in the future");
                alert.setContentText("Date must be in the future as appointments can not be made in the past.");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("End time can't be before Start Time ");
                alert.setHeaderText("End time can't be before Start Time");
                alert.setContentText("End time can't be before Start Time. Also start and end times can't be the same");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Appointment overlap");
                alert.setHeaderText("Appointment overlap");
                alert.setContentText("Appointment overlap, you can not schedule an appointment that overlaps with another customers appointment." +
                        " The appointment you overlap with is" );
                alert.showAndWait();
                break;



        }
    }

/** Calls methods that set default data in combo boxes for creating new appointments
 * @param url resolve relative paths for the root object
 * @param resourceBundle resources used to localize the root object */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aptTypeCombo();
        aptLocationCombo();
        contactCombo();
        customerIDCombo();
        userIDCombo();
        timeCombo();
    }
}
