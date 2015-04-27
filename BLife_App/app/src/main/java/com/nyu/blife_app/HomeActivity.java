package com.nyu.blife_app;

import android.content.Intent;

import android.net.ConnectivityManager;

import android.graphics.Point;
import android.os.Build;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends ActionBarActivity {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<Integer> listDataHeaderImages;
    GPStracker home_gpstracker;
    //HashMap<Integer, List<Integer>> listDataChildImages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        expListView=(ExpandableListView) findViewById(R.id.expandableListView2);
        prepareListData();
        listAdapter=new ExpandableListAdapter(this,listDataHeader,listDataChild, listDataHeaderImages);
        expListView.setAdapter(listAdapter);
        home_gpstracker=new GPStracker(HomeActivity.this);

        //code to resize the Expandable ListView as per the device screen size when list view has been completely populated with child views
        //Code Reference - http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
       /* WindowManager w = this.getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        // includes window decorations (status bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        // includes window decorations (status bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }*/


       // expListView.setLayoutParams(new RelativeLayout.LayoutParams(500, 300));


        // ListView Group click listener

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView
                        .getPackedPositionForGroup(groupPosition));

               //highlight the selected list group using selector from list_group_highlighter.xml
                parent.setItemChecked(index, true);

                if(listDataHeader.get(groupPosition).equals("SEARCH DONORS")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent searchDonorIntent = new Intent(getApplicationContext(), SearchDonorsActivity.class);
                    startActivity(searchDonorIntent);
                }

                if(listDataHeader.get(groupPosition).equals("HELPLINE")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent helplineIntent = new Intent(getApplicationContext(), HelplineNumbersScreen.class);
                    startActivity(helplineIntent);
                }

                if(listDataHeader.get(groupPosition).equals("TIPS")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent tipsIntent = new Intent(getApplicationContext(), HealthTipsActivity.class);
                    startActivity(tipsIntent);
                }

                if(listDataHeader.get(groupPosition).equals("FAQs")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent faqIntent = new Intent(getApplicationContext(), FAQsScreen.class);
                    startActivity(faqIntent);
                }

                if(listDataHeader.get(groupPosition).equals("ABOUT B-LIFE")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent aboutIntent = new Intent(getApplicationContext(), AboutBLifeActivity.class);
                    startActivity(aboutIntent);
                }


                return false;
            }
        });

        // ListView Child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {

                int index = parent.getFlatListPosition(ExpandableListView
                        .getPackedPositionForChild(groupPosition, childPosition));
                //highlight the selected list child using selector from list_item_highlighter.xml
                parent.setItemChecked(index, true);

                if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("SEND BLOOD REQUEST")){
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    Intent sendBRIntent = new Intent(getApplicationContext(), SendBloodRequestActivity.class);
                    startActivity(sendBRIntent);
                }

                if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("VIEW BLOOD REQUEST")){
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    Intent viewBRIntent = new Intent(getApplicationContext(), ViewBloodRequestActivity.class);
                    startActivity(viewBRIntent);
                }

                if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("MANAGE BLOOD REQUESTS")){
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    Intent manageReqIntent = new Intent(getApplicationContext(), ManageRequestsScreen.class);
                    startActivity(manageReqIntent);
                }

                if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("SEARCH HOSPITALS")){
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    if(isInternetOn()) {
                           if(check_gps()) {
                               Intent searchHospitalIntent = new Intent(getApplicationContext(),
                                       SearchHospitalsScreen.class);
                               startActivity(searchHospitalIntent);

                           }



                    }
                }

                if(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).equals("SEARCH BLOOD BANKS")){
                    Toast.makeText(
                            getApplicationContext(),
                            listDataHeader.get(groupPosition)
                                    + " : "
                                    + listDataChild.get(
                                    listDataHeader.get(groupPosition)).get(
                                    childPosition), Toast.LENGTH_SHORT)
                            .show();
                    if(isInternetOn()) {
                        if(check_gps()) {
                            Intent searchBanksIntent = new Intent(getApplicationContext(), SearchBloodBankActivity.class);
                            startActivity(searchBanksIntent);
                        }
                    }
                }

                return false;
            }
        });

        // ListView Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {


               /* if(listDataHeader.get(groupPosition).equals("SEARCH DONORS")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent searchDonorIntent = new Intent(getApplicationContext(), SearchDonorsActivity.class);
                    startActivity(searchDonorIntent);

                }*/


               /* if(listDataHeader.get(groupPosition).equals("BLOOD REQUEST")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();

                    TextView textView = (TextView)expListView.findViewById(R.id.lblListHeader);
                    textView.setHighlightColor(getResources().getColor(R.color.list_group_highlighter));

                    //textView.setBackgroundColor(getBaseContext().getResources().getColor(R.color.home_activity_header));
                }*/

               /* if(listDataHeader.get(groupPosition).equals("SEARCH DONORS")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Expanded",
                            Toast.LENGTH_SHORT).show();
                    Intent searchDonorIntent = new Intent(getApplicationContext(), SearchDonorsActivity.class);
                    startActivity(searchDonorIntent);
                }*/

