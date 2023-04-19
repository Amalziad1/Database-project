//package application;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RestockController implements Initializable {
    @FXML private TableView<product> tableView;
   // @FXML private TableColumn<product, Integer> serialNumCol;
    @FXML private TableColumn<product, String> productNameCol;
    @FXML private TableColumn<product, Integer> stockCol;
    @FXML private TableColumn<product, Double> priceCol;
    @FXML private TableColumn<product, Double> costCol;
    @FXML private TableColumn<product, String> supplierNameCol;
    @FXML private TableColumn<product, String> categoryCol;

    private ObservableList<product> Product = FXCollections.observableArrayList();

   
    public void initialize(URL url, ResourceBundle rb)  {
    	System.out.println("-------------");
    	showRestock();
        
        
    }
    
    
    public void showRestock() {
    	ArrayList<product> p = new ArrayList<product>();		
    	//Restock r= new Restock();
    	

        try {
        	
        //	p=Restock.getProductsToRestock();
           MakeConnection.connect();
            Statement statement = MakeConnection.con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
           
            while (resultSet.next()) {
               // int serial_num = resultSet.getInt("serial_num");
                int serial_num = resultSet.getInt(1);
               // String product_name = resultSet.getString("product_name");
                String product_name = resultSet.getString(2);
               // int stock = resultSet.getInt("stock");
                int stock = resultSet.getInt(4);
            //    double price = resultSet.getDouble("price");
                double price = resultSet.getDouble(3);
              //  double cost = resultSet.getDouble("cost");
                double cost = resultSet.getDouble(6);
                //String supplier_name = resultSet.getString("supplier_name");
                String supplier_name = resultSet.getString(7);
              //  String category = resultSet.getString("category");
                String category = resultSet.getString(5);

                Product.add(new product(serial_num, product_name, stock, price, cost, supplier_name, category));
                System.out.println(product_name);
                
            } 
            ObservableList<product> list = FXCollections.observableArrayList(Product);
        //    serialNumCol.setCellValueFactory(new PropertyValueFactory<product, Integer>("serial_num"));
            productNameCol.setCellValueFactory(new PropertyValueFactory<product, String>("product_name"));
            stockCol.setCellValueFactory(new PropertyValueFactory<product,Integer>("stock"));
            priceCol.setCellValueFactory(new PropertyValueFactory<product, Double>("price"));
            costCol.setCellValueFactory(new PropertyValueFactory<product,Double>("cost"));
            supplierNameCol.setCellValueFactory(new PropertyValueFactory<product, String>("supplier_name"));
            categoryCol.setCellValueFactory(new PropertyValueFactory<product, String>("category"));
            tableView.setItems(list);
    	
        	
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    	
    	
    	
    	
    	
    }
}
