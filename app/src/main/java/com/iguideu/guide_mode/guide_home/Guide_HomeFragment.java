package com.iguideu.guide_mode.guide_home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
<<<<<<< HEAD
=======
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
>>>>>>> cda21f7f889793b3f46c7721008a52e05af383dc
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.iguideu.R;
import com.iguideu.data.Route_Data;
<<<<<<< HEAD
import com.iguideu.guide_mode.Route_Add_Activity.Guide_Route_Add_Activity;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
=======
import com.iguideu.guide_mode.Route_Add_Activity.Giude_Route_Add;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;
>>>>>>> cda21f7f889793b3f46c7721008a52e05af383dc

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_HomeFragment extends Fragment{

    Context m_Context;
    RecyclerView recyclerView;
    ImageButton add_route_Btn;

    List<Route_Data> mDataList = new ArrayList<>();
    Guide_HomeRecyclerAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

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
        add_route_Btn = (ImageButton)view.findViewById(R.id.add_route_Btn);
        add_route_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Guide_Route_Add_Activity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView)view.findViewById(R.id.added_route_RecyclerView);
        SetData();


//        recyclerView.setOnItemMoveListener(mItemMoveListener);

        adapter = new Guide_HomeRecyclerAdapter(m_Context,mDataList);

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    void SetData(){
        List<String> image_URL = new ArrayList<>();

        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");

        mDataList.add(new Route_Data(AppData.getCurTime() +"iD","id","pass","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072"
        ,AppData.getCurTime(),"Title",image_URL,false,"2PM","gd","gd",5,null,5));
        mDataList.add(new Route_Data(AppData.getCurTime() +"iD","id","pass","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072"
                ,AppData.getCurTime(),"Title",image_URL,false,"2PM","gd","gd",5,null,5));
        mDataList.add(new Route_Data(AppData.getCurTime() +"iD","id","pass","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072"
                ,AppData.getCurTime(),"Title",image_URL,false,"2PM","gd","gd",5,null,5));

    }

}
