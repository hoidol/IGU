package com.iguideu.guide_mode.guide_schedule;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.iguideu.dialog.CheckAcceptDialog;
import com.iguideu.dialog.CheckYOrNDialog;
import com.iguideu.main_Inbox.message.MessageActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-11.
 */

public class Guide_ScheduleCheckFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;

    List<Request_Data> MyRequest_Data_List;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide_schedulecheck, container, false);
    }
    Request_Data data;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.schedulecheck_RecyclerView);
        MyRequest_Data_List = new ArrayList<>();

        setRecyclerData();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(m_Context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        // TODO Handle item click
                        // 다이얼 로그 띄워

                        data = MyRequest_Data_List.get(position);

                        final CheckAcceptDialog check_dialog = new CheckAcceptDialog(getContext());
                        check_dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface dialog) {
                                String[] Dates = data.Request_Date.split("_");
                                Log.d(AppData.LOG_INDICATOR,Dates[0] + "년 "+ Dates[1]+"월 " + Dates[2]+"일 " +data.Tourist_User_Name+"님이\n 가이드를 요청하셨습니다.");
                                check_dialog.setDialog_check_Text(Dates[0] + "년 "+ Dates[1]+"월 " + Dates[2]+"일 " +data.Tourist_User_Name+"님이\n 가이드를 요청하셨습니다.");
                                
                                check_dialog.dialog_Y_Btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AppData.myRef.child("requests").child(data.Request_Index).child("Request_State").setValue(1);
                                        MyRequest_Data_List.get(position).Request_State = 1;
                                        check_dialog.hide();
                                    }
                                });

                                check_dialog.dialog_N_Btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        AppData.myRef.child("requests").child(data.Request_Index).child("Request_State").setValue(2);
                                        MyRequest_Data_List.get(position).Request_State = 2;
                                        check_dialog.hide();
                                    }
                                });

                                check_dialog.message_Btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //메시지 보내기
                                        Intent intent = new Intent(getContext(), MessageActivity.class);
                                        intent.putExtra("Other_User_Id",data.Tourist_User_ID);
                                        getContext().startActivity(intent);
                                        check_dialog.hide();
                                    }
                                });


                            }
                        });
                        if(MyRequest_Data_List.get(position).Request_State == 0){
                            check_dialog.show();
                        }
                    }
                })
        );

    }

    void setRecyclerData(){

        for(int i =0;i<AppData.Request_Data_ForGuide_List.size();i++){
            Request_Data data = AppData.Request_Data_ForGuide_List.get(i);

            String[] request_Dates = data.Request_Date.split("_");
            int request_year = Integer.parseInt(request_Dates[0]);
            int request_month = Integer.parseInt(request_Dates[1]);
            int request_day = Integer.parseInt(request_Dates[2]);

            String[] cur_Dates = AppData.getCurTime().split("_");
            int cur_year = Integer.parseInt(cur_Dates[0]);
            int cur_month = Integer.parseInt(cur_Dates[1]);
            int cur_day = Integer.parseInt(cur_Dates[2]);

            if(request_year<cur_year){
                continue;
            }else{
                if(request_month<cur_month){
                    continue;
                }else{
                    if(request_day<cur_day){
                        continue;
                    }
                }
            }

            MyRequest_Data_List.add(data);
        }


        ScheduleCheckRecyclerAdapter adapter = new ScheduleCheckRecyclerAdapter(m_Context,MyRequest_Data_List);
        recyclerView.setAdapter(adapter);

    }


}
