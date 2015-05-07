package com.nyu.blife_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nyu.blife_app.models.User;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class RegistrationAuthenticationActivity extends ActionBarActivity
{

    Button auth_submit;
    EditText veri_code;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_authentication);
        final String message,full_details;
        String message_code="Acceptor_Code";
        String user_content="Acceptor_User";
        final String donor_info;
        Intent intent = getIntent();
        final String user_type=intent.getStringExtra("Type");
        if(user_type.equals("Donor")){
            message_code="Donor_Code";
            user_content="Donor_User";

        }
                message = intent.getStringExtra(message_code);
                full_details = intent.getStringExtra(user_content);
                donor_info = intent.getStringExtra("donor_details");

        auth_submit = (Button) findViewById(R.id.auth_button);
        veri_code = (EditText) findViewById(R.id.auth_editText);
        //Toast.makeText(getApplicationContext(), user_type+message+full_details,Toast.LENGTH_LONG).show();

        auth_submit.setOnClickListener(
                new View.OnClickListener() {
            public void onClick(View view) {
                String veri = veri_code.getText().toString();
                if (veri.equals(message)) {
                    Toast.makeText(getApplicationContext(), "Correct Code",
                            Toast.LENGTH_LONG).show();
                    if (user_type.equals("Donor")){
                        signup(full_details, donor_info, user_type);
                    }
                    else{
                        signup(full_details, user_type);
                    }
                        //calling signup method
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Code",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

   /** copy paste this inside your send message method */
    /* defining signup method */

private void signup(String input_details, String input_donor, String input_type){
    //setup new parse user


    String[] parse_details=input_details.split(",");
    final String get_firstname = parse_details[0];
    final String get_lastname = parse_details[1];
    final String get_username = parse_details[2];
    final String get_password = parse_details[3];
    //final long get_phone = Integer.parseInt(parse_details[4]);
    final Long get_phone = Long.parseLong(parse_details[4]);
    final int get_zip = Integer.parseInt(parse_details[5]);
    final String get_city = parse_details[6];
            String member_type = input_type;
    String[] input_donor_values = input_donor.split(",");
    final String get_dob = input_donor_values[0];
    final String get_blood_group = input_donor_values[1];
    final String get_gender = input_donor_values[2];
    final String get_weight = input_donor_values[3];
    final String get_disease_value = input_donor_values[4];
    final String get_pregnancy = input_donor_values[5];
   // Toast.makeText(getApplicationContext(), get_city+get_firstname+get_lastname,Toast.LENGTH_LONG).show();


    ParseUser user = new ParseUser();
    user.setUsername(get_username);
    user.setPassword(get_password);
    user.put("firstName", get_firstname);
    user.put("lastName",get_lastname);
    user.put("phoneNumber",get_phone);
    user.put("zipCode", get_zip);
    user.put("city", get_city);
    user.put("dob", get_dob);
    user.put("bloodGroup", get_blood_group);
    user.put("gender", get_gender);
    user.put("weight", get_weight);
    user.put("disease", get_disease_value);
    user.put("pregnancy", get_pregnancy);
    user.put("userType", member_type);

    User User = new User();
    User.setFirstName(get_firstname);
    User.setLastName(get_lastname);
    User.setUsername(get_username);
    User.setPassword(get_password);
    User.setPhoneNumber(get_phone);
    User.setZipCode(get_zip);
    User.setCity(get_city);



    User.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            finish();
        }
    });

    //call the parse signup method
    user.signUpInBackground(new SignUpCallback()
    {
        @Override
        public void done(ParseException e) {

            if(e != null){
                //show the error message
                Toast.makeText(RegistrationAuthenticationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                //start an intent for homescreen activity
                Intent loggedin = new Intent(RegistrationAuthenticationActivity.this, HomeActivity.class);
                loggedin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loggedin);
            }
        }
    });
}


//    /** end of signup method */


    /*start of sign up only for receivers*/

    private void signup(String input_details, String input_type){
        String[] parse_details=input_details.split(",");
        final String get_firstname = parse_details[0];
        final String get_lastname = parse_details[1];
        final String get_username = parse_details[2];
        final String get_password = parse_details[3];
        //final long get_phone = Integer.parseInt(parse_details[4]);
        final Long get_phone = Long.parseLong(parse_details[4]);
        final int get_zip = Integer.parseInt(parse_details[5]);
        final String get_city = parse_details[6];
        String member_type = input_type;
        Log.d("hello1", member_type);
        ParseUser user = new ParseUser();
        user.setUsername(get_username);
        user.setPassword(get_password);
        user.put("firstName",get_firstname);
        user.put("lastName",get_lastname);
        user.put("phoneNumber",get_phone);
        user.put("zipCode", get_zip);
        user.put("city", get_city);
        user.put("userType", member_type);

        user.signUpInBackground(new SignUpCallback()
        {
            @Override
            public void done(ParseException e) {

                if(e != null){

                    //show the error message
                    Toast.makeText(RegistrationAuthenticationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                }
                else
                {


                    //start an intent for homescreen activity

                    Intent loggedin = new Intent(RegistrationAuthenticationActivity.this, HomeActivity.class);

                    loggedin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loggedin);


                }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration_authentication, menu);
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
