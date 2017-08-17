package com.iguideu.feed_detail;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.squareup.picasso.Picasso;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class FeedDetailActivity extends AppCompatActivity{

    Context m_Context;

    String User_ID;
    String User_Name;
    String User_Profile_URL;
    String Feed_Image_URL;
    String Feed_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        setReceivedData();
        setlayout_Data();

    }

    void setlayout_Data(){
        ImageView progile_ImageView = (ImageView)findViewById(R.id.user_Profile_ImageView);
        Picasso.with(m_Context).load(User_Profile_URL).placeholder(R.mipmap.default_profile_icon).into(progile_ImageView);
        TextView ID_TextView = (TextView)findViewById(R.id.user_Profile_Name);
        ID_TextView.setText(User_Name);

        ImageView feed_ImageView = (ImageView)findViewById(R.id.feed_ImageView);
        Picasso.with(m_Context).load(Feed_Image_URL).into(feed_ImageView);

        TextView Content_TextView = (TextView)findViewById(R.id.feed_Content_Text);
        Content_TextView.setText(Feed_Content);


        ImageView back_ImageView = (ImageView)findViewById(R.id.toolbar_back_ImagmeView);
        back_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void setReceivedData(){

        Intent receivedIntent = getIntent();

        this.User_ID = receivedIntent.getStringExtra("User_ID");
        this.User_Name = receivedIntent.getStringExtra("User_Name");
        this.User_Profile_URL = receivedIntent.getStringExtra("User_Profile_URL");
        this.Feed_Image_URL = receivedIntent.getStringExtra("Feed_Image_URL");
        this.Feed_Content = receivedIntent.getStringExtra("Feed_Content");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
