package com.iguideu.route_detail;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iguideu.R;
import com.iguideu.data.Route_Data;
import com.iguideu.dialog.CheckRouteDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by Hoyoung on 2017-07-30.
 */

public class Route_Detail_Fragment_2 extends Fragment{
    Context m_Context;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    Fragment Cur_Fragment;
    Route_Data Cur_Route_Data;

    CalendarDay cur_Selected_Date;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public Route_Detail_Fragment_2() {
        // Required empty public constructor
    }

    public void setFragmentData(Route_Detail_Fragment_2 Cur_Fragment, Route_Data route_data){
        this.Cur_Fragment = Cur_Fragment;
        Cur_Route_Data = route_data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_route_detail_2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button)view.findViewById(R.id.route_detail_Btn_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cur_Selected_Date != null){
                    setNextFragment();
                }

            }
        });

        setToolbar(view);
        setCalendarView(view);

    }
    void setCalendarView(View view){
        MaterialCalendarView calendarView = (MaterialCalendarView)view.findViewById(R.id.route_detail_CalendarView);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                cur_Selected_Date = date;

                Toast.makeText(getContext(),"CalendarDay 클래스 toString() : " + cur_Selected_Date.toString(), Toast.LENGTH_SHORT).show();

                final CheckRouteDialog check_dialog = new CheckRouteDialog(getContext());
                check_dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        int year = cur_Selected_Date.getYear();
                        int month = cur_Selected_Date.getMonth() + 1;
                        int day = cur_Selected_Date.getDay();

                        check_dialog.setDialog_check_Text(year + "년 " + month+"월 " + day + "일 " + getString(R.string.dialog_check_comment_kr));
                        check_dialog.setDialog_check_Btn_Text(getString(R.string.dialog_check_kr));

                        check_dialog.dialog_check_Btn.setOnClickListener(new View.OnClickListener() {
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

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("날짜 설정하기");

        ImageButton toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);

        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(Cur_Fragment);
                fragmentTransaction.commit();
            }
        });
    }
    void setNextFragment(){

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Route_Detail_Fragment_3 fragment = new Route_Detail_Fragment_3();
        fragmentTransaction.replace(R.id.route_detail_FrameLayout,fragment);
        fragmentTransaction.commit();

    }
}
