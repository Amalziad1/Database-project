import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class deleteCustController {

	@FXML
	private Button ConDelete;


	@FXML
	private TextField DelID;

	@FXML
	private Button viewDelete;

	@FXML
	private TableView<Customer> table;

	@FXML
	private TableColumn<Customer, Integer> ide;

	@FXML
	private TableColumn<Customer, String> n;

	@FXML
	private TableColumn<Customer, String> add;

	@FXML
	void DeleteCust(ActionEvent event) throws ClassNotFoundException, SQLException {

		ArrayList<Customer> item = new ArrayList<>();
		
		String SQL = " delete from Customers where C_ID= ? ";
		ConAndQueries.connectDB();
		PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
		stmt.setInt(1, Integer.parseInt(DelID.getText()));
		stmt.execute();
		stmt.close();
		ConAndQueries.con.close();
		
//		testTest look= new testTest ();
//		look.show(event);
	 
		
	}

	@FXML
	void ShowDelete(ActionEvent event) throws ClassNotFoundException, SQLException {

		ArrayList<Customer> item = new ArrayList<>();
		
	
		ConAndQueries.connectDB();
		String SQL = "select * from Customers where Customers.C_ID = ?";
		PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
		stmt.setInt(1, Integer.parseInt(DelID.getText()));
		stmt.execute();
		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				item.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3))); }

		ConAndQueries.con.close();
		stmt.close();
		ConAndQueries.con.close();
		

		ObservableList<Customer> dellist = FXCollections.observableArrayList(item);
		
			n.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Name"));
			ide.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("C_ID"));
			add.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Address"));

			table.setItems(dellist);

	}

}
