package com.iguideu.tourist_mode;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.search.SearchActivity;
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

    AppBarLayout home_AppBarLayout;
    RelativeLayout init_route_search_Container;
    RelativeLayout exten_route_search_Container;
    RelativeLayout search_keyword_Container;
    RelativeLayout search_date_Container;

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
        setSearchContainer(view);

        Log.d(AppData.LOG_INDICATOR, AppData.getCurTime());
    }


    void setTabLayout(View view){

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        tab_TextView[0] = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.home_custom_tab, null);
        tab_TextView[0].setText("추천");
        tab_TextView[0].setTextColor(getResources().getColor(R.color.IGU_Point_Color));
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

    void setSearchContainer(final View view){

        home_AppBarLayout = (AppBarLayout) view.findViewById(R.id.home_AppBarLayout);
        init_route_search_Container = (RelativeLayout)view.findViewById(R.id.init_route_search_Container);
        exten_route_search_Container = (RelativeLayout)view.findViewById(R.id.exten_route_search_Container);

        search_keyword_Container = (RelativeLayout) view.findViewById(R.id.search_keyword_Container);
        search_date_Container = (RelativeLayout) view.findViewById(R.id.search_date_Container);

        home_AppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0)
                {
                    // Collapsed
                    // 열려있을 때 (내렸을 때) - 처음 상태임
                }
                else
                {
                    // Not collapsed
                    //닫혀 있을 떄 위로 올렷을 때
                    init_route_search_Container.setVisibility(View.VISIBLE);
                    exten_route_search_Container.setVisibility(View.GONE);
                }
            }
        });

        init_route_search_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init_route_search_Container.setVisibility(View.GONE);
                exten_route_search_Container.setVisibility(View.VISIBLE);
            }
        });


        search_keyword_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("search_index", 0);
                startActivity(intent);
            }
        });

        search_date_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("search_index", 1);
                startActivity(intent);
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

}
