package ClientGUIControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale.Category;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Orders.Branch;
import Orders.DominantColor;
import Orders.FlowerColor;
import Orders.Item;
import Orders.ItemCategory;
import Orders.ItemType;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class is responsible for adding Items to catalog
 * @author Ebrahem, Enbtawe
 *
 */
public class CatalogAddController extends UsersController implements Initializable {
	
	/**
	 * static message of type FullMessage to send to server
	 */
	public static FullMessage message;
	/**
	 * Variable anchorid of type Pane 
	 */
	@FXML
	private Pane anchorid;
	/**
	 * TextField for path of picture
	 */
	@FXML
	private TextField TextFieldPath;
	/**
	 * TextField for name of item
	 */
	@FXML
	private TextField TextFieldName;
	/**
	 * TextField for price
	 */
	@FXML
	private TextField TextFieldPrice;
	/**
	 * TextField of amount
	 */
	@FXML
	private TextField TextFieldAmount;
	/**
	 *ComboBox for ItemCategory
	 */
	@FXML
	private ComboBox<ItemCategory> CategoryComboBox;
	/**
	 * ComboBox for flowerColor
	 */
	@FXML
	private ComboBox<FlowerColor> ColorComboBox;
	/**
	 * ComboBox for ItemType
	 */
	@FXML
	private ComboBox<ItemType> TypeComboBox;
	/**
	 * ComboBox For dominant Color
	 */
	@FXML
	private ComboBox<DominantColor> DominantComboBox;
	/**
	 * Error label to display errors
	 */
	@FXML
	private Label ErrorLabel;

	/**
	 *This method initializes the comboBoxes
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		SetComboBoxes();
	}

	/**
	 * This method sets all the comboBoxes values
	 */
	public void SetComboBoxes() {
		
		ArrayList<ItemCategory> categoryList = new ArrayList<>();
		categoryList.add(ItemCategory.BOUQUET);
		categoryList.add(ItemCategory.FLOWER);

		ArrayList<FlowerColor> colorList = new ArrayList<>();
		colorList.add(FlowerColor.GREEN);
		colorList.add(FlowerColor.MIXED);
		colorList.add(FlowerColor.PINK);
		colorList.add(FlowerColor.PURPLE);
		colorList.add(FlowerColor.RED);
		colorList.add(FlowerColor.WHITE);
		colorList.add(FlowerColor.YELLOW);

		ArrayList<ItemType> typeList = new ArrayList<>();
		typeList.add(ItemType.FLORABLOOM);
		typeList.add(ItemType.LILLY);

		ArrayList<DominantColor> dominantColor = new ArrayList<>();
		dominantColor.add(DominantColor.GREEN);
		dominantColor.add(DominantColor.MIXED);
		dominantColor.add(DominantColor.PINK);
		dominantColor.add(DominantColor.PURPLE);
		dominantColor.add(DominantColor.RED);
		dominantColor.add(DominantColor.WHITE);
		dominantColor.add(DominantColor.YELLOW);

		DominantComboBox.getItems().addAll(dominantColor);
		TypeComboBox.getItems().addAll(typeList);
		ColorComboBox.getItems().addAll(colorList);
		CategoryComboBox.getItems().addAll(categoryList);

	}

	/**
	 * This method is responsible to check if all textfield is not empty
	 * @return check (true,false)
	 */
	private boolean TextFieldsIsInvalid() {
		boolean check = false;

		if (TextFieldName.getText().equals("")) {

			TextFieldName.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldName.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		if (TextFieldPrice.getText().equals("")) {

			TextFieldPrice.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldPrice.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		if (TextFieldAmount.getText().equals("")) {

			TextFieldAmount.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldAmount.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TextFieldPath.getText().equals("")) {

			TextFieldPath.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TextFieldPath.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;

	}

	/**
	 * This method checks if all comboBoxes are not null returns false if are null
	 * @return check
	 */
	private boolean ComboBoxesIsInvalid() {
		boolean check = false;

		if (CategoryComboBox.getValue() == null) {

			CategoryComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			CategoryComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (ColorComboBox.getValue() == null) {

			ColorComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			ColorComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (TypeComboBox.getValue() == null) {

			TypeComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			TypeComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (DominantComboBox.getValue() == null) {

			DominantComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			DominantComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;
	}

	/**
	 * This method responsible for sending message to server to add the desired item to db
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void AddButton(ActionEvent event) throws IOException {
		

		String price = TextFieldPrice.getText();
		String amount = TextFieldAmount.getText();
		String name = TextFieldName.getText();
		String path = TextFieldPath.getText();

		if (TextFieldsIsInvalid()) {
			errorControl("Please fill all Empty Details");
			if (ComboBoxesIsInvalid()) {
				errorControl("Please fill all Empty Details");
			}
		} else {
			char firstcharAmount = amount.charAt(0);
			char firstcharPrice = price.charAt(0);
			if (!(isNumeric(price)) || !(isNumeric(amount)))
				errorControl("input is Invalid for Amount or Price!!");
			else if (firstcharPrice == '0' || firstcharAmount == '0') {
				errorControl("Input Cannot Start with 0 for Price and amount");
			} else {

				Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("Are You Sure You want to Add this Item?!");
				if (Option.get() == ButtonType.OK) {
					ItemCategory category = CategoryComboBox.getValue();
					FlowerColor color = ColorComboBox.getValue();
					String Name = TextFieldName.getText();
					double Price = Double.parseDouble(TextFieldPrice.getText());
					String Path = TextFieldPath.getText();
					String card = "hey you";
					ItemType type = TypeComboBox.getValue();
					DominantColor dominant = DominantComboBox.getValue();
					int Amount = Integer.parseInt(TextFieldAmount.getText());

					Item itemToAdd = new Item(null, category, color, Name, Price, Path, card, type, dominant, Amount);
					message = new FullMessage(Request.ADD_ITEM_TO_DB, Response.Wait, itemToAdd);
					ZliClientUI.ZliClientController.accept(message);
					PopUpMsg.AlertForUser("Item Added Successfully");
					((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogManagement.fxml"));
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
				else {
					
				}
			}
		}
	}

	/**
	 * This method checks if the text contains numbers only
	 * @param strNum
	 * @return true,false
	 */
	public static boolean isNumeric(String strNum) {
		
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * This method exist the window and disconnects user
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
	 * This method loads the previous page 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void BackButton(MouseEvent event) throws Exception {
		
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogManagement.fxml"));
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
	 * This method opens the window to browse for a file from pc
	 * @param event
	 */
	@FXML
	public void BrowseButton(ActionEvent event) {
		

		String slash = "\\\\";
		String path;
		String[] parsedMsgFromClient;
		final FileChooser chooser = new FileChooser();
		Stage stage = (Stage) anchorid.getScene().getWindow();
		File file = chooser.showOpenDialog(stage);

		if (file != null) {
			parsedMsgFromClient = file.getAbsolutePath().split("C:");
			path = "File:C:" + slash + parsedMsgFromClient[1];
			TextFieldPath.setText(path);
		}
	}

	/**
	 * This method controls all errors
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
