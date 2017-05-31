package com.example.android.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.button;

public class Main2Activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ////////Spinners..........
        ///.................{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{
        Spinner spinner1 = (Spinner) findViewById(R.id.city_spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.college_spinner);
        Spinner spinner3 = (Spinner) findViewById(R.id.service_spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.college_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.service_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+ " is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+ " is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+ " is selected",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//............................................}}}}}}}}}}}}}}}}}}}Spinners code ends here....
    }

    public void button(View view){
        Intent i = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);


    }
}
