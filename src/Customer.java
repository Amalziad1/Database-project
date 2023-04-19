import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
	public  static ArrayList<Customer> data = new ArrayList<Customer>();
	
	public int C_ID;
	public String C_Name;
	public String C_Address;
	public ArrayList<String> Phones = new ArrayList<>(); 
	public String C_Phone;
	
	
	
	
	public Customer() {
		
	}
	public Customer(int C_ID, String C_Name, String C_Address) {
		// TODO Auto-generated constructor stub
		this.C_ID=C_ID;
		this.C_Name=C_Name;
		this.C_Address=C_Address;
		
	}
	public Customer(int C_ID, String C_Name, String C_Address, ArrayList <String>Phones) {
		// TODO Auto-generated constructor stub
		this.C_ID=C_ID;
		this.C_Name=C_Name;
		this.C_Address=C_Address;
		this.Phones=Phones;
		
	}




	public int getC_ID() {
		return C_ID;
	}
	public void setC_ID(int c_ID) {
		C_ID = c_ID;
	}
	public String getC_Name() {
		return C_Name;
	}
	public void setC_Name(String c_Name) {
		C_Name = c_Name;
	}
	public String getC_Address() {
		return C_Address;
	}
	public void setC_Address(String c_Address) {
		C_Address = c_Address;
	}

	public String getC_Phone() {
		return C_Phone;
	}
	public void setC_Phone(String c_Phone) {
		C_Phone = c_Phone;
	}
	
	
	
	public ArrayList<String> getPhones() {
		return Phones;
	}
	public void setPhones(ArrayList<String> phones) {
		Phones = phones;
	}
	public ArrayList<Customer> ShowAllCustomers() throws SQLException, ClassNotFoundException {
		data.clear();
		String SQL;

		
		ConAndQueries.connectDB(); 
		SQL = "select * from Customers";

		Statement stmt = ConAndQueries.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			data.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3)));
			
		}
		 
		   
		rs.close();
		stmt.close();

		ConAndQueries.con.close();
		
	 
		return (data);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
