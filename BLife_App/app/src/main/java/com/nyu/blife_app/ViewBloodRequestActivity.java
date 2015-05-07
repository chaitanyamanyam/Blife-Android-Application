package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class ViewBloodRequestActivity extends ActionBarActivity {


    Spinner searchCity, searchBG;
    Button searchreq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blood_request);

        searchCity = (Spinner) findViewById(R.id.searchCity);
        searchBG = (Spinner) findViewById(R.id.searchBG);
        searchreq = (Button) findViewById(R.id.btnSearchReq);

        searchreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vbr_searchcity = searchCity.getSelectedItem().toString();
                String vbr_searchbg = searchBG.getSelectedItem().toString();

                if(vbr_searchcity.equals("CITY") && vbr_searchbg.equals("BLOOD GROUP"))
                {
                    Toast.makeText(getBaseContext(),"Select atleast One Field", Toast.LENGTH_SHORT).show();
                }

                else if(!vbr_searchbg.equals("BLOOD GROUP") && vbr_searchcity.equals("CITY"))
                {
                    Toast.makeText(getBaseContext(),"else if 1", Toast.LENGTH_SHORT).show();
                }
                else if(vbr_searchbg.equals("BLOOD GROUP") && !vbr_searchcity.equals("CITY"))
                {
                    Toast.makeText(getBaseContext(),"else if 2", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "hi", Toast.LENGTH_SHORT).show();
                    Intent search_intent = new Intent(ViewBloodRequestActivity.this,SearchResultVBRActvity.class);
                    search_intent.putExtra("city",vbr_searchcity);
                    search_intent.putExtra("blood",vbr_searchbg);
                    startActivity(search_intent);

                }
            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_blood_request, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        Intent back_Intent = new Intent(ViewBloodRequestActivity.this, HomeActivity.class);
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





//package com.nyu.blife_app;
//
//import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
//import android.os.Bundle;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Toast;
//
//import com.melnykov.fab.FloatingActionButton;
//
//
//public class ViewBloodRequestActivity extends ActionBarActivity {
//
//    private RecyclerView card_ViewBR;
//    private card_viewBR_adapter card_viewBRAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_blood_request);
//
//        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
//
//
//        card_ViewBR = (RecyclerView)findViewById(R.id.recyclerViewBR);
//        fab.attachToRecyclerView(card_ViewBR);
//        card_ViewBR.setHasFixedSize(true);
//        LinearLayoutManager viewBRlayoutManager = new LinearLayoutManager(getApplication());
//        card_ViewBR.setLayoutManager(viewBRlayoutManager);
//
//        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
//                new FetchDonorListData("Phone",R.mipmap.phone1),
//                new FetchDonorListData("Phone",R.mipmap.plus),
//                new FetchDonorListData("Phone",R.mipmap.right)};
//
//        card_viewBRAdapter = new card_viewBR_adapter(this, itemsData);
//        card_ViewBR.setAdapter(card_viewBRAdapter);
//        card_ViewBR.setItemAnimator(new DefaultItemAnimator());
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent fab_intent = new Intent(ViewBloodRequestActivity.this, SendBloodRequestActivity.class);
//                startActivity(fab_intent);
//                finish();
//            }
//        });
//
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_view_blood_request, menu);
//        return true;
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        Intent back_Intent = new Intent(ViewBloodRequestActivity.this, HomeActivity.class);
//        startActivity(back_Intent);
//        finish();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}

