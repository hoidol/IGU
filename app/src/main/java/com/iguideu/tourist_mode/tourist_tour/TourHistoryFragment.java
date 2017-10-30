package com.iguideu.tourist_mode.tourist_tour;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.guide_mode.guide_history.HistoryRecyclerAdapter;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class TourHistoryFragment  extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;
    TourHistoryRecyclerAdapter adapter;
    List<Request_Data> Tour_Request_Data_List;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public TourHistoryFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_tour_history, container, false);
    }



    View view;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        Tour_Request_Data_List = new ArrayList<>();
        setRecycler(this.view);

    }

    void setRecycler(View view){
        recyclerView=(RecyclerView)view.findViewById(R.id.tour_history_RecyclerView);
        //조건
        for(int i =0;i<AppData.Request_Data_ForTourist_List.size();i++){
            Request_Data data = AppData.Request_Data_ForTourist_List.get(i);

            String[] request_Dates = data.Request_Date.split("_");
            int request_year = Integer.parseInt(request_Dates[0]);
            int request_month = Integer.parseInt(request_Dates[1]);
            int request_day = Integer.parseInt(request_Dates[2]);

            String[] cur_Dates = AppData.getCurTime().split("_");
            int cur_year = Integer.parseInt(cur_Dates[0]);
            int cur_month = Integer.parseInt(cur_Dates[1]);
            int cur_day = Integer.parseInt(cur_Dates[2]);

            if(data.Request_State == 0){ //시간이 시간 대기는 삭제
                if(request_year<cur_year){
                    AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                    continue;
                }else{
                    if(request_month<cur_month){
                        AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                        continue;
                    }else{
                        if(request_day<cur_day){
                            AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                            continue;
                        }
                    }
                }
            }

            if(data.Request_State == 1){
                data.Request_State = 3;
                AppData.myRef.child("requests").child(data.Request_Index).child("Request_State").setValue(3);
            }

            if(data.Request_State == 2){ // 시간 지난 거절은 삭제
                if(request_year<cur_year){
                    AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                    continue;
                }else{
                    if(request_month<cur_month){
                        AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                        continue;
                    }else{
                        if(request_day<cur_day){
                            AppData.myRef.child("requests").child(data.Request_Index).removeValue();
                            continue;
                        }
                    }
                }
            }
            Tour_Request_Data_List.add(data);
        }

        adapter = new TourHistoryRecyclerAdapter(getContext(),Tour_Request_Data_List);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
    }
}
