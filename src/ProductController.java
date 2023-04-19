
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeMap;
import com.mysql.jdbc.StringUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProductController implements Initializable {
	@FXML
	private TableView<product> table;
	@FXML
	private TableColumn<product, Integer> serial_num;
	@FXML
	private TableColumn<product, String> product_name;
	@FXML
	private TableColumn<product, Integer> price;
	@FXML
	private TableColumn<product, Integer> cost;
	@FXML
	private TableColumn<product, Integer> stock;
	@FXML
	private TableColumn<product, String> category;
	@FXML
	private TableColumn<product, String> supplier_name;
	public ObservableList<product> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			MakeConnection.connect();
			String sql = "SELECT * FROM product ORDER BY serial_num DESC";
			Statement st = null;
			st = MakeConnection.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(new product());
			}
			MakeConnection.con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		table.setItems(list);
	}

/////////////////////////search 
	@FXML
	private TextField searchid;
	@FXML
	private TextField searchname;
	@FXML
	private Label productname;
	@FXML
	private Label actualprice;
	@FXML
	private Label showprice;
	@FXML
	private Label productqty;
	@FXML
	private Label suppliername;
	@FXML
	private Label productcat;

	@FXML
	private Label error;
	@FXML
	private Label productid;
	@FXML
	private Label searchproductid;
	@FXML
	private TextField editsearchid;
	@FXML
	private TextField editsearchname;
	@FXML
	private TextField editproductname;
	@FXML
	private TextField editactualprice;
	@FXML
	private TextField editshowprice;
	@FXML
	private TextField editproductqty;
	@FXML
	private TextField editproductcat;
	@FXML
	private TextField editproductsupplier;
	@FXML
	private Label editerror;

	public void searchById(ActionEvent event) throws Exception	{
		 
			MakeConnection.connect();
			if(!StringUtils.isStrictlyNumeric(searchid.getText()))
			{
				error.setText("Not a valid id");
			}
			else
			{
				String sql = "SELECT * FROM product where serial_num = "+Integer.parseInt(searchid.getText());
			
				
				Statement st = null;
			 
					st = MakeConnection.con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if(rs.next())
					{	System.out.println("dsfsfsf");
						error.setText("");
						searchname.setText("");
						error.setText("");
						searchid.setText("");
						searchproductid.setText(String.valueOf(rs.getInt(1)));
						productname.setText(rs.getString(2));
						showprice.setText(String.valueOf(rs.getDouble(6)));
						productqty.setText(String.valueOf(rs.getInt(4)));
						productcat.setText(rs.getString(5));
						actualprice.setText(String.valueOf(rs.getDouble(3)));
						suppliername.setText(rs.getString(7));
					}
					else
					{
						searchproductid.setText("");
						productname.setText("");
						actualprice.setText("");
						showprice.setText("");
						productqty.setText("");
						productcat.setText("");
						suppliername.setText("");
						error.setText("Sorry...No Product at this Id");
					}
				 
			}
			
			
		
		
	}

	public void searchByName(ActionEvent event) throws Exception {
		MakeConnection.connect();
		String text = searchname.getText();
		String sql = "SELECT * FROM product where product_name = '" + text + "'";
		Statement st  = MakeConnection.con.createStatement();

			ResultSet rs = st.executeQuery(sql);
		 
			if (rs.next()) {
				error.setText("");
				searchid.setText("");
				searchproductid.setText(String.valueOf(rs.getInt(1)));
				productname.setText(rs.getString(2));
				showprice.setText(String.valueOf(rs.getDouble(6)));
				productqty.setText(String.valueOf(rs.getInt(4)));
				productcat.setText(rs.getString(5));
				actualprice.setText(String.valueOf(rs.getDouble(3)));
				suppliername.setText(rs.getString(7));
			} else {
				searchproductid.setText("");
				productname.setText("");
				actualprice.setText("");
				showprice.setText("");
				productqty.setText("");
				productcat.setText("");
				suppliername.setText("");
				error.setText("Sorry...No Product at this Id");
			}

		 System.out.println("Searching by name");

	}

	public void editProductById() throws Exception {
		String text = editsearchid.getText();
		String sql = "SELECT * FROM product where serial_num = " + Integer.parseInt(editsearchid.getText());
		Statement st = null;

		 
			MakeConnection.connect();
			st = MakeConnection.con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				editerror.setText("");
				editsearchname.setText("");
				productid.setText(String.valueOf(rs.getInt(1)));
				editproductname.setText(rs.getString(2));
				editactualprice.setText(String.valueOf(rs.getDouble(6)));
				editshowprice.setText(String.valueOf(rs.getDouble(3)));
				editproductqty.setText(String.valueOf(rs.getInt(4)));
				editproductcat.setText(rs.getString(5));
				editproductsupplier.setText(rs.getString(7));
			} else {
				editproductname.setText("");
				editactualprice.setText("");
				editshowprice.setText("");
				editproductqty.setText("");
				editproductcat.setText("");
				editsearchid.setText("");
				editproductsupplier.setText("");
				editerror.setText("Sorry...No Product at this Id");
			}
			 

		 
	}

	/*public void editProductByName(ActionEvent event) throws Exception {
	    MakeConnection.connect();
	    if (editsearchname.getText().equals("")) {
	        editerror.setText("Enter a valid name");
	    } else {
	        String sql = "SELECT * FROM product where product_name = '" + editsearchname.getText() + "'";
	        Statement st = null;
	        st = MakeConnection.con.createStatement();
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next()) {
	            editerror.setText("");
	            editsearchid.setText("");
	            editproductname.setText(rs.getString("product_name"));
	            editactualprice.setText(String.valueOf(rs.getInt("cost")));
	            editshowprice.setText(String.valueOf(rs.getInt("price")));
	            editproductqty.setText(String.valueOf(rs.getInt("stock")));
	            editproductcat.setText(rs.getString("category"));
	            editproductsupplier.setText(rs.getString("supplier_name"));
	        } else {
	            editerror.setText("No product found with this name");
	        }
	        MakeConnection.con.close();
	    }
	}*/
	public void updateProduct(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		 
		String name = editproductname.getText();
		String actualprice = editactualprice.getText();
		String showprice = editshowprice.getText();
		String productqty = editproductqty.getText();
		String productcat = editproductcat.getText();
		String productsupplier = editproductsupplier.getText();
		String sql = "SELECT * FROM product ";
		Statement st = null;

		new MakeConnection();
		st = MakeConnection.con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
		
					 
			editerror.setText("");
			editsearchid.setText("");
			productid.setText(String.valueOf(rs.getInt(1)));
			editproductname.setText(rs.getString(2));
			editactualprice.setText(String.valueOf(rs.getDouble(3)));
			editshowprice.setText(String.valueOf(rs.getDouble(6)));
			editproductqty.setText(String.valueOf(rs.getInt(4)));
			editproductcat.setText(rs.getString(5));
		} else {
			editproductname.setText("");
			editactualprice.setText("");
			editshowprice.setText("");
			editproductqty.setText("");
			editproductcat.setText("");
			editsearchname.setText("");
			editproductsupplier.setText("");
			editerror.setText("Sorry...No Product has this Name");
		}

		ArrayList<Integer> cat = new ArrayList<Integer>();
	
		while (rs.next()) {
			cat.add(rs.getInt(1));
		}
		if (!cat.contains(Integer.parseInt(productcat))) {
			editerror.setText("Invalid Category...Available : " + cat);
		} else {
			sql = "Update product set product_name = '" + name + "',price = " + actualprice + ",cost= " + showprice
					+ ",stock = " + productqty + ",category = " + productcat + ",supplier_name =" + productsupplier
					+ " WHERE serial_num = " + productid.getText();
			int res = st.executeUpdate(sql);
			if (res > 0) {
				editsearchid.setText("");
				productid.setText("");
				editsearchname.setText("");
				editproductname.setText("");
				editactualprice.setText("");
				editshowprice.setText("");
				editproductqty.setText("");
				editproductcat.setText("");
				editsearchname.setText("");
				editerror.setText("Product Updated");
			} else {
				editerror.setText("Database Error");
			}
		}

	}

	public void deleteProduct(ActionEvent event) throws ClassNotFoundException, SQLException {
		String id = searchproductid.getText();
		if (id.isEmpty()) {
			error.setText("Product Empty...");
		} else {
			String sql = "Update products set product_status = 'deleted' where serial_num = " + id;
			Statement st = MakeConnection.con.createStatement();
			int result = st.executeUpdate(sql);
			if (result > 0) {
				searchid.setText("");
				searchproductid.setText("");
				productname.setText("");
				actualprice.setText("");
				showprice.setText("");
				productqty.setText("");
				productcat.setText("");
				suppliername.setText("");
				error.setTextFill(Color.RED);
				error.setText("Product Deleted...");
			} else {
				error.setText("Database Error...Try Again");
			}
		}
	}
