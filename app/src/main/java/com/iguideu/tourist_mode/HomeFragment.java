package com.iguideu.tourist_mode;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.search.SearchActivity;
import com.iguideu.tourist_mode.tourist_home.guide.GuideFragment;
import com.iguideu.tourist_mode.tourist_home.recommend.RecommendFragment;
import com.iguideu.tourist_mode.tourist_home.route.RouteFragment;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class HomeFragment extends Fragment implements RecommendFragment.ListItemSelectedListener{


    TabLayout tabLayout;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    TextView[] tab_TextView = new TextView[3];

    AppBarLayout home_AppBarLayout;
    RelativeLayout init_route_search_Container;
    RelativeLayout search_keyword_Container;

    TextView search_keyword_TextView;


    public HomeFragment Cur_Fragment;
    public RecommendFragment recommendFragment;
    public RouteFragment routeFragment;
    public GuideFragment guideFragment;

    String[] Home_Taps ={"추천", "루트","가이드"};
    String[] Home_Taps_en ={"Rec", "Route","Guide"};
    public void setHomeFragment(HomeFragment homeFragment){
        Cur_Fragment = homeFragment;
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
        search_keyword_TextView = (TextView)view.findViewById(R.id.search_keyword_TextView);
        recommendFragment = new RecommendFragment();
        routeFragment = new RouteFragment();
        guideFragment = new GuideFragment();

        recommendFragment.setHomeFragment(this);
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.home_FragmLayout,recommendFragment);
        fragmentTransaction.commit();

        setTabLayout(view);
        setSearchContainer(view);
    }


    void setTabLayout(View view){

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        tab_TextView[0] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        if(AppData.getApp_Language().equals("en")){
            tab_TextView[0].setText(Home_Taps_en[0]);
        }else{
            tab_TextView[0].setText(Home_Taps[0]);
        }
        tab_TextView[0].setTextColor(getResources().getColor(R.color.IGU_Point_Color));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[0]));

        tab_TextView[1] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        if(AppData.getApp_Language().equals("en")){
            tab_TextView[1].setText(Home_Taps_en[1]);
        }else{
            tab_TextView[1].setText(Home_Taps[1]);
        }
        tab_TextView[1].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[1]));

        tab_TextView[2] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        if(AppData.getApp_Language().equals("en")){
            tab_TextView[2].setText(Home_Taps_en[2]);
        }else{
            tab_TextView[2].setText(Home_Taps[2]);
        }
        tab_TextView[2].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
        tabLayout.addTab(tabLayout.newTab().setCustomView(tab_TextView[2]));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment fragment = null;
                int position = tab.getPosition();
                setTab_TextColor(position);
                switch (position){
                    case 0:
                        fragment = recommendFragment;
                        break;
                    case 1:
                        fragment = routeFragment;
                        break;
                    case 2:
                        fragment = guideFragment;
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

    void setSearchContainer(final View view){

        home_AppBarLayout = (AppBarLayout) view.findViewById(R.id.home_AppBarLayout);
        init_route_search_Container = (RelativeLayout)view.findViewById(R.id.init_route_search_Container);
        search_keyword_Container = (RelativeLayout) view.findViewById(R.id.search_keyword_Container);


        search_keyword_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("search_index", 0);
                startActivityForResult(intent, AppData.REQUEST_CODE_KEYWORD);
            }
        });

    }

    void setTab_TextColor(int position){
        for(int i =0;i<tab_TextView.length;i++){
            if(position == i){
                tab_TextView[i].setTextColor(getResources().getColor(R.color.IGU_Point_Color));
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }else{
                tab_TextView[i].setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
                tabLayout.getTabAt(i).setCustomView(tab_TextView[i]);
            }

        }

    }


    @Override
    public void onResume() {
        super.onResume();
        String keyword = AppData.KeywordData;
        if(!keyword.equals("")){
            search_keyword_TextView.setText(keyword);
            show_Searched_Keyword_Route(keyword);
        }else{
            search_keyword_TextView.setText(getResources().getString(R.string.toolbar_inf_kr_word));
        }
    }


    void show_Searched_Keyword_Route(String keyword){
        tabLayout.getTabAt(1).select();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.home_FragmLayout, routeFragment);
        fragmentTransaction.commit();
    }

    void show_Searched_date_Route(String date){
        tabLayout.getTabAt(1).select();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.home_FragmLayout, routeFragment);
        fragmentTransaction.commit();
    }

    public void setTabAt(int index){
        tabLayout.getTabAt(index).select();
    }

    @Override
    public void onListItemSelected(int index) {
        tabLayout.getTabAt(index).select();
        String keyword = AppData.KeywordData;
        search_keyword_TextView.setText(keyword);
    }
}
