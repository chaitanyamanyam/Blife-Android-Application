package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SingleItemView extends ActionBarActivity {
    // Declare Variables
    TextView nameTextView, cityTextView, locationTextView, cellTextView, bloodTextView;
    String name, city, location, cellNumber, blood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();

        // Get the name
        name = i.getStringExtra("name");
        city = i.getStringExtra("city");
        location = i.getStringExtra("location");
        cellNumber = i.getStringExtra("cellNumber");
        blood = i.getStringExtra("blood");
        // Locate the TextView in singleitemview.xml
        nameTextView = (TextView) findViewById(R.id.name);
        cityTextView = (TextView)findViewById(R.id.city);
        locationTextView = (TextView)findViewById(R.id.location);
        cellTextView = (TextView)findViewById(R.id.cellNumber);
        bloodTextView = (TextView) findViewById(R.id.blood);
        // Load the text into the TextView
        nameTextView.setText(name);
        cityTextView.setText(city);
        locationTextView.setText(location);
        cellTextView.setText(cellNumber);
        bloodTextView.setText(blood);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_item_view, menu);
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
}
