package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
/**
 * donuts controller
 * open window of donuts and set the list of donuts
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class DonutsActivity extends AppCompatActivity {

    private ArrayList<Donuts> donuts = new ArrayList<>();
    private int [] donuts_Images = {R.drawable.jelly,R.drawable.glazed, R.drawable.chocolate_frosted,
            R.drawable.strawberry_frosted_donuts,R.drawable.sugar,R.drawable.lemon_filled,
            R.drawable.cinnamon_sugar,R.drawable.old_fashion, R.drawable.blueberry,
            R.drawable.glazed_holes, R.drawable.jelly_holes, R.drawable.cinnamon_sugar_holes,};
    /**
     * Get the references of all instances of Views defined in the layout file, set up the list of
     * donuts to be display in the RecyclerView.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        RecyclerView donuts_recyclerview = findViewById(R.id.recyclerView);
        setupMenuItems(); //add the list of items to the ArrayList
        DonutsAdapter adapter = new DonutsAdapter(this, donuts); //create the adapter
        donuts_recyclerview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        donuts_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
    /**
     * Helper method to set up the data (the Model of the MVC).
     */
    private void setupMenuItems() {

        String [] donutsNames = getResources().getStringArray(R.array.donuts_Names);

        for (int i = 0; i < 6; i++) {
            donuts.add(new Donuts(donutsNames[i], donuts_Images[i], "$1.59"));
        }
        for (int i = 6; i < 9; i++) {
            donuts.add(new Donuts(donutsNames[i], donuts_Images[i], "$1.79"));
        }
        for (int i = 9; i < 12; i++) {
            donuts.add(new Donuts(donutsNames[i], donuts_Images[i], "$0.39"));
        }
    }
}
