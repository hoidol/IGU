package com.iguideu.route_detail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-07-30.
 */

public class Route_Detail_Activity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Route_Detail_Fragment_1 fragment = new Route_Detail_Fragment_1();
        fragmentTransaction.add(R.id.route_detail_FrameLayout,fragment);
        fragmentTransaction.commit();
    }
}
