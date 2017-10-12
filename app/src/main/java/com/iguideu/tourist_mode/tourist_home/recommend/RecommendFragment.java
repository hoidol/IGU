package com.iguideu.tourist_mode.tourist_home.recommend;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.Route_Detail.Route_Detail_Activity;
import com.iguideu.data.AppData;
import com.iguideu.data.KeywordData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.main_Profile.ProfileActivity;
import com.iguideu.tourist_mode.tourist_home.route.RouteFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RecommendFragment extends Fragment {

    Context m_Context;
    View view;
    RecyclerView attraction_RecyclerView;
    RecyclerView route_RecyclerView;
    RecyclerView guide_RecyclerView;

    public Recommend_AttractionRecyclerAdapter Recommend_Attraction_adapter;
    public Recommend_RouteRecyclerAdapter Recommend_Route_adapter;
    public Recommend_GuideRecyclerAdapter Recommend_Guide_adapter;

    List<KeywordData> Keyword_List = new ArrayList<>();
    List<Route_Data> Route_List= new ArrayList<>();
    List<User> Guide_List= new ArrayList<>();

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

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
        return inflater.inflate(R.layout.fragment_tourist_home_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //추천
        attraction_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_attraction_RecyclerView);
        Keyword_List = AppData.Attraction_Keyword_List;
        Recommend_Attraction_adapter = new Recommend_AttractionRecyclerAdapter(m_Context,Keyword_List);
        attraction_RecyclerView.setAdapter(Recommend_Attraction_adapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, false);
        attraction_RecyclerView.setLayoutManager(layoutManager1);
        attraction_RecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RouteFragment fragment = new RouteFragment();
                fragment.setFilterKeyword(AppData.Attraction_Keyword_List.get(position).Keyword);
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.home_FragmLayout, fragment);
                fragmentTransaction.commit();
            }
        }));

        // 루트
        route_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_route_RecyclerView);
        Route_List = AppData.Recommend_Route_List;
        Recommend_Route_adapter= new Recommend_RouteRecyclerAdapter(m_Context,Route_List);
        route_RecyclerView.setAdapter(Recommend_Route_adapter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, false);
        route_RecyclerView.setLayoutManager(layoutManager2);

        route_RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), Route_Detail_Activity.class);
                        intent.putExtra("Route_Index", Route_List.get(position).Route_Index);
                        getContext().startActivity(intent);
                    }
                })
        );

        // 가이드
        guide_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_guide_RecyclerView);
        Guide_List = AppData.Recommend_Guider_List;
        Recommend_Guide_adapter = new Recommend_GuideRecyclerAdapter(m_Context,Guide_List);
        guide_RecyclerView.setAdapter(Recommend_Guide_adapter);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, false);
        guide_RecyclerView.setLayoutManager(layoutManager3);

        guide_RecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), ProfileActivity.class);
                        intent.putExtra("User_ID", Guide_List.get(position).User_ID);
                        getContext().startActivity(intent);
                    }
                })
        );
    }

   public  void setAttractionRecyclerData(List<KeywordData> list){
       Keyword_List = list;
    }
    public void setRouteRecyclerData(List<Route_Data> list){
        Route_List = list;
    }
    public void setGuideRecyclerData(List<User> list){
        Guide_List = list;
    }

    @Override
    public void onResume() {
        super.onResume();
        Recommend_Attraction_adapter.setData(Keyword_List);
        Recommend_Route_adapter.setData(Route_List);
        Recommend_Guide_adapter.setData(Guide_List);
    }

}
