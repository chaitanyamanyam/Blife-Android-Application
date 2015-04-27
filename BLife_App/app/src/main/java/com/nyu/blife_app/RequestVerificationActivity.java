package com.nyu.blife_app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                    new SweetAlertDialog(RequestVerificationActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Posted")
                            .setContentText("Your Request has been Posted..")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    Intent i = new Intent(getBaseContext(), ManageRequestsScreen.class);
                                    i.putExtra("Request Details" , full_details);
                                    i.putExtra("Verification Code" , message);
                                    i.putExtra("Status" , "Request Posted");
                                    sDialog.cancel();
                                    finish();
                                    startActivity(i);

                                }
                            }).show();


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
