import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class welcomeController {

    @FXML
    private Button btn;

    @FXML
    void showReports(ActionEvent event)  {

    	  try {
     		 FXMLLoader report = new FXMLLoader(getClass().getResource("reports.fxml"));
  			Parent reportPane = (Parent) report.load();
  			reportsController repController =report.getController();
  			Scene scene = new Scene(reportPane);
 		//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

}
