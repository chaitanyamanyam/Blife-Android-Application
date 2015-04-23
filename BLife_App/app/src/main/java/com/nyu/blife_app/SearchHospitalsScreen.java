package com.nyu.blife_app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.location.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchHospitalsScreen extends Activity {
    private TextView mSearchResultsText;
    private ListView shs_lv;
    private LocationManager locationManager;
    private String provider;
    private Context Context;
    private float lat,lng;
    class Business {
        final String name;
        final String url;
        //List<String> data_location=new ArrayList<String>();
        final String data_location;
        public Business(String name, String url,String data_location) {
            this.name = name;
            this.url = url;
            this.data_location=data_location;
        }

        @Override
        public String toString() {
            return name;
        }


        public String getLocation(){
            return data_location;
        }
        public String changetof(){
            String re1=".*?";	// Non-greedy match on filler
            String re2="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Float 1
            String re3=".*?";	// Non-greedy match on filler
            String re4="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Float 2
            String float1="";
            String float2="";
            Pattern p = Pattern.compile(re1+re2+re3+re4,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(data_location);
            if (m.find())
            {
               float1=m.group(1);
               float2=m.group(2);
                Log.v("Code",float1+" "+float2);
                // System.out.print("("+float1.toString()+")"+"("+float2.toString()+")"+"\n");
            }
            return float1;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_search_hospitals_screen);
//        mSearchResultsText=(TextView)findViewById(R.id.search_hospitals_textview);
        shs_lv=(ListView)findViewById(R.id.search_hospitals_lview);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        }
        else
        {
            Log.v("Location Details","Not able tp get");
        }


        AsyncTask<Void, Void, List<Business>> hospitals = new AsyncTask<Void, Void, List<Business>>() {
            @Override
            protected List<Business> doInBackground(Void... params) {
                Yelp get_hospitals = Yelp.getYelp(SearchHospitalsScreen.this);
                //String hospitals = get_hospitals.search("hospitals", 34.0204989,-118.4117325);
                String hospitals = get_hospitals.search("hospitals", lat, lng);
                try {
                    return processJson(hospitals);
                } catch (JSONException e) {
                    Log.v("Full Errors", "Over here");
                    return Collections.<Business>emptyList();
                    //return hospitals;
                }


            }

            @Override
            protected void onPostExecute(List<Business> result) {
//                mSearchResultsText.setText(result);


                ArrayAdapter<Business> new_adapter = new ArrayAdapter<Business>(SearchHospitalsScreen.this,
                        android.R.layout.simple_list_item_1,
                        result);
                shs_lv.setAdapter(new_adapter);

                //setProgressBarIndeterminateVisibility(false);
            }
        }.execute();
    }


   //@Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        Business biz = (Business)listView.getItemAtPosition(position);
        Log.v("List View",biz.toString());
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(biz.url)));
    };

        List<Business> processJson (String jsonStuff)throws JSONException {
            JSONObject json = new JSONObject(jsonStuff);
            JSONArray hospitals_json = json.getJSONArray("businesses");
            ArrayList<Business> hospitalNames = new ArrayList<Business>(hospitals_json.length());
            List<String> json_location=new ArrayList<String>();
            for (int i = 0; i < hospitals_json.length(); i++) {

                JSONObject hospital_returned = hospitals_json.getJSONObject(i);
                hospitalNames.add(new Business(hospital_returned.optString("name"),
                        hospital_returned.optString("mobile_url"),hospital_returned.optString("location")));
                Log.v("Inside Json232",hospital_returned.optString("location"));


            }
            Integer image_id=R.drawable.icon_caller;
            return hospitalNames;

            //return TextUtils.join("\n", hospitalNames);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_hospitals_screen, menu);
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
    public void onLocationChanged(Location location) {
        lat = (float) (location.getLatitude());
        lng = (float) (location.getLongitude());
        Log.v("My Location",String.valueOf(lat)+" "+String.valueOf(lng));
    }
}
