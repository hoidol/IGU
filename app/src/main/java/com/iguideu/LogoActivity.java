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
import com.iguideu.aboutSort.Keyword_Descending;
import com.iguideu.aboutSort.Route_Data_Descending;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.KeywordData;
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

;
        AppData.SetFirebase();

        AppData.mAuth.signOut();
        if(AppData.getApp_AutoLogin() == false){
            //
        }



        AppData.setApp_Mode(0);

        AppCheckPermission_LOCATION();


        // LOCATION -> STORAGE -> LOGIN 순서로 검사함

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

        AppData.mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //Main으로
                    if(AppData.getApp_AutoLogin() == true){
                        ValueEventListener postListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Get Post object and use the values to update the UI
                                User cur_user = dataSnapshot.child("users").child(AppData.StringReplace(user.getEmail())).getValue(User.class);
                                AppData.setCur_User(cur_user);

                                delay_startActivity(new Intent(LogoActivity.this,MainActivity.class));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Getting Post failed, log a message
                                // ...
                            }
                        };

                        AppData.myRef.addListenerForSingleValueEvent(postListener);
                    }else{
                        if(AppData.getApp_Language().equals("NULL")){
                            Log.d(AppData.LOG_INDICATOR,"InitSettingActivity.class !! 이거 호출됨!!");
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




