package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class connection {
	public static Connection con=null;
	PreparedStatement ps;
	
	public static Connection getConnection()
	{
		try
		{
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("dirver load..");
		//establish the connection 
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","manager");
		System.out.println("connection establish...");
		}//try
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	public void main(String args)
	{
		getConnection();
	}
	
}
