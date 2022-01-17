package Model;
/**
 *User Class used to create country objects for the scheduling program and assign values to object */
/** @author Brandon Clay */
public class User {
    private int userID;
    private String userName;
    private String password;
    /** User Constructor
     * @param userID Id of user
     * @param password Users password
     * @param userName  Username of user*/
    public User(int userID, String userName, String password) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }
    /**Get user id
     * @return userID ID of user  */
    public int getUserID() {
        return userID;
    }
    /**Set user ID */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    /**Get Users username
     * @return  userName  */
    public String getUserName() {
        return userName;
    }
    /**Set username */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**Get password of user
     * @return password */
    public String getPassword() {
        return password;
    }
    /**Set password of user */
    public void setPassword(String password) {
        this.password = password;
    }
}
