import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class addCustController {

	@FXML
	private TextField nField;

	@FXML
	private TextField IDField;

	@FXML
	private CheckBox c;

	@FXML
	private TextField AddField;

	@FXML
	private TextField P1Field;

	@FXML
	private TextField P2Field;

	@FXML
	void addNewCust(ActionEvent event) throws SQLException, ClassNotFoundException {

		ConAndQueries.connectDB();
		System.out.println("entering next");
		String SQL1 = " insert into Customers (C_ID, C_Name, C_Address)" + " values (?, ?, ?)";
		String SQL2 = " insert into C_Phone (C_ID, Phone)" + "  values (?, ?)";
		String SQL3 = " insert into C_Phone (C_ID, Phone)" + "  values (?, ?)";

		PreparedStatement stmt1 = ConAndQueries.con.prepareStatement(SQL1);
		PreparedStatement stmt2 = ConAndQueries.con.prepareStatement(SQL2);
		PreparedStatement stmt3 = ConAndQueries.con.prepareStatement(SQL3);


		System.out.println("Do you reach here");
		stmt1.setString(1, IDField.getText());
		stmt1.setString(2, nField.getText());
		stmt1.setString(3, AddField.getText());

		stmt2.setString(1, IDField.getText());
		stmt2.setString(2, P1Field.getText());
		
		if (IDField.getText().isBlank() || P1Field.getText().isBlank() || nField.getText().isBlank() || AddField.getText().isBlank() ) {
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Data Incomplete! Fill Required Data!");
			a.show();
			return;
		}

		try {
			stmt1.execute();
			stmt2.execute();
			if (P2Field.getText().isBlank() == false) {
				stmt3.setString(1, IDField.getText());
				stmt3.setString(2, P2Field.getText());
				stmt3.execute();
			}

		} catch (java.sql.SQLException e) {
			System.out.println("Cannot Perform operation");
			Alert a = new Alert(AlertType.NONE);
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Duplicate Customer ID Or Phone Number! Please try again");
			a.show();
			return;

		}

		ConAndQueries.con.close();
		Alert success = new Alert(AlertType.NONE);
		success.setAlertType(AlertType.CONFIRMATION);
		success.setContentText("Customer Added Successfully, Refresh to see");
		success.show();
	
	

	}
}
