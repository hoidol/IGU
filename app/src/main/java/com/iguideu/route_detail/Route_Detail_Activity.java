package com.iguideu.route_detail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;

/**
 * Created by Hoyoung on 2017-07-30.
 */

public class Route_Detail_Activity extends AppCompatActivity {

    public Route_Data Cur_Route_Data;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    int Cur_Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        Cur_Route_Data = ReceiveData();
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Route_Detail_Fragment_1 fragment = new Route_Detail_Fragment_1();
        fragment.SetRoute_Data(Cur_Route_Data);

        fragmentTransaction.add(R.id.route_detail_FrameLayout,fragment);
        fragmentTransaction.commit();


    }

    Route_Data ReceiveData(){
        Intent receivedIntent = getIntent();
        Cur_Position = receivedIntent.getIntExtra("Cur_Route_Position",0);

        return AppData.Route_Data_List.get(this.Cur_Position);
    }
}
