package ZliClient;


import ClientGUIControllers.ServerIPController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class responsible to start the main function
 *
 */
public class ZliClientUI extends Application{
	
	/**
	 * static variable of type ZliClientController
	 */
	public static ZliClientController ZliClientController;
	/**
	 * variable of type ServerIPController
	 */
	public ServerIPController ipFormController;

	/**
	 * This method starts the primary stage
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ipFormController = new ServerIPController(); /* create controller for first Frame */
		ipFormController.start(primaryStage); /* go to start method in the IPController */
		
		
	}
	
	/**
	 * this method lanches the main
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		
		launch(args);
	} // end main

	
	/**
	 * This method starts the controller
	 * @param ipTxt
	 */
	public static void StartController(String ipTxt) {
		
		ZliClientController = new ZliClientController(ipTxt,5555); /* start running with the given IP from the IP frame entered by the client */
	}

}
