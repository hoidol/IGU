package com.iguideu.tourist_mode.tourist_home.recommend;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
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
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.tourist_mode.tourist_home.route.RouteFragment;

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

        //setAttractionRecycler(view);
        //setRouteRecycler(view);
        //setGuideRecycler(view);
    }

    void setAttractionRecycler(View view){
        attraction_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_attraction_RecyclerView);

        Recommend_AttractionRecyclerAdapter adapter = new Recommend_AttractionRecyclerAdapter(m_Context);
        attraction_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, true);
        attraction_RecyclerView.setLayoutManager(layoutManager);


        attraction_RecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),"position : " + position +"번째를 누름",Toast.LENGTH_SHORT).show();

                RouteFragment fragment = new RouteFragment();
                fragment.setFilterKeyword(AppData.Attraction_Keyword_List.get(position).Keyword);
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.home_FragmLayout, fragment);
                fragmentTransaction.commit();
            }
        }));
    }

    void setRouteRecycler(View view){
        route_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_route_RecyclerView);

        List<Route_Data> list = new ArrayList<>();


        Recommend_RouteRecyclerAdapter adapter = new Recommend_RouteRecyclerAdapter(m_Context);
        route_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, false);
        route_RecyclerView.setLayoutManager(layoutManager);
    }

    void setGuideRecycler(View view){
        guide_RecyclerView = (RecyclerView)view.findViewById(R.id.recommend_guide_RecyclerView);

        List<User> list = new ArrayList<>();

        Recommend_GuideRecyclerAdapter adapter = new Recommend_GuideRecyclerAdapter(m_Context);
        guide_RecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.HORIZONTAL, true);
        guide_RecyclerView.setLayoutManager(layoutManager);
    }



}
