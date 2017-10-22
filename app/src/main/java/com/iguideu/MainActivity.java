package com.iguideu;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.aboutSort.Guider_Descending;
import com.iguideu.aboutSort.Keyword_Descending;
import com.iguideu.aboutSort.Route_Data_Descending;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.KeywordData;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.guide_mode.guide_history.Guide_HistoryFragment;
import com.iguideu.guide_mode.guide_home.Guide_HomeFragment;
import com.iguideu.guide_mode.guide_schedule.Guide_ScheduleCheckFragment;
import com.iguideu.main_Setting.SettingFragment;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.iguideu.tourist_mode.HomeFragment;
import com.iguideu.tourist_mode.tourist_home.recommend.RecommendFragment;
import com.iguideu.tourist_mode.tourist_tour.TourFragment;
import com.iguideu.tourist_mode.tourist_feed.FeedFragment;
import com.iguideu.main_Inbox.InboxFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecommendFragment.ListItemSelectedListener{
    TabLayout tabLayout;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    int App_Mode;

    int[] activity_tab_TouristMode_icons = {R.mipmap.home_icon,R.mipmap.tour_icon,R.mipmap.feed_icon,R.mipmap.chatting_icon,R.mipmap.profile_icon};
    int[] inactivity_tab_TouristMode_icons = {R.mipmap.home_no_icon,R.mipmap.tour_no_icon,R.mipmap.feed_no_icon,R.mipmap.chatting_no_icon,R.mipmap.profile_no_icon};

    int[] activity_tab_GuideMode_icons = {R.mipmap.home_icon,R.mipmap.schedule_icon,R.mipmap.history_icon,R.mipmap.chatting_icon,R.mipmap.profile_icon};
    int[] inactivity_tab_GuideMode_icons = {R.mipmap.home_no_icon,R.mipmap.schedule_no_icon,R.mipmap.history_no_icon,R.mipmap.chatting_no_icon,R.mipmap.profile_no_icon};


    String[] TouristMode_Title = {"홈","여행","피드","메세지", "프로필"};
    String[] GuideMode_Title = {"홈","스케줄","히스토리","메세지", "프로필"};


    TextView[] tab_TextView;


    HomeFragment homeFragment;
    Guide_HomeFragment guide_homeFragment;
    TourFragment tourFragment;
    Guide_ScheduleCheckFragment guide_scheduleCheckFragment;
    FeedFragment feedFragment;
    Guide_HistoryFragment guide_historyFragment;
    InboxFragment inboxFragment;
    SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        homeFragment.setHomeFragment(homeFragment);
        guide_homeFragment = new Guide_HomeFragment();
        tourFragment = new TourFragment();
        guide_scheduleCheckFragment = new Guide_ScheduleCheckFragment();
        feedFragment = new FeedFragment();
        guide_historyFragment = new Guide_HistoryFragment();
        inboxFragment = new InboxFragment();
        settingFragment = new SettingFragment();

        setDatas();
        App_Mode = AppData.getApp_Mode();
        setTabLayout();

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.main_Fragment,new HomeFragment());
        fragmentTransaction.commit();
    }

    void setTabLayout(){

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(Color.WHITE);
        tab_TextView = new TextView[5];

        tab_TextView[0] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[0].setText(TouristMode_Title[0]);
        tab_TextView[0].setTextColor(getResources().getColor(R.color.Color_Black));
        tab_TextView[0].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home_icon, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[0]));

        tab_TextView[1] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[1].setText(TouristMode_Title[1]);
        tab_TextView[1].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[1].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.tour_no_icon, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[1]));

        tab_TextView[2] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[2].setText(TouristMode_Title[2]);
        tab_TextView[2].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[2].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.feed_no_icon, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[2]));

        tab_TextView[3] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[3].setText(TouristMode_Title[3]);
        tab_TextView[3].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[3].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.chatting_no_icon, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[3]));

        tab_TextView[4] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[4].setText(TouristMode_Title[4]);
        tab_TextView[4].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[4].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.profile_no_icon, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[4]));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        if(App_Mode == 0){
                            fragment = homeFragment;
                            setTouristTabIcon(0);
                        }else if(App_Mode == 1){
                            fragment = guide_homeFragment;
                            setGuideTabIcon(0);
                        }
                        break;
                    case 1:
                        if(App_Mode == 0) {
                            fragment = tourFragment;
                            setTouristTabIcon(1);
                        }else if(App_Mode == 1){
                            fragment = guide_scheduleCheckFragment;
                            setGuideTabIcon(1);
                        }
                        break;
                    case 2:
                        if(App_Mode == 0) {
                            fragment = feedFragment;
                            setTouristTabIcon(2);
                        }else if(App_Mode == 1){
                            fragment = guide_historyFragment;
                            setGuideTabIcon(2);
                        }
                        break;
                    case 3:
                        fragment = inboxFragment;
                        if(App_Mode == 0) {
                            setTouristTabIcon(3);
                        }else if(App_Mode == 1){
                            setGuideTabIcon(3);
                        }
                        break;
                    case 4:
                        fragment = settingFragment;
                        if(App_Mode == 0) {
                            setTouristTabIcon(4);
                        }else if(App_Mode == 1){
                            setGuideTabIcon(4);
                        }
                        break;
                }

                if (fragment != null) {
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.main_Fragment, fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void setTouristTabIcon(int position){
        for(int i= 0; i< 5;i++){
            if(position == i){
                tab_TextView[i].setCompoundDrawablesWithIntrinsicBounds(0, activity_tab_TouristMode_icons[i], 0, 0);
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Black));
                tab_TextView[i].setText(TouristMode_Title[i]);
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }else {
                tab_TextView[i].setCompoundDrawablesWithIntrinsicBounds(0, inactivity_tab_TouristMode_icons[i], 0, 0);
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Gray));
                tab_TextView[i].setText(TouristMode_Title[i]);
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }
        }
    }

    void setGuideTabIcon(int position){
        for(int i= 0; i< 5;i++){
            if(position == i){
                tab_TextView[i].setCompoundDrawablesWithIntrinsicBounds(0, activity_tab_GuideMode_icons[i], 0, 0);
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Black));
                tab_TextView[i].setText(GuideMode_Title[i]);
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }else {
                tab_TextView[i].setCompoundDrawablesWithIntrinsicBounds(0, inactivity_tab_GuideMode_icons[i], 0, 0);
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Gray));
                tab_TextView[i].setText(GuideMode_Title[i]);
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }
        }
    }



    public void changeMode(int mode){
        App_Mode = mode;
        if(App_Mode == 0) {
            setTouristTabIcon(4);
        }else if(App_Mode == 1){
            setGuideTabIcon(4);
        }
    }


    void setDatas(){
        setChattingRoom();
        setRequestData();
    }

    void setChattingRoom(){

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("chattingrooms").getChildren();
                User Cur_User = AppData.getCur_User();
                ArrayList<ChattingRoom> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    User Sended_User =  cur_Snapshot.child("Sended_User").getValue(User.class);
                    User Received_User =  cur_Snapshot.child("Received_User").getValue(User.class);

                    if(Cur_User.User_ID.equals(Sended_User.User_ID) || Cur_User.User_ID.equals(Received_User.User_ID)){
                        list.add(cur_Snapshot.getValue(ChattingRoom.class));
                    }
                }
                AppData.ChattingRoom_Data_List = list;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addValueEventListener(listener);
    }
    void setRequestData(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("requests").getChildren();

                List<Request_Data> list_ForGuide = new ArrayList<>();
                List<Request_Data> list_ForTourist = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    Request_Data cur_request_data =  cur_Snapshot.getValue(Request_Data.class);

                    if(AppData.getCur_User().User_ID.equals(cur_request_data.Guider_User_ID)){
                        list_ForGuide.add(cur_request_data);
                    }

                    if(AppData.getCur_User().User_ID.equals(cur_request_data.Tourist_User_ID)){
                        list_ForTourist.add(cur_request_data);
                    }
                }
                AppData.Request_Data_ForGuide_List = list_ForGuide;
                AppData.Request_Data_ForTourist_List = list_ForTourist;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addValueEventListener(listener);
    }

    public void onListItemSelected(int index) {
        homeFragment.setTabAt(index);
    }

}
