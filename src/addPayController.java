
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class addPayController implements Initializable {
	//String [] pendingOrderIDs;

    @FXML
  //  private ChoiceBox<String> OrderCB ;//= new ChoiceBox(FXCollections.observableArrayList(pendingOrderIDs));
    private ChoiceBox<Integer> OrderCB ;
    
    
    @FXML
    private DatePicker PayDate;

    @FXML
    private TextField amountTF;

    @FXML
    private TextField PayIdTF;

    @FXML
    private Button addPaybtn;

    @FXML
    void addPay(ActionEvent event) throws SQLException, ClassNotFoundException {
    	
    LocalDate d = PayDate.getValue();
    Date payDate= Date.valueOf(d.toString());
   
   
   ConAndQueries.connectDB();
		System.out.println("entering next");
		String SQL1 = "insert into Payment (Pay_ID, O_ID, Pay_amount, Pay_Date )"+" values (?, ?, ?, ?)";
		
 
		PreparedStatement stmt1 = ConAndQueries.con.prepareStatement(SQL1);
 

		
		stmt1.setInt(1, Integer.parseInt(PayIdTF.getText()) );
		stmt1.setInt(2, OrderCB.getSelectionModel().getSelectedItem());
		stmt1.setDouble(3, Double.parseDouble(amountTF.getText()) );
		stmt1.setDate(4,  payDate);
 

	 try {
		System.out.println("Do you reach here");
			stmt1.execute();
		

		 } catch (java.sql.SQLException e) {
			System.out.println("Cannot Perform operation");
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Invalid Payment ID");
			a.show();
			return;
 
 		}

		ConAndQueries.con.close();
		Alert success = new Alert(AlertType.NONE);
		success.setAlertType(AlertType.CONFIRMATION);
		success.setContentText("New Payment Added Confirmed!");
		success.show();
 
	

	
    	
    
    	
    	
    	
    	
    	
     
    	

    }

	@Override
	public void initialize(URL url, ResourceBundle rb)  {
	//	choice.getItems().addAll(FXCollections.observableArrayList(categories));
		
	
		try {
		
			ArrayList <Integer > pendingOrderIDs;
			//String [] pendingOrderIDs;
	 
			pendingOrderIDs = getPending();
			
			OrderCB.getItems().addAll(FXCollections.observableArrayList(pendingOrderIDs));
	//	 System.out.println("------ "+ getPending()[1]);
			
			OrderCB.setOnAction((event) -> {
			    //int selectedIndex = choice.getSelectionModel().getSelectedIndex();
			    int OrderID = OrderCB.getSelectionModel().getSelectedItem();
//			    if (OrderCB.getSelectionModel().getSelectedItem() == 5) {
//			    	System.out.println("sfosfsfsfsfdfd");
//					 
//				}
			});
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		 
		
		
	}
	

	
//public String[] getPending () throws ClassNotFoundException, SQLException {
public ArrayList<Integer> getPending () throws ClassNotFoundException, SQLException {
	ArrayList<order> item = new ArrayList<order>();
	//ArrayList<String> pendingList = new ArrayList<String>();
	ArrayList<Integer> pendingList = new ArrayList<Integer>();
    	
		ConAndQueries.connectDB();
		String SQL = "select  * from orders where orders.orderId  not in"
				+ " (select  orderId from orders, Payment where orders.orderId = Payment.O_ID); ";
		PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
	
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
			//	pendingList.add(rs.getString(1));
				pendingList.add(rs.getInt(1));}

		ConAndQueries.con.close();
		stmt.close();
		ConAndQueries.con.close();
		
		/*String [] p= new String [pendingList.size()];
		for (int i=0 ; i< p.length ; i++) {
			p[i]=pendingList.get(i);
			System.out.println("   --" + p [i]); 
		}
		 */
		return pendingList;
		//return p; 
		
	}

}
