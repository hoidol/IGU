package com.iguideu.main_setting;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.iguideu.MainActivity;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.route_detail.Route_Detail_Fragment_2;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class SettingFragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    Button toGuide_Btn;
    Button Language_Btn;
    Button Envir_Btn;
    Button Login_Btn;


    MainActivity mainActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mainActivity = (MainActivity)activity;


    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_setting, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toGuide_Btn =(Button)view.findViewById(R.id.setting_toGuide);
        setTouchEvent(toGuide_Btn);
        fm = getFragmentManager();

        toGuide_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        Language_Btn = (Button)view.findViewById(R.id.setting_Language);
        Language_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Language_Btn);

        Envir_Btn = (Button)view.findViewById(R.id.setting_Envir);
        Envir_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Envir_Btn);

        Login_Btn = (Button)view.findViewById(R.id.setting_Login);
        Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitClick(v);
            }
        });
        setTouchEvent(Login_Btn);

    }

    public void InitClick(View v)
    {
        switch (v.getId())
        {
            case R.id.setting_toGuide:

                int mode = AppData.getApp_Mode();

                switch (mode){
                    case 0:
                        // 0 -> 1로 바꾸기
                        AppData.setApp_Mode(1);
                        mainActivity.changeMode(1);
                        toGuide_Btn.setText(getString(R.string.setting_change_tourist_kr));

                        break;
                    case 1:
                        // 1 -> 0로 바꾸기
                        AppData.setApp_Mode(0);
                        mainActivity.changeMode(0);
                        toGuide_Btn.setText(getString(R.string.setting_change_guide_kr));
                        break;
                }
                break;
            case R.id.setting_Language:

                fragmentTransaction = fm.beginTransaction();
                SettingLanguageFragment fragment = new SettingLanguageFragment();
                fragmentTransaction.replace(R.id.main_Fragment,fragment);
                fragmentTransaction.commit();
                break;

            case R.id.setting_Envir:
                fragmentTransaction = fm.beginTransaction();
                SettingEnvirFragment fragment1 = new SettingEnvirFragment();
                fragmentTransaction.replace(R.id.main_Fragment,fragment1);
                fragmentTransaction.commit();
                break;
            case R.id.setting_Login:
                break;

        }
    }


    void setTouchEvent(final Button btn){
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ||  event.getAction() == MotionEvent.ACTION_BUTTON_PRESS || event.getAction() == MotionEvent.ACTION_POINTER_DOWN) {

                    btn.setBackground(getResources().getDrawable(R.color.IGU_Point_Color));
                    btn.setTextColor(getResources().getColor(R.color.Color_White));
                    btn.setElevation(0);

                } else if(event.getAction() == MotionEvent.ACTION_HOVER_EXIT || event.getAction() == MotionEvent.ACTION_UP){
                    btn.setBackground(getResources().getDrawable(R.color.Color_White));
                    btn.setTextColor(getResources().getColor(R.color.Color_Setting_Text));
                    btn.setElevation(0);
                }
                return false;
            }
        });
    }

}
