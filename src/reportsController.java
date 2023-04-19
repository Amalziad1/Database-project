import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class reportsController implements Initializable {

	@FXML
	private Label numCust;

	@FXML
	private Label numOrd;

	@FXML
	void showCustPending(ActionEvent event) {

		try {
			FXMLLoader pendingCustomers = new FXMLLoader(getClass().getResource("showCustPending.fxml"));
			Parent reportPane = (Parent) pendingCustomers.load();
			showCustPendController pendingcustController = pendingCustomers.getController();
			Scene scene = new Scene(reportPane);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

	@FXML
	void topFive(ActionEvent event) {
		
		try {
			FXMLLoader topCustomers = new FXMLLoader(getClass().getResource("showTopFive.fxml"));
			Parent topCustomersPane = (Parent) topCustomers.load();
			topCustomerController topCustController = topCustomers.getController();
			Scene scene = new Scene(topCustomersPane);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage editStage = new Stage();
			editStage.setResizable(false);
			editStage.setScene(scene);
			editStage.show();
			System.out.println("Envoking topFive Screen");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	public void initialize(URL url, ResourceBundle rb) {
		String SQL1 = " Select count(Customers.C_ID) from Customers";

		String SQL2 = "Select count(orders.orderID) from orders";
		try {
			ConAndQueries.connectDB();
			Statement stmt1 = ConAndQueries.con.createStatement();
			Statement stmt2 = ConAndQueries.con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(SQL1);
			ResultSet rs2 = stmt2.executeQuery(SQL2);

			while (rs1.next()) {
				numCust.setText(String.valueOf(rs1.getInt(1)));
			}
			while (rs2.next()) {
				numOrd.setText(String.valueOf(rs2.getInt(1)));
			}

			stmt1.close();
			stmt2.close();
			ConAndQueries.con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
