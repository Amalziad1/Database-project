import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class searchCustController {

	@FXML
	private TextField nameField;

	@FXML
	private TextField IDField;

	@FXML
	private TextField addField;

	@FXML
	private Button btn;

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
	void search(ActionEvent event) throws ClassNotFoundException, SQLException {

		ArrayList<Customer> item = new ArrayList<>();
		ArrayList<String> phonelist;

		if (IDField.getText() != "") {
			ConAndQueries.connectDB();
			String SQL = "     SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address, GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)'\r\n"
					+ "FROM C_Phone, Customers  where Customers.C_ID= C_Phone.C_ID and Customers.C_ID= ?  ";
			PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);
			stmt.setInt(1, Integer.parseInt(IDField.getText()));
			System.out.println("------------   " + IDField.getText());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String separator = ",";
				phonelist = new ArrayList<>(Arrays.asList(rs.getString(4).split(separator)));
				System.out.println(phonelist);

				item.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), phonelist));

				

				
			}

			ConAndQueries.con.close();
			stmt.close();
			ConAndQueries.con.close();

		}

		else if (nameField.getText() != "") {

			ConAndQueries.connectDB();

			String SQL = "SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address,  GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)' "
					+ "FROM C_Phone, Customers " + "WHERE Customers.C_ID = C_Phone.C_ID "
					+ "AND Customers.C_Name LIKE ? " + "GROUP BY Customers.C_ID";
			PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);

			stmt.setString(1, "%" + nameField.getText() + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String separator = ",";

				phonelist = new ArrayList<>(Arrays.asList(rs.getString(4).split(separator)));
				System.out.println(phonelist);

				item.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), phonelist));

				

			}

			ConAndQueries.con.close();
			stmt.close();
			ConAndQueries.con.close();

		} else if (addField.getText() != "") {

			ConAndQueries.connectDB();

			String SQL = "SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address, GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)' "
					+ "FROM C_Phone, Customers " + "WHERE Customers.C_ID = C_Phone.C_ID "
					+ "AND Customers.C_Address LIKE ? " + "GROUP BY Customers.C_ID";
			PreparedStatement stmt = ConAndQueries.con.prepareStatement(SQL);

			stmt.setString(1, "%" + addField.getText() + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String separator = ",";

				phonelist = new ArrayList<>(Arrays.asList(rs.getString(4).split(separator)));
				System.out.println(phonelist);


				item.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), phonelist));

				
			}

			ConAndQueries.con.close();
			stmt.close();
			ConAndQueries.con.close();

		}

		ObservableList<Customer> updlist = FXCollections.observableArrayList(item);

		n.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Name"));
		ide.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("C_ID"));
		add.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Address"));
		//p1.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Phone"));
		p1.setCellValueFactory(new PropertyValueFactory<Customer, ArrayList<String> >("Phones"));
		
		table.setItems(updlist);

	}

}
