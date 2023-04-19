 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class query {
	@FXML
	private TableColumn<queryProduct, Integer> a;

	@FXML
	private TableColumn<queryProduct, Integer> id;

	@FXML
	private TableColumn<queryProduct, String> name;

	@FXML
	private TableView<queryProduct> t;

	@FXML
	private Button b;
	
	public void get() throws ClassNotFoundException, SQLException {
		connection.connectDB();
		String SQL=" select product.serial_num, product.product_name, sum(orders.quantity) as amount from orders , product  "
				+ "where product.serial_num=orders.productId group by product.serial_num  order by amount desc";
		Statement stmt = (Statement) connection.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		ArrayList<queryProduct> arr = new ArrayList<>();
		while(rs.next()) {
			arr.add(new queryProduct(rs.getInt(1),rs.getString(2),rs.getInt(3)));
		}
		ObservableList<queryProduct> list = FXCollections.observableArrayList(arr);
		id.setCellValueFactory(new PropertyValueFactory<queryProduct, Integer>("productId"));
		name.setCellValueFactory(new PropertyValueFactory<queryProduct, String>("productName"));
		a.setCellValueFactory(new PropertyValueFactory<queryProduct, Integer>("purchases"));
		t.setItems(list);
		
	}

}
