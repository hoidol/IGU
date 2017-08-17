package com.iguideu;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;

import com.iguideu.data.AppData;
import com.iguideu.guide_mode.guide_history.Guide_HistoryFragment;
import com.iguideu.guide_mode.guide_home.Guide_HomeFragment;
import com.iguideu.guide_mode.guide_schedule.Guide_ScheduleFragment;
import com.iguideu.main_setting.SettingFragment;
import com.iguideu.tourist_mode.tourist_home.HomeFragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.iguideu.tourist_mode.tourist_favorite.FavoriteFragment;
import com.iguideu.tourist_mode.tourist_feed.FeedFragment;
import com.iguideu.main_inbox.InboxFragment;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    int App_Mode;

    int[] activity_tab_TouristMode_icons = {R.mipmap.home,R.mipmap.favorite,R.mipmap.feed,R.mipmap.message,R.mipmap.profile};
    int[] inactivity_tab_TouristMode_icons = {R.mipmap.home_gray,R.mipmap.favorite_gray,R.mipmap.feed_gray,R.mipmap.message_gray,R.mipmap.profile_gray};

    int[] activity_tab_GuideMode_icons = {R.mipmap.home,R.mipmap.schedule,R.mipmap.history,R.mipmap.message,R.mipmap.profile};
    int[] inactivity_tab_GuideMode_icons = {R.mipmap.home_gray,R.mipmap.schedule_gray,R.mipmap.history_gray,R.mipmap.message_gray,R.mipmap.profile_gray};


    String[] TouristMode_Title = {"홈","즐겨찾기","피드","메세지", "프로필"};
    String[] GuideMode_Title = {"홈","스케줄","히스토리","메세지", "프로필"};


    TextView[] tab_TextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        tab_TextView[0].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.home, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[0]));

        tab_TextView[1] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[1].setText(TouristMode_Title[1]);
        tab_TextView[1].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[1].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.favorite_gray, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[1]));

        tab_TextView[2] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[2].setText(TouristMode_Title[2]);
        tab_TextView[2].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[2].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.feed_gray, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[2]));

        tab_TextView[3] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[3].setText(TouristMode_Title[3]);
        tab_TextView[3].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[3].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.message_gray, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[3]));

        tab_TextView[4] = (TextView) LayoutInflater.from(this).inflate(R.layout.main_custom_tab, null);
        tab_TextView[4].setText(TouristMode_Title[4]);
        tab_TextView[4].setTextColor(getResources().getColor(R.color.Color_Gray));
        tab_TextView[4].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.profile_gray, 0, 0);
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[4]));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        if(App_Mode == 0){
                            fragment = new HomeFragment();
                            setTouristTabIcon(0);
                        }else if(App_Mode == 1){
                            fragment = new Guide_HomeFragment();
                            setGuideTabIcon(0);
                        }
                        break;
                    case 1:
                        if(App_Mode == 0) {
                            fragment = new FavoriteFragment();
                            setTouristTabIcon(1);
                        }else if(App_Mode == 1){
                            fragment = new Guide_ScheduleFragment();
                            setGuideTabIcon(1);
                        }
                        break;
                    case 2:
                        if(App_Mode == 0) {
                            fragment = new FeedFragment();
                            setTouristTabIcon(2);
                        }else if(App_Mode == 1){
                            fragment = new Guide_HistoryFragment();
                            setGuideTabIcon(2);
                        }
                        break;
                    case 3:
                        fragment = new InboxFragment();
                        if(App_Mode == 0) {
                            setTouristTabIcon(3);
                        }else if(App_Mode == 1){
                            setGuideTabIcon(3);
                        }
                        break;
                    case 4:
                        fragment = new SettingFragment();
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
}
