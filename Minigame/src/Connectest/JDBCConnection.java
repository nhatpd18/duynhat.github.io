package Connectest;
import java.sql.*;
public class JDBCConnection {
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minigame","nhat","123456");
			return con;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}