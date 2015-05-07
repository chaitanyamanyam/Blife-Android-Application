package com.nyu.blife_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

/**
* Created by Yeshwant on 25/04/2015.
*/
public class CustomAdapter extends ParseQueryAdapter<ParseUser> {




    public CustomAdapter(Context context, QueryFactory<ParseUser> queryFactory) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri


        super(context, queryFactory);


//                new ParseQueryAdapter.QueryFactory<ParseUser>() {
//
//            //String[] blood1 = blood;
//            public ParseQuery<ParseUser> create() {
//                ParseQuery query = ParseUser.getQuery();
//                //query.whereEqualTo("userType","Donor");
//                query.whereEqualTo("bloodGroup", blood);
//                //query.whereEqualTo("zipCode", zip);
//
//                Log.i("query output", query.toString());
//                return query;
//
//            }
//
//        });
    }
    // Customize the layout by overriding getItemView

    @Override
    public View getItemView(ParseUser user, View v, ViewGroup parent) {
        // build your views
        // Add the title view
//        ListView titleTextView = (ListView) v.findViewById(R.id.listview);
//       titleTextView.setAdapter();
        if (v == null) {
            v = View.inflate(getContext(), R.layout.recyclerview_items_layout, null);
        }

        super.getItemView(user, v, parent);

        //super.getItemView(user, v, parent);

        TextView view1 = (TextView)v.findViewById(R.id.item_title);
        view1.setText(user.getString("firstName"));
        ImageView imgIcon = (ImageView) v.findViewById(R.id.item_icon);
        Picasso.with(imgIcon.getContext()).load(R.mipmap.phone).into(imgIcon);
        return v;
    }

}
