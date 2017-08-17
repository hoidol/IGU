package com.iguideu.main_setting;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.MainActivity;
import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-08-10.
 */

public class SettingLanguageFragment extends Fragment {

    TextView toolbar_title_TextView;
    ImageButton toolbar_back_ImagmeView;

    Button setting_KR_Btn;
    Button setting_EN_Btn;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_setting_language, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fm = getFragmentManager();
        setToolbar(view);
        setLanguageListener(view);

    }

    void setToolbar(View view){
        toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("언어");
        toolbar_title_TextView.setTextColor(getResources().getColor(R.color.Color_All_Primary_Text));
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.Setting_Title));

        toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);
        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fm.beginTransaction();
                SettingFragment fragment = new SettingFragment();
                fragmentTransaction.replace(R.id.main_Fragment,fragment);
                fragmentTransaction.commit();
            }
        });
    }

    void setLanguageListener(View view){
        setting_KR_Btn = (Button)view.findViewById(R.id.setting_KR_Btn);
        setTouchEvent(setting_KR_Btn);
        setting_EN_Btn = (Button)view.findViewById(R.id.setting_EN_Btn);
        setTouchEvent(setting_EN_Btn);

        setting_KR_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(),"한글버전",Snackbar.LENGTH_SHORT).setAction("",null).show();
            }
        });

        setting_EN_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(),"영어버전",Snackbar.LENGTH_SHORT).setAction("",null).show();
            }
        });

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
