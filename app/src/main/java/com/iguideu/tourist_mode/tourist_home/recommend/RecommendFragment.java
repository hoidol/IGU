package com.iguideu.tourist_mode.tourist_home.recommend;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RecommendFragment extends Fragment {

    Context m_Context;
    RecyclerView attraction_RecyclerView;
    RecyclerView route_RecyclerView;
    RecyclerView guide_RecyclerView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public RecommendFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setAttractionRecycler(view);
        setRouteRecycler(view);
        setGuideRecycler(view);
    }

    void setAttractionRecycler(View view){
        attraction_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_attraction_RecyclerView);

        List<Route_Data> list = new ArrayList<>();
        Recommend_AttractionRecyclerAdapter adapter = new Recommend_AttractionRecyclerAdapter(m_Context,list);
        attraction_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, true);
        attraction_RecyclerView.setLayoutManager(layoutManager);
    }

    void setRouteRecycler(View view){
        route_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_route_RecyclerView);

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


        Recommend_RouteRecyclerAdapter adapter = new Recommend_RouteRecyclerAdapter(m_Context,list);
        route_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, false);
        route_RecyclerView.setLayoutManager(layoutManager);
    }

    void setGuideRecycler(View view){
        guide_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_guide_RecyclerView);

        List<User> list = new ArrayList<>();

        Recommend_GuideRecyclerAdapter adapter = new Recommend_GuideRecyclerAdapter(m_Context,list);
        guide_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, true);
        guide_RecyclerView.setLayoutManager(layoutManager);
    }



}