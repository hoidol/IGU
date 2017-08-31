package com.iguideu.tourist_mode.tourist_tour.Rating_Route;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.data.Route_Data;
import com.iguideu.guide_mode.guide_history.HistoryRecyclerAdapter;
import com.iguideu.search.SearchDateFragment;
import com.iguideu.search.SearchKeywordFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class RatingRouteActivity extends AppCompatActivity {

    String Route_Index;
    String Guider_Index;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setRatingData();
        setFrameLayout();
    }

    void setRatingData(){
        Intent receivedIntent = getIntent();

        Route_Index = receivedIntent.getStringExtra("Route_Index");
        Guider_Index = receivedIntent.getStringExtra("Guider_Index");
    }

    void setFrameLayout(){
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.add(R.id.rating_route_FrameLayout,new RatingRouteMainFragment());
        fragmentTransaction.commit();

    }
}