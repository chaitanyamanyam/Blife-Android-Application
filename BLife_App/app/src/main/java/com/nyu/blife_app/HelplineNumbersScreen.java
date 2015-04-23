package com.nyu.blife_app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class HelplineNumbersScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline_numbers_screen);

        ListView helpline_lview=(ListView)findViewById(R.id.listView);
        final String[] helpline_name = new String[] {
                "US Suicide ",
                "NDMDA Depression Hotline ",
                "Suicide Prevention Services Crisis ",
                "Suicide Prevention Services Depression",
                "AAA Crisis Pregnancy Center",
                 "Child Abuse",
                "Crisis Help Line",
                "Domestic & Teen Dating Violence",
                "Parental Stress",
                "Runaway Hotline",
                "Sexual Assault Hotline ",
                "Suicide & Depression Hotline",
                "National Child Abuse Hotline	",
                "National Domestic Violence Hotline",
                "National Domestic Violence (TDD)",
                "National Youth Crisis Hotline"

        };
        final String[] number_helpline=new String[]{
                "1-800-784-2433",
                "800-826-3632",
                "800-784-2433",
                "630-482-9696",
                "800-560-0717",
                "800-792-5200",
                "800-233-4357",
                "800-992-2600",
                "800-632-8188",
                "800-231-6946",
                "800-223-5001",
                "800-999-9999",
                "800-422-4453",
                "800-799-7223",
                "800-787-3224",
                "800-448-4663"
                    };
        Integer image_id=R.drawable.icon_caller;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, helpline_name);
        customlistadapter adapter=new customlistadapter(this,image_id,helpline_name);
        helpline_lview.setAdapter(adapter);
        helpline_lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = helpline_name[position];
                String itemNumber=number_helpline[position];

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Calling "+ itemValue, Toast.LENGTH_LONG)
                        .show();
                Uri call_number=Uri.parse("tel:" + itemNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL,call_number);
                startActivity(intent);

            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_helpline_numbers_screen, menu);
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
