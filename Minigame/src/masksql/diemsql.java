package masksql;

import java.sql.*;
import Login.CPlay;
import Connectest.JDBCConnection;

public class diemsql 
{
	int mask;
	public diemsql(){}
	
	public int getmark(String username)
	{
		PreparedStatement pst = null;
		Connection conn = null;
		try
		{
			conn = JDBCConnection.getConnection();
			
			String sql = "select * from mask where userr = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
        
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				mask = rs.getInt(3);
		}
		catch (Exception e1) {}
		return mask;
	}
	
	public void savemark(String username, int mark)
	{
		PreparedStatement pst = null;
		Connection conn = null;
		try
		{
			conn = JDBCConnection.getConnection();
			
			String sql = "update Mask set mark = ? where userr = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1	,mark);
			pst.setString(2, username);
        
			int rowAffected = pst.executeUpdate();
		}
		catch (Exception e1) {}
	}
}