package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;


public class ManageRequestsScreen extends ActionBarActivity {

    private RecyclerView cardMRView;
    private cardMRAdapter cardreqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_requests_screen);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        cardMRView = (RecyclerView)findViewById(R.id.card_view);
        fab.attachToRecyclerView(cardMRView);
        cardMRView.setHasFixedSize(true);
        LinearLayoutManager dListlayoutManager = new LinearLayoutManager(getApplication());
        cardMRView.setLayoutManager(dListlayoutManager);

        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
                new FetchDonorListData("Phone",R.mipmap.phone1),
                new FetchDonorListData("Phone",R.mipmap.plus),
                new FetchDonorListData("Phone",R.mipmap.right)};

        cardreqAdapter = new cardMRAdapter(this, itemsData);
        cardMRView.setAdapter(cardreqAdapter);
        cardMRView.setItemAnimator(new DefaultItemAnimator());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fab_intent = new Intent(ManageRequestsScreen.this, SendBloodRequestActivity.class);
                startActivity(fab_intent);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_requests_screen, menu);
        return true;
    }
    public void onBackPressed() {
        Intent back_Intent = new Intent(ManageRequestsScreen.this, HomeActivity.class);
        startActivity(back_Intent);
        finish();
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
