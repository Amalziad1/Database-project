import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class topCustomerController implements Initializable{

    @FXML
    private TableView<CustOrder> table;

    @FXML
    private TableColumn<CustOrder, Integer> cid;

    @FXML
    private TableColumn<CustOrder, String> cname;

    @FXML
    private TableColumn<CustOrder, String> caddress;

    @FXML
    private TableColumn<CustOrder, Double> total;
    
    
    
    
    public void initialize(URL url, ResourceBundle rb) {

		ArrayList<CustOrder> item = new ArrayList<>();
		ArrayList<Double> paid = new ArrayList<>();

		try {
			String countSQL = " Select * from Customers ";
			ConAndQueries.connectDB();

			Statement getIDs = ConAndQueries.con.createStatement();
			ResultSet getIDrs = getIDs.executeQuery(countSQL);

			while (getIDrs.next()) {
				int id = getIDrs.getInt(1);
				String name = getIDrs.getString(2);
				String address = getIDrs.getString(3);

				double sum = 0;

				String SQL = "select sum(orders.quantity * product.price) from Customers, orders, product"
						+ " where Customers.C_ID= orders.customerID and orders.productID=product.serial_num and Customers.C_ID=?;";

				PreparedStatement getSum = ConAndQueries.con.prepareStatement(SQL);

				getSum.setInt(1, id);
				getSum.execute();
				ResultSet rs = getSum.executeQuery();

				while (rs.next()) {
					sum = rs.getDouble(1);
					item.add(new CustOrder(id, name, address, sum));

				}

				paid.add(sum);

			}

			ArrayList<CustOrder> inList = new ArrayList<>();
			Collections.sort(paid, Collections.reverseOrder());
			for (int i = 0; i < 5; i++) {
				System.out.println(paid.get(i));
				for (int j = 0; j < item.size(); j++) {
					if ((item.get(j).Sum == paid.get(i)) && inList.contains(item.get(j)) == false) {
						System.out.println(item.get(j).C_name + "   " + item.get(j).C_ID);
						inList.add(item.get(j));
						break;
					}

					else if ((item.get(j).Sum == paid.get(i)) && inList.contains(item.get(j)) == false) {
						System.out.println("---------" + item.get(j).C_name + "   " + item.get(j).C_ID);
						inList.add(item.get(j));
					}

				}
			}

			for (int i = 0; i < inList.size(); i++) {
				System.out.println(inList.get(i).C_name + "   " + inList.get(i).Sum);
			}
			
			
			ObservableList<CustOrder> TopList = FXCollections.observableArrayList(inList);
			
			 
			cid.setCellValueFactory(new PropertyValueFactory<CustOrder, Integer>("C_ID"));
			cname.setCellValueFactory(new PropertyValueFactory<CustOrder, String>("C_name"));
			caddress.setCellValueFactory(new PropertyValueFactory<CustOrder, String>("C_Address"));
			total.setCellValueFactory(new PropertyValueFactory<CustOrder, Double>("Sum"));

			table.setItems(TopList);
    	
			
			
			
			
			
			
			
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
	    
		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
