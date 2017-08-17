package com.iguideu.tourist_mode.tourist_home;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.tourist_mode.tourist_home.guide.GuideFragment;
import com.iguideu.tourist_mode.tourist_home.recommend.RecommendFragment;
import com.iguideu.tourist_mode.tourist_home.route.RouteFragment;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class HomeFragment extends Fragment {


    TabLayout tabLayout;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    Context m_Context;

    TextView[] tab_TextView = new TextView[3];
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.home_FragmLayout,new RecommendFragment());
        fragmentTransaction.commit();

        setTabLayout(view);


        Log.d(AppData.LOG_INDICATOR, AppData.getCurTime());
    }

    void setTabLayout(View view){

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        tab_TextView[0] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        tab_TextView[0].setText("추천");
        tab_TextView[0].setTextColor(getResources().getColor(R.color.IGU_Point2_Color));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[0]));

        tab_TextView[1] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        tab_TextView[1].setText("루트");
        tab_TextView[1].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[1]));

        tab_TextView[2] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        tab_TextView[2].setText("가이드");
        tab_TextView[2].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[2]));


   /*   tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.recommend_kr_word)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.route_kr_word)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.guide_kr_word)));*/


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment = null;
                int position = tab.getPosition();
                setTab_TextColor(position);
                switch (position){
                    case 0:
                        fragment = new RecommendFragment();
                        break;
                    case 1:
                        fragment = new RouteFragment();
                        break;
                    case 2:
                        fragment = new GuideFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.home_FragmLayout, fragment);
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


    void setTab_TextColor(int position){
        for(int i =0;i<tab_TextView.length;i++){
            if(position == i){
                tab_TextView[i].setTextColor(getResources().getColor(R.color.IGU_Point2_Color));
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }else{
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }

        }

    }

}
