package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * main controller
 * open main window and function of button
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class MainActivity extends AppCompatActivity {

    private Button order_donuts;
    private Button order_coffee;
    private Button your_order;
    private Button store_order;

    /**
     * open main window
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * show donuts window
     * @param view
     */
    public void showDonuts(View view) {
        Intent intent = new Intent(this, DonutsActivity.class);
        startActivity(intent);
    }

    /**
     * show coffee window
     * @param view
     */
    public void showCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * show order window
     * @param view
     */
    public void showOrder(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * show store window
     * @param view
     */
    public void showStore(View view) {
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
    }

}