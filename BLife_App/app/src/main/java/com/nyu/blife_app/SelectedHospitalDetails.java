package com.nyu.blife_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SelectedHospitalDetails extends ActionBarActivity {

    private GoogleMap googleMap;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hospital_details);
        GPStracker my_gps=new GPStracker(SelectedHospitalDetails.this);
        Intent k=getIntent();
        String ki=k.getStringExtra("lat");
        String ki3=k.getStringExtra("long");
        final String number_from=k.getStringExtra("num");
        String display_address=k.getStringExtra("address");
        String display_name=k.getStringExtra("name");
        LatLng hospital_location=new LatLng(Double.parseDouble(ki),Double.parseDouble(ki3));
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        TextView add_number=(TextView)findViewById(R.id.textView_shd);
        TextView add_address=(TextView)findViewById(R.id.textView_shd_address);
        LatLng my_locat_coord=new LatLng(my_gps.getLatitude(),my_gps.getLongitude());
        Log.v("Full ","Addreess");
//        TextView display_add=(TextView)findViewById(R.id.textView_add);
        if (googleMap!=null)
        {
            Marker hamburg = googleMap.addMarker(new MarkerOptions().position(hospital_location)
                    .title(display_name));
            Marker my_location=googleMap.addMarker(new MarkerOptions().position(my_locat_coord).
                    title("My Location").icon(BitmapDescriptorFactory.
                    defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hospital_location, 15));
//            display_add.setText(display_address);

            add_address.setText("Address:"+display_address);
            add_number.setText("Phone Number :"+number_from);

//        try {
//            // Loading map
//            initilizeMap();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        }
        add_number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri call_number=Uri.parse("tel:" + number_from);
                Intent intent_dialer = new Intent(Intent.ACTION_DIAL,call_number);
                startActivity(intent_dialer);
            }
        });
     }

//    /**
//     * function to load map. If map is not created it will create it for you
//     * */
//    private void initilizeMap() {
//        if (googleMap == null) {
//            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
//                    R.id.map)).getMap();
//
//            // check if map is created successfully or not
//            if (googleMap == null) {
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        initilizeMap();
//    }







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
