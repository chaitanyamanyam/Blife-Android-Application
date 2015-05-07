package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nyu.blife_app.models.BloodRequest;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class RequestVerificationActivity extends ActionBarActivity {

    Button submitreq_veri;
    EditText submitreqveri_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_verification);

        final String message,full_details;
        String message_code="Request_Code";
        String user_content="Request_User";
        Intent intent = getIntent();
        final String user_type=intent.getStringExtra("Type");
       // long millis = intent.getLongExtra("beforeDate", )
        final Date stDate = new Date(getIntent().getExtras().getLong("beforeDate",-1));

        message = intent.getStringExtra(message_code);
        full_details = intent.getStringExtra(user_content);


        submitreq_veri = (Button) findViewById(R.id.verify_button);
        submitreqveri_code = (EditText) findViewById(R.id.verify_editText);
        Toast.makeText(getApplicationContext(), user_type +" - " + message + " - " + full_details, Toast.LENGTH_LONG).show();
        //Store these in database - values are being caught here


        submitreq_veri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String veri = submitreqveri_code.getText().toString();
                if (veri.equals(message)) {
                    String[] send_request_values = full_details.split(",");
                    String get_requestorName = send_request_values[0];
                    String get_hospital = send_request_values[1];
                    String get_city = send_request_values[2];
                    String get_bloodGroup = send_request_values[3];
                    String get_message = send_request_values[4];
                    //Date get_requiredBefore = send_request_values[5];
                   // String Date bloodDate = get_requiredBefore.
                     String get_phoneNumber = send_request_values[5];

                    BloodRequest request = new BloodRequest();
                    request.setrequestorName(get_requestorName);
                    request.setLocation(get_hospital);
                    request.setCity(get_city);
                    request.setBloodGroup(get_bloodGroup);
                    request.setMessage(get_message);
                    request.setRequiredBefore(stDate);
                    request.setCellNumber(get_phoneNumber);
                    request.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            finish();
                        }
                    });

                    Intent i = new Intent(RequestVerificationActivity.this, ManageRequestsScreen.class);
                    i.putExtra("Request Details" , full_details);
                    i.putExtra("Verification Code" , message);
                    i.putExtra("Status" , "Request Posted");
                    startActivity(i);
//                    new SweetAlertDialog(RequestVerificationActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//
//                            .setTitleText("Posted")
//                            .setContentText("Your Request has been Posted..")
//                            .setConfirmText("OK")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                   // sDialog.setConfirmText("Done!!");
//                                    sDialog.cancel();
//
//                                    //RequestVerificationActivity.this.finish();
//
//                                }
//                            }).show();
//                          //SweetAlertDialog sDialog = new SweetAlertDialog.;
//                    //sDialog.show();


                }
                else
                {
                    if(!veri.equals(message))
                    {
                        Toast.makeText(getApplicationContext(), "Wrong Code",Toast.LENGTH_SHORT).show();
                    }
//                    else
//                    {
                    //    back_Intent.putExtra("Code Empty" , "Awaiting confirmation");
//                        onBackPressed();
//                    }

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent back_Intent = new Intent(RequestVerificationActivity.this, ManageRequestsScreen.class);
        startActivity(back_Intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_request_verification, menu);
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
