package com.iguideu.guide_mode.guide_history;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_feed.FeedRecyclerAdapter;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_HistoryFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public Guide_HistoryFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.history_RecyclerView);
        List<Route_Data> list = new ArrayList<>();
        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(m_Context,list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);




    }
}
