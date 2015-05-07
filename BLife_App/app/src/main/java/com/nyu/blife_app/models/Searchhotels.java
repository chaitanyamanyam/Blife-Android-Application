package com.nyu.blife_app.models;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nyu.blife_app.GPStracker;
import com.nyu.blife_app.R;
import com.nyu.blife_app.SelectedHospitalDetails;
import com.nyu.blife_app.Yelp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searchhotels extends ActionBarActivity {

    private GPStracker gps;
    final Context context = this;
    private double lat, lng;

    class Hotel {
        final String name;
        final String url;
        //List<String> data_location=new ArrayList<String>();
        final String data_location;

        public Hotel(String name, String url, String data_location) {
            this.name = name;
            this.url = url;
            this.data_location = data_location;
        }

        @Override
        public String toString()
        {

            return name;
        }


        public String getLocation()
        {

            return data_location;
        }
        public String getnumber(){
            return url;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotels);

        gps = new GPStracker(Searchhotels.this);
        //check_network_status();
        lat = gps.getLatitude();
        lng = gps.getLongitude();
        final ListView lv_hotel=(ListView)findViewById(R.id.listView_search_hotels);

        AsyncTask<Void, Void, List<Hotel>> hospitals = new AsyncTask<Void, Void, List<Hotel>>() {
            @Override
            protected List<Hotel> doInBackground(Void... params) {
                Yelp get_hospitals = Yelp.getYelp(Searchhotels.this);
                //String hospitals = get_hospitals.search("hospitals", 34.0204989,-118.4117325);
                Log.v("Location details", String.valueOf(lat) + " " + String.valueOf(lng));
                String hotel_ret = get_hospitals.search("hotels", lat, lng);
                try {
                    return processJson(hotel_ret);
                } catch (JSONException e) {
                    Log.v("Full Errors", "Over here");
                    return Collections.<Hotel>emptyList();
                    //return hospitals;
                }


            }

            @Override
            protected void onPostExecute(List<Hotel> result) {
//                mSearchResultsText.setText(result);


                ArrayAdapter<Hotel> new_adapter = new ArrayAdapter<Hotel>(Searchhotels.this,
                        android.R.layout.simple_list_item_1,
                        result);

                lv_hotel.setAdapter(new_adapter);
                lv_hotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        Hotel biz = (Hotel) lv_hotel.getItemAtPosition(position);
                        Log.v("List View", biz.toString());
                        Toast.makeText(getApplicationContext(), biz.toString(), Toast.LENGTH_LONG).show();
//
                        String re1 = ".*?";    // Non-greedy match on filler
                        String re2 = "([+-]?\\d*\\.\\d{2,})(?![-+0-9\\.])";
                        String re3 = ".*?";    // Non-greedy match on filler
                        String re4 = "([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";
                        String float1 = "";
                        String float2 = "";
                        Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                        Matcher m = p.matcher(biz.getLocation());
                        if (m.find()) {
                            float1 = m.group(1);
                            float2 = m.group(2);
                            Log.v("Code", float1 + " " + float2);
                        }
                        String add = "";
                        try {
                            JSONObject for_address = new JSONObject(biz.getLocation());
                            add = for_address.getString("display_address");
                            add = add.replaceAll("[\\[\\]\"]", "");

                            Log.v("Getting address555", add);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String uri = String.format(Locale.ENGLISH,
                                "http://maps.google.com/maps?daddr=%f,%f",
                                Float.valueOf(float1), Float.valueOf(float2));
//                            Intent intent44 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                            intent44.setClassName("com.google.android.apps.maps",
//                                    "com.google.android.maps.MapsActivity");
//                            startActivity(intent44);

                        Intent it = new Intent(getApplicationContext(), SelectedHospitalDetails.class);
                        it.putExtra("lat", float1);
                        it.putExtra("long", float2);
                        it.putExtra("num", biz.getnumber());
                        it.putExtra("address", add);
                        it.putExtra("name", biz.toString());
                        startActivity(it);
                    }
                });

                //setProgressBarIndeterminateVisibility(false);
            }
        }.execute();
    }

    List<Hotel> processJson(String jsonStuff) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray hotel_json = json.getJSONArray("businesses");
        ArrayList<Hotel> hotelNames = new ArrayList<Hotel>(hotel_json.length());
        List<String> json_location = new ArrayList<String>();
        for (int i = 0; i < hotel_json.length(); i++) {

            JSONObject hospital_returned = hotel_json.getJSONObject(i);
            hotelNames.add(new Hotel(hospital_returned.optString("name"),
                    hospital_returned.optString("phone"), hospital_returned.optString("location")));
            Log.v("Inside Json 732", hospital_returned.optString("location"));


        }
        Integer image_id = R.drawable.icon_caller;
        return hotelNames;

        //return TextUtils.join("\n", hospitalNames);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searchhotels, menu);
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