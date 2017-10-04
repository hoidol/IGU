package com.iguideu.guide_mode.guide_schedule;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.iguideu.dialog.CheckRouteDialog;
import com.iguideu.dialog.CheckYOrNDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_ScheduleFragment extends Fragment {

    Context m_Context;

    MaterialCalendarView guide_schedule_CalendarView;

    CalendarDay cur_Selected_Date;

    List<Request_Data> MyRequest_Data;

    List<CalendarDay> Requested_Days;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public Guide_ScheduleFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_guide_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guide_schedule_CalendarView = (MaterialCalendarView)view.findViewById(R.id.guide_schedule_CalendarView);
        MyRequest_Data = AppData.Request_Data_List;

        setRequestData(view);
        setCalendar(view);

    }

    void setRequestData(View view){
        int MyRequest_Data_size = MyRequest_Data.size();
        if( MyRequest_Data_size<= 0)
            return;

        Requested_Days = new ArrayList<>();

        for(int i=0; i < MyRequest_Data_size;i++){
            // "yyyy_MM_dd_" 형태

            String[] Dates = MyRequest_Data.get(i).Request_Date.split("_");

            int year = Integer.parseInt(Dates[0]);
            int month = Integer.parseInt(Dates[1]);
            int date = Integer.parseInt(Dates[2]);
            CalendarDay cur_Day = new CalendarDay(year,month,date);
            guide_schedule_CalendarView.setSelectedDate(cur_Day);

            Requested_Days.add(cur_Day);
        }

    }

    void setCalendar(View view){
        guide_schedule_CalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                cur_Selected_Date = date;

                for(int i =0;i<Requested_Days.size();i++){
                    if(Requested_Days.get(i) == cur_Selected_Date){
                        fragmentTransaction = fm.beginTransaction();
                        Guide_ScheduleCheckFragment fragment = new Guide_ScheduleCheckFragment();
                        fragment.SetCur_Date(cur_Selected_Date);
                        fragmentTransaction.replace(R.id.main_Fragment, fragment);
                        fragmentTransaction.commit();
                        break;
                    }
                }

            }
        });
    }
}
