package com.iguideu.Route_Detail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

/**
 * Created by Hoyoung on 2017-07-30.
 */

public class Route_Detail_Activity extends AppCompatActivity {

    public Route_Data Cur_Route_Data;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    String Route_Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        ReceiveData();


    }

    void ReceiveData(){
        Intent receivedIntent = getIntent();
        Route_Index = receivedIntent.getStringExtra("Route_Index");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI

                Cur_Route_Data = dataSnapshot.child("routes").child(Route_Index).getValue(Route_Data.class);
                if(Cur_Route_Data != null){

                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    Route_Detail_Fragment_1 fragment = new Route_Detail_Fragment_1();
                    fragment.SetRoute_Data(Cur_Route_Data);

                    fragmentTransaction.add(R.id.route_detail_FrameLayout,fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }
}
