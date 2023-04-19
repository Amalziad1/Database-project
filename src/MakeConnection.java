import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MakeConnection 
{
private static String dbURL; 
private static String username = "root";
	private static String password = "zahia123";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	public static String dbName = "shop";
	public static Connection con;
	
	
	 
	public static void connect() throws Exception
	{
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT&allowPublicKeyRetrieval=True";

		
		Properties p = new Properties();
		p.setProperty("user", username);
		p.setProperty("password", password);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

		 	
	}
}
