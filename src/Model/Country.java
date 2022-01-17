package Model;

/**
 *Country Class used to create country objects for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class Country {
    private int countryID;
    private String country;

    /** Country contructor
     * @param country name of country
     * @param countryID ID of country*/
    public Country(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }
    /** Get country ID
     * @return countryID ID of country */
    public int getCountryID() {
        return countryID;
    }
    /** set country ID */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }
    /**Get name of country
     * @return  country name */
    public String getCountry() {
        return country;
    }
    /**Set country name */
    public void setCountry(String country) {
        this.country = country;
    }
}
