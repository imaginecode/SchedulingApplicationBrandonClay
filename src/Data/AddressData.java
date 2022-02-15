package Data;

import Model.Contact;
import Model.Country;
import Model.FirstLvlDivisions;
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

    /** Querying first level division data that have a country ID of selected country
     * @param selectedCountryID countryId that to filter first lvl divisions
     * @throws SQLException stack trace printed out in catch block*/
    public static ObservableList<FirstLvlDivisions> getFirstLVLByID(int selectedCountryID) throws SQLException {
        ObservableList<FirstLvlDivisions> firstLevelResultSet = FXCollections.observableArrayList();
        String query = "SELECT * FROM first_level_divisions WHERE Country_ID =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setString(1, String.valueOf(selectedCountryID));

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //Going through result set
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");


//                //Creating new country object
                FirstLvlDivisions firstLvlDivisions = new FirstLvlDivisions(divisionID,divisionName,selectedCountryID);
                firstLevelResultSet.add(firstLvlDivisions);

            }
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace());
            System.out.println("Breaking at first level div query");
            return null;
        }
        return firstLevelResultSet;

    }

    /** Getting countryID by name
     * @return id of country that has the passed in name*/
    public static Country getCountryID(String nameOfCountry) throws SQLException {
        String query = "SELECT * FROM countries WHERE country =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, nameOfCountry);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //Going through result set
            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");



//                //Creating new country object
                return new Country(countryID,countryName);

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

/** finding the first level ID by passing in the selected item string. ie Oklahoma
 * @param selectedItem selected first lvl division name
 * @return  the division ID of selectedItem query*/
    public static int getFirstLvlID(String selectedItem) throws SQLException {

        String query = "SELECT * FROM first_level_divisions WHERE Division =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setString(1, selectedItem);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
//            //Going through result set
            while (rs.next()) {
                return rs.getInt("Division_ID");

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Didn't get first level ID");
            return -1;
        }
        return -1;
    }

    /** Finding division name and country ID by querying divisionID */
    public static FirstLvlDivisions getDivisionName(int selectedItem) throws SQLException {

        String query = "SELECT * FROM first_level_divisions WHERE Division_ID =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, selectedItem);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
//            //Going through result set
            while (rs.next()) {

               String divisionName = rs.getString("Division");
               int countryID = rs.getInt("Country_ID");

//                //Creating new country object
                return new FirstLvlDivisions(selectedItem,divisionName,countryID);

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could exe query for getting Division ID name and Country ID");
            return null;
        }
        return null;
    }

    /** Getting country name by ID
     * @return name of country that has the passed in name*/
    public static Country getCountryName(int countryID) throws SQLException {
        String query = "SELECT * FROM countries WHERE Country_ID =?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), query);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, countryID);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //Going through result set
            while (rs.next()) {
                String countryName = rs.getString("Country");



//                //Creating new country object
                return new Country(countryID,countryName);

            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


}
