 

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

public class SupplierController {

	@FXML
	private TableView<supplier> TableData;

	@FXML
	private TableColumn<supplier, String> name;

	@FXML
	private TableColumn<supplier, Integer> phone;

	@FXML
	private TableColumn<supplier, String> address;
	
	@FXML
	private TableColumn<supplier, String> email;
	@FXML
	private Button Update;
	
    @FXML
    private Button btnRefresh;

	@FXML
	private TextField oldName;

	@FXML
	private TextField newName;

	@FXML
	private TextField newPhone;

	@FXML
	private TextField newAddress;
	@FXML
	private TextField newEmail;

	@FXML
	private TextField SearchFiled;

	@FXML
	private TextField nameCompany;

	@FXML
	private Button Delete;

	@FXML
	private TextField addName;

	@FXML
	private TextField addPhone;

	@FXML
	private TextField addAddress;
	@FXML
	private TextField addEmail;
	
	@FXML
	private Button add;

	private ArrayList<supplier> data;
	private ObservableList<supplier> dataList;
	private String companyName;

	public void initialize() {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
	//TableData.setEditable(true);

		name.setCellValueFactory(new PropertyValueFactory<supplier, String>("supplier_name"));
		name.setCellFactory(TextFieldTableCell.<supplier>forTableColumn());
		name.setOnEditCommit((CellEditEvent<supplier, String> t) -> {
			String string = t.getRowValue().getSupplier_name();
			((supplier) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setSupplier_name(t.getNewValue()); // display
			// only

			try {
				updateName(string, t.getNewValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		phone.setCellValueFactory(new PropertyValueFactory<supplier, Integer>("phone"));
		phone.setCellFactory(TextFieldTableCell.<supplier, Integer>forTableColumn(new IntegerStringConverter()));
		phone.setOnEditCommit((CellEditEvent<supplier, Integer> t) -> {
			((supplier) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setPhone(t.getNewValue()); // display
			// only

			//updatePhone(t.getRowValue(). getSupplier_name(), t.getNewValue());
				updatePhone(t.getRowValue(). getSupplier_name(), t.getNewValue());
		});

		address.setCellValueFactory(new PropertyValueFactory<supplier, String>("address"));
		address.setCellFactory(TextFieldTableCell.<supplier>forTableColumn());
		address.setOnEditCommit((CellEditEvent<supplier, String> t) -> {
			((supplier) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setAddress(t.getNewValue());// display
			// only

			updateAddress(t.getRowValue().getSupplier_name(), t.getNewValue());
		});
		
		email.setCellValueFactory(new PropertyValueFactory<supplier, String>("email"));
		email.setCellFactory(TextFieldTableCell.<supplier>forTableColumn());
		email.setOnEditCommit((CellEditEvent<supplier, String> t) -> {
			((supplier) t.getTableView().getItems().get(t.getTablePosition().getRow()))
					.setEmail(t.getNewValue());
			// only

			updateEmail(t.getRowValue().getSupplier_name(), t.getNewValue());
		});
 
		getData();
		TableData.setItems(dataList);
		search();
		getCompanyName();
	}

	private void updateAddress(String companyName, String newValue) {
	    try {
	        MakeConnection.connect();
	        MakeConnection.con.createStatement().executeUpdate("update supplier set address = '" + newValue
	                + "' where supplier_name = '" + companyName + "';");
	        MakeConnection.con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();}
	    }


	private void updatePhone(String companyName, int newValue) {
	    try {
	        MakeConnection.connect();
	        MakeConnection.con.createStatement().executeUpdate("update provide_company set phone = '" + newValue
	                + "' where supplier_name = '" + companyName + "';");
	        MakeConnection.con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void updateEmail(String companyName, String newValue) {
	    try {
	        MakeConnection.connect();
	        MakeConnection.con.createStatement().executeUpdate("update supplier set email = '" + newValue
	                + "' where supplier_name = '" + companyName + "';");
	        MakeConnection.con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();}
	    }


	private void getData() {
	    String SQL = "select * from supplier order by supplier_name";
	    try {
	        MakeConnection.connect();
	        java.sql.Statement state = MakeConnection.con.createStatement();
	        ResultSet rs = state.executeQuery(SQL);
	        while (rs.next()) {
	            supplier company = new supplier( rs.getString(1), rs.getInt(2),
	                    rs.getString(3) ,rs.getString(4));
	            dataList.add(company);
	        }
	        rs.close();
	        state.close();
	        MakeConnection.con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}




	private void updateName(String companyName, String newValue) throws Exception {
		try {
			MakeConnection.connect();
			Statement st = MakeConnection.con.createStatement();
			String sql = "update supplier set company_name = '" + newValue + "' where company_name = '" + companyName + "';";
			st.executeUpdate(sql);
			MakeConnection.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();}
		}

	private void search() {
		supplier supplier=new supplier ();
		FilteredList<supplier> filteredData = new FilteredList<>(dataList, b -> true);
		SearchFiled.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(provide_company -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (supplier.getSupplier_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if (String.valueOf(provide_company.getPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				} else if (supplier.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; }

				else
					return false; // Does not match.
			});
		});
		SortedList<supplier> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(TableData.comparatorProperty());
		TableData.setItems(sortedData);
	}
/////////////////////////////////////////////////
	@FXML
	void addOnAction(ActionEvent event) {
		try {

			Stage stage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("AddSupplier.fxml"));
			Scene scene = new Scene(root, 584, 263);
			stage.setScene(scene);
			stage.setTitle("Add a new suppling Company");
			stage.showAndWait();

		} catch (Exception e) {
			// TODO: handle exception
		}
		if (supplier.prov != null) {
			dataList.add(supplier.prov);
		}
		supplier.prov = null;
		initialize();
	}
//////////////////////////////////////////////////
	@FXML
	void deleteOnAction(ActionEvent event) {
		try {
			if (nameCompany.getText() != null) {
				String nameString = nameCompany.getText();
				deleteRow(nameString);
			}

			nameCompany.clear();
		} catch (Exception e) {

		}
		initialize();

	}

	@FXML
	void deleteOnAction2(ActionEvent event) throws Exception {

		ObservableList<supplier> selectedRows = TableData.getSelectionModel().getSelectedItems();
		ArrayList<supplier> rows = new ArrayList<>(selectedRows);
		if (rows.size() == 0) {
			return;
		}

		deleteRow(rows.get(0).getSupplier_name());
		initialize();

	}

	private void deleteRow(String nameString) throws Exception {
		try {

			String sql =("delete from  supplier where supplier_name ='" + nameString + "';");
			Statement st = null;
			MakeConnection.connect();
			st = MakeConnection.con.createStatement();
			ResultSet rs = st.executeQuery(sql);


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void updateOnAction(ActionEvent event) throws SQLException {

		try {
			if (oldName.getText() != null) {
				String st = oldName.getText();

				if (newPhone.getText().length() > 0) {
			 	updatePhone(st, Integer.parseInt(newPhone.getText()));
					 
				}

				if (newAddress.getText().length() > 0) {
					updateAddress(st, newAddress.getText());
				}
				if (newName.getText().length() > 0) {
					updateName(st, newName.getText());
				}
				if (newEmail.getText().length() > 0) {
					updateName(st, newEmail.getText());
				}
		
				oldName.clear();
				newName.clear();
				newPhone.clear();
				newAddress.clear();
				newEmail.clear();
				initialize();

			}
		} catch (Exception e) {
			oldName.clear();
			newName.clear();
			newPhone.clear();
			newAddress.clear();
			newEmail.clear();
			showDialog("new Phone", "", "you entered a rong format for phone", AlertType.ERROR);
		}


	}

	public void showDialog(String title, String header, String body, AlertType type) {
		Alert alert = new Alert(type); 
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(body);

		alert.show();

	}



	@FXML
	void btnRefresh(ActionEvent event) {
		initialize();
	}

	@FXML

	private void getCompanyName() {
		TableData.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				companyName = TableData.getSelectionModel().getSelectedItem().getSupplier_name();

			} else {
				companyName = null;
			}
		});
	}
}
