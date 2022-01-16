package Data;

import Utilities.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserName and password query that is used in the login screen
 */

/**@author Brandon Clay */

public class UserNamePassQuery {
/** Queries username and password entered in the login screen
 * @param username username checked
 * @param password  password checked
 * @throws SQLException stack trace is printed for reference */
    public static boolean validateUser(String username, String password) throws SQLException {
        //Select Statement
        String  findUserStatement = "SELECT * FROM users WHERE UserName=? AND Password=?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), findUserStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //Key value mapping for username and password
        ps.setString(1,username);
        ps.setString(2,password);
        //Execute Statement
        try
        {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            return rs.next();
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
            System.out.println("Invalid Credentials entered this is on the query page");
            System.out.println(username + password);
            return false;
        }



    }
}
