package com.iguideu.tourist_mode.tourist_home.route;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RouteFragment extends Fragment {

    Context m_Context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public RouteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_route, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner(view);
        setRecycler(view);
    }

    void setSpinner(View view){
        Spinner spinner = (Spinner)view.findViewById(R.id.route_Spinner);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(),R.array.route_spinner_item,R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    void setRecycler(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.route_RecyclerView);

        List<Route_Data> list = new ArrayList<>();


        RouteRecyclerAdapter adapter = new RouteRecyclerAdapter(m_Context,list);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

    }
}
