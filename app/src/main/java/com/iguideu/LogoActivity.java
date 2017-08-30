package com.iguideu;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.Login.LoginActivity;
import com.iguideu.Login.LoginActivity2;
import com.iguideu.aboutSort.Guider_Descending;
import com.iguideu.aboutSort.Route_Data_Descending;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-14.
 */

public class LogoActivity extends AppCompatActivity {

    ImageView imageView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        imageView=(ImageView)findViewById(R.id.logo_icon);

        //SharedPreferences AppData에 저장
        SharedPreferences preferences = getSharedPreferences("AppData", MODE_PRIVATE);
        AppData.setPreferences(preferences);



        AppData.SetFirebase();

        if(AppData.getApp_AutoLogin() == false){
            //AppData.mAuth.signOut();
        }

        setRouteData();
        setFeedData();
        setGuiderData();
        setChattingRoom();
        setRequestData();
        setTouristHistoryData();
        setGuiderHistoryData();
        sort_Rating_Route();
        sort_Rating_Guider();

        AppData.setApp_Mode(0);

        AppCheckPermission_LOCATION();


        // LOCATION -> STORAGE -> LOGIN 순서로 검사함

    }

    void setRouteData(){
        List<String> image_URL = new ArrayList<>();

        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");

        List<Route_Data> list = new ArrayList<>();


        AppData.Route_Data_List = list;

        /* ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("feeds").getChildren();

                List<Route_Data> list = new ArrayList<>();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    Route_Data feed_data =  cur_Snapshot.getValue(Route_Data.class);
                    list.add(feed_data);

                }
                AppData.Route_Data_List= list;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);*/
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

        List<String> Favorites_Route_List = new ArrayList<>();
        List<String> ChattingRooms_List = new ArrayList<>();
        List<String> MyRoute_Route_List = new ArrayList<>();
        List<String> Request_Data_List = new ArrayList<>();
        List<String> Tourist_History_Data_List = new ArrayList<>();
        List<String> Guider_History_Data_List = new ArrayList<>();

        List<User> list = new ArrayList<>();
        list.add(new User("qkrghdud@naver.com","pass","박호영","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"MrPark",4,"자기 소개를 합니다. 쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","고길동","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"MrKim",1,"자기 소개를 합니다. 자기 소개를 합니다자기 소개를 합니다쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","둘리","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"Nicon",5,"자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","또치","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"AAAMM",5,"자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","마이콜","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"QQWW",4,"자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...자기 소개를 합니다. 쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","마징가","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"RRREE",3,"자기 소개를 합니다. 쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        list.add(new User("qkrghdud@naver.com","pass","짱짱맨","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",true,"MMPPII",2,"자기 소개를 합니다. 쩜쩜쩜...",Favorites_Route_List,ChattingRooms_List,MyRoute_Route_List,Request_Data_List,Tourist_History_Data_List,Guider_History_Data_List));
        AppData.Guider_Data_List = list;

      /*  ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);*/
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

    void setAttraction_Route(){

    }
    void setTouristHistoryData(){

    }
    void setGuiderHistoryData(){

    }
    void sort_Rating_Route(){
        AppData.Recommend_Route_List = AppData.Route_Data_List;
        Route_Data_Descending descending = new Route_Data_Descending();
        Collections.sort(AppData.Recommend_Route_List,descending);
    }
    void sort_Rating_Guider(){
        AppData.Recommend_Guider_List = AppData.Guider_Data_List;
        Guider_Descending descending = new Guider_Descending();
        Collections.sort(AppData.Recommend_Guider_List,descending);

    }


    void delay_startActivity(final Intent intent){
        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                AppData.mAuthStateListener = null;
                startActivity(intent);
                finish();
            }
        };
        myHandler.sendEmptyMessageDelayed(0,500);

    }

    void AppCheckPermission_LOCATION(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        AppData.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }else{
            AppCheckPermission_STORAGE();
        }
    }

    void AppCheckPermission_STORAGE(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        AppData.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }else{
            CheckUserLogin();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppData.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppCheckPermission_STORAGE();// 수락 했을 때
                }else{
                    AppCheckPermission_STORAGE();
                }
                break;
            }
            case AppData.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    CheckUserLogin(); // 수락 했을 때
                }else{
                    CheckUserLogin();
                }
                break;
            }
        }
    }

    void CheckUserLogin(){

        if(AppData.mAuthStateListener != null)
            return;

        Log.d(AppData.LOG_INDICATOR, "Test3");
        AppData.mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //Main으로
                    if(AppData.getApp_AutoLogin() == true){
                        User cur_User = new User(user.getEmail(), null,user.getDisplayName(),user.getPhotoUrl().toString());
                        AppData.setCur_User(cur_User);

                        delay_startActivity(new Intent(LogoActivity.this,MainActivity.class));
                    }else{
                        if(AppData.getApp_Language().equals("NULL")){
                            delay_startActivity(new Intent(LogoActivity.this,InitSettingActivity.class));
                        }else{
                            delay_startActivity(new Intent(LogoActivity.this, LoginActivity.class));
                        }
                    }
                }else{
                    delay_startActivity(new Intent(LogoActivity.this,InitSettingActivity.class));
                }
            }
        };
        AppData.mAuth.addAuthStateListener(AppData.mAuthStateListener);
    }


}




