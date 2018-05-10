package gui.controller;

import bll.logic.BankAccountLogic;
import bll.logic.ClientLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * controller for the bank account list view
 *
 * @author François Burgener, Bryan Curchod
 * @version 1.5
 */
public class Controller_bankAccount implements Initializable, IController {
	
	
	HashMap<Integer, AccountDisplayer> displayerList;
	
	/**
	 * class to display a bank account with a GridPane
	 */
	private class AccountDisplayer extends GridPane {
		
		/*Default size*/
		private final int WIDTH = 100;
		private final int HEIGHT = 50;
		
		private Label nameAccount;
		private Label amountAccount;
		private BankAccountLogic bankAccount;
		
		/**
		 * Constructor of the class
		 *
		 * @param bankAccount bank account we want to display
		 */
		public AccountDisplayer(BankAccountLogic bankAccount) {
			
			this.bankAccount = bankAccount;
			nameAccount = new Label(bankAccount.getName());
			amountAccount = new Label("" + bankAccount.getAmount() + " CHF");
			
			this.add(nameAccount, 0, 0);
			this.add(amountAccount, 0, 1);
			
			this.getStyleClass().add("AccountDisplay");
			this.setPrefSize(WIDTH, HEIGHT);
			
			/**
			 * Click event who load the detail bank account frame
			 */
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				
				@Override public void handle(MouseEvent event) {
					
					detailBankAccount(bankAccount);
				}
			});
		}
		
		public void redraw() {
			
			nameAccount.setText(bankAccount.getName());
			amountAccount.setText(String.valueOf(bankAccount.getAmount()));
		}
	}
	
	
	@FXML private FlowPane frame_bankAccount;
	@FXML private Button create_button;
	@FXML private AnchorPane paneform;
	
	/**
	 * Event on the create button that will load the account creation page
	 */
	public void callform() {
		
		/* we load the form fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBankAccount.fxml"));
		
		/*Create a instance of the controller of bank account form*/
		Controller_formBankAccount cba = new Controller_formBankAccount(this, null);
		
		/*Sets the controller associated with the root object*/
		loader.setController(cba);
		
		paneform.setVisible(true);
		paneform.setMouseTransparent(false);
		
		try {
			AnchorPane pane = loader.load();
			// todo faire en sorte que le pane prenne les dimmensions du parent (paneform)
			paneform.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*Methode who create a AccountDisplayer and createItem to the frame*/
	public void createItem(Object bal) {
		
		unloadform();
		if (bal != null) {
			AccountDisplayer accountDisplayer = new AccountDisplayer((BankAccountLogic) bal);
			addToFrame(accountDisplayer);
			displayerList.put(((BankAccountLogic) bal).getId(), accountDisplayer);
		}
	}
	
	/**
	 * Delete the displayer and the data in the DB
	 *
	 * @param toDelete
	 */
	@Override public void deleteItem(Object toDelete) {
		
		unloadform();
		if (toDelete != null) {
			BankAccountLogic bal = (BankAccountLogic) toDelete;
			System.out.println("Suppression du compte id : " + bal.getId());
			frame_bankAccount.getChildren().removeAll(displayerList.get(bal.getId()));
			bal.supp();
		}
		
	}
	
	/**
	 * Unload the form bank account
	 */
	private void unloadform() {
		
		paneform.getChildren().clear();
		paneform.setMouseTransparent(true);
		paneform.setVisible(false);
	}
	
	/**
	 * update the datas in the DB and refresh
	 *
	 * @param updated
	 */
	@Override public void modifyItem(Object updated) {
		
		unloadform();
		BankAccountLogic bal = (BankAccountLogic) updated;
		displayerList.get(bal.getId()).redraw();
	}
	
	/**
	 * Add an AccountDisplayer to the frame
	 *
	 * @param accountDisplayer node to display
	 */
	private void addToFrame(AccountDisplayer accountDisplayer) {
		
		frame_bankAccount.getChildren().add(accountDisplayer);
		FlowPane.setMargin(accountDisplayer, new Insets(5, 5, 5, 5));
	}
	
	
	/**
	 * Event on the create bankAccountDisplayer that will load the detail bank account page
	 *
	 * @param bal the bank account to show the detail
	 */
	private void detailBankAccount(BankAccountLogic bal) {
		
		/* we load the detailBankAccount fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/detailBankAccount.fxml"));
		
		/*Create a instance of the controller detailBankAccount*/
		Controller_detailBankAccount cdba = new Controller_detailBankAccount(this, bal);
		
		/*Sets the controller associated with the root object*/
		loader.setController(cdba);
		paneform.setVisible(true);
		paneform.setMouseTransparent(false);
		
		try {
			AnchorPane pane = loader.load();
			paneform.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or null if the root object was not localized.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		displayerList = new HashMap<>();
		
		//Remove all children from FlowPane container (frame_bankAccount)
		frame_bankAccount.getChildren().clear();
		paneform.setVisible(false);
		paneform.setMouseTransparent(true);
		
		//Go through the list of bank accounts and createItem it to our frame
		for (BankAccountLogic bankAccount : ClientLogic.getInstance().getBankAccounts()) {
			AccountDisplayer accountDisplayer = new AccountDisplayer(bankAccount);
			displayerList.put(bankAccount.getId(), accountDisplayer);
			addToFrame(accountDisplayer);
		}
		
		/*Add event at our button*/
		create_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				callform();
			}
		});
	}
}
