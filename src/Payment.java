import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Payment {
public  static ArrayList<Payment> data = new ArrayList<Payment>();
	
	public int Pay_ID;
	public int O_ID;
	public int Pay_amount;
	public Date Pay_Date;
	 
	public Payment() {
		
	}
	
	public Payment(int Pay_ID, int O_ID, int Pay_amount, Date Pay_Date) {
	
		this.Pay_ID = Pay_ID;
		this.O_ID = O_ID;
		this.Pay_amount = Pay_amount;
		this.Pay_Date = Pay_Date;
	}
	public int getPay_ID() {
		return Pay_ID;
	}
	public void setPay_ID(int pay_ID) {
		Pay_ID = pay_ID;
	}
	public int getO_ID() {
		return O_ID;
	}
	public void setO_ID(int o_ID) {
		O_ID = o_ID;
	}
	public int getPay_amount() {
		return Pay_amount;
	}
	public void setPay_amount(int pay_amount) {
		Pay_amount = pay_amount;
	}
	public Date getPay_Date() {
		return Pay_Date;
	}
	public void setPay_Date(Date pay_Date) {
		Pay_Date = pay_Date;
	}
	
	public ArrayList<Payment> ShowAllPayments() throws SQLException, ClassNotFoundException {
		data.clear();
		String SQL;

		
		ConAndQueries.connectDB(); 
		SQL = "select * from Payment";

		Statement stmt = ConAndQueries.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			data.add(new Payment(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getDate(4)));
			
		}
		 
		   
		rs.close();
		stmt.close();

		ConAndQueries.con.close();
		
	 
		return (data);
		
		
	}
	
	
	
	
	
}
