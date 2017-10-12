package com.iguideu.main_Profile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.Route_Detail.Route_Detail_Activity;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-10-05.
 */

public class Profile_Route_List_Fragment extends Fragment {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    String Profile_User_Id;
    Profile_Route_List_Fragment Cur_Fragment;

    RecyclerView recyclerView;
    RouteRecyclerAdapter adapter;

    List<Route_Data> Cur_Profile_Route_List = new ArrayList<>();
    public void set_Profile_User_Id(String user_id){
        this.Profile_User_Id = user_id;
    }
    public void set_Profile_Main_Fragment(Profile_Route_List_Fragment fragment){
        Cur_Fragment = fragment;
    }

    public Profile_Route_List_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_profile_route_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.profile_route_list_RecyclerView);
        adapter  = new RouteRecyclerAdapter(getContext(),Cur_Profile_Route_List);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), Route_Detail_Activity.class);
                intent.putExtra("Route_Index", Cur_Profile_Route_List.get(position).Route_Index);
                getContext().startActivity(intent);
            }
        }));

        setData();
        setToolbar(view);

    }

    void setData(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("routes").getChildren();

                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    String temp_User_Id = cur_Snapshot.child("User_ID").getValue().toString();

                    if(Profile_User_Id.equals(temp_User_Id)){
                        Cur_Profile_Route_List.add(cur_Snapshot.getValue(Route_Data.class));
                    }
                }

                setRecyclerData();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }
    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("루트");
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));
        ImageButton toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);

        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();

                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(Cur_Fragment);
                fragmentTransaction.commit();
            }
        });
    }

    void setRecyclerData(){
        adapter.setData(Cur_Profile_Route_List);
    }
}
