
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class statController implements Initializable {

    @FXML
    private Label TotalOrdersMonth;

    @FXML
    private Label TotalSalesMonth;

    @FXML
    private Label TotalOrdersYear;

    @FXML
    private Label TotalSalesYear;
    
    @FXML
    private Label TotalOrders;

    @FXML
    private Label TotalSales;

	@Override
	public void initialize(URL url, ResourceBundle rb)  {
		String totalOrderMonth = null; 
		String totalSalesMonth = null;
		String totalSalesYear = null;
		String totalOrdersYear= null; 
		String totalOrders=null; 
		String totalSales=null; 
		
	
		try {
			ConAndQueries.connectDB();
			String getTotalMonthOrders = "select  count(orderId) from orders"
					+ " where month(orderDate) = (SELECT month(now())) and year(orderDate) = (SELECT year(now()) )";
			
			 
			Statement stmt = ConAndQueries.con.createStatement();
			ResultSet rs = stmt.executeQuery(getTotalMonthOrders);
			while (rs.next()) {
				 totalOrderMonth= rs.getString(1);
			}
			
			
			String getTotalMonthSales = "select  sum(Pay_amount) from Payment  "
					+ "where month(Pay_Date) = (SELECT month(now())) and year(Pay_Date) = (SELECT year(now()) )";
			
			 
			Statement stmt2 = ConAndQueries.con.createStatement();
			ResultSet rs2 = stmt2.executeQuery(getTotalMonthSales);
			while (rs2.next()) {
				 totalSalesMonth= rs2.getString(1);
			}
			
			 
			String getTotalYearOrders = "select  count(orderId) from orders where year(orderDate) = (SELECT year(now()) ) ";
			Statement stmt3 = ConAndQueries.con.createStatement();
			ResultSet rs3 = stmt3.executeQuery(getTotalYearOrders);
			while (rs3.next()) {
				totalOrdersYear= rs3.getString(1);
			}
			
			
			
			String getTotalYearSales = "select  sum(Pay_amount) from Payment  where year(Pay_Date) = (SELECT year(now()) )" ;
			Statement stmt4 = ConAndQueries.con.createStatement();
			ResultSet rs4 = stmt4.executeQuery(getTotalYearOrders);
			while (rs4.next()) {
				 totalSalesYear= rs4.getString(1);
			}
			
			
			
			
			String getTotalOrders= " select count(orderID) from orders"; 
			Statement stmt5 = ConAndQueries.con.createStatement();
			ResultSet rs5 = stmt5.executeQuery(getTotalOrders);
			while (rs5.next()) {
				 totalOrders = rs5.getString(1);
			}
			
			String getTotalSales= "select  sum(Pay_amount) from Payment ";
			
			Statement stmt6 = ConAndQueries.con.createStatement();
			ResultSet rs6 = stmt6.executeQuery(getTotalSales);
			while (rs6.next()) {
				System.out.println("-------------");
				 totalSales= rs6.getString(1);
				 System.out.println("-------------" +  totalSales);
			}
		 
			TotalOrdersMonth.setText(totalOrderMonth);
			TotalSalesMonth.setText(totalSalesMonth);
			TotalOrdersYear.setText(totalOrdersYear);
			TotalSalesYear.setText(totalSalesYear);
			TotalSales.setText(totalSales);
			TotalOrders.setText(totalOrders);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		/* sum payments this month: 
		select  sum(Pay_amount) from Payment  where month(Pay_Date) = (SELECT month(now())) and year(Pay_Date) = (SELECT year(now()) ) ;
		*/

		
		
		
	}

    
    
    
    
}



