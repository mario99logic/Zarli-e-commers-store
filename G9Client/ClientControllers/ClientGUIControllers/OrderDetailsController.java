package ClientGUIControllers;

import java.io.IOException;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import Orders.Branch;
import Orders.Order;
import Orders.TypeOfSupply;
import RequestsAndResponses.FullMessage;
import RequestsAndResponses.Request;
import RequestsAndResponses.Response;
import Util.Constants;
import ZliClient.PopUpMsg;
import ZliClient.ZliClientUI;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * Class description: 
 * This is a class for 
 * controlling the Start Sales 
 *  
 *@author maisalon safory
 *
 */

public class OrderDetailsController extends UsersController implements Initializable {

	@FXML
	/**
	 * Label For Message For The User 
	 */
	private Label ErrorLabel;
	@FXML
	/**
	 * ComboBox for Branches
	 */
	private ComboBox<Branch> selectBranchComboBox;

	@FXML
	/**
	 * ComboBox for Times
	 */
	private ComboBox<String> Time;

	@FXML
	/**
	 * DatePicker for Dates
	 */
	private DatePicker Date;

	@FXML
	/**
	 * Text Field for Card Number
	 */
	private TextField TextFieldCard;
	/**
	 * variable of Order
	 */
	public static Order order;

	@FXML
	/**
	 * Text for Error text for user
	 */
	private Text errorText;

	
	/**
	 * message type of FullMessage
	 */
	public static FullMessage message;
	/**
	 * Array List of percent
	 */
	ArrayList<String> percent1 = new ArrayList<>();
	/**
	 * Array List of Branch
	 */
	ArrayList<String> branch1 = new ArrayList<>();

	private String branch;
	private String percent;

