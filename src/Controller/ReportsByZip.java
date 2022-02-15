package Controller;

import Data.CustomersData;
import Model.Customer;
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
import java.util.Optional;
import java.util.ResourceBundle;

/** Report of customers by zip code
 * @author Brandon Clay*/
public class ReportsByZip implements Initializable {
    public TableColumn Name;
    public Button exitReport;
    public TextField searchPostalCode;
    public TableView customersTable;
    public TableColumn customerID;
    public TableColumn name;
    public TableColumn zip;
    public TableColumn phone;
    public TableColumn address;
    /** Observable List of customers*/
    public static ObservableList<Customer> customers;
    public Button search;


    public void exitReportHandler(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        stage.setTitle("Reports");
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Takes text entered by user in field for postal code and searches against the data base and returns customers with that zip
     * @param actionEvent button click of search */
    public void searchHandler(ActionEvent actionEvent) {
        try{customers = CustomersData.getCustomerbyZip(searchPostalCode.getText());
            customersTable.setItems(customers);
            if(customers.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Postal Code does not exist please try an existing code");
                alert.showAndWait();
            }
        }
        catch (SQLException e){
            System.out.println("Postal Code does not exist please try an existing code");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Postal Code does not exist please try an existing code");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initializing Customer table values

        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        zip.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }


}
