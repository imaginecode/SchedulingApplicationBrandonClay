package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery
{
    private static PreparedStatement statement; //Statement Reference

    //create statement object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException
    {
        statement = conn.prepareStatement(sqlStatement);
    }
    //Return Statement Object
    public static PreparedStatement getPreparedStatement()
    {
        return statement;
    }

}
