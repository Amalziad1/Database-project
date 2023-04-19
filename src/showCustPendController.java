import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class showCustPendController implements Initializable{

    @FXML
    private TableView<CustOrder> table;

    @FXML
    private TableColumn<CustOrder, String> nameField;

    @FXML
    private TableColumn<CustOrder, Integer> CIDField;

    @FXML
    private TableColumn<CustOrder, String> AddField;

    @FXML
    private TableColumn<CustOrder, Integer> OIDField;

    @FXML
    private TableColumn<CustOrder, String> OStatField;

    
    
    public void initialize(URL url, ResourceBundle rb) {
    	ArrayList<CustOrder> item = new ArrayList<>();
    	
    	String SQL ="Select Customers.*, orders.orderID, orders.orderStatus from Customers,"
    			+ " orders where orders.customerID=Customers.C_ID and orders.orderStatus= 'no' "; 
      	 
 
		try {
			ConAndQueries.connectDB();
			Statement stmt = ConAndQueries.con.createStatement();
		 
		ResultSet rs = stmt.executeQuery(SQL);
		
		
		 
		
		while (rs.next()) {
			item.add(new CustOrder (rs.getInt(1), rs.getInt(4), rs.getString(2), rs.getString(3), rs.getString(5) )); 
		System.out.println(" --------"+ rs.getString(2));
		}
	 
		
		stmt.close();
		
		ConAndQueries.con.close();
		
		ObservableList<CustOrder> pendingOrderCustomer = FXCollections.observableArrayList(item);
		
		nameField.setCellValueFactory(new PropertyValueFactory<CustOrder, String>("C_name"));
		CIDField.setCellValueFactory(new PropertyValueFactory<CustOrder, Integer>("C_ID"));
		AddField.setCellValueFactory(new PropertyValueFactory<CustOrder, String>("C_Address"));
		OIDField.setCellValueFactory(new PropertyValueFactory<CustOrder, Integer>("O_ID"));
		OStatField.setCellValueFactory(new PropertyValueFactory<CustOrder, String>("O_Status"));
		
		table.setItems(pendingOrderCustomer);
    	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}
