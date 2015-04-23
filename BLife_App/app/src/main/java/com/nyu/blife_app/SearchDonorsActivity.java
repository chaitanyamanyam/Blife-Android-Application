package com.nyu.blife_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;


public class SearchDonorsActivity extends ActionBarActivity{

    Spinner spinnerBloodGroup, spinnerCity;
    EditText zipCode;
    Button btnSearchDonors;
    String bgroupvalidate_input, cityvalidate_input;
    Boolean Bgroup_bool, City_bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donors);

        spinnerBloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        spinnerBloodGroup.setFocusable(true);
        spinnerBloodGroup.setFocusableInTouchMode(true);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        zipCode = (EditText) findViewById(R.id.editTextZipCode);
        btnSearchDonors = (Button) findViewById(R.id.buttonSearchDonors);



        spinnerBloodGroup.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bgroupvalidate_input = parent.getItemAtPosition(position).toString();
                Bgroup_bool = validate(bgroupvalidate_input);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinnerCity.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cityvalidate_input = parent.getItemAtPosition(position).toString();
                        City_bool = validate(cityvalidate_input);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });

        btnSearchDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String get_zip = zipCode.getText().toString();

                if(!get_zip.equals(""))
                {
                    if(Bgroup_bool && City_bool) {
                        Toast.makeText(getBaseContext(), "Blood group - " + bgroupvalidate_input, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "City - " + cityvalidate_input, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "Zip - " + get_zip, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SearchDonorsActivity.this, DonorsListScreenActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Select valid entry.",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(Bgroup_bool && City_bool) {
                        Toast.makeText(getBaseContext(), "Blood group - " + bgroupvalidate_input, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "City - " + cityvalidate_input, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), "Zip is empty - " + get_zip, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(SearchDonorsActivity.this, DonorsListScreenActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Select valid entry.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_donors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public Boolean validate(String get_inputDetail)
    {

        if(get_inputDetail.equals("BLOOD GROUP"))
        {
            spinnerBloodGroup.requestFocus();
            //Toast.makeText(getBaseContext(), "Select valid entry.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(get_inputDetail.equals("CITY"))
        {
            spinnerBloodGroup.requestFocus();
            //Toast.makeText(getBaseContext(),"Select valid entry.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            Toast.makeText(getBaseContext(), "Selected entry - " + get_inputDetail,Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
