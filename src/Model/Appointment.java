package Model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *Appointment Class used to create appointment objects and assign values to object */
/** @author Brandon Clay */
public class Appointment {
    private int aptID;
    private String aptTitle;
    private String aptDescription;
    private String aptLocation;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;
    private int userID;
    private int contactID;

    /** Appointment constructor
     * @param aptID Appointment ID
     * @param aptTitle Appointment title
     * @param aptDescription  Appointment Description
     * @param aptLocation  Appointment Location
     * @param contactID ID of the contact associated with customer such as the marketeer who contacted them initially
     * @param type type of appointment
     * @param customerID ID of the customer
     * @param start Start date and time of appointment
     * @param end End date and time of appointment
     * @param userID ID of user logged into  scheduling application
     * */
    public Appointment(int aptID, String aptTitle, String aptDescription, String aptLocation, String type,
                       LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID)
    {
        this.aptID = aptID;
        this.aptTitle = aptTitle;
        this.aptDescription = aptDescription;
        this.aptLocation = aptLocation;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**Get appointment ID
     * @return aptID  */
    public int getAptID() {
        return aptID;
    }
    /**Sets appointment ID */
    public void setAptID(int aptID) {
        this.aptID = aptID;
    }
    /**Gets appointment Title
     * @return aptTitle title of appointment */
    public String getAptTitle() {
        return aptTitle;
    }
    /**Sets appointment title*/
    public void setAptTitle(String aptTitle) {
        this.aptTitle = aptTitle;
    }
    /**Gets appointment description
     * @return aptDescription description of appointment */
    public String getAptDescription() {
        return aptDescription;
    }
    /**Set appointment description*/
    public void setAptDescription(String aptDescription) {
        this.aptDescription = aptDescription;
    }
    /**Get appointment Location
     * @return appointment location */
    public String getAptLocation() {
        return aptLocation;
    }
    /**Set appointment location*/
    public void setAptLocation(String aptLocation) {
        this.aptLocation = aptLocation;
    }
    /**Get type of appointment
     * @return type Type of appointment */
    public String getType() {
        return type;
    }
    /**Set type of appointment */
    public void setType(String type) {
        this.type = type;
    }
    /**  Get start date and time of appointment
     * @return start time*/
    public LocalDateTime getStart() {
        return start;
    }
    /**Set start date and time of appointment */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**Get end date and time of appointment
     * @return end time */
    public LocalDateTime getEnd() {
        return end;
    }
    /**Set end date and time of appointment */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**Get CustomerID
     * @return customerID ID of customer */
    public int getCustomerID() {
        return customerID;
    }
    /**set customer ID  */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    /**Get UserID
     * @return userID ID of user */
    public int getUserID() {
        return userID;
    }
    /** Set userID */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**Get contact ID
     * @return  contactID ID for contact */
    public int getContactID() {
        return contactID;
    }
    /**Set Contact ID  */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