////////////////////////////--ADD--////////////////////////////////////////////////

	@FXML
	private TextField getid;

	@FXML
	private Label insertmessage;
	@FXML
	private TextField getname;
	@FXML
	private TextField getactualprice;
	@FXML
	private TextField getshowprice;
	@FXML
	private TextField getqty;
	@FXML
	private TextField getcategory;
	@FXML
	private TextField getsupplier;

	public void addProduct(ActionEvent event) throws Exception {
		String name = getname.getText();
		String act_price = getactualprice.getText();
		String show_price = getshowprice.getText();
		String qty = getqty.getText();
		String cat = getcategory.getText();
		String suppliername = getsupplier.getText();
		String id = getid.getText();

		if(id.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("You must enter an id for the product");
	} else if(name.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("Name can not be empty");
		} else if (act_price.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText(" Price can not be empty");
		} else if (show_price.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("Product Cost can not be empty");
		} else if (qty.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("Please Enter the Stock you have of this product");
		} else if (cat.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("You Must Enter A Category For This Product");
		} else if (suppliername.isEmpty()) {
			insertmessage.setTextFill(Color.RED);
			insertmessage.setText("You must enter suppliers name");
		} else {
			String sql = "INSERT INTO product (productid,product_name,price,cost,stock,category,supplier_name)\r\n" + "VALUES('"+
					id+"," +name + "'," + show_price + "," + act_price + "," + qty + "," + cat + "," + suppliername + ")";
			Statement st = MakeConnection.con.createStatement();
			int result = st.executeUpdate(sql);
			if (result > 0) {
				insertmessage.setTextFill(Color.GREEN);
				insertmessage.setText("Product Inserted");
				 
				getname.setText("");
				getactualprice.setText("");
				getshowprice.setText("");
				getqty.setText("");
				getcategory.setText("");
				getsupplier.setText("");
			} else {
				insertmessage.setText("Database Error...");
			}

		}
	}

///////////////////////////////--ADD--////////////////////////////////////////
	public void reloadTable(ActionEvent event) throws IOException {
	    Parent root = FXMLLoader.load(getClass().getResource("ProductController.fxml"));
	    Scene scene = new Scene(root);
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(scene);
	    stage.show();
	}
	       
}