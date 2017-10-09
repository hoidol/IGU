package com.iguideu.main_Setting;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;

/**
 * Created by Hoyoung on 2017-08-10.
 */

public class SettingEnvirFragment extends Fragment {

    TextView toolbar_title_TextView;
    ImageButton toolbar_back_ImagmeView;

    Switch setting_alarm_Switch;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_setting_envir, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fm = getFragmentManager();
        setToolbar(view);
        setSwitch(view);
    }

    void setSwitch(View view){
        setting_alarm_Switch = (Switch)view.findViewById(R.id.setting_alarm_Switch);
        setting_alarm_Switch.setChecked(AppData.getApp_Alarm());
        setting_alarm_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Snackbar.make(getView(),"활성화",Snackbar.LENGTH_SHORT).setAction("",null).show();
                    AppData.setApp_Alarm(true);
                }else{
                    Snackbar.make(getView(),"비활성화",Snackbar.LENGTH_SHORT).setAction("",null).show();
                    AppData.setApp_Alarm(false);
                }
            }
        });

    }

    void setToolbar(View view){
        toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("환경설정");
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
}
