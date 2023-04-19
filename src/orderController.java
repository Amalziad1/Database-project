

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class orderController {
	@FXML
	private TableColumn<order, Integer> Q;

	@FXML
	private TableColumn<order, Integer> customerID;

	@FXML
	private TableColumn<order, Date> date;

	@FXML
	private TableColumn<order, Integer> orderID;

	@FXML
	private TableColumn<order, Integer> productID;
	@FXML
	private TableColumn<order, String> status;

	@FXML
	private Button show;
	@FXML
	private TableView<order> ordersTable;

	@FXML
	private Button add;

	@FXML
	private TextField cidText;

	@FXML
	private DatePicker dT;

	@FXML
	private Button delete;

	@FXML
	private Label msg;

	@FXML
	private TextField oidText;

	@FXML
	private TextField pidText;

	@FXML
	private ChoiceBox<String> sT;

	@FXML
	private TextField qT;

	@FXML
	private Button search;

	@FXML
	private Button update;

	public String initialSet() {
		sT.getItems().addAll("yes", "no");
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("-?[0-9]*")) {
				return change;
			}
			return null;
		};
		oidText.setTextFormatter(new TextFormatter<>(filter));
		pidText.setTextFormatter(new TextFormatter<>(filter));
		cidText.setTextFormatter(new TextFormatter<>(filter));
		qT.setTextFormatter(new TextFormatter<>(filter));
		return sT.getValue();
	}

	public void ShowAllOrders() throws ClassNotFoundException, SQLException {
		String SQL = "select * from orders";
		select(SQL);
	}

	public void select(String SQL) throws SQLException, ClassNotFoundException {
		sT.getItems().clear();
		initialSet();
		// String SQL="select * from orders;";
		ArrayList<order> arr = new ArrayList<order>();
		connection.connectDB();
		Statement stmt = connection.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			arr.add(new order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getDate(5), rs.getString(6)));
		}
		stmt.close();
		connection.con.close();
		ObservableList<order> dellist = FXCollections.observableArrayList(arr);
		orderID.setCellValueFactory(new PropertyValueFactory<order, Integer>("orderId"));
		productID.setCellValueFactory(new PropertyValueFactory<order, Integer>("productId"));
		customerID.setCellValueFactory(new PropertyValueFactory<order, Integer>("customerId"));
		Q.setCellValueFactory(new PropertyValueFactory<order, Integer>("quantity"));
		date.setCellValueFactory(new PropertyValueFactory<order, Date>("orderDate"));
		status.setCellValueFactory(new PropertyValueFactory<order, String>("orderStatus"));
		ordersTable.setItems(dellist);
	}

	public void InsertNew() throws SQLException, ClassNotFoundException {
		String str = initialSet();
		sT.getItems().clear();
		initialSet();
		if (oidText.getText().length() == 0 || pidText.getText().length() == 0 || cidText.getText().length() == 0
				|| qT.getText().length() == 0 || dT.getValue() == null || str==null) {
			msg.setText("Missing Date!");
		} else {
			int oid = Integer.parseInt(oidText.getText());
			int pid = Integer.parseInt(pidText.getText());
			int cid = Integer.parseInt(cidText.getText());
			int q = Integer.parseInt(qT.getText());

			//System.out.println(str);

			LocalDate date1 = dT.getValue();
			if (date1 != null) {
				String formattedDate = date1.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				connection.connectDB();
				String SQL1 = "Insert into orders values(?,?,?,?,?,?);";
				try {
					PreparedStatement pst = (PreparedStatement) connection.con.prepareStatement(SQL1);
					pst.setInt(1, oid);
					pst.setInt(2, pid);
					pst.setInt(3, cid);
					pst.setInt(4, q);
					pst.setString(5, formattedDate);
					pst.setString(6, str);
					pst.execute();
					//
					ShowAllOrders();
					msg.setText("Added Successfully!");
					oidText.clear();
					cidText.clear();
					pidText.clear();
					qT.clear();
				} catch (SQLException a) {
					msg.setText(a.getMessage());
				}
			} else {
				msg.setText("You Must Select a Date!");
			}
		}
	}

	public void updateOrder() throws ClassNotFoundException, SQLException {
		String str = initialSet();
		sT.getItems().clear();
		initialSet();
		int e = 0;
		String SQL = "update orders SET";
		if (oidText.getText().length() != 0 && pidText.getText().length() != 0 && cidText.getText().length() != 0) {
			if (qT.getText().length() != 0) {
				e = 1;
				SQL = SQL + " quantity=" + Integer.parseInt(qT.getText());
			}
			if (dT.getValue() != null) {
				if (e == 1)
					SQL = SQL + ",";
				LocalDate date1 = dT.getValue();
				String formattedDate = date1.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
				SQL = SQL + " orderDate='" + formattedDate + "'";
				e = 1;
			}
			if (str != null) {
				if (e == 1)
					SQL = SQL + ",";
				SQL = SQL + " orderStatus='" + str + "'";
				e = 1;
			}
			if (e == 1) {
				String c = " where orderId=" + Integer.parseInt(oidText.getText()) + " AND productId="
						+ Integer.parseInt(pidText.getText()) + " And customerId="
						+ Integer.parseInt(cidText.getText());
				SQL = SQL + c;

				connection.connectDB();
				System.out.println("==================SQL=================" + SQL);
				PreparedStatement pr = (PreparedStatement) connection.con.prepareStatement(SQL);
				pr.executeUpdate(SQL);

				String s = "select * from orders " + c;// get the update result
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

	public void searchOrders() throws ClassNotFoundException, SQLException {
		String s = initialSet();
		sT.getItems().clear();
		initialSet();
		String SQL = "select * from orders where ";
		int e = 0;// (e):exists specification before
		if (oidText.getText().length() != 0) {
			SQL = SQL + "orderId=" + Integer.parseInt(oidText.getText());
			e = 1;
		}
		if (pidText.getText().length() != 0) {
			if (e == 1)
				SQL = SQL + " AND ";// if there is a previous column specified
			SQL = SQL + "productId=" + Integer.parseInt(pidText.getText());
			e = 1;
		}
		if (cidText.getText().length() != 0) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "customerId=" + Integer.parseInt(cidText.getText());
			e = 1;
		}
		if (qT.getText().length() != 0) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "quantity=" + Integer.parseInt(qT.getText());
			e = 1;
		}
		if (dT.getValue() != null) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "orderDate='" + String.valueOf(dT.getValue()) + "'";
			e = 1;
		}
		if (s != null) {
			if (e == 1)
				SQL = SQL + " AND ";
			SQL = SQL + "orderStatus='" + s + "'";
			e = 1;
		}
		SQL = SQL + ";";
		System.out.println(SQL);
//		System.out.println(e);
		if (e == 1) {
			select(SQL);
		}

	}

	public void deleteOrder() throws SQLException, ClassNotFoundException {
		String s = initialSet();
		sT.getItems().clear();
		initialSet();
		String SQL = "delete from orders where ";
		int e = 0;// (e):exists specification before
		String c="";
		if (oidText.getText().length() != 0) {
			c = c + "orderId=" + Integer.parseInt(oidText.getText());
			e = 1;
		}
		if (pidText.getText().length() != 0) {
			if (e == 1)
				c = c + " AND ";// if there is a previous column specified
			c = c + "productId=" + Integer.parseInt(pidText.getText());
			e = 1;
		}
		if (cidText.getText().length() != 0) {
			if (e == 1)
				c = c + " AND ";
			c = c + "customerId=" + Integer.parseInt(cidText.getText());
			e = 1;
		}
		if (qT.getText().length() != 0) {
			if (e == 1)
				c = c + " AND ";
			c = c + "quantity=" + Integer.parseInt(qT.getText());
			e = 1;
		}
		if (dT.getValue() != null) {
			if (e == 1)
				c = c + " AND ";
			c = c + "orderDate='" + String.valueOf(dT.getValue()) + "'";
			e = 1;
		}
		if (s != null) {
			if (e == 1)
				c = c + " AND ";
			c = c + "orderStatus='" + s + "'";
			e = 1;
		}
		c = c + ";";
		if (e == 1) {
			connection.connectDB();
			SQL=SQL+c;
			//System.out.println(SQL);
			PreparedStatement pr = (PreparedStatement) connection.con.prepareStatement(SQL);
			int done=pr.executeUpdate(SQL);
			String sql = "select * from orders" ;// show all data after deletion
			select(sql);
			if(done == 1) {
				msg.setText("Deleted Successfully!");
			}else {
				msg.setText("No records found to delete!");
			}
			connection.con.close();
			pr.close();
		}else {
			msg.setText("Nothing to delete!");
		}
	}

}
