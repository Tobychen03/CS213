package com.example.project4;

import com.example.project4.bean.MenuItem;
import com.example.project4.bean.Order;
import com.example.project4.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

/**
 * orders controller
 * add function to ordering-orders-view.fxml
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class ordersController {

    public ListView order_info;
    public ComboBox oredernumber;
    public TextField total_price;

    /**
     * initialize the window
     */
    @FXML
    public void initialize() {
        oredernumber.getItems().clear();
        oredernumber.setValue("");
        order_info.getItems().clear();
        total_price.setText("");
        if (StageManager.storeOrders.getCnt() > 0) {
            oredernumber.setValue(1);
            int cnt = StageManager.storeOrders.getCnt();
            for (int i = 1; i <= cnt; i++) {
                oredernumber.getItems().add(i);
            }
            show(1);
        } else {
            oredernumber.setValue("");
        }
    }

    /**
     * explore all information of orders
     * @param actionEvent
     */
    public void exportOrders(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Export all orders");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to export all orders?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            FileWriter fileWriter = new FileWriter( "./orders.txt");
            ArrayList<Order> orders = StageManager.storeOrders.getOrders();
            for (Order order : orders) {
                fileWriter.append(order.toString() + "\n");
            }
            fileWriter.close();
        }
    }

    /**
     * click bottom and delete order
     * @param actionEvent
     */
    public void cancelOrder(ActionEvent actionEvent) {
        if (StageManager.storeOrders.getCnt() <= 0) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete the order");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to delete the order?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int i = (int) oredernumber.getValue();
            StageManager.storeOrders.remove(i - 1);
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        }
    }


    /**
     * select order
     * @param actionEvent
     */
    public void selectOrder(ActionEvent actionEvent) {
        int cnt = StageManager.storeOrders.getCnt();
        String str = String.valueOf(oredernumber.getValue());
        if (cnt > 0 && oredernumber.getValue() != null &&  str.length() > 0) {
            int i = Integer.valueOf(String.valueOf(oredernumber.getValue()));
            oredernumber.setValue(i);
            show(i);
        } else if(cnt > 0) {
            oredernumber.setValue(1);
        }
    }

    /**
     * show information of order
     * @param i
     */
    public void show(int i) {
        order_info.getItems().clear();

        Order order = StageManager.storeOrders.getOrders().get(i - 1);
        for (MenuItem menuItem : order.getLists()) {
            order_info.getItems().add(menuItem.toString());
        }

        double sub = order.getTotalPrice();
        double tax = sub * 0.06625;
        double price = sub + tax;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        total_price.setText(decimalFormat.format(price));
    }
}
