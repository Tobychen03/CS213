package com.example.project4;

import com.example.project4.bean.Donut;
import com.example.project4.bean.MenuItem;
import com.example.project4.bean.Order;
import com.example.project4.util.StageManager;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * donuts controller
 * add function to ordering-donuts-view.fxml
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class donutsController {

    public ComboBox choose_dtype;
    public Button add;
    public Button delete;

    public ListView leftview;
    public ListView rightview;
    public TextField sub_value;
    public ComboBox number_to_add;
    public static Double donutscost = 0.0;
    public Double yeast_cost = 1.59;
    public Double cake_cost = 1.79;
    public Double hole_cost = 0.39;

    Donut jelly = new Donut(0,"jelly");
    Donut glazed = new Donut(0,"glazed");
    Donut chocolate_frosted = new Donut(0,"chocolate frosted");
    Donut strawberry_frosted = new Donut(0,"strawberry frosted");
    Donut cinnamon_sugar = new Donut(1,"cinnamon sugar");
    Donut old_fashion = new Donut(1,"old fashion");
    Donut blueberry = new Donut(1,"blueberry");
    Donut glazed_holes = new Donut(2,"glazed holes");
    Donut jelly_holes = new Donut(2,"jelly holes");
    Donut cinnamon_sugar_holes = new Donut(2,"cinnamon sugar holes");

    Order donut_order = new Order();


    /**
     * set items in comboBox to choose type of donuts
     */
    @FXML
    public void initialize() {
        choose_dtype.setValue("yeast donut");
        ObservableList<String> observableList = FXCollections.observableArrayList("yeast donut", "cake donuts", "donut holes");
        choose_dtype.setItems(observableList);

        number_to_add.setValue(1);
        ObservableList<Integer> observableList1 = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
        number_to_add.setItems(observableList1);

        leftview.getItems().add("jelly");
        leftview.getItems().add("glazed");
        leftview.getItems().add("chocolate frosted");
        leftview.getItems().add("strawberry frosted");
    }

    /**
     * set items in leftview
     */
    @FXML
    public void setListView(){
        if(choose_dtype.getValue() == "yeast donut") {
            leftview.getItems().clear();
            if(jelly.getCnt() == 0){
                leftview.getItems().add("jelly");
            }
            if(glazed.getCnt() == 0){
                leftview.getItems().add("glazed");
            }
            if(chocolate_frosted.getCnt() == 0){
                leftview.getItems().add("chocolate frosted");
            }
            if(strawberry_frosted.getCnt() == 0){
                leftview.getItems().add("strawberry frosted");
            }
        }
        if(choose_dtype.getValue() == "cake donuts") {
            leftview.getItems().clear();
            if(cinnamon_sugar.getCnt() == 0){
                leftview.getItems().add("cinnamon sugar");
            }
            if(old_fashion.getCnt() == 0){
                leftview.getItems().add("old fashion");
            }
            if(blueberry.getCnt() == 0){
                leftview.getItems().add("blueberry");
            }
        }
        if(choose_dtype.getValue() == "donut holes") {
            leftview.getItems().clear();
            if(glazed_holes.getCnt() == 0){
                leftview.getItems().add("glazed holes");
            }
            if(jelly_holes.getCnt() == 0){
                leftview.getItems().add("jelly holes");
            }
            if(cinnamon_sugar_holes.getCnt() == 0){
                leftview.getItems().add("cinnamon sugar holes");
            }
        }
    }


    /**
     * click add
     * remove flavor from left to right
     * change sub_total
     */
    @FXML
    public void clickadd(){
        Object selectedItem = leftview.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            String name = (String) selectedItem;
            Object numadd = number_to_add.getValue();
            Integer num = (Integer) numadd;
            if (choose_dtype.getValue() == "yeast donut") {
                if (name == "jelly") {
                    jelly.setCnt(num);
                    donut_order.add(jelly);
                }
                if (name == "glazed") {
                    glazed.setCnt(num);
                    donut_order.add(glazed);
                }
                if (name == "chocolate frosted") {
                    chocolate_frosted.setCnt(num);
                    donut_order.add(chocolate_frosted);
                }
                if (name == "strawberry frosted") {
                    strawberry_frosted.setCnt(num);
                    donut_order.add(strawberry_frosted);
                }
                String flavor = name + "(" + num + ")";
                rightview.getItems().add(flavor);
                leftview.getItems().remove(selectedItem);
                donutscost += yeast_cost * num;
            }
            if (choose_dtype.getValue() == "cake donuts") {
                if (name == "cinnamon sugar") {
                    cinnamon_sugar.setCnt(num);
                    donut_order.add(cinnamon_sugar);
                }
                if (name == "old fashion") {
                    old_fashion.setCnt(num);
                    donut_order.add(old_fashion);
                }
                if (name == "blueberry") {
                    blueberry.setCnt(num);
                    donut_order.add(blueberry);
                }
                String flavor = name + "(" + num + ")";
                rightview.getItems().add(flavor);
                leftview.getItems().remove(selectedItem);
                donutscost += cake_cost * num;
            }
            if (choose_dtype.getValue() == "donut holes") {
                if (name == "glazed holes") {
                    glazed_holes.setCnt(num);
                    donut_order.add(glazed_holes);
                }
                if (name == "jelly holes") {
                    jelly_holes.setCnt(num);
                    donut_order.add(jelly_holes);
                }
                if (name == "cinnamon sugar holes") {
                    cinnamon_sugar_holes.setCnt(num);
                    donut_order.add(cinnamon_sugar_holes);
                }
                String flavor = name + "(" + num + ")";
                rightview.getItems().add(flavor);
                leftview.getItems().remove(selectedItem);
                donutscost += hole_cost * num;
            }

        }
        showsub_total(donutscost);
    }

    /**
     * click delete bottom
     * remove flavor from right to left
     * change sub_total
     */
    @FXML
    public void clickdelete(){
        Object selectedItem = rightview.getSelectionModel().getSelectedItem();
        String name = (String) selectedItem;
        if(selectedItem != null) {
            if (name.contains("cinnamon sugar holes")) {
                donutscost -= cinnamon_sugar_holes.itemPrice();
                donut_order.remove(cinnamon_sugar_holes);
                cinnamon_sugar_holes.setCnt(0);
            } else if (name.contains("jelly holes")) {
                donutscost -= jelly_holes.itemPrice();
                donut_order.remove(jelly_holes);
                jelly_holes.setCnt(0);
            } else if (name.contains("glazed holes")) {
                donutscost -= glazed_holes.itemPrice();
                donut_order.remove(glazed_holes);
                glazed_holes.setCnt(0);
            } else if (name.contains("blueberry")) {
                donutscost -= blueberry.itemPrice();
                donut_order.remove(blueberry);
                blueberry.setCnt(0);
            } else if (name.contains("old fashion")) {
                donutscost -= old_fashion.itemPrice();
                donut_order.remove(old_fashion);
                old_fashion.setCnt(0);
            } else if (name.contains("cinnamon sugar")) {
                donutscost -= cinnamon_sugar.itemPrice();
                donut_order.remove(cinnamon_sugar);
                cinnamon_sugar.setCnt(0);
            } else if (name.contains("strawberry frosted")) {
                donutscost -= strawberry_frosted.itemPrice();
                donut_order.remove(strawberry_frosted);
                strawberry_frosted.setCnt(0);
            } else if (name.contains("chocolate frosted")) {
                donutscost -= chocolate_frosted.itemPrice();
                donut_order.remove(chocolate_frosted);
                chocolate_frosted.setCnt(0);
            } else if (name.contains("glazed")) {
                donutscost -= glazed.itemPrice();
                donut_order.remove(glazed);
                glazed.setCnt(0);
            } else if (name.contains("jelly")) {
                donutscost -= jelly.itemPrice();
                donut_order.remove(jelly);
                jelly.setCnt(0);
            }
            setListView();
            rightview.getItems().remove(selectedItem);

        }
        showsub_total(donutscost);
    }


    /**
     * show sub_total in textarea
     * @param donutscost
     */
    public void showsub_total(double donutscost){
        String output = String.format("%.2f", donutscost);
        sub_value.setText(output);
    }

    /**
     * click and add order
     * @param actionEvent
     */
    public void add_donut(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Complete Order");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to complete the order?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            for (MenuItem menuItem : donut_order.getLists()) {
                StageManager.order.add(menuItem);
            }

            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        }
    }
}
