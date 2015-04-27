package com.nyu.blife_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SelectedHospitalDetails extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hospital_details);
        Intent get_details=getIntent();
        String hospital_name=get_details.getStringExtra("Name");
        String hospital_location_details=get_details.getStringExtra("Coordiantes");
        Toast.makeText(getApplicationContext(),hospital_name,Toast.LENGTH_SHORT).show();
        String re1=".*?";	// Non-greedy match on filler
        String re2="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Float 1
        String re3=".*?";	// Non-greedy match on filler
        String re4="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Float 2
        String float1="";
        String float2="";
        Pattern p = Pattern.compile(re1+re2+re3+re4,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(hospital_location_details);
        if (m.find())
        {
            float1=m.group(1);
            float2=m.group(2);
            Log.v("Code", float1 + " " + float2);
            // System.out.print("("+float1.toString()+")"+"("+float2.toString()+")"+"\n");
        }
        Toast.makeText(getApplicationContext(),String.valueOf(float1)+" "+String.valueOf(float2),
                Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selected_hospital_details, menu);
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
