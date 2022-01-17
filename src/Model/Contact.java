package Model;
/**
 *Contact Class used to create contact objects  for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**Contact Constructor
     * @param contactID ID of contact
     * @param contactName Name of contact
     * @param email email of contact*/
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }
    /**Get contact ID
     * @return contactID ID of contact */
    public int getContactID() {
        return contactID;
    }
    /**Set ID of contact */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**Get name of contact
     * @return contactName Name of contact */
    public String getContactName() {
        return contactName;
    }
    /**Set name of contact */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** Get email of contact
     * @return email of contact*/
    public String getEmail() {
        return email;
    }
    /**Set email of contact */
    public void setEmail(String email) {
        this.email = email;
    }
}
