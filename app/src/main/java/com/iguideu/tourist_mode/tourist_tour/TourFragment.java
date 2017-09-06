package com.iguideu.tourist_mode.tourist_tour;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.iguideu.R;
import com.iguideu.data.Route_Data;
import com.iguideu.guide_mode.guide_history.Guide_HistoryFragment;
import com.iguideu.guide_mode.guide_home.Guide_HomeFragment;
import com.iguideu.guide_mode.guide_schedule.Guide_ScheduleFragment;
import com.iguideu.main_inbox.InboxFragment;
import com.iguideu.main_setting.SettingFragment;
import com.iguideu.tourist_mode.tourist_feed.FeedFragment;
import com.iguideu.tourist_mode.tourist_home.HomeFragment;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-16.
 */

public class TourFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    TourHistoryFragment History_Fragment;
    TourFavoriteFragment Favorite_Fragment;

    Button[] Tour_Btns = new Button[2];

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public TourFragment() {
        // Required empty public constructor
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_tour, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        History_Fragment = new TourHistoryFragment();
        Favorite_Fragment = new TourFavoriteFragment();

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.tour_FrameLayout,History_Fragment);
        fragmentTransaction.commit();

        Tour_Btns[0] = (Button)view.findViewById(R.id.Tour_History_Btn);
        Tour_Btns[1] = (Button) view.findViewById(R.id.Tour_Favorite_Btn);

        setBtnBackground(Tour_Btns[0]);

        Tour_Btns[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnBackground(Tour_Btns[0]);
                if (History_Fragment != null) {
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.tour_FrameLayout, History_Fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        Tour_Btns[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnBackground(Tour_Btns[1]);
                if (Favorite_Fragment != null) {
                    fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.tour_FrameLayout, Favorite_Fragment);
                    fragmentTransaction.commit();
                }
            }
        });



    }

    void setBtnBackground(Button btn){
        for(int i =0; i<Tour_Btns.length;i++){
            if(Tour_Btns[i] == btn){
                Tour_Btns[i].setBackground(getResources().getDrawable(R.drawable.check_solid));
                Tour_Btns[i].setTextColor(getResources().getColor(R.color.Color_White));
                Tour_Btns[i].setEnabled(false);
            }else{
                Tour_Btns[i].setBackground(getResources().getDrawable(R.drawable.noncheck_solid));
                Tour_Btns[i].setTextColor(getResources().getColor(R.color.Color_Black));
                Tour_Btns[i].setEnabled(true);
            }
        }
    }


}