//                if(listDataHeader.get(groupPosition).equals("HELPLINE")){
//                    Toast.makeText(getApplicationContext(),
//                            listDataHeader.get(groupPosition) + " Expanded",
//                            Toast.LENGTH_SHORT).show();
//                    Intent helplineIntent = new Intent(getApplicationContext(), HelplineNumbersScreen.class);
//                    startActivity(helplineIntent);
//                }
//
//                if(listDataHeader.get(groupPosition).equals("TIPS")){
//                    Toast.makeText(getApplicationContext(),
//                            listDataHeader.get(groupPosition) + " Expanded",
//                            Toast.LENGTH_SHORT).show();
//                    Intent tipsIntent = new Intent(getApplicationContext(), HealthTipsActivity.class);
//                    startActivity(tipsIntent);
//                }
//
//                if(listDataHeader.get(groupPosition).equals("FAQs")){
//                    Toast.makeText(getApplicationContext(),
//                            listDataHeader.get(groupPosition) + " Expanded",
//                            Toast.LENGTH_SHORT).show();
//                    Intent faqIntent = new Intent(getApplicationContext(), FAQsScreen.class);
//                    startActivity(faqIntent);
//                }
//
//                if(listDataHeader.get(groupPosition).equals("ABOUT B-LIFE")){
//                    Toast.makeText(getApplicationContext(),
//                            listDataHeader.get(groupPosition) + " Expanded",
//                            Toast.LENGTH_SHORT).show();
//                    Intent aboutIntent = new Intent(getApplicationContext(), AboutBLifeActivity.class);
//                    startActivity(aboutIntent);
//                }

            }
        });


        // Listview Group collapsed listener
        /*expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

                if(listDataHeader.get(groupPosition).equals("BLOOD REQUEST")){
                    Toast.makeText(getApplicationContext(),
                            listDataHeader.get(groupPosition) + " Collapsed",
                            Toast.LENGTH_SHORT).show();
                    TextView textView = (TextView)expListView.findViewById(R.id.lblListHeader);
                    textView.setBackgroundColor(getBaseContext().getResources().getColor(R.color.home_activity_button));
                }


            }
        });*/


           /* @Override
            public void onClick(View view) {

                if (txtView.getText().toString().equals("SEARCH DONORS")){
                    Intent intent = new Intent(HomeActivity.this, SearchDonorsActivity.class);
                    startActivity(intent);
                }
                if (txtView.getText().toString().equals("FAQs")){
                    Intent intent = new Intent(HomeActivity.this, FAQsScreen.class);
                    startActivity(intent);
                }
            }
        });*/

    }


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i1 = new Intent(this, SettingsActivity.class);
                startActivity(i1);
                Toast.makeText(getBaseContext(), "you selected settings", Toast.LENGTH_LONG).show();
                break;

            case R.id.log_out:
                ParseUser.logOut();

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

        }
        return true;
    }
    private boolean check_gps(){
           boolean k=true;

            if (home_gpstracker.canGetLocation()) {
                k= true;
            }
            else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                home_gpstracker.showSettingsAlert();
                k=false;



            }

            return k;

//                finish();



    }

//    protected void onPause(){
//        super.onPause();
//        HomeActivity.this.finish();
//    }
//    @Override
//    protected void onResume() {
//
//        super.onResume();
//       HomeActivity.this.recreate();
//    }
@Override
public void onRestart() {
    super.onRestart();
    //When BACK BUTTON is pressed, the activity on the stack is restarted
    //Do what you want on the refresh procedure here
    Log.v("Full","Restart");
    HomeActivity.this.recreate();
}
    //prepare data for lists of group and child titles as well as lists of group and child images
    private void prepareListData(){

        //for titles
        listDataHeader=new ArrayList<String>();
        listDataChild=new HashMap<String,List<String>>();
        listDataHeader.add("SEARCH DONORS");
        List<String> home_list = new ArrayList<String>();


        listDataHeader.add("BLOOD REQUEST");
        List<String> home_list1=new ArrayList<String>();
        home_list1.add("SEND BLOOD REQUEST");
        home_list1.add("VIEW BLOOD REQUEST");
        home_list1.add("MANAGE BLOOD REQUESTS");

        listDataHeader.add("SEARCH DONATION CAMPS");
        List<String> home_list2 =new ArrayList<String>();
        home_list2.add("SEARCH HOSPITALS");
        home_list2.add("SEARCH BLOOD BANKS");

        listDataHeader.add("HELPLINE");
        List<String> home_list3 =new ArrayList<String>();

        listDataHeader.add("TIPS");
        List<String> home_list4 =new ArrayList<String>();

        listDataHeader.add("FAQs");
        List<String> home_list5 =new ArrayList<String>();

        listDataHeader.add("ABOUT B-LIFE");
        List<String> home_list6 =new ArrayList<String>();


        //for images
        listDataHeaderImages = new ArrayList<Integer>();
        //listDataChildImages = new HashMap<Integer,List<Integer>>();

        listDataHeaderImages.add(R.drawable.globe);
        //List<String> home_list = new ArrayList<String>();


        listDataHeaderImages.add(R.drawable.users);

        listDataHeaderImages.add(R.drawable.plus);
        listDataHeaderImages.add(R.drawable.phone1);
        listDataHeaderImages.add(R.drawable.tips);
        listDataHeaderImages.add(R.drawable.faq);
        listDataHeaderImages.add(R.drawable.rightgray);




        listDataChild.put(listDataHeader.get(0), home_list);
        listDataChild.put(listDataHeader.get(1), home_list1);

        listDataChild.put(listDataHeader.get(2), home_list2);

        listDataChild.put(listDataHeader.get(3), home_list3);

        listDataChild.put(listDataHeader.get(4), home_list4);
        listDataChild.put(listDataHeader.get(5), home_list5);
        listDataChild.put(listDataHeader.get(6), home_list6);



    }

}
