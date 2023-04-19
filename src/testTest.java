import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class testTest implements Initializable {
	@FXML
	public TableView<Customer> table;

	@FXML
	public TableColumn<Customer, String> n;

	@FXML
	public TableColumn<Customer, Integer> ide;

	@FXML
	public TableColumn<Customer, String> add;

	@FXML
	private Button btn;

	String categories[] = { "Show all customers", "Search for a customer", "Delete Customer", "Add Customer",
			"Update Customer Info" };
	@FXML
	private ChoiceBox<String> choice = new ChoiceBox(FXCollections.observableArrayList(categories));

	@FXML
	public void show(ActionEvent event) {
		// table.getItems().removeAll();

		ArrayList<Customer> cust = new ArrayList<Customer>();
		Customer c = new Customer();
		System.out.println(cust.size());

		try {
			cust = c.ShowAllCustomers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<Customer> Clist = FXCollections.observableArrayList(cust);
		// TODO Auto-generated method stub
		n.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Name"));
		ide.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("C_ID"));
		add.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Address"));

		table.setItems(Clist);
		System.out.println("should print");
		System.out.println("done");

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ArrayList<Customer> cust = new ArrayList<Customer>();
		Customer c = new Customer();
		System.out.println(cust.size());

		try {
			cust = c.ShowAllCustomers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<Customer> Clist = FXCollections.observableArrayList(cust);
		// TODO Auto-generated method stub
		n.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Name"));
		ide.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("C_ID"));
		add.setCellValueFactory(new PropertyValueFactory<Customer, String>("C_Address"));

		table.setItems(Clist);
		System.out.println("should print");
		System.out.println("done");

		try {
			ConAndQueries.connectDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		choice.getItems().addAll(FXCollections.observableArrayList(categories));
		choice.setOnAction((event) -> {
			// int selectedIndex = choice.getSelectionModel().getSelectedIndex();
			Object selectedItem = choice.getSelectionModel().getSelectedItem();
			if (choice.getSelectionModel().getSelectedItem() == "Show all customers") {
				show(event);
			}

			else if (choice.getSelectionModel().getSelectedItem() == "Delete Customer") {

				try {
					FXMLLoader DeleteL = new FXMLLoader(getClass().getResource("deleteCust.fxml"));
					Parent delPane = (Parent) DeleteL.load();
					deleteCustController del = DeleteL.getController();
					Scene scene = new Scene(delPane);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage editStage = new Stage();
					editStage.setResizable(false);
					editStage.setScene(scene);
					editStage.show();
					System.out.println("Envoking deleteCust Screen");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (choice.getSelectionModel().getSelectedItem() == "Add Customer") {
				FXMLLoader AddL = new FXMLLoader(getClass().getResource("addCust.fxml"));
				Parent AddPane;
				try {
					AddPane = (Parent) AddL.load();
					addCustController add = AddL.getController();
					Scene scene = new Scene(AddPane);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage editStage = new Stage();
					editStage.setResizable(false);
					editStage.setScene(scene);
					editStage.show();
					System.out.println("Envoking updCust Screen");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		 	else if (choice.getSelectionModel().getSelectedItem() == "Update Customer Info") {
				FXMLLoader UpdL = new FXMLLoader(getClass().getResource("updCust.fxml"));
				Parent UpdPane;
				try {
					UpdPane = (Parent) UpdL.load();
					updCustController upd = UpdL.getController();
					Scene scene = new Scene(UpdPane);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage editStage = new Stage();
					editStage.setResizable(false);
					editStage.setScene(scene);
					editStage.show();
					System.out.println("Envoking addCust Screen");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
 

			else if (choice.getSelectionModel().getSelectedItem() == "Search for a customer") {
				
				FXMLLoader AddL = new FXMLLoader(getClass().getResource("searchCust.fxml"));
				Parent AddPane;
				try {
					AddPane = (Parent) AddL.load();
					searchCustController add = AddL.getController();
					Scene scene = new Scene(AddPane);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage editStage = new Stage();
					editStage.setResizable(false);
					editStage.setScene(scene);
					editStage.show();
					System.out.println("Envoking updCust Screen");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

}
