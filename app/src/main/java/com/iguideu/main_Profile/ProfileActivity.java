package com.iguideu.main_Profile;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.tourist_mode.HomeFragment;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class ProfileActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setProfileData();

        Profile_Main_Fragment fragment = new Profile_Main_Fragment();
        fragment.set_Profile_User_Id(user_id);
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.profile_FrameLayout,fragment);
        fragmentTransaction.commit();
    }

    void setProfileData(){
        Intent receivedIntent = getIntent();
        user_id = receivedIntent.getStringExtra("User_ID");
    }


}
