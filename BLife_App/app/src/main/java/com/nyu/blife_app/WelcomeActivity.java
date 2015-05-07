package com.nyu.blife_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nyu.blife_app.models.BloodRequest;
import com.nyu.blife_app.models.User;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class WelcomeActivity extends Activity {

    Button btnSignup, btnSignin;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        check_network_status();
        /** enabling the database */
        //Parse.enableLocalDatastore(this);
       // Parse.initialize(getApplicationContext());
        Parse.initialize(this, "6qUFzAHfl9bXRzzDlBegiXZx0Tw5dc29m3jXmnHt", "TS6OswWh6HwKQm2uCJxqfprlyrP2mfpkmwkx3Vg9");

        //code for setting up Parse's "Push notifications"
        //ParseInstallation.getCurrentInstallation().saveInBackground();

       /* ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });*/

        /*calling the User class from models */
        ParseObject.registerSubclass(User.class);
        ParseObject.registerSubclass(BloodRequest.class);
        //code for maintaining the sessions
         if (ParseUser.getCurrentUser() != null){
         startActivity(new Intent(this, HomeActivity.class));
         }


        btnSignup = (Button) findViewById(R.id.signUpButton);
        btnSignin = (Button) findViewById(R.id.signInButton);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeActivity.this, RegistrationActivity.class);
                startActivity(i);

            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
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
        if (id == R.id.log_out) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void check_network_status() {
        if (isNetworkStatusAvialable(getApplicationContext())) {
           // Toast.makeText(getApplicationContext(), "internet available", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(getApplicationContext(), "internet is not available", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set title
            alertDialogBuilder.setTitle("Network Connectivity Problem");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Application not able to connect to the Internet")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            WelcomeActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                            check_network_status();


                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


        }
    }
}
