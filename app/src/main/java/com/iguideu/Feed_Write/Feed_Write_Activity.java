package com.iguideu.Feed_Write;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class Feed_Write_Activity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_write);
        setFrameLayout();
    }

    void setFrameLayout(){
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.add(R.id.feed_write_Fragment,new Feed_Write_Photo_Fragment());
        fragmentTransaction.commit();

    }
}
