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
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_feed.FeedRecyclerAdapter;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
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

            if(data.Request_State == 0){
                continue;
            }
            String[] request_Dates = data.Request_Date.split("_");
            int request_year = Integer.parseInt(request_Dates[0]);
            int request_month = Integer.parseInt(request_Dates[1]);
            int request_day = Integer.parseInt(request_Dates[2]);

            String[] cur_Dates = AppData.getCurTime().split("_");
            int cur_year = Integer.parseInt(cur_Dates[0]);
            int cur_month = Integer.parseInt(cur_Dates[1]);
            int cur_day = Integer.parseInt(cur_Dates[2]);

            if(request_year>cur_year){
                continue;
            }else{
                if(request_month>cur_month){
                    continue;
                }else{
                    if(request_day>cur_day){
                        continue;
                    }
                }
            }
            list.add(data);
        }

        HistoryRecyclerAdapter adapter = new HistoryRecyclerAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
