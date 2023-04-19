import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Application.launch(args);

	}

	// @Override
	public void start(Stage stage) throws SQLException, ParseException, IOException, ClassNotFoundException {
 
		try {
			
		//Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Test.fxml"));
			 
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScreen.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
