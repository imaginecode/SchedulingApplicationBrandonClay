package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Reports {
    public Button genCustomerSchedule;
    public TextField searchPostalCode;
    public Button exitReports;
    public ComboBox pickCustomerSchedule;
    public Button zipReports;
    public Button appointmentsByTypeMonth;
    public Button customerAppointmentsReport;

    public void genCustomerScheduleHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/customerScheduleReport.fxml"));
        stage.setTitle("Customer Schedule Report");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void pickCustomerScheduleHandler(ActionEvent actionEvent)  {

    }


   /** Allows user to enter zip code for a report that shows appointments by zip code of customer */
    public void searchPostalCodeHandler(ActionEvent actionEvent) throws IOException {

    }

    public void appointmentsByTypeMonthHandler(ActionEvent actionEvent) throws IOException {

    }

    public void zipReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/ReportByZip.fxml"));
        stage.setTitle("ReportByZip");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void ExitReportsHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setTitle("Home");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void customerAppointmentsReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/appointmentReport.fxml"));
        stage.setTitle("Appointments Report");
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
