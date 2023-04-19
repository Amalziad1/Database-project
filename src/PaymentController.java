import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PaymentController implements Initializable {

    @FXML
    private TableView<Payment> table;

    @FXML
    private TableColumn<Payment, Integer> payID;

    @FXML
    private TableColumn<Payment, Integer> OrdID;

    @FXML
    private TableColumn<Payment, Integer> Amount;

    @FXML
    private TableColumn<Payment, Date> PayDate;

    @FXML
    private Button showStat;

    @FXML
    private Button showPend;

    @FXML
    private Button showAllPay;

  
    
	public void initialize(URL url, ResourceBundle rb)  {
    	
    	showAllPay();
    }
    
     @FXML 
    void showAllPay(/*ActionEvent event*/) {
    	//table.getItems().removeAll();

    	
    	
    		ArrayList<Payment> pay = new ArrayList<Payment>();			 
    		Payment p = new Payment();	
    		System.out.println(pay.size());	
    		 	
    		
    		try {
    			pay = p.ShowAllPayments();
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		ObservableList<Payment> Paylist = FXCollections.observableArrayList(pay);
    		// TODO Auto-generated method stub
    		payID.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("Pay_ID"));
    		OrdID.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("O_ID"));
    		Amount.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("Pay_amount"));
    		PayDate.setCellValueFactory(new PropertyValueFactory<Payment, Date>("Pay_Date"));

    		table.setItems(Paylist);
    System.out.println("should print");
    		System.out.println("done");
    }

    @FXML
    void showPending(ActionEvent event) {
    	try {
   		 FXMLLoader SPending = new FXMLLoader(getClass().getResource("ShowPending.fxml"));
			Parent pendingPane = (Parent) SPending.load();
			ShowPendingController pend = SPending.getController();
			Scene scene = new Scene(pendingPane);
		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage editStage = new Stage();
			editStage.setResizable(false);
			editStage.setScene(scene);
			editStage.show();
			System.out.println("Envoking PendingPayments Screen");
			
			
			
			
			
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

    }

    @FXML
    void showStatistics(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	
    	
		
	     try {
    		 FXMLLoader stat = new FXMLLoader(getClass().getResource("stat.fxml"));
 			Parent statPane = (Parent) stat.load();
 			statController sController = stat.getController();
 			Scene scene = new Scene(statPane);
		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage editStage = new Stage();
			editStage.setResizable(false);
			editStage.setScene(scene);
			editStage.show();
			System.out.println("Envoking stat Screen");
 			
 			
 			
 			
 			
 			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
    }

}
