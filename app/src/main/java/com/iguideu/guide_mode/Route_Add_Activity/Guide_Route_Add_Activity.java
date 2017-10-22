package com.iguideu.guide_mode.Route_Add_Activity;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;

public class Guide_Route_Add_Activity extends AppCompatActivity {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_route_add);

        fm=getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Guide_Route_Add_Fragment fragment=new Guide_Route_Add_Fragment();
        fragmentTransaction.add(R.id.route_add_frame,fragment);
        fragmentTransaction.commit();

    }

}
