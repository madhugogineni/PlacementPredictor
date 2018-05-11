package Connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnect {
	
	static Connection con=null;
	public static Connection getconn()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","miniproject", "miniproject");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con;
	}

}
