package com.nyu.blife_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.List;


public class DonorsListScreenActivity extends ActionBarActivity {

    //    private RecyclerView donorsListView;
//    private RecyclerView.Adapter dListAdapter;
//    private RecyclerView.LayoutManager dListLayoutManager;
    Button backbtn;

    // Declare Variables
    ListView listview;
    List<ParseUser> ob;
    ProgressDialog mProgressDialog;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_list_screen);
        Intent i1 = getIntent();
        //final String[] blood =  new String[]{i1.getStringExtra("blood")};
        //String city = i1.getStringExtra("city");
        //String zip = i1.getStringExtra("zip");

        //String[] search = {blood, city, zip};

        backbtn = (Button) findViewById(R.id.donorListBackbtn);
        // listview = (ListView) findViewById(R.id.listview);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(DonorsListScreenActivity.this, SearchDonorsActivity.class);
                startActivity(backIntent);
                finish();               // review it
            }
        });

        Toast.makeText(getBaseContext(), "Reached Successfully", Toast.LENGTH_SHORT).show();


        CustomAdapter adapter = new CustomAdapter(this, new ParseQueryAdapter.QueryFactory<ParseUser>() {
            // Log.i("output blood", blood[0]);
            @Override
            public ParseQuery<ParseUser> create() {
                // String blood1 = blood[0];
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                //Log.i("output blood", blood[0]);
                query.whereEqualTo("userType", "Donor");
                // query.whereEqualTo("bloodGroup", blood1);
                return query;
            }
        });

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

    }
//        adapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Message> () {
//            @Override
//            public void onLoading() {
//                mProgressDialog = new ProgressDialog(DonorsListScreenActivity.this);
//                // Set progressdialog title
//                mProgressDialog.setTitle("loading all donors");
//                // Set progressdialog message
//                mProgressDialog.setMessage("Loading...");
//                mProgressDialog.setIndeterminate(false);
//                // Show progressdialog
//                mProgressDialog.show();
//                // show progress bar
//            }
//
//            @Override
//            public void onLoaded(List<Message> messages, Exception e) {
//                mProgressDialog.dismiss();
//            }


//


//        donorsListView = (RecyclerView) findViewById(R.id.recyclerDonorsList);
//        donorsListView.setHasFixedSize(true);
//        LinearLayoutManager dListlayoutManager = new LinearLayoutManager(getApplication());
////        donorsListView.setLayoutManager(dListlayoutManager);
//        Intent i1 = getIntent();
//        final String blood =  i1.getStringExtra("blood");
//        final String city = i1.getStringExtra("city");
//        final String zip = i1.getStringExtra("zip");
//        new Runnable(){
//            public void run(){
//                new RemoteDataTask().execute();
//            }
//
//        };

    //  new RemoteDataTask().execute();
    // new RemoteDataTask().execute();
    //   ParseQueryAdapter<ParseObject> mainAdapter;
//        CustomAdapter urgentTodosAdapter;

    // Initialize main ParseQueryAdapter
//     mainAdapter = new ParseQueryAdapter<ParseObject>(this, "User");
//      mainAdapter.setTextKey("firstName");
    // Initialize ListView and set initial view to mainAdapter
//      listView = (ListView) findViewById(R.id.text1);
//       listView.setAdapter(mainAdapter);
//     mainAdapter.loadObjects();

// Set the ListActivity's adapter to be the PQA
    //     setListAdapter(mainAdapter);
//        final ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
//        query.whereEqualTo("bloodGroup", blood);
//        query.whereEqualTo("city", city);
//        query.whereEqualTo("zipCode", zip);
//        query.whereEqualTo("userType", "Donor");
//        query.getFirstInBackground(new GetCallback<ParseObject>() {
//            public void done(ParseObject object, com.parse.ParseException e) {
//                if (object == null) {
//                    Log.d("score", "The getFirst request failed.");
//                } else {
//
//                    String fName = object.getString("firstName");
//                    Number phone = object.getNumber("phoneNumber");
//                    Log.d("score", "Retrieved the object.");
//                }
//            }
//
//
//        });


//        // Add donors dynamically with database
//        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
//                new FetchDonorListData("Phone",R.mipmap.phone1),
//                new FetchDonorListData("Phone",R.mipmap.plus),
//                new FetchDonorListData("Phone",R.mipmap.right),
//                new FetchDonorListData("Phone",R.mipmap.blackpoint),
//                new FetchDonorListData("Phone",R.mipmap.globe),
//                new FetchDonorListData("Phone",R.mipmap.welcomepic),
//                new FetchDonorListData("Phone",R.mipmap.search),
//                new FetchDonorListData("Phone",R.mipmap.phone),
//                new FetchDonorListData("Phone",R.mipmap.phone)};
//
//
//        // creation of adapter
//
//        dListAdapter = new RecyclerViewAdapter(this,itemsData);
//
//        donorsListView.setAdapter(dListAdapter);
//        donorsListView.setItemAnimator(new DefaultItemAnimator());


