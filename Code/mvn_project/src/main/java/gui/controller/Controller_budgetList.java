package gui.controller;

import bll.logic.BudgetLogic;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_budgetList implements IController, Initializable {
	
	@FXML private VBox paneList;
	@FXML private JFXButton btnAdd;
	@FXML private AnchorPane paneForm;
	@FXML private ScrollPane scrollPane;
	
	HashMap<Integer, budgetDisplayer> displayerList;
	
	private class budgetDisplayer extends AnchorPane implements Initializable {
		@FXML private AnchorPane budgetPane;
		@FXML private Label lbltitre;
		@FXML private Label lblcurrentExpense;
		@FXML private Label lblmaxExpense;
		@FXML private JFXProgressBar expenseProgress;
		private BudgetLogic budget;
		
		budgetDisplayer(BudgetLogic budget){
			JFXDepthManager.setDepth(this, 1);
			this.budget = budget;
		}
		
		private void openDetail() {

			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDetail.fxml"));
			loader.setController(new Controller_detailBudget(Controller_budgetList.this, budget));
			
			paneForm.getChildren().clear();
			try {
				paneForm.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override public void initialize(URL location, ResourceBundle resources) {
			redraw();

			budgetPane.setMinHeight(130);
			JFXDepthManager.setDepth(budgetPane, 1);
			budgetPane.setOnMouseClicked(event -> openDetail());
			
			
		}

		public void redraw() {
			lbltitre.setText(budget.getName());
			lbltitre.setStyle("-fx-font-size: 24");
			lblcurrentExpense.setText("Dépenses actuelles : 999.99 CHF"); // TODO calculer les dépenses totales du budget
			lblmaxExpense.setText("Plafond : "+ Double.toString(budget.getAmount()) +"CHF");
			expenseProgress.setProgress(0.5); // TODO Dépensé/plafond
		}
	}

	/**
	 * load the form to create a new Budget
	 */
	 public void callform(BudgetLogic budget) {
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget.fxml"));
		loader.setController(new Controller_formBudget(this, budget));
		
		paneForm.getChildren().clear();
		try {
			paneForm.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the displayer and the data in the DB
	 * @param toDelete
	 */
	@Override
	public void deleteItem(Object toDelete) {
		BudgetLogic b = (BudgetLogic) toDelete;
		paneList.getChildren().remove(displayerList.get(b.getId()));
		b.supp();

	}

	/**
	 * update the datas in the DB and refresh
	 * @param updated
	 */
	@Override public void modifyItem(Object updated) {
		BudgetLogic b = (BudgetLogic)updated;
		displayerList.get(b.getId()).redraw();
	}
	
	@Override public void createItem(Object result) {
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);

		if(result != null) {
			BudgetLogic b = (BudgetLogic) result;

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDisplayer.fxml"));
			loader.setController(new budgetDisplayer(b));
			try {
				paneList.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		btnAdd.setOnAction(event -> callform(null));
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);

		// event on the click of the button
		scrollPane.setStyle("-fx-background-color:transparent;");

		// we set the basics data
		for(BudgetLogic b: ClientLogic.getInstance().getBudgets()){
			// creating budgetDisplayer for existing budgets
			budgetDisplayer bd = new budgetDisplayer(b);
			displayerList.put(b.getId(), bd);
			paneList.getChildren().add(bd);
		}
	}
}
