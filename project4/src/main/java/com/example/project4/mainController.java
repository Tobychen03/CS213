package com.example.project4;

import com.example.project4.bean.MenuItem;
import com.example.project4.util.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * main controller
 * add function to main-view.fxml
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class mainController {

    public Button orderdonuts;
    public Button ordercoffee;
    public Button orderbasket;
    public Button storeorder;

    /**
     * open window to buy donuts
     */
    @FXML
    private void donutsbutton(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("ordering-donuts-view.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Rutgers Coffee");
            anotherStage.setScene(new Scene(anotherRoot, 662, 508));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * open window to buy coffee
     */
    @FXML
    private void coffeebutton(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("ordering-coffee-view.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Rutgers Coffee");
            anotherStage.setScene(new Scene(anotherRoot, 621, 518));
            anotherStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * open window to check order and place order
     */
    @FXML
    private void basketbutton(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("ordering-basket-view.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Rutgers Coffee");
            anotherStage.setScene(new Scene(anotherRoot, 553, 400));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * open window to check all orders
     */
    @FXML
    private void storebutton(){
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("store-orders-view.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("Rutgers Coffee");
            anotherStage.setScene(new Scene(anotherRoot, 600, 467));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}