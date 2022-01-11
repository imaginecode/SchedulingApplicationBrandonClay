package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerScheduleReport {
    public ComboBox customerComboPick;
    public Button exitReport;
    public TableColumn customerID;
    public TableColumn end;
    public TableColumn start;
    public TableColumn typeDescription;
    public TableColumn title;
    public TableColumn appointmentID;

    public void customerComboPickHandler(ActionEvent actionEvent) {
    }

    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("ReportByZip");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
