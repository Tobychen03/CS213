package com.example.project4;

import com.example.project4.bean.MenuItem;
import com.example.project4.bean.Order;
import com.example.project4.util.StageManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Optional;

/**
 * basket controller
 * add function to ordering-basket-view.fxml
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */

public class basketController {
    public ListView order_menuitems;
    public TextField sub_total;
    public TextField sales_tax;
    public TextField total;


    /**
     * initialize the basket view
     */
    @FXML
    public void initialize() {
        order_menuitems.getItems().clear();
        for (MenuItem menuItem : StageManager.order.getLists()) {
            order_menuitems.getItems().add(menuItem.toString());
        }

        double sub = StageManager.order.getTotalPrice();
        double tax = sub * 0.06625;
        double total_price = sub + tax;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        sub_total.setText(decimalFormat.format(sub));
        sales_tax.setText(decimalFormat.format(tax));
        total.setText(decimalFormat.format(total_price));
    }

    public void removeItem(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete item");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to delete the item?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Object selectedItem = order_menuitems.getSelectionModel().getSelectedItem();
            String name = (String) selectedItem;
            for (MenuItem menuItem : StageManager.order.getLists()) {
                if (menuItem.toString().equals(name)) {
                    StageManager.order.remove(menuItem);
                    break;
                }
            }
            initialize();
        }
    }

    /**
     * click place order and add order
     *
     * @param actionEvent
     */
    public void addOrder(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Complete Order");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you sure to complete the order?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (StageManager.order.getCnt() > 0) {
                StageManager.order.setOrderNum(StageManager.order_num + 1);
                StageManager.order_num += 1;
                StageManager.storeOrders.add(StageManager.order);
                StageManager.order = new Order();
            }
            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
        }

    }
}
