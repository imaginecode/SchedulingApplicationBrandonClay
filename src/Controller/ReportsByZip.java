package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsByZip {
    public TableView customersByZipTable;
    public TableColumn Name;
    public TableColumn Customer_IDPostalCode;
    public Button exitReport;
    public TextField searchPostalCode;

    public void customersByZipTableHandler(SortEvent<TableView> tableViewSortEvent) {
    }

    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void searchPostalCodeHandler(ActionEvent actionEvent) {
    }
}
