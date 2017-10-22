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
import com.iguideu.aboutSort.History_Descending;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_feed.FeedRecyclerAdapter;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_HistoryFragment extends Fragment {

    RecyclerView recyclerView;

    List<Request_Data> list;

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
        list = new ArrayList<>();

        for( int i =0; i<AppData.Request_Data_ForGuide_List.size();i++){
            Request_Data data = AppData.Request_Data_ForGuide_List.get(i);

            if(data.Request_State == 0||data.Request_State == 2){
                continue;
            }
            list.add(data);
        }
        History_Descending descending = new History_Descending();
        Collections.sort(list,descending);

        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
