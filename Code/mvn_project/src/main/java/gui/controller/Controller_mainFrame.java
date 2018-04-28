package gui.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Implements the behavior of the main frame. Basically, it simply changes the view when we select
 */
public class Controller_mainFrame implements Initializable {
    @FXML private Label header_mainFrame;
    @FXML private AnchorPane mainContent;

    @FXML private JFXHamburger burgerBtn;

    @FXML private JFXDrawer drawer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer.setMouseTransparent(true);
        VBox box = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/lateralMenu.fxml"));
            gui.controller.Controller_lateralMenu controller_lateralMenu = new Controller_lateralMenu();
            loader.setController(controller_lateralMenu);
            box = loader.load();
            drawer.setSidePane(box);

            loadFrame(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(burgerBtn);
            transition.setRate(-1);
            drawer.setOnDrawerClosing(event -> {drawer.setMouseTransparent(true);});
            drawer.setOnDrawerOpening(event -> drawer.setMouseTransparent(false));
            burgerBtn.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
                transition.setRate(transition.getRate()*-1);
                transition.play();

                if(drawer.isShown()){
                    drawer.close();

                } else {
                    drawer.open();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFrame(VBox box){
        for(Node node : box.getChildren()){
            node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
                try {
                    switch (node.getAccessibleText()){
                    case "Dashboard" :
                        /*TODO*/
                        header_mainFrame.setText("Dashboard");
                        load("/gui/view/bankAccount.fxml");
                        break;
                    case "Budget" :
                        /*TODO*/
                        header_mainFrame.setText("Budget");
                        load("/gui/view/bankAccount.fxml");
                        break;
                    case "Transaction" :
                        header_mainFrame.setText("Transaction");
                        load("/gui/view/transactionList.fxml");
                        break;
                    case "Dept" :
                        /*TODO*/
                        header_mainFrame.setText("Dettes");
                        load("/gui/view/bankAccount.fxml");
                        break;
                    case "BankAccount" :
                        header_mainFrame.setText("Compte bancaire");
                        load("/gui/view/bankAccount.fxml");
                        break;
                    case "CategoryModel" :
                        header_mainFrame.setText("Catégorie");
                        load("/gui/view/categoryList.fxml");
                        break;
                }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    private void load(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Controller_bankAccount controller_bankAccount = new Controller_bankAccount();
        loader.setController(controller_bankAccount);
        AnchorPane pane = loader.load();
        pane.prefWidthProperty().bind(mainContent.widthProperty());
        pane.prefHeightProperty().bind(mainContent.heightProperty());
        mainContent.getChildren().add(pane);
    }


}
