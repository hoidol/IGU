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
import com.iguideu.Login.LoginActivity;
import com.iguideu.data.AppData;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-14.
 */

public class LogoActivity extends AppCompatActivity {

    ImageView imageView;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        imageView=(ImageView)findViewById(R.id.logo_icon);

        //SharedPreferences AppData에 저장
        SharedPreferences preferences = getSharedPreferences("AppData", MODE_PRIVATE);
        AppData.setPreferences(preferences);



        AppData.SetFirebase();
        AppData.mAuth.signOut();
        if(AppData.getApp_AutoLogin() == false){

        }

        setRouteData();
        setFeedData();
        setGuiderData();

        AppData.setApp_Permission_Storage(false);
        AppData.setApp_Permission_Location(false);

        // LOCATION -> STORAGE -> LOGIN 순서로 검사함
        AppCheckPermission_LOCATION();



    }

    void setRouteData(){
        List<String> image_URL = new ArrayList<>();

        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/7.jpg?alt=media&token=5e5b04f7-c1d7-40f8-a042-3163704ba072");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/2.jpg?alt=media&token=99010d45-e81a-41ca-a913-01e4a4ad4183");
        image_URL.add("https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914");

        List<Route_Data> list = new ArrayList<>();

        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));
        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));
        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));
        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));
        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));
        list.add(new Route_Data("asdf","asdf","호이돌","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"타이틀입니다,",image_URL,false,"test",0,0,null,5));

        AppData.Route_Data_List = list;
    }

    void setFeedData(){

        List<Feed_Data> list = new ArrayList<>();

        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));
        list.add(new Feed_Data("123","ID","Name","https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/users%2Fqkrghdud0%20gmail%20co%2Fprofile_image.jpg?alt=media&token=f3e5d663-f8a2-4db2-9adc-9622469c8828",AppData.getCurTime(),"https://firebasestorage.googleapis.com/v0/b/iguideu-4befb.appspot.com/o/5.jpg?alt=media&token=d988b3e1-6ee7-4e15-9dfb-9bc78966f914","컨텐츠!!"));


        AppData.Feed_Data_List = list;
    }

    void setGuiderData(){
        List<User> list = new ArrayList<>();
        AppData.Guider_Data_List = list;
    }

    void setSort_

    void delay_startActivity(final Intent intent){
        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mAuthStateListener = null;
                startActivity(intent);
                finish();
            }
        };
        myHandler.sendEmptyMessageDelayed(0,500);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
    @Override
    protected void onStop() {
        super.onStop();

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
            AppData.setApp_Permission_Location(true);
            AppCheckPermission_STORAGE();
            Log.d(AppData.LOG_INDICATOR, "Test1");
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
            AppData.setApp_Permission_Storage(true);
            Log.d(AppData.LOG_INDICATOR, "Test2");

            CheckUserLogin();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case AppData.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    AppData.setApp_Permission_Location(true);
                    AppCheckPermission_STORAGE();
                }else{
                    AppData.setApp_Permission_Location(false);
                    AppCheckPermission_STORAGE();
                }
                break;
            }
            case AppData.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    AppData.setApp_Permission_Storage(true);
                    CheckUserLogin();
                }else{
                    AppData.setApp_Permission_Storage(false);
                    CheckUserLogin();
                }
                break;
            }
        }
    }

    void CheckUserLogin(){

        if(mAuthStateListener != null)
            return;
        Log.d(AppData.LOG_INDICATOR, "Test3");
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
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

                    Log.d(AppData.LOG_INDICATOR,"이게 호출되는게 맞는데");
                    delay_startActivity(new Intent(LogoActivity.this,InitSettingActivity.class));
                }
            }
        };
        AppData.mAuth.addAuthStateListener(mAuthStateListener);
    }


}
