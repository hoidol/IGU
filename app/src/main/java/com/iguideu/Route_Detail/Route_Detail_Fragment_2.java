package com.iguideu.Route_Detail;

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
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
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

    String cur_Selected_Date;

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
                    //요청 만들기

                    String Request_Index = AppData.getCurTime() + AppData.StringReplace(AppData.getCur_User().User_ID);

                    Request_Data request_data = new Request_Data(Request_Index, Cur_Route_Data.Route_Index ,AppData.getCurTime(),Cur_Route_Data.Route_Main_Title,Cur_Route_Data.User_ID,Cur_Route_Data.User_Profile_URL,AppData.getCur_User().User_ID, AppData.getCur_User().User_Profile_URL,AppData.getCur_User().User_Name,cur_Selected_Date.toString(),0);
                    AppData.myRef.child("requests").child(Request_Index).setValue(request_data);

                    setNextFragment();
                }else{
                    // 날짜를 선택해주세요.
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
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull final CalendarDay date, boolean selected) {
                final int year = date.getYear();
                final int month = date.getMonth() +1;
                final int day = date.getDay();

                cur_Selected_Date = year+"_"+month+"_"+day; // "yyyy_MM_dd_"

                Toast.makeText(getContext(),date.toString(),Toast.LENGTH_LONG).show();
                final CheckRouteDialog check_dialog = new CheckRouteDialog(getContext());
                check_dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

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
