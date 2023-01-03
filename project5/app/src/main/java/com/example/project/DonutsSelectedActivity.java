package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * For demonstration purpose, this class is the Activity to be started when an donut on the
 * RecyclerView was clicked
 * Get the name of the donut from an intent extra. The text of the button is set to the donut name.
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class DonutsSelectedActivity extends AppCompatActivity {
    private Button btn_donutsName;

    /**
     * set the window of donuts
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        btn_donutsName = findViewById(R.id.btn1);
        Intent intent = getIntent();
        btn_donutsName.setText(intent.getStringExtra("ITEM"));
    }
}