import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class updCustController {

	@FXML
	private TextField nField;

	@FXML
	private TextField P2Field;

	@FXML
	private TextField P1Field;

	@FXML
	private TextField addField;

	@FXML
	private TextField IDField;

	@FXML
	private Button curr;

	@FXML
	private TableView<Customer> table;

	@FXML
	private TableColumn<Customer, Integer> ide;

	@FXML
	private TableColumn<Customer, String> n;

	@FXML
	private TableColumn<Customer, String> add;

	@FXML
	private TableColumn<Customer, ArrayList<String>> p1;

	@FXML
	private TableColumn<Customer, String> p2;

	@FXML
	private Button upd;

	@FXML
	void updCust(ActionEvent event) throws ClassNotFoundException, SQLException {

		ArrayList<Customer> item = new ArrayList<>();
		ArrayList<String> phonelist = new ArrayList<>();
		String name = null;
		String address = null;
		String phone1 = null;
		String phone2 = null;
		
		String phone1N = null;
		String phone2N = null;
		

		ConAndQueries.connectDB();
		String SQL1 = "     SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address, GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)'\r\n"
				+ "FROM C_Phone, Customers  where Customers.C_ID= C_Phone.C_ID and Customers.C_ID= ?  ";
		PreparedStatement stmt1 = ConAndQueries.con.prepareStatement(SQL1);
		stmt1.setInt(1, Integer.parseInt(IDField.getText()));
		stmt1.execute();
		
		ResultSet QSelectAll = stmt1.executeQuery();
		while (QSelectAll.next()) {

			name = QSelectAll.getString(2);
			address = QSelectAll.getString(3);
			String separator = ",";
			phonelist = new ArrayList<>(Arrays.asList(QSelectAll.getString(4).split(separator)));
			// System.out.println(phonelist);
			phone1 = phonelist.get(0);
			phone1N = phonelist.get(0);

			if (phonelist.size() > 1) {			
				phone2 = phonelist.get(1);	phone2N = phonelist.get(1).trim();
			}else {
				phone2 = null;	phone2N = null;
			}
			
		}

		if (nField.getText().isBlank() == false) {
			name = nField.getText();
		}

		if (addField.getText().isBlank() == false) {
			address = addField.getText();
		}

		if (P1Field.getText().isBlank() == false) {
			phone1 = P1Field.getText();
		}

		if (P2Field.getText().isBlank() == false) {
			phone2 = P2Field.getText();

		}

		System.out.println("New info" + name + " " + address + " " + phone1 + " (" + phone2 + ") " + phonelist.size());

		String SQL2 = "update Customers  SET C_Name= ?, C_Address=?   where C_ID=?";
		PreparedStatement stmt2 = ConAndQueries.con.prepareStatement(SQL2);
		stmt2.setString(1, name);
		stmt2.setString(2, address);
		stmt2.setInt(3, Integer.parseInt(IDField.getText()));
		stmt2.execute();

		 

		if (phonelist.size() == 1 && phone2.equals("-1") ==false) {
			System.out.println("--------- " + phonelist);
			String AddP2 = "insert into C_Phone (C_ID, Phone)" + "  values (?, ?)";
			PreparedStatement stmt4 = ConAndQueries.con.prepareStatement(AddP2);
			stmt4.setString(2, phone2);
			stmt4.setInt(1, Integer.parseInt(IDField.getText()));
			phonelist.add(phone2);
			stmt4.execute();

		}
		if (phonelist.size() == 1 && phone1.equals("-1") ==false) {
			System.out.println("--------- " + phonelist);
			String AddP2 = "insert into C_Phone (C_ID, Phone)" + "  values (?, ?)";
			PreparedStatement stmt4 = ConAndQueries.con.prepareStatement(AddP2);
			stmt4.setString(2, phone1);
			stmt4.setInt(1, Integer.parseInt(IDField.getText()));
			phonelist.add(phone1);
			stmt4.execute();

		}
		
	 
		
		if (phonelist.size() == 2 && (phone1.equals("-1") || phone2.equals("-1"))) {
			 System.out.println(phone1 +"---------    " + phone2 + "   " +phone2N);
			 if (phone1.equals("-1") && phone2.equals("-1")==false) {
				  ConAndQueries.connectDB();
			    System.out.println(phonelist+"---------  Deleting " + phone1N);
			    String delP1 = "DELETE FROM C_Phone WHERE C_ID= ? and Phone = ?";
			    PreparedStatement stmt4 = ConAndQueries.con.prepareStatement(delP1);
			    stmt4.setInt(1, Integer.parseInt(IDField.getText()));
			    stmt4.setString(2, phone1N);
			    stmt4.execute();
			    phonelist.remove(phone1N);
			    stmt4.close();
			  } 
			  else if (phone2.equals("-1") && phone1.equals("-1")==false) {
				  ConAndQueries.connectDB();
			    System.out.println(phonelist+" ********** Deleting  " + phone2N);
			    String delP2 = "DELETE FROM C_Phone WHERE C_ID= ? and Phone = ?";
			    PreparedStatement stmt5 = ConAndQueries.con.prepareStatement(delP2);
			    stmt5.setInt(1, Integer.parseInt(IDField.getText()));
			    stmt5.setString(2, phone2N);
			   System.out.println(stmt5);
			    	 stmt5.execute();
			   
			   
			    System.out.println(phonelist+" ********** fdfffxxcxfx  " );
			    phonelist.remove(phone2N);
			    stmt5.close();
			  }
			  
			  else if (phone2.equals("-1") && phone1.equals("-1")) {
				  Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.ERROR);
					a.setContentText("Cannot delete both numbers");
					a.show();
					return;
			  }
			}
		else if (phonelist.size() == 2 /*&& (phone1.equals("-1")==false || phone2.equals("-1")==false)*/) {
			 System.out.println(phone1 +"---------    " + phone2 + "   " +phone2N);
			 if (phone1.equals("-1")==false  && phone1.isBlank()==false) {
				  String delP1 = "update C_Phone SET Phone= ? where C_ID=? and Phone=?";
			    PreparedStatement stmt4 = ConAndQueries.con.prepareStatement(delP1);
			    stmt4.setString(1, phone1);
			    stmt4.setInt(2, Integer.parseInt(IDField.getText()));
			    stmt4.setString(3, phone1N);
			    stmt4.execute();
			    phonelist.remove(phone1N);
			    phonelist.add(phone1);
			    
			    stmt4.close();
			 }
			 if (phone2.equals("-1")==false  && phone2.isBlank()==false) {
				  String delP1 = "update C_Phone SET Phone= ? where C_ID=? and Phone=?";
				    PreparedStatement stmt4 = ConAndQueries.con.prepareStatement(delP1);
				    stmt4.setString(1, phone2);
				    stmt4.setInt(2, Integer.parseInt(IDField.getText()));
				    stmt4.setString(3, phone2N);
				    stmt4.execute();
				    phonelist.remove(phone2N);
				    phonelist.add(phone1);
			 }
			 
 
			}
		
		
		

 
	 

		ConAndQueries.con.close();
		stmt1.close();
		stmt2.close();
		stmt2.close();
		
		// stmt3.close();

		ConAndQueries.con.close();
		System.out.println("**********************");
		viewCurrentInfo(event);

	}

	@FXML
	void viewCurrentInfo(ActionEvent event) throws ClassNotFoundException, SQLException {

		ArrayList<Customer> item = new ArrayList<>();
		ArrayList<String> phonelist;

		ConAndQueries.connectDB();
		String SQL = "     SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address, GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)'\r\n"
				+ "FROM C_Phone, Customers  where Customers.C_ID= C_Phone.C_ID and Customers.C_ID= ?  ";
		PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
		stmt.setInt(1, Integer.parseInt(IDField.getText()));
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			String separator = ",";
			phonelist = new ArrayList<>(Arrays.asList(rs.getString(4).split(separator)));
			// System.out.println(phonelist);
			item.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), phonelist));
		}

		ConAndQueries.con.close();
		stmt.close();
		ConAndQueries.con.close();

		ObservableList<Customer> updlist = FXCollections.observableArrayList(item);

		n.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Name"));
		ide.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("C_ID"));
		add.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Address"));
		p1.setCellValueFactory(new PropertyValueFactory<Customer, ArrayList<String>>("Phones"));

		table.setItems(updlist);

	}

}
