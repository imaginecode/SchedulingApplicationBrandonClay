package Data;

import Utilities.JDBC;
import com.mysql.cj.protocol.Resultset;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserNamePassQuery {

    public static boolean validateUser(String username, String password) throws SQLException {
        //Select Statement
        String  findUserStatement = "SELECT * FROM users WHERE UserName=? AND Password=?";

        DBQuery.setPreparedStatement(JDBC.getConnection(), findUserStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        //Key value mapping for username and password
        ps.setString(1, username);
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
            return true;
        }



    }
}