//    // RemoteDataTask AsyncTask
//    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Create a progressdialog
//            mProgressDialog = new ProgressDialog(DonorsListScreenActivity.this);
//            // Set progressdialog title
//            mProgressDialog.setTitle("loading all donors");
//            // Set progressdialog message
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            // Show progressdialog
//            mProgressDialog.show();
//        }
//
//        // RemoteDataTask AsyncTask
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            // Locate the class table named "Country" in Parse.com
//            ParseQuery<ParseUser> query = ParseUser.getQuery();
//
//            try {
//                ob = query.find();
//
//            } catch (com.parse.ParseException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            listview = (ListView) findViewById(R.id.listview);
//            // Pass the results into an ArrayAdapter
//            adapter = new ArrayAdapter<String>(DonorsListScreenActivity.this,
//                    R.layout.listview_item);
//            // Retrieve object "name" from Parse.com database
//            for (ParseObject User : ob) {
//                adapter.add((String) User.get("firstName"));
//            }
//            // Binds the Adapter to the ListView
//            listview.setAdapter(adapter);
//
//            // Close the progressdialog
//            mProgressDialog.dismiss();
//            // Capture button clicks on ListView items
//
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_donors, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent back_Intent = new Intent(DonorsListScreenActivity.this, SearchDonorsActivity.class);
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
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import com.parse.ParseQuery;
//import com.parse.ParseQueryAdapter;
//import com.parse.ParseUser;
//
//import java.util.List;
//
//
//public class DonorsListScreenActivity extends ActionBarActivity {
//
//    //    private RecyclerView donorsListView;
////    private RecyclerView.Adapter dListAdapter;
////    private RecyclerView.LayoutManager dListLayoutManager;
//    Button backbtn;
//
//    // Declare Variables
//    ListView listview;
//    List<ParseUser> ob;
//    ProgressDialog mProgressDialog;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donors_list_screen);
//        Intent i1 = getIntent();
//        //final String[] blood =  new String[]{i1.getStringExtra("blood")};
//        //String city = i1.getStringExtra("city");
//        //String zip = i1.getStringExtra("zip");
//
//
//        backbtn = (Button) findViewById(R.id.donorListBackbtn);
//
//        donorsListView = (RecyclerView) findViewById(R.id.recyclerDonorsList);
//        donorsListView.setHasFixedSize(true);
//        LinearLayoutManager dListlayoutManager = new LinearLayoutManager(getApplication());
//        donorsListView.setLayoutManager(dListlayoutManager);
//
//        // Add donors dynamically with database
//        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
//                new FetchDonorListData("Phone",R.mipmap.phone1),
//                new FetchDonorListData("Phone",R.mipmap.plus),
//                new FetchDonorListData("Phone",R.mipmap.right),
//                new FetchDonorListData("Phone",R.mipmap.blackpoint),
//                new FetchDonorListData("Phone",R.drawable.globe),
//                new FetchDonorListData("Phone",R.mipmap.welcomepic),
//                new FetchDonorListData("Phone",R.mipmap.search),
//                new FetchDonorListData("Phone",R.mipmap.phone),
//                new FetchDonorListData("Phone",R.mipmap.phone)};
//
//
//        // creation of adapter
//
//        dListAdapter = new RecyclerViewAdapter(this,itemsData);
//
//        donorsListView.setAdapter(dListAdapter);
//        donorsListView.setItemAnimator(new DefaultItemAnimator());
//
//
//        backbtn = (Button) findViewById(R.id.donorListBackbtn);
//       // listview = (ListView) findViewById(R.id.listview);
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent backIntent = new Intent(DonorsListScreenActivity.this, SearchDonorsActivity.class);
//                startActivity(backIntent);
//                finish();               // review it
//            }
//        });
//
//        Toast.makeText(getBaseContext(), "Reached Successfully", Toast.LENGTH_SHORT).show();
//
//
//
//        CustomAdapter adapter = new CustomAdapter(this, new ParseQueryAdapter.QueryFactory<ParseUser>() {
//           // Log.i("output blood", blood[0]);
//            @Override
//            public ParseQuery<ParseUser> create() {
//               // String blood1 = blood[0];
//                ParseQuery<ParseUser> query = ParseUser.getQuery();
//                //Log.i("output blood", blood[0]);
//                query.whereEqualTo("userType", "Donor");
//               // query.whereEqualTo("bloodGroup", blood1);
//                return query;
//            }
//        });
//
//        listview = (ListView) findViewById(R.id.listview);
//        listview.setAdapter(adapter);
//
//    }
////        adapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<Message> () {
////            @Override
////            public void onLoading() {
////                mProgressDialog = new ProgressDialog(DonorsListScreenActivity.this);
////                // Set progressdialog title
////                mProgressDialog.setTitle("loading all donors");
////                // Set progressdialog message
////                mProgressDialog.setMessage("Loading...");
////                mProgressDialog.setIndeterminate(false);
////                // Show progressdialog
////                mProgressDialog.show();
////                // show progress bar
////            }
////
////            @Override
////            public void onLoaded(List<Message> messages, Exception e) {
////                mProgressDialog.dismiss();
////            }
//
//
////
//
//
////        donorsListView = (RecyclerView) findViewById(R.id.recyclerDonorsList);
////        donorsListView.setHasFixedSize(true);
////        LinearLayoutManager dListlayoutManager = new LinearLayoutManager(getApplication());
//////        donorsListView.setLayoutManager(dListlayoutManager);
////        Intent i1 = getIntent();
////        final String blood =  i1.getStringExtra("blood");
////        final String city = i1.getStringExtra("city");
////        final String zip = i1.getStringExtra("zip");
////        new Runnable(){
////            public void run(){
////                new RemoteDataTask().execute();
////            }
////
////        };
//
//        //  new RemoteDataTask().execute();
//        // new RemoteDataTask().execute();
//        //   ParseQueryAdapter<ParseObject> mainAdapter;
////        CustomAdapter urgentTodosAdapter;
//
//        // Initialize main ParseQueryAdapter
////     mainAdapter = new ParseQueryAdapter<ParseObject>(this, "User");
////      mainAdapter.setTextKey("firstName");
//        // Initialize ListView and set initial view to mainAdapter
////      listView = (ListView) findViewById(R.id.text1);
////       listView.setAdapter(mainAdapter);
////     mainAdapter.loadObjects();
//
//// Set the ListActivity's adapter to be the PQA
//        //     setListAdapter(mainAdapter);
////        final ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
////        query.whereEqualTo("bloodGroup", blood);
////        query.whereEqualTo("city", city);
////        query.whereEqualTo("zipCode", zip);
////        query.whereEqualTo("userType", "Donor");
////        query.getFirstInBackground(new GetCallback<ParseObject>() {
////            public void done(ParseObject object, com.parse.ParseException e) {
////                if (object == null) {
////                    Log.d("score", "The getFirst request failed.");
////                } else {
////
////                    String fName = object.getString("firstName");
////                    Number phone = object.getNumber("phoneNumber");
////                    Log.d("score", "Retrieved the object.");
////                }
////            }
////
////
////        });
//
//
////        // Add donors dynamically with database
////        FetchDonorListData itemsData[] = { new FetchDonorListData("Phone",R.mipmap.phone),
////                new FetchDonorListData("Phone",R.mipmap.phone1),
////                new FetchDonorListData("Phone",R.mipmap.plus),
////                new FetchDonorListData("Phone",R.mipmap.right),
////                new FetchDonorListData("Phone",R.mipmap.blackpoint),
////                new FetchDonorListData("Phone",R.mipmap.globe),
////                new FetchDonorListData("Phone",R.mipmap.welcomepic),
////                new FetchDonorListData("Phone",R.mipmap.search),
////                new FetchDonorListData("Phone",R.mipmap.phone),
////                new FetchDonorListData("Phone",R.mipmap.phone)};
////
////
////        // creation of adapter
////
////        dListAdapter = new RecyclerViewAdapter(this,itemsData);
////
////        donorsListView.setAdapter(dListAdapter);
////        donorsListView.setItemAnimator(new DefaultItemAnimator());
//
//
////    // RemoteDataTask AsyncTask
////    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
////        @Override
////        protected void onPreExecute() {
////            super.onPreExecute();
////            // Create a progressdialog
////            mProgressDialog = new ProgressDialog(DonorsListScreenActivity.this);
////            // Set progressdialog title
////            mProgressDialog.setTitle("loading all donors");
////            // Set progressdialog message
////            mProgressDialog.setMessage("Loading...");
////            mProgressDialog.setIndeterminate(false);
////            // Show progressdialog
////            mProgressDialog.show();
////        }
////
////        // RemoteDataTask AsyncTask
////        @Override
////        protected Void doInBackground(Void... params) {
////
////            // Locate the class table named "Country" in Parse.com
////            ParseQuery<ParseUser> query = ParseUser.getQuery();
////
////            try {
////                ob = query.find();
////
////            } catch (com.parse.ParseException e) {
////                e.printStackTrace();
////            }
////            return null;
////        }
////
////        @Override
////        protected void onPostExecute(Void result) {
////            listview = (ListView) findViewById(R.id.listview);
////            // Pass the results into an ArrayAdapter
////            adapter = new ArrayAdapter<String>(DonorsListScreenActivity.this,
////                    R.layout.listview_item);
////            // Retrieve object "name" from Parse.com database
////            for (ParseObject User : ob) {
////                adapter.add((String) User.get("firstName"));
////            }
////            // Binds the Adapter to the ListView
////            listview.setAdapter(adapter);
////
////            // Close the progressdialog
////            mProgressDialog.dismiss();
////            // Capture button clicks on ListView items
////
////        }
////    }
//
//
//
//    @Override
//    public void onBackPressed() {
//        Intent back_Intent = new Intent(DonorsListScreenActivity.this, SearchDonorsActivity.class);
//        startActivity(back_Intent);
//        finish();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_donors, menu);
//        return true;
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
//
//}
