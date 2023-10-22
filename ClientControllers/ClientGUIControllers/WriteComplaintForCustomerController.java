package ClientGUIControllers;

import java.io.IOException;


import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import customerService.Complaint;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class description: 
 * This is a class for 
 * controlling the Write Complaint for customer 
 *  
 *@author Obied haddad
 *
 */

public class WriteComplaintForCustomerController extends UsersController implements Initializable {

	/**
	 * TextArea of TextAreaField
	 */
	@FXML
	private TextArea TextAreaField;
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	@FXML
	/**
	 * Label For Message For The User 
	 */
	private Label ErrorLabel;
	@FXML
	/**
	 * variable of complaint
	 */
	
	private Complaint complaint;
	
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		complaint = WorkerInsertComplaintChooseOrder.complaint;
	}

	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void BackButton(MouseEvent event) throws Exception {
		
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/InsertComplaintChooseOrder.fxml"));
		Scene scene = new Scene(root);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		scene.setOnMousePressed(pressEvent -> {
			scene.setOnMouseDragged(dragEvent -> {
				primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 */
	@FXML
	public void ExitButton(MouseEvent event) {
		
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	/**
	 * After Clicking On Finish Button 
	 * The Function Get The Text Field Contents
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void FinishButton(ActionEvent event) throws IOException {
		
		

		if (TextAreaField.getText().equals("")) {
			PopUpMsg.AlertForUser("Please enter text before submitting Complaint");
		} else {
			PopUpMsg.AlertForUser("The complaint's details was proccesed into the System");
			complaint.setText(TextAreaField.getText());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			complaint.setComplaintDate(timestamp);
			message = new FullMessage(Request.ADD_COMPLAINT_FROM_USER_TO_DB, Response.Wait, complaint);
			ZliClientUI.ZliClientController.accept(message);
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/ComplaintHandling.fxml"));
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			scene.setOnMousePressed(pressEvent -> {
				scene.setOnMouseDragged(dragEvent -> {
					primaryStage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
					primaryStage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
				});
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}

	/**
	 * The Function Display's The Message On The Label
	 * @param message
	 */
    private void errorControl(String message) {
		

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ErrorLabel.setText(message);
				ErrorLabel.setVisible(true);
			}

		});
	}

}
