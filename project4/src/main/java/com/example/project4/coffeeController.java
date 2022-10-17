package com.example.project4;

import com.example.project4.bean.Coffee;
import com.example.project4.bean.MenuItem;
import com.example.project4.bean.Order;
import com.example.project4.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * coffee controller
 * add function to ordering-coffee-view.fxml
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class coffeeController {

    public ComboBox coffeesize;
    public ComboBox coffeenumber;
    public CheckBox coffee0;
    public CheckBox coffee1;
    public CheckBox coffee2;
    public CheckBox coffee3;
    public CheckBox coffee4;
    public TextField coffeePrice;
    static Coffee addCoffee = new Coffee();


    /**
     * set items in comboBox to choose type of donuts and number of coffee
     */
    @FXML
    public void initialize() {
        coffeesize.setValue("Short");
        ObservableList<String> observableList = FXCollections.observableArrayList("Short", "Tall", "Grande","Venti");
        coffeesize.setItems(observableList);
        coffeenumber.setValue(1);
        ObservableList<Integer> observableList2 = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        coffeenumber.setItems(observableList2);
        coffeePrice.setText("1.69");
    }

    /**
     * click bottom and add coffee
     */
    public void add_coffee(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Complete Order");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to complete the order?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (coffee0.isSelected()) addCoffee.add(0);
            if (coffee1.isSelected()) addCoffee.add(1);
            if (coffee2.isSelected()) addCoffee.add(2);
            if (coffee3.isSelected()) addCoffee.add(3);
            if (coffee4.isSelected()) addCoffee.add(4);

            int size = 0;
            String size_str = (String) coffeesize.getValue();
            if (size_str == "Short") size = 0;
            if (size_str == "Tall") size = 1;
            if (size_str == "Grande") size = 2;
            if (size_str == "Venti") size = 3;
            addCoffee.setSize(size);

            addCoffee.setCnt((Integer) coffeenumber.getValue());

            StageManager.order.add(addCoffee);

            addCoffee = new Coffee();
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        }
    }

    /**
     * click check box or combo box to change the price
     */
    @FXML
    public void add_in(){
        Coffee varible = new Coffee();
        if (coffee0.isSelected()) varible.add(0);
        if (coffee1.isSelected()) varible.add(1);
        if (coffee2.isSelected()) varible.add(2);
        if (coffee3.isSelected()) varible.add(3);
        if (coffee4.isSelected()) varible.add(4);

        int size = 0;
        String size_str = (String) coffeesize.getValue();
        if (size_str == "Short") size = 0;
        if (size_str == "Tall") size = 1;
        if (size_str == "Grande") size = 2;
        if (size_str == "Venti") size = 3;
        varible.setSize(size);

        varible.setCnt((Integer) coffeenumber.getValue());
        double itemprice = varible.itemPrice();
        String output = String.format("%.2f", itemprice);
        coffeePrice.setText(output);
    }
}
