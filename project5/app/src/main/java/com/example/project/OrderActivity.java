package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.bean.Coffee;
import com.example.project.bean.MenuItem;
import com.example.project.bean.Order;
import com.example.project.util.StageManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * order controller
 * open order window and set information of order
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class OrderActivity extends AppCompatActivity {
    private TextView sub_total;
    private TextView sales_tax;
    private TextView total;
    private ListView order_items;
    private Button delete_items;
    private Button place_order;
    private int idx_select = -1;
    private  ArrayAdapter<String> adapter;
    private double tax_rate = 0.06625;

    /**
     * set the default value of window
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        sub_total = findViewById(R.id.sub_total);
        sales_tax = findViewById(R.id.sales_tax);
        total = findViewById(R.id.total_price);
        order_items = findViewById(R.id.order_items);
        delete_items = findViewById(R.id.delete_item);
        place_order = findViewById(R.id.button2);

        show();

        /**
         * set listener to item
         */
        order_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                order_items.setSelection(position);
                idx_select = position;
            }
        });

        /**
         * set delete button
         */
        delete_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setIcon(null);
                builder.setTitle("Delete item");
                builder.setMessage("Are you sure to delete the item?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (idx_select == -1) {
                            Toast.makeText(OrderActivity.this, "no item selected!", Toast.LENGTH_SHORT).show();
                        } else {
                            StageManager.order.remove(StageManager.order.getLists().get(idx_select));
                            List<String> listViewValues = new ArrayList<String>();
                            for (MenuItem menuItem : StageManager.order.getLists()) {
                                listViewValues.add(menuItem.toString());
                            }
                            show();

                            Toast.makeText(OrderActivity.this, "delete successfully!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(OrderActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        /**
         * set the place order button
         */
        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setIcon(null);
                builder.setTitle("Place Order");
                builder.setMessage("Are you sure to place the order?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (StageManager.order.getCnt() != 0) {
                            StageManager.order.setOrderNum(StageManager.order_num);
                            StageManager.order_num++;
                            StageManager.storeOrders.add(StageManager.order);
                            StageManager.order = new Order();
                            Toast.makeText(OrderActivity.this, "place successfully!!!", Toast.LENGTH_SHORT).show();
                            OrderActivity.this.finish();
                        } else {
                            Toast.makeText(OrderActivity.this, "order is empty!", Toast.LENGTH_SHORT).show();
                            OrderActivity.this.finish();
                        }
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(OrderActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    /**
     * set the price information of order
     */
    public void show() {
        double sub = StageManager.order.getTotalPrice();
        double tax = sub * tax_rate;
        double total_price = sub + tax;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        sub_total.setText(decimalFormat.format(sub));
        sales_tax.setText(decimalFormat.format(tax));
        total.setText(decimalFormat.format(total_price));
        List<String> listViewValues = new ArrayList<String>();
        for (MenuItem menuItem : StageManager.order.getLists()) {
            listViewValues.add(menuItem.toString());
        }
         adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                listViewValues.toArray(new String[listViewValues.size()]));
        order_items.setAdapter(adapter);

    }
}