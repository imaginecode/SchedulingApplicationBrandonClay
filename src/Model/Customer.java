package Model;
/**
 *Customer Class used to create customer objects  for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private int postalCode;
    private int divisionID;

    /** Customer Constructor
     * @param customerID ID of customer
     * @param address address of customer
     * @param customerName Name of customer
     * @param divisionID Division ID of customer
     * @param postalCode Zip/PostalCode of customer*/
    public Customer(int customerID, String customerName, String address, int postalCode, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionID = divisionID;
    }
    /** Gets customer ID
     * @return customerID Id of customer */
    public int getCustomerID() {
        return customerID;
    }
    /**Set ID of Customer */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**get Name of customer
     * @return customerName */
    public String getCustomerName() {
        return customerName;
    }
    /**Set customer name */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /** Get address of customer
     * @return address of customer */
    public String getAddress() {
        return address;
    }
    /** Set address of customer*/
    public void setAddress(String address) {
        this.address = address;
    }
    /**Get postal code of customer
     * @return postalCode */
    public int getPostalCode() {
        return postalCode;
    }
    /**Set postal code of customer */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    /**Get division ID of customer
     * @return divisionID */
    public int getDivisionID() {
        return divisionID;
    }
    /**Set divisionID of customer */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
