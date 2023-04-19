

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.UnaryOperator;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

public class shipmentController {
	@FXML
	private TableColumn<shipment, Integer> Q;

	@FXML
	private DatePicker aD;

	@FXML
	private TableColumn<shipment, Date> adate;

	@FXML
	private Button add;

	@FXML
	private Button delete;

	@FXML
	private Label msg;

	@FXML
	private TextField pid;

	@FXML
	private TableColumn<shipment, Integer> productID;

	@FXML
	private TextField qT;

	@FXML
	private DatePicker rD;

	@FXML
	private TableColumn<shipment, Date> rdate;

	@FXML
	private ChoiceBox<String> s;

	@FXML
	private Button search;

	@FXML
	private TableColumn<shipment, Integer> shipmentID;

	@FXML
	private TableView<shipment> shipmentTable;

	@FXML
	private Button show;

	@FXML
	private TextField sid;

	@FXML
	private TableColumn<shipment, String> status;

	@FXML
	private Button update;

	public String initialSet() {
		s.getItems().addAll("yes", "no");
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("-?[0-9]*")) {
				return change;
			}
			return null;
		};
		sid.setTextFormatter(new TextFormatter<>(filter));
		pid.setTextFormatter(new TextFormatter<>(filter));
		qT.setTextFormatter(new TextFormatter<>(filter));
		return s.getValue();
	}

	public void select(String SQL) throws SQLException, ClassNotFoundException {
		s.getItems().clear();
		initialSet();
		ArrayList<shipment> arr = new ArrayList<shipment>();
		connection.connectDB();
		Statement stmt = connection.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			arr.add(new shipment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5),
					rs.getString(6)));
		}
		stmt.close();
		connection.con.close();
		ObservableList<shipment> dellist = FXCollections.observableArrayList(arr);
		shipmentID.setCellValueFactory(new PropertyValueFactory<shipment, Integer>("shipmentId"));
		productID.setCellValueFactory(new PropertyValueFactory<shipment, Integer>("productId"));
		Q.setCellValueFactory(new PropertyValueFactory<shipment, Integer>("quantity"));
		rdate.setCellValueFactory(new PropertyValueFactory<shipment, Date>("requestDate"));
		adate.setCellValueFactory(new PropertyValueFactory<shipment, Date>("arrivalDate"));
		status.setCellValueFactory(new PropertyValueFactory<shipment, String>("shipmentStatus"));
		shipmentTable.setItems(dellist);
	}

	public void ShowAllOrders() throws ClassNotFoundException, SQLException {
		String SQL = "select * from shipment";
		select(SQL);
	}

	public void InsertNew() throws SQLException, ClassNotFoundException {
		String str = initialSet();
		s.getItems().clear();
		initialSet();
		if (sid.getText().length() == 0 || pid.getText().length() == 0 || qT.getText().length() == 0
				|| rD.getValue() == null || aD.getValue() == null || str == null) {
			msg.setText("Missing Date!");
		} else {
			int id1 = Integer.parseInt(sid.getText());
			int id2 = Integer.parseInt(pid.getText());
			int quan = Integer.parseInt(qT.getText());
//
//			System.out.println(str);

			LocalDate date1 = rD.getValue();
			LocalDate date2 = aD.getValue();
			if (date1 != null && date2 != null) {
				String formattedDate1 = date1.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				String formattedDate2 = date2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				connection.connectDB();
				String SQL1 = "Insert into shipment values(?,?,?,?,?,?);";
				try {
					PreparedStatement pst = (PreparedStatement) connection.con.prepareStatement(SQL1);
					pst.setInt(1, id1);
					pst.setInt(2, id2);
					pst.setInt(3, quan);
					pst.setString(4, formattedDate1);
					pst.setString(5, formattedDate2);
					pst.setString(6, str);
					pst.execute();
					//
					ShowAllOrders();
					msg.setText("Added Successfully!");
					sid.clear();
					pid.clear();
					qT.clear();
				} catch (SQLException a) {
					msg.setText(a.getMessage());
				}
			} else {
				msg.setText("You Must Select a Date!");
			}
		}
	}

	public void updateShipment() throws ClassNotFoundException, SQLException {
		String str = initialSet();
		s.getItems().clear();
		initialSet();
		int e = 0;
		String SQL = "update shipment SET";
		if (sid.getText().length() != 0 && pid.getText().length() != 0) {
			if (qT.getText().length() != 0) {
				e = 1;
				SQL = SQL + " quantity=" + Integer.parseInt(qT.getText());
			}
			if (rD.getValue() != null) {
				if (e == 1)
					SQL = SQL + ",";
				LocalDate date1 = rD.getValue();
				String formattedDate1 = date1.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				SQL = SQL + " requestDate='" + formattedDate1 + "'";
				e = 1;

			}
			if (aD.getValue() != null) {
				if (e == 1)
					SQL = SQL + ",";
				LocalDate date2 = aD.getValue();
				String formattedDate2 = date2.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				SQL = SQL + " arrivalDate='" + formattedDate2 + "'";
				e = 1;
			}
			if (str != null) {
				if (e == 1)
					SQL = SQL + ",";
				SQL = SQL + " shipmentStatus='" + str + "'";
				e = 1;
			}
			if (e == 1) {
				String c = " where shipmentId=" + Integer.parseInt(sid.getText()) + " AND productId="
						+ Integer.parseInt(pid.getText());
				SQL = SQL + c;

				connection.connectDB();
				// System.out.println("==================SQL=================" + SQL);
				PreparedStatement pr = (PreparedStatement) connection.con.prepareStatement(SQL);
				pr.executeUpdate(SQL);

				String s = "select * from shipment " + c;// get the update result
				select(s);
				msg.setText("Updated Successfully!");
				connection.con.close();
				pr.close();
			} else {
				msg.setText("Nothing to update");
			}
		} else {
			msg.setText("Missing Ids!");
		}
	}

	public void searchShipment() throws ClassNotFoundException, SQLException {
		String str = initialSet();
		s.getItems().clear();
		initialSet();
		String SQL = "select * from shipment where ";
		int e = 0;// (e):exists specification before
		if (sid.getText().length() != 0) {
			SQL = SQL + "shipmentId=" + Integer.parseInt(sid.getText());
			e = 1;
		}
		if (pid.getText().length() != 0) {
			if (e == 1)
				SQL = SQL + " AND ";// if there is a previous column specified
			SQL = SQL + "productId=" + Integer.parseInt(pid.getText());
			e = 1;
		}
		if (qT.getText().length() != 0) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "quantity=" + Integer.parseInt(qT.getText());
			e = 1;
		}
		if (rD.getValue() != null) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "requestDate='" + String.valueOf(rD.getValue()) + "'";
			e = 1;
		}
		if (aD.getValue() != null) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "arrivalDate='" + String.valueOf(aD.getValue()) + "'";
			e = 1;
		}
		if (str != null) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "orderStatus='" + str + "'";
			e = 1;
		}
		SQL = SQL + ";";
