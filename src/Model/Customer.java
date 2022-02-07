package Model;
/**
 *Customer Class used to create customer objects  for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private int divisionID;
    private String phone;

    /** Customer Constructor
     * @param customerID ID of customer
     * @param customerName Name of customer
     * @param address address of customer
     * @param postalCode Zip/PostalCode of customer
     * @param phone phone number of customer
     * @param divisionID Division ID of customer*/
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionID = divisionID;
        this.phone = phone;
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
    public String getPostalCode() {
        return postalCode;
    }
    /**Set postal code of customer */
    public void setPostalCode(String postalCode) {
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
    /**Get Phone Number of customer
     * @return phone number */
    public String getPhone() {
        return phone;
    }
    /**Set phone number of customer */
    public void setPhone(String phone) {
        this.phone = phone;
    }



}
