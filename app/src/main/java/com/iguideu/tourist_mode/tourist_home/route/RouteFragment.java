package com.iguideu.tourist_mode.tourist_home.route;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RouteFragment extends Fragment {

    Context m_Context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public RouteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_route, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner(view);
        setRecycler(view);
    }

    void setSpinner(View view){
        Spinner spinner = (Spinner)view.findViewById(R.id.route_Spinner);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(),R.array.route_spinner_item,R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    void setRecycler(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.route_RecyclerView);

        List<Route_Data> list = new ArrayList<>();

        List<String> Route_Photo_URL1_List = new ArrayList<>();
        Route_Photo_URL1_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        Route_Photo_URL1_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/1.jpg?alt=media&token=676b9807-fbb3-4ffb-b35b-05f9790517d3");

        List<String> Route_Photo_URL2_List = new ArrayList<>();
        Route_Photo_URL2_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        Route_Photo_URL2_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/1.jpg?alt=media&token=676b9807-fbb3-4ffb-b35b-05f9790517d3");


        List<String> Route_Photo_URL3_List = new ArrayList<>();
        Route_Photo_URL3_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        Route_Photo_URL3_List.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/1.jpg?alt=media&token=676b9807-fbb3-4ffb-b35b-05f9790517d3");



        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL1_List,"최고의 여행지 등촌역","솰라쏼라","08 PM ~ 12PM",3,2,5,true));
        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL2_List,"홍대 맛집 Tour","솰라쏼라","08 PM ~ 12PM",3,2,4,true));
        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL3_List,"Do you want to be happy?","솰라쏼라","08 PM ~ 12PM",3,2,2,true));
        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL1_List,"최고의 여행지 등촌역","솰라쏼라","08 PM ~ 12PM",3,2,0,true));
        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL2_List,"홍대 맛집 Tour","솰라쏼라","08 PM ~ 12PM",3,2,1,true));
        list.add(new Route_Data("qkrghdud0"+ AppData.getCurTime(),"qkrghdud0","박호영",null, AppData.getCurTime(),null,null,Route_Photo_URL3_List,"Do you want to be happy?","솰라쏼라","08 PM ~ 12PM",3,2,3,true));

        RouteRecyclerAdapter adapter = new RouteRecyclerAdapter(m_Context,list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

    }
}
