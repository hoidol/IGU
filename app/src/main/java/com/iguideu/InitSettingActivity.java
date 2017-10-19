package com.iguideu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iguideu.Login.LoginActivity;
import com.iguideu.data.AppData;

/**
 * Created by Hoyoung on 2017-07-14.
 */

public class InitSettingActivity extends AppCompatActivity {

    Button Korean,English;

    boolean kr=false,en=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initsetting);
        AppData.setApp_Language("NULL");
        Korean=(Button)findViewById(R.id.init_btn_korean);
        English=(Button)findViewById(R.id.init_btn_English);
    }
    public void InitClick(View v)
    {
        switch (v.getId())
        {
            case R.id.init_btn_korean:
                InitImage();
                Korean.setBackground(getResources().getDrawable(R.drawable.base_btn_click));
                Korean.setTextColor(getResources().getColor(R.color.Color_Base_Point));
                AppData.setApp_Language("KR");
                break;
            case R.id.init_btn_English:
                InitImage();
                English.setBackground(getResources().getDrawable(R.drawable.base_btn_click));
                English.setTextColor(getResources().getColor(R.color.Color_Base_Point));
                AppData.setApp_Language("EN");
                break;

            case R.id.init_btn_check:

                if(AppData.getApp_Language().equals("NULL")){
                }else{
                    Log.d(AppData.LOG_INDICATOR,"현재 언어 : " + AppData.getApp_Language());
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }
                break;

        }
    }

    public void InitImage()
    {
        Korean.setBackground(getResources().getDrawable(R.drawable.base_btn));
        Korean.setTextColor(getResources().getColor(R.color.Color_White));
        English.setBackground(getResources().getDrawable(R.drawable.base_btn));
        English.setTextColor(getResources().getColor(R.color.Color_White));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}