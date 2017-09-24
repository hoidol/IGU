package com.iguideu.Signup_Guider;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;
import com.iguideu.tourist_mode.tourist_home.HomeFragment;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class SignUpGuiderActivity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_guider);

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.signup_guider_FrameLayout,new SignUpGuider_Check_Fragment());
        fragmentTransaction.commit();
    }
}
