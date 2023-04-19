import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShowPendingController implements Initializable{

    @FXML
    private TableView<order> table;

    @FXML
    private TableColumn<order, Integer> oid;

    @FXML
    private TableColumn <order, Integer> pid;

    @FXML
    private TableColumn<order, Integer> cid;
    
    @FXML
    private TableColumn<order, Integer> q;

    @FXML
    private TableColumn<order, Date> odate;

    @FXML
    private TableColumn <order, String> ostatus;

    @FXML
    public void  showPending(/*ActionEvent event*/) throws ClassNotFoundException, SQLException {
    	/*ArrayList<Integer> pendingList = new ArrayList<Integer>();*/
    	ArrayList<order> item = new ArrayList<order>();
		
    	
		ConAndQueries.connectDB();
		String SQL = "select  * from orders where orders.orderId  not in"
				+ " (select  orderId from orders, Payment where orders.orderId = Payment.O_ID); ";
		PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
	
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				item.add(new order(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4), rs.getDate(5), rs.getString(6)));
				 }

		ConAndQueries.con.close();
		stmt.close();
		ConAndQueries.con.close();
		
	 
		
		
		    
		    
		ObservableList<order> dellist = FXCollections.observableArrayList(item);
		
			oid.setCellValueFactory(new PropertyValueFactory<order, Integer>("orderId"));
			pid.setCellValueFactory(new PropertyValueFactory<order, Integer>("productId"));
			cid.setCellValueFactory(new PropertyValueFactory<order, Integer>("customerId"));
			q.setCellValueFactory(new PropertyValueFactory<order, Integer>("quantity"));
			odate.setCellValueFactory(new PropertyValueFactory<order, Date>("orderDate"));
			ostatus.setCellValueFactory(new PropertyValueFactory<order, String>("orderStatus"));

			table.setItems(dellist);
			
			 

    }
    
    @FXML
    void addPayEnvoker(ActionEvent event) throws ClassNotFoundException, SQLException {
 
    	try {
      		 FXMLLoader AddPayment = new FXMLLoader(getClass().getResource("addPay.fxml"));
   			Parent AddPayPane = (Parent) AddPayment.load();
   			addPayController newPayPane= AddPayment.getController();
   			Scene scene = new Scene(AddPayPane);
   		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
   			Stage editStage = new Stage();
   			editStage.setResizable(false);
   			editStage.setScene(scene);
   			editStage.show();
   			System.out.println("Envoking add newPayment Screen");
   			
   			
   			
   			
   			
   			
   			 
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		 

    }
    
    
	public void initialize(URL url, ResourceBundle rb)  {
	 
		try {
			showPending(/*null*/);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
