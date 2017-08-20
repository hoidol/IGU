package com.iguideu.tourist_mode.tourist_favorite;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class FavoriteFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setRecyclerView(view);
    }

    void setRecyclerView(View view){

        recyclerView = (RecyclerView)view.findViewById(R.id.favorite_RecyclerView);
        List<Route_Data> list = new ArrayList<>();


<<<<<<< HEAD
=======
        List<String> Route_Photo_URL2_List = new ArrayList<>();
        Route_Photo_URL2_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        Route_Photo_URL2_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/1.jpg?alt=media&token=676b9807-fbb3-4ffb-b35b-05f9790517d3");


        List<String> Route_Photo_URL3_List = new ArrayList<>();
        Route_Photo_URL3_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        Route_Photo_URL3_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/1.jpg?alt=media&token=676b9807-fbb3-4ffb-b35b-05f9790517d3");




>>>>>>> a6bfdb8e4102142ae8f0f977d175805234c36cab

        RouteRecyclerAdapter adapter = new RouteRecyclerAdapter(m_Context,list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
    }
}
