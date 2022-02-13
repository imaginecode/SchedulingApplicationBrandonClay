package Data;

import Model.Contact;
import Model.Country;
import Utilities.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains MYSQL queries for getting country and first lvl data
 /** @author Brandon Clay */
public class AddressData {

    /** Query for all countries
     * @return ResultSet a result set of all countries
     * @throws SQLException and prints error message
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countryResultSet = FXCollections.observableArrayList();
        String query = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();


        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //Going through result set
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");



//                //Creating new country object
                Country newCountry = new Country(countryID,countryName);
                countryResultSet.add(newCountry);

            }
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace());
            return null;
        }
        return countryResultSet;

    }




    }
