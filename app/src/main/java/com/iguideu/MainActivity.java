package com.iguideu;

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
import com.iguideu.data.Feed_Data;
import com.iguideu.data.KeywordData;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;
import com.iguideu.data.User;
import com.iguideu.guide_mode.guide_history.Guide_HistoryFragment;
import com.iguideu.guide_mode.guide_home.Guide_HomeFragment;
import com.iguideu.guide_mode.guide_schedule.Guide_ScheduleFragment;
import com.iguideu.main_setting.SettingFragment;
import com.iguideu.tourist_mode.tourist_home.HomeFragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.iguideu.tourist_mode.tourist_tour.TourFragment;
import com.iguideu.tourist_mode.tourist_feed.FeedFragment;
import com.iguideu.main_inbox.InboxFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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

        setDatas();
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
                            fragment = new HomeFragment();
                            setTouristTabIcon(0);
                        }else if(App_Mode == 1){
                            fragment = new Guide_HomeFragment();
                            setGuideTabIcon(0);
                        }
                        break;
                    case 1:
                        if(App_Mode == 0) {
                            fragment = new TourFragment();
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

    void setDatas(){
        setRouteData();
        setFeedData();
        setGuiderData();
        setChattingRoom();
        setRequestData();
       // setAttraction_Route();

        setTouristHistoryData();
        setGuiderHistoryData();
    }

    void setRouteData(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("routes").getChildren();
                List<Route_Data> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();

                    Route_Data route_data = cur_Snapshot.getValue(Route_Data.class);
                    list.add(route_data);

                }
                AppData.Route_Data_List= list;
                sort_Rating_Route();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }
    void setFeedData(){

        List<Feed_Data> list = new ArrayList<>();
        list.add(new Feed_Data("201708120516ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708120612ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708120112ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708120912ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708130512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708170512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201702120512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201708120512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201711120512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201712110512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201601120512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("201705120512ID","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));


        AppData.Feed_Data_List = list;

        /* ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("feeds").getChildren();

                List<Feed_Data> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    Feed_Data feed_data =  cur_Snapshot.getValue(Feed_Data.class);
                    list.add(feed_data);

                }
                AppData.Feed_Data_List= list;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);*/
    }
    void setChattingRoom(){
       /* ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("chattingrooms").getChildren();

                List<ChattingRoom> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    User cur_user =  cur_Snapshot.child("Cur_User").getValue(User.class);
                    if(AppData.getCur_User().User_ID == cur_user.User_ID){
                        list.add(cur_Snapshot.getValue(ChattingRoom.class));
                    }

                }
                AppData.ChattingRoom_Data_List = list;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);*/
    }

    void setGuiderData(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI

                Log.d(AppData.LOG_INDICATOR,"데이터가 바꿔서 setGuiderData(), 의 리스너 호출됨");
                Iterable<DataSnapshot> iterable = dataSnapshot.child("users").getChildren();

                List<User> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    boolean IsGuider =  (boolean)cur_Snapshot.child("User_Guide").getValue();
                    if(IsGuider == true){
                        list.add(cur_Snapshot.getValue(User.class));
                    }
                }
                AppData.Guider_Data_List = list;
                sort_Rating_Guider();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addValueEventListener(listener);
    }
    void setRequestData(){
        List<Request_Data> list = new ArrayList<>();

        AppData.Request_Data_List = list;

        /*ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("requests").getChildren();

                List<Request_Data> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();

                    String Requester_ID =  cur_Snapshot.child("Requester_ID").getValue().toString();

                    if(AppData.getCur_User().User_ID == Requester_ID){
                        list.add( cur_Snapshot.getValue(Request_Data.class));
                    }

                }
                AppData.Request_Data_List = list;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);*/
    }

    List<KeywordData> KeywordData_List = new ArrayList<>();
    void setAttraction_Route(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI

                //키워드량 비교
                Iterable<DataSnapshot> iterable = dataSnapshot.child("keywords").getChildren();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    KeywordData data =  cur_Snapshot.getValue(KeywordData.class);
                    KeywordData_List.add(data);
                }

                Keyword_Descending data_descending = new Keyword_Descending();
                Collections.sort(KeywordData_List,data_descending);

                AppData.Attraction_Keyword_List = KeywordData_List;

                // 루트 가져오기
                iterable = dataSnapshot.child("routes").getChildren();
                int KeywordData_size = KeywordData_List.size();
                KeywordData_size = ((KeywordData_size<=10)? KeywordData_size:10);

                List<String> Attraction_Image_URL_List = new ArrayList<>();

                for(int i = 0; i<KeywordData_size;i++){
                    while (iterable.iterator().hasNext()){
                        DataSnapshot cur_Snapshot = iterable.iterator().next();
                        Route_Data data =  cur_Snapshot.getValue(Route_Data.class);

                        boolean IsSearched = false;
                        for(int j=0; j< data.Route_Locations.size();j++){
                            if(data.Route_Locations.get(j).Route_Title.equals(KeywordData_List.get(i).Keyword)){
                                Attraction_Image_URL_List.add(data.Route_Photo_URLs.get(0));
                                IsSearched = true;
                                break;
                            }
                        }

                        if(IsSearched == true){
                            break;
                        }
                    }
                }

               AppData.Attraction_Image_URL_List = Attraction_Image_URL_List;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addValueEventListener(listener);
    }
    void setTouristHistoryData(){

    }
    void setGuiderHistoryData(){

    }
    void sort_Rating_Route(){
        AppData.Recommend_Route_List = AppData.Route_Data_List;

        if(AppData.Recommend_Route_List == null)
            return;

        Route_Data_Descending descending = new Route_Data_Descending();
        Collections.sort(AppData.Recommend_Route_List,descending);
    }
    void sort_Rating_Guider(){
        AppData.Recommend_Guider_List = AppData.Guider_Data_List;

        if(AppData.Recommend_Guider_List == null)
            return;

        Guider_Descending descending = new Guider_Descending();
        Collections.sort(AppData.Recommend_Guider_List,descending);

    }

}