//		System.out.println(SQL);
//		System.out.println(e);
		if (e == 1) {
			select(SQL);
		}

	}

	public void deleteShipment() throws SQLException, ClassNotFoundException {
		String str = initialSet();
		s.getItems().clear();
		initialSet();
		String SQL = "delete from shipment where ";
		int e = 0;// (e):exists specification before
		String c = "";
		if (sid.getText().length() != 0) {
			c = c + "shipmentId=" + Integer.parseInt(sid.getText());
			e = 1;
		}
		if (pid.getText().length() != 0) {
			if (e == 1)
				c = c + " AND ";// if there is a previous column specified
			c = c + "productId=" + Integer.parseInt(pid.getText());
			e = 1;
		}
		if (qT.getText().length() != 0) {
			if (e == 1)
				c = c + " AND ";
			c = c + "quantity=" + Integer.parseInt(qT.getText());
			e = 1;
		}
		if (rD.getValue() != null) {
			if (e == 1)
				c = c + " AND ";
			c = c + "requestDate='" + String.valueOf(rD.getValue()) + "'";
			e = 1;
		}
		if (aD.getValue() != null) {
			if (e == 1)
				c = c + " AND ";
			c = c + "arrivaltDate='" + String.valueOf(aD.getValue()) + "'";
			e = 1;
		}
		if (s != null) {
			if (e == 1)
				c = c + " AND ";
			c = c + "shipmentStatus='" + str + "'";
			e = 1;
		}
		c = c + ";";
		if (e == 1) {
			connection.connectDB();
			SQL = SQL + c;
			// System.out.println(SQL);
			PreparedStatement pr = (PreparedStatement) connection.con.prepareStatement(SQL);
			int done = pr.executeUpdate(SQL);
			String sql = "select * from shipment";// show all data after deletion
			select(sql);
			if (done == 1) {
				msg.setText("Deleted Successfully!");
			} else {
				msg.setText("No records found to delete!");
			}
			connection.con.close();
			pr.close();
		} else {
			msg.setText("Nothing to delete!");
		}
	}

}