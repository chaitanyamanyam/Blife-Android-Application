package com.nyu.blife_app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DonorRegistrationActivity extends ActionBarActivity implements DatePickerDialog.OnDateSetListener {

    TextView bDateTextView, txtviewgender, txtviewdisease, txtviewpregnant;
    Spinner spinnerselectBloodGroup;
    EditText edittextWeight;
    RadioGroup radiogrpGender, radiogrpDisease, radiogrpPregnant;
    RadioButton radiobtnGender, radiobtnDisease, radiobtnPregnant;
    Button registerbtnDonor;
    String weight,bloodgroup_selected;
    public final static String EXTRA_MESSAGE = "com.mycompany.Blife.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);

        Intent intent = getIntent();
        final String get_phoneno = intent.getStringExtra("Phone_Number");
        final String get_reg_details=intent.getStringExtra("User");

        bDateTextView = (TextView)findViewById(R.id.txtDOB);
        bDateTextView.setFocusable(true);
        bDateTextView.setFocusableInTouchMode(true);
        registerbtnDonor = (Button) findViewById(R.id.registerButton_Donor);

        txtviewgender = (TextView)findViewById(R.id.txtGender);
        txtviewdisease = (TextView)findViewById(R.id.txtDisease);
        txtviewpregnant = (TextView)findViewById(R.id.txtPregnant);
        spinnerselectBloodGroup = (Spinner)findViewById(R.id.spinnerDonorBloodGroup);
        spinnerselectBloodGroup.setFocusable(true);
        spinnerselectBloodGroup.setFocusableInTouchMode(true);
        edittextWeight = (EditText) findViewById(R.id.editWeight);
        radiogrpGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radiogrpDisease = (RadioGroup) findViewById(R.id.radioGroupDisease);
        radiogrpPregnant = (RadioGroup) findViewById(R.id.radioGroupPregnant);


        registerbtnDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String get_date = bDateTextView.getText().toString();
                final String get_weight = edittextWeight.getText().toString();
                final String get_BloodGroup = spinnerselectBloodGroup.getSelectedItem().toString();


                int id_gender = addListenerRadioGroupGender();
                int id_disease = addListenerRadioGroupDisease();
                int id_pregnant = addListenerRadioGroupPregnant();

                validateWeight(get_weight);
                validateBloodGroup(get_BloodGroup);

                if(!get_weight.isEmpty() && !get_BloodGroup.isEmpty())
                {
                    if((id_gender > 0) && (id_disease > 0) && (id_pregnant > 0))
                    {
                        sendSMSMessage(get_phoneno,get_reg_details);

                    }
                }
                else
                {
                    new AlertDialog.Builder(DonorRegistrationActivity.this).setTitle("Donor Registration Failed.")
                            .setMessage("Please check Details.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).create()
                            .show();
                }

            }


            private String validateBloodGroup(String get_BloodGroup) {

                if(get_BloodGroup.equals("BLOOD GROUP"))
                {
                    spinnerselectBloodGroup.requestFocus();
                    Toast.makeText(getBaseContext(),"Select valid Blood Group entry.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    bloodgroup_selected = get_BloodGroup;
                }
                return bloodgroup_selected;
            }
            private String validateWeight(String get_weight) {

                if (get_weight.equals("") ){
                    edittextWeight.requestFocus();
                    edittextWeight.setError("Required Field! ");
                    edittextWeight.setText("");
                }
                else if(Integer.parseInt(get_weight)<110 )
                {
                    edittextWeight.requestFocus();
                    edittextWeight.setError("Enter valid data.(Less than 110lbs)");
                    edittextWeight.setText("");
                }
                else
                {
                    weight = get_weight;
                }
                return weight;
            }
        });
    }

    private int addListenerRadioGroupPregnant() {
        int id_selected = radiogrpPregnant.getCheckedRadioButtonId();
        radiobtnPregnant = (RadioButton) findViewById(id_selected);
        if(id_selected <= 0)
        {
            txtviewpregnant.setError("Select option..");
            return id_selected;

        }
        else
        {
            txtviewpregnant.setError(null);
            return id_selected;
        }
    }
    private int addListenerRadioGroupDisease() {
        int id_selected = radiogrpDisease.getCheckedRadioButtonId();
        radiobtnDisease = (RadioButton) findViewById(id_selected);
        if(id_selected <= 0)
        {
            txtviewdisease.setError("Select option..");
            return id_selected;
        }
        else
        {
            txtviewdisease.setError(null);
            return id_selected;
        }
    }
    private int addListenerRadioGroupGender() {
        int id_selected = radiogrpGender.getCheckedRadioButtonId();
        radiobtnGender = (RadioButton) findViewById(id_selected);
        if(id_selected <= 0)
        {
            txtviewgender.setError("Select Gender..");
            return id_selected;
        }
        else
        {
            txtviewgender.setError(null);
            return id_selected;
        }

     }

    protected void sendSMSMessage(String phone,String send_details)
    {
        Log.i("Send SMS", "");
        String phoneNo = phone;
        String type_user ="Donor";
        String my_name = generatePIN();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null,my_name , null, null);
            Toast.makeText(getBaseContext(), "SMS sent.",Toast.LENGTH_LONG).show();
            //Toast.makeText(getBaseContext(), my_name+send_details,Toast.LENGTH_LONG).show();
            Intent intent1 = new Intent(this, RegistrationAuthenticationActivity.class);
            intent1.putExtra("Donor_Code", my_name);
            intent1.putExtra("Donor_User",send_details);
            intent1.putExtra("Type",type_user);

            startActivity(intent1);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),"SMS failed, please try again.",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public String generatePIN()
    {
        int x = (int)(Math.random() * 9);
        x = x + 1;
        String randomPIN = (x + "") + ( ((int)(Math.random()*1000)) + "" );
        return randomPIN;
    }

    //method to display a Date Picker Dialog Fragment on click of 'Date Of Birth' TextView

    public void showDatePickerDialog(View view) {
        Log.d("DonorReg", "Entered showDialog");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogFragment newFragment = DatePickerDialogFragment.newInstance(DonorRegistrationActivity.this);

        newFragment.show(ft, "Select Date");
        Log.d("DonorReg", "Exited showDialog");
    }

    /**
     * This inner class represents a reusable Date Picker hosted in a Dialog Fragment for B-Life app.
     * Code Reference : http://www.codinguser.com/2012/06/time-and-date-inputs-in-android/
     *
     */

    public static class DatePickerDialogFragment extends DialogFragment {

        protected DatePickerDialog.OnDateSetListener mDateSetListener;

        //Empty default constructor to prevent our app from crashing when the device is rotated.
        public DatePickerDialogFragment() {
            // nothing to see here, move along
        }

        public static DialogFragment newInstance(DatePickerDialog.OnDateSetListener callback){
            Log.d("DonorReg", "Entered newInstance");
            DatePickerDialogFragment dFragment = new DatePickerDialogFragment();
            dFragment.mDateSetListener = callback;
            Log.d("DonorReg", "Exited newInstance");
            return dFragment;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar cal = Calendar.getInstance();

            return new DatePickerDialog(getActivity(),mDateSetListener, cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }
    }


    //method to set the date selected from the Date Picker on the txtDOB TextView
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        Calendar cal = new GregorianCalendar(year, monthOfYear, dayOfMonth);


       final Calendar now = new GregorianCalendar();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
                || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        if (cal.after(now)){
            bDateTextView.setError("cannot select a future date");
            bDateTextView.setText("DATE OF BIRTH");
        }
        else if (age < 17){
            bDateTextView.setError("age fault....less than 17 yrs..");
            bDateTextView.setText("DATE OF BIRTH");
        }
        else {
            DateFormat dateFormat = DateFormat.getDateInstance();
            bDateTextView.setText(dateFormat.format(cal.getTime()));
            bDateTextView.setError(null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donor_registration, menu);
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
