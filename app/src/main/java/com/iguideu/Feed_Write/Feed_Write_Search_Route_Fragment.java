package com.iguideu.Feed_Write;

import android.app.Activity;
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
import com.iguideu.custom_view.SquareImageView;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Search_Route_Fragment extends Fragment {
    Context m_Context;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }


    public Feed_Write_Search_Route_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feed_write_search_route, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
