package com.iguideu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iguideu.data.AppData;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setProfileData();
        setToolbar();
    }

    void setProfileData(){
        Intent receivedIntent = getIntent();

        String user_id = receivedIntent.getStringExtra("User_ID");
    }

    void setToolbar(){
        TextView toolbar_title_TextView = (TextView)findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("프로필");
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));
        ImageButton toolbar_back_ImagmeView = (ImageButton)findViewById(R.id.toolbar_back_ImagmeView);

        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
