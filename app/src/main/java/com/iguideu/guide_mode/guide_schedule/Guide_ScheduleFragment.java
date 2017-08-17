package com.iguideu.guide_mode.guide_schedule;

import android.app.Fragment;
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
import com.iguideu.dialog.CheckRouteDialog;
import com.iguideu.dialog.CheckYOrNDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by Hoyoung on 2017-08-06.
 */

public class Guide_ScheduleFragment extends Fragment {

    Context m_Context;

    MaterialCalendarView guide_schedule_CalendarView;
    CalendarDay cur_Selected_Date;

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

        setCalendar(view);

    }

    void setCalendar(View view){
        guide_schedule_CalendarView = (MaterialCalendarView)view.findViewById(R.id.guide_schedule_CalendarView);
        guide_schedule_CalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                cur_Selected_Date = date;
                final CheckYOrNDialog check_dialog = new CheckYOrNDialog(getContext());
                check_dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        int year = cur_Selected_Date.getYear();
                        int month = cur_Selected_Date.getMonth() +1;
                        int day = cur_Selected_Date.getDay();

                        check_dialog.setDialog_check_Text(year + "년 " + month+"월 " + day + "일 " + getString(R.string.dialog_check_schedule_comment_kr));


                        check_dialog.dialog_Y_Btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 비활성화해야함
                                check_dialog.hide();
                            }
                        });
                        check_dialog.dialog_N_Btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                check_dialog.hide();
                            }
                        });
                    }
                });

                check_dialog.show();
            }
        });
    }
}
