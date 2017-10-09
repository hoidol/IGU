package com.iguideu.tourist_mode.tourist_tour;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.Route_Detail.Route_Detail_Activity;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class TourFavoriteFragment  extends Fragment {

    List<Route_Data> list;
    RecyclerView recyclerView;

    public TourFavoriteFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_tour_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setRecyclerView(view);
    }

    void setRecyclerView(View view){

        recyclerView = (RecyclerView)view.findViewById(R.id.tour_favorite_RecyclerView);
        list = new ArrayList<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI

                for (int i = 0;i < AppData.getCur_User().User_Favorites_Route_List.size();i++){
                    String route_Index = AppData.getCur_User().User_Favorites_Route_List.get(i);
                    if(!route_Index.equals("-1")) {
                        Route_Data temp = dataSnapshot.child("routes").child(route_Index).getValue(Route_Data.class);
                        Log.d(AppData.LOG_INDICATOR,"현재 루트 인덱스 : "+temp.Route_Index);
                        list.add(temp);
                    }
                }
                Log.d(AppData.LOG_INDICATOR,"현재 즐겨찾기 출력될 수 : " + list.size());
                RouteRecyclerAdapter adapter = new RouteRecyclerAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);



        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), Route_Detail_Activity.class);
                intent.putExtra("Route_Index", list.get(position).Route_Index);
                getContext().startActivity(intent);
            }
        }));
    }
}
