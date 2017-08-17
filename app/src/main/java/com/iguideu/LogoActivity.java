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
import com.iguideu.data.User;

import java.util.ArrayList;

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

        AppData.setApp_Permission_Storage(false);
        AppData.setApp_Permission_Location(false);

        // LOCATION -> STORAGE -> LOGIN 순서로 검사함
        AppCheckPermission_LOCATION();



    }

    void delay_startActivity(final Intent intent){
        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

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
