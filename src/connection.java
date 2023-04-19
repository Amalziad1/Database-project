 
import java.sql.*;
import java.util.Properties;


public class connection {
	private static String dbURL; 
 
	private static String dbUsername = "root";
	private static String dbPassword = "zahia123";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	public static String dbName = "shop";
	public static Connection con;
	
	
	
	public static void connectDB() throws ClassNotFoundException, SQLException {
		
		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT&allowPublicKeyRetrieval=True";

		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}
	
//    private  Connection con;
//
//    public  void connect() {
//        try {
//            String url = "jdbc:mysql://hostname:3306/store";
//            String user = "root";
//            String password = "1234";
//            con = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public  ResultSet query(String query) {
//        try {
//            Statement stmt = con.createStatement();
//            return stmt.executeQuery(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public  void close() {
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    
	
}