	@FXML
	/**
	 * After Clicking On Back Button 
	 * The Function Hide The Current Window 
	 * And Load The previous Window 
	 * And We Can Drag the Window How Ever We Want
	 * @param event
	 * @throws IOException
	 */
	public void BackButton(MouseEvent event) throws IOException {

		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/CatalogPage.fxml"));
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
	 * this function is for clicking the TA button we save the new order details
	 * 
	 * @param event
	 * @throws IOException
	 * @throws ParseException
	 */

	@FXML
	public void TAButton(MouseEvent event) throws IOException, ParseException {

		boolean checkContinue = false;
		if (isInvalid()) {
			errorText.setText("Please fill the empty fields!");
			errorText.setFill(Color.RED);
		} else {

			order.setSupplyType(TypeOfSupply.TAKE_AWAY);
			order.setDeliveryCost(Constants.NoDeliveryCost);
			order.setTotalPrice(CatalogController.TotalPrice);
			checkContinue = CheckIfCustomerHaveDiscount();
			if (checkContinue) {
				addDetails();

				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/Payment.fxml"));
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

				// init for the payment

			}
		}
	}

	/**
	 * this function saves the added order details and go to the delivery page
	 * 
	 * @param event
	 * @throws IOException
	 * @throws ParseException
	 */

	public void DeliveryButton(MouseEvent event) throws IOException, ParseException {

		boolean checkContinue = false;

		if (isInvalid()) {
			errorText.setText("Please fill the empty fields!");
			errorText.setFill(Color.RED);
		} else {

			order.setSupplyType(TypeOfSupply.DELIVERY);
			order.setTotalPrice(CatalogController.TotalPrice);
			order.setDeliveryCost(Constants.DeliveryCost);
			checkContinue = CheckIfCustomerHaveDiscount();
			if (checkContinue) {
				addDetails();

				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/ClientFXMLFiles/DeliveryDetails.fxml"));
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

				// DeliveryDetailsController deliveryDetailsController = new
				// DeliveryDetailsController();
				// deliveryDetailsController.initDeliveryScreen(order);

			}
		}
	}
	/**
	 * After Clicking On Exit Button
	 * The Function Send A Message To The Server 
	 * The Function LogOut The Account 
	 * And Disconnect From The Server  
	 * @param event
	 * @throws IOException
	 */
	public void ExitButton(MouseEvent event) {
		message = new FullMessage(Request.LOGOUT, Response.Wait, CurrentUser);
		ZliClientUI.ZliClientController.accept(message);
		message = new FullMessage(Request.Disconnect, Response.Wait, null);
		ZliClientUI.ZliClientController.accept(message);
		System.exit(0);
	}

	public void DisplayMessageForUser(String message) {
		Platform.runLater(() -> {
			ErrorLabel.setText(message);
		});

	}

	/**
	 * this function check if the user filled all the required fields
	 * 
	 * @return
	 */

	public boolean CheckIfCustomerHaveDiscount() {

		switch (CatalogController.IsSaleOn) {
		case "0":
			if (order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
				order.setTotalPrice((order.getTotalPrice() + Constants.DeliveryCost));
				Optional<ButtonType> Option = PopUpMsg
						.ConfirmationForUser("Price Of Delivery is: " + Constants.DeliveryCost + "\n"
								+ "The Final price is " + order.getTotalPrice() + "\n" + "Do you want to continue?");
				if (Option.get() == ButtonType.OK) {
					return true;
				} else
					return false;

			} else {
				Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser(
						"The Final price is " + order.getTotalPrice() + "\n" + "Do you want to continue");
				if (Option.get() == ButtonType.OK) {
					return true;
				} else
					return false;
			}
		case "1":
			double discount = Double.parseDouble(percent1.get(0));
			discount = Double.parseDouble(percent1.get(0));
			discount = 100.0 - discount;
			discount = discount / 100.0;
			if (order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
				order.setTotalPrice((order.getTotalPrice() * discount) + Constants.DeliveryCost);
				Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("You have a discount of " + percent1.get(0)
						+ "%" + "\n" + "Price Of Delivery is: " + Constants.DeliveryCost + "\n" + "The Final price is "
						+ order.getTotalPrice() + "\n" + "Do you want to continue?");
				if (Option.get() == ButtonType.OK) {
					return true;
				} else
					return false;

			} else {
				order.setTotalPrice(order.getTotalPrice() * discount);
				Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("You have a discount of " + percent1.get(0)
						+ "%" + "\n" + "The Final price is " + order.getTotalPrice());
				if (Option.get() == ButtonType.OK) {
					return true;
				} else
					return false;

			}
		case "2":
			String branchValue = selectBranchComboBox.getValue().toString();
			if (branch1.contains(branchValue)) {
				int index = branch1.indexOf(branchValue);
				discount = Double.parseDouble(percent1.get(index));
				discount = 100.0 - discount;
				discount = discount / 100.0;
				if (order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
					order.setTotalPrice((order.getTotalPrice() * discount) + Constants.DeliveryCost);
					Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser(
							"You have a discount of " + percent1.get(index) + "%" + "\n" + "Price Of Delivery is: "
									+ Constants.DeliveryCost + "\n" + "The Final price is " + order.getTotalPrice());
					if (Option.get() == ButtonType.OK) {
						return true;
					} else
						return false;

				} else {
					order.setTotalPrice((order.getTotalPrice() * discount));
					Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("You have a discount of "
							+ percent1.get(index) + "%" + "\n" + "The Final price is: " + order.getTotalPrice());
					if (Option.get() == ButtonType.OK) {
						return true;
					} else
						return false;
				}
			} else {
				if (order.getSupplyType().equals(TypeOfSupply.DELIVERY)) {
					order.setTotalPrice(order.getTotalPrice() + Constants.DeliveryCost);
					Optional<ButtonType> Option = PopUpMsg.ConfirmationForUser("Price Of Delivery is: "
							+ Constants.DeliveryCost + "\n" + "The Final price is " + order.getTotalPrice());
					if (Option.get() == ButtonType.OK) {
						return true;
					} else
						return false;

				} else {
					Optional<ButtonType> Option = PopUpMsg
							.ConfirmationForUser("The Final price is: " + order.getTotalPrice());
					if (Option.get() == ButtonType.OK) {
						return true;
					} else
						return false;

				}

			}

		}
		return false;

	}
	/**
	this function check if the ComboBox is null
	 * @return
	 */
	private boolean isInvalid() {
		
		
		boolean check = false;

		if (selectBranchComboBox.getValue() == null) {

			selectBranchComboBox.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			selectBranchComboBox.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}

		if (Time.getValue() == null) {

			Time.setStyle("-fx-border-color: red; -fx-background-radius: 15; -fx-border-radius: 15");
			check = true;
		} else {
			Time.setStyle("-fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15");
		}
		return check;
	}
	/**
	 * this function makes date to insert to DB

	 * @throws ParseException
	 */
	public void MakeDateForDB() throws ParseException {
		
		

		String time = Time.getValue();
		time = time + ":00";
		LocalDate date = Date.getValue();
		String dateFormatted = date + " " + time;
		Timestamp dateTimeStamp = Timestamp.valueOf(dateFormatted);
		order.setEstimatedDate(dateTimeStamp);

	}

	/**
	 * save all the details the user has selected
	 * 
	 * @throws ParseException
	 * 
	 */
	private void addDetails() throws ParseException {

		order.setGreetingCard(TextFieldCard.getText());
		Branch branch = selectBranchComboBox.getValue();
		MakeDateForDB();
		order.setBranch(branch);
	}

	/**
	 * this function open the OrderDetails page and get the order details from the
	 * previous page
	 * 
	 * @param order
	 */

	@Override
	/**
	 *Initializing The List After Getting All The Relevant Data
	 *Send To The Server Message That Contains All the Relevant Data 
	 */
	public void initialize(URL location, ResourceBundle resources) {
		OrderDetailsController.order = CartPageController.order;
		branch = CatalogController.branch;
		percent = CatalogController.percent;
		branch1 = CatalogController.branch1;
		percent1 = CatalogController.percent1;
		Date.setValue(LocalDate.now());
		Date.getEditor().setDisable(true);
		order.setTotalPrice(CatalogController.TotalPrice);

		ArrayList<Branch> bracnhesList = new ArrayList<>();
		bracnhesList.add(Branch.TheSecretGarden);
		bracnhesList.add(Branch.YourNeighborhoodFlorist);
		bracnhesList.add(Branch.BeautifulBlossoms);

		selectBranchComboBox.getItems().addAll(bracnhesList);

		List<String> hourArray = new ArrayList<String>(); // create hour array
		Date date = new Date();
		int hour = date.getHours();

		// disable the previous dates in the date picker
		Callback<DatePicker, DateCell> disablePreviousDates = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker param) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						LocalDate today = LocalDate.now();
						setDisable(empty || item.compareTo(today) < 0);
					}

				};
			}

		};
		Date.setDayCellFactory(disablePreviousDates);
		/*
		 * Set the times in the combo box for the default date we show first , which is
		 * the current date
		 */
		if (hour < 11) { // the current time is earlier than 11am (but in the current day)
			for (int i = 11; i < 24; i++) { // change to 23
				hourArray.add(String.valueOf(i) + ":00");
			}
		}

		else { // the current time is later than 11am (but in the current day)
			for (int i = hour + 1; i < 24; i++) { // change to 23
				hourArray.add(String.valueOf(i) + ":00");
			}
		}

		Time.getItems().addAll(hourArray);
		// when user clicked on some date in the Dater Picker
		Date.setOnAction((event) -> {

			// check if the clicked date is equal to the current date
			boolean checkIfCustomerPickedCurrentDayForTheSupply = Date.getValue().equals(LocalDate.now());
			hourArray.clear();
			Time.getItems().clear();

			/* adding elements to the hour array depending on the current time */
			if (checkIfCustomerPickedCurrentDayForTheSupply) { // if the customer picked the current day

				if (hour < 11) { // the current time is earlier than 11am (but in the current day)
					for (int i = 11; i < 23; i++) {
						hourArray.add(String.valueOf(i) + ":00");
					}
				}

				else { // the current time is later than 11am (but in the current day)
					for (int i = hour + 1; i < 24; i++) { // change to 23
						hourArray.add(String.valueOf(i) + ":00");
					}
				}

			}

			else { // the customer want the delivery for the next days
				for (int i = 11; i < 24; i++) { // change to 23
					hourArray.add(String.valueOf(i) + ":00");
				}
			}
			Time.getItems().addAll(hourArray);
		});

	}

}
