package com.iguideu.guide_mode.guide_home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.guide_mode.Route_Add_Activity.Guide_Route_Add_Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_HomeFragment extends Fragment{

    RecyclerView recyclerView;
    ImageButton add_route_Btn;

    List<Route_Data> mDataList = new ArrayList<>();
    Guide_HomeRecyclerAdapter adapter;

    public Guide_HomeFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataList.clear();

        add_route_Btn = (ImageButton)view.findViewById(R.id.add_route_Btn);
        add_route_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Guide_Route_Add_Activity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.added_route_RecyclerView);

        for(int i =0;i<AppData.Route_Data_List.size();i++){
            Route_Data data = AppData.Route_Data_List.get(i);
            if(data.User_ID.equals(AppData.getCur_User().User_ID)){
                mDataList.add(data);
            }
        }


        adapter = new Guide_HomeRecyclerAdapter(getContext(),mDataList);

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), Guide_Route_Add_Activity.class);
                intent.putExtra("Route_Index",mDataList.get(position).Route_Index);
                startActivity(intent);
            }
        }));

    }



}
