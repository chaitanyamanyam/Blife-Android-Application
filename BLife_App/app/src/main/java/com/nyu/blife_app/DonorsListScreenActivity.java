package com.nyu.blife_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DonorsListScreenActivity extends ActionBarActivity {

    private RecyclerView donorsListView;
    private RecyclerView.Adapter dListAdapter;
    private RecyclerView.LayoutManager dListLayoutManager;
    Button backbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_list_screen);

        backbtn = (Button) findViewById(R.id.donorListBackbtn);

        donorsListView = (RecyclerView) findViewById(R.id.recyclerDonorsList);
        donorsListView.setHasFixedSize(true);
        LinearLayoutManager dListlayoutManager = new LinearLayoutManager(getApplication());
        donorsListView.setLayoutManager(dListlayoutManager);

        // Add donors dynamically
        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
                new FetchDonorListData("Phone",R.mipmap.phone1),
                new FetchDonorListData("Phone",R.mipmap.plus),
                new FetchDonorListData("Phone",R.mipmap.right),
                new FetchDonorListData("Phone",R.mipmap.blackpoint),
                new FetchDonorListData("Phone",R.mipmap.globe),
                new FetchDonorListData("Phone",R.mipmap.welcomepic),
                new FetchDonorListData("Phone",R.mipmap.search),
                new FetchDonorListData("Phone",R.mipmap.phone),
                new FetchDonorListData("Phone",R.mipmap.phone)};


        // creation of adapter

        dListAdapter = new RecyclerViewAdapter(this,itemsData);

        donorsListView.setAdapter(dListAdapter);
        donorsListView.setItemAnimator(new DefaultItemAnimator());


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(DonorsListScreenActivity.this, SearchDonorsActivity.class);
                startActivity(backIntent);
                finish();               // review it
            }
        });

        Toast.makeText(getBaseContext(), "Reached Successfully", Toast.LENGTH_SHORT).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donors_list_screen, menu);
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
