package Model;
/**
 *First level divisions Class used to create division objects for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class FirstLvlDivisions {
    private int divisionID;
    private String division;
    private int countryID;

    /** First level division constructor
     *@param countryID ID of country
     *@param divisionID ID of division
     *@param division name of division*/
    public FirstLvlDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;

    }
     /**Get division ID
      * @return divisionID, ID of division */
    public int getDivisionID() {
    return divisionID;
    }
    /**Set Division ID  */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**Get  name division
     * @return division */
    public String getDivision() {
        return division;
    }
    /**Set division */
    public void setDivision(String division) {
        this.division = division;
    }
    /**Get country ID
     * @return countryID, ID of country */
    public int getCountryID() {
        return countryID;
    }
    /**Set ID of country */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }




}
