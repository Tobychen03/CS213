package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.bean.Coffee;
import com.example.project.util.StageManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * coffee controller
 * open window of coffee
 * @author Tianle Chen tc822, Zesheng Zhang zz354
 */
public class CoffeeActivity extends AppCompatActivity {

    private Button addToOrder;
    private CheckBox cream;
    private CheckBox caramel;
    private CheckBox milk;
    private CheckBox syrup;
    private CheckBox whipped;
    private Spinner spinner;
    private EditText number;
    private TextView price;

    private static Coffee coffee = new Coffee();

    /**
     * open coffee window
     * set default value
     * set controller
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);


        addToOrder = findViewById(R.id.add_to_order);
        spinner = findViewById(R.id.coffee_size);
        cream = findViewById(R.id.checkBox);
        caramel = findViewById(R.id.checkBox2);
        milk = findViewById(R.id.checkBox3);
        syrup = findViewById(R.id.checkBox4);
        whipped = findViewById(R.id.checkBox5);
        number = findViewById(R.id.coffee_number);
        price = findViewById(R.id.price);

        coffee.setSize(0);
        coffee.setCnt(1);
        number.setText("1");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        price.setText(decimalFormat.format(coffee.itemPrice()));

        String[] items = new String[]{
                "Short", "Tall", "Grande", "Venti"
        };
        ArrayList<String> options = new ArrayList<String>();
        for (String str : items) {
            options.add(str);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, options);
        spinner.setAdapter(adapter);
        spinner.setSelection(options.indexOf(0));

        /**
         * set price
         * according to the size to change price
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                coffee.setSize(i);
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /**
         * if cream is check, the price change
         */
        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.add(0);
                } else {
                    coffee.remove(0);
                }
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }
        });
        /**
         * if caramel is check, the price change
         */
        caramel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.add(1);
                } else {
                    coffee.remove(1);
                }
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }
        });
        /**
         * if milk is check, the price change
         */
        milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.add(2);
                } else {
                    coffee.remove(2);
                }
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }
        });
        /**
         * if syrup is check, the price change
         */
        syrup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.add(3);
                } else {
                    coffee.remove(3);
                }
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }
        });
        /**
         * if whipped is check, the price change
         */
        whipped.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    coffee.add(4);
                } else {
                    coffee.remove(4);
                }
                price.setText(decimalFormat.format(coffee.itemPrice()));
            }
        });

        /**
         * change the price according to the number of coffee
         */
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String cnt = number.getText().toString();
                    coffee.setCnt(Integer.valueOf(cnt));
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                } catch (Exception e) {
                    coffee.setCnt(0);
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String cnt = number.getText().toString();
                    coffee.setCnt(Integer.valueOf(cnt));
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                } catch (Exception e) {
                    coffee.setCnt(0);
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String cnt = number.getText().toString();
                    coffee.setCnt(Integer.valueOf(cnt));
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                } catch (Exception e) {
                    coffee.setCnt(0);
                    price.setText(decimalFormat.format(coffee.itemPrice()));
                }
            }
        });
        /**
         * click add button
         */
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CoffeeActivity.this);
                builder.setIcon(null);
                builder.setTitle("Complete Order");
                builder.setMessage("Are you sure to complete the order?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        StageManager.order.add(coffee);
                        coffee = new Coffee();
                        Toast.makeText(CoffeeActivity.this, "successfully!!!", Toast.LENGTH_SHORT).show();
                        CoffeeActivity.this.finish();
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CoffeeActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

    }
}