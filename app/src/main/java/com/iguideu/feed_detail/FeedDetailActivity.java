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
import com.iguideu.data.Feed_Data;
import com.squareup.picasso.Picasso;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class FeedDetailActivity extends AppCompatActivity{

    Context m_Context;

    Feed_Data Cur_Feed_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        Cur_Feed_Data = setReceivedData();
        setlayout_Data(Cur_Feed_Data);

    }



    Feed_Data setReceivedData(){
        Intent receivedIntent = getIntent();

        int position = receivedIntent.getIntExtra("Feed_Position",0);
        return AppData.Feed_Data_List.get(position);
    }

    void setlayout_Data(Feed_Data feed_data){

        ImageView progile_ImageView = (ImageView)findViewById(R.id.user_Profile_ImageView);
        Picasso.with(m_Context).load(feed_data.User_Profile_URL).placeholder(R.mipmap.default_profile_icon).into(progile_ImageView);
        TextView ID_TextView = (TextView)findViewById(R.id.user_Profile_Name);
        ID_TextView.setText(feed_data.User_Name);

        ImageView feed_ImageView = (ImageView)findViewById(R.id.feed_ImageView);
        Picasso.with(m_Context).load(feed_data.Feed_Image_URL).into(feed_ImageView);

        TextView Content_TextView = (TextView)findViewById(R.id.feed_Content_Text);
        Content_TextView.setText(feed_data.Feed_Contents);

        ImageView back_ImageView = (ImageView)findViewById(R.id.toolbar_back_ImagmeView);
        back_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
