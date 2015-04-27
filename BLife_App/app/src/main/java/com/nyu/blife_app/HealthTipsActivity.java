package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HealthTipsActivity extends ActionBarActivity {
    ExpandableListView_FAQ listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);
        
        expListView=(ExpandableListView) findViewById(R.id.expandableListView);
        prepareListData();
        listAdapter=new ExpandableListView_FAQ(this,listDataHeader,listDataChild);
        expListView.setAdapter(listAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faqs_screen, menu);
        return true;
    }

    private void prepareListData(){

        listDataHeader=new ArrayList<String>();
        listDataChild=new HashMap<String,List<String>>();
        listDataHeader.add("Before Your Donation");
        List<String> wib=new ArrayList<String>();
        wib.add("B-life is an app connecting donors and receivers using social network elements");

        listDataHeader.add("How many gallons of blood I can donate?? ");
        List<String> wib1=new ArrayList<String>();
        wib1.add("46.5 gallons amount of blood you could donate if you begin at age 17 " +
                "and donate every 56 days until you reach 79 years old");

        listDataHeader.add("How much time does it take to donate blood once??");
        List<String> wib2=new ArrayList<String>();
        wib2.add("The actual donation of" +
                " a pint of whole blood unit takes eight to 10 minutes. However, the time varies " +
                "slightly with each person depending on several factors " +
                ". It’s about an hour of your " +
                "time.");

        listDataHeader.add("Types of red blood cells??");
        List<String> wib3=new ArrayList<String>();
        wib3.add("Four main red blood cell types: A, B, AB and O. Each can be positive or negative" +
                " for the Rh factor. AB is the universal recipient, O negative is the universal " +
                "donor of red blood cells.");

        listDataHeader.add("How often can I donate blood?");
        List<String> wib4=new ArrayList<String>();
        wib4.add("You must wait at least eight weeks (56 days) between donations of whole blood " +
                "and 16 weeks (112 days) between double red cell donations.");



        listDataHeader.add("Can I get HIV by donating blood?");
        List<String> wib5=new ArrayList<String>();
        wib5.add("No. Sterile procedures and disposable equipment are used during the process");

        listDataHeader.add("How often can I donate platelets?");
        List<String> wib6=new ArrayList<String>();
        wib6.add("Every 7 days up to 24 apheresis donations can be made in a year. " +
                "Some apheresis donations can generate two or three adult-sized platelet " +
                "transfusion doses from one donation.");

        listDataHeader.add("Name");
        List<String> wib7=new ArrayList<String>();
        wib7.add("Chaitanya");
        wib7.add("Manyam");

        listDataChild.put(listDataHeader.get(0),wib);
        listDataChild.put(listDataHeader.get(1),wib1);
        listDataChild.put(listDataHeader.get(2),wib2);
        listDataChild.put(listDataHeader.get(3),wib3);
        listDataChild.put(listDataHeader.get(4),wib4);
        listDataChild.put(listDataHeader.get(5),wib5);
        listDataChild.put(listDataHeader.get(6),wib6);
        listDataChild.put(listDataHeader.get(7),wib7);
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
