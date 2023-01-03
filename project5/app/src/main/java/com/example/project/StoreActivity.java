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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.bean.Coffee;
import com.example.project.bean.MenuItem;
import com.example.project.bean.Order;
import com.example.project.util.StageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * store controller
 * open store window and watch the information of orders
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class StoreActivity extends AppCompatActivity {

    private Spinner ids;
    private TextView numbers;
    private ListView listView;
    private Button button;
    private int current = 0;

    /**
     * set the window of store
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ids = findViewById(R.id.order_id);
        numbers = findViewById(R.id.text_order);
        listView = findViewById(R.id.orders);
        button = findViewById(R.id.delete_order);


        ArrayList<String> options = new ArrayList<String>();

        for (Order order : StageManager.storeOrders.getOrders()) {
            options.add(String.valueOf(order.getOrderNum()));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, options);
        ids.setAdapter(adapter);
        ids.setSelection(options.indexOf(0));

        /**
         * set the listener to number of order
         */
        ids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 current = i;
                 show(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**
         * set the listener to button
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(StoreActivity.this);
                builder.setIcon(null);
                builder.setTitle("Delete Order");
                builder.setMessage("Are you sure to delete the order?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StageManager.storeOrders.remove(current);
                        Toast.makeText(StoreActivity.this, "successfully!!!", Toast.LENGTH_SHORT).show();
                        StoreActivity.this.finish();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(StoreActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    /**
     * show the window
     * @param i
     */
    public void show(int i) {
        List<String> listViewValues = new ArrayList<String>();
        for (MenuItem menuItem : StageManager.storeOrders.getOrders().get(i).getLists()) {
            listViewValues.add(menuItem.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                listViewValues.toArray(new String[listViewValues.size()]));
        listView.setAdapter(adapter);
    }
}