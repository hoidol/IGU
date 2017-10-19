package com.iguideu.tourist_mode.tourist_home.route;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.Route_Detail.Route_Detail_Activity;
import com.iguideu.data.Route_Data;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RouteFragment extends Fragment {

    Context m_Context;
    String Searched_Date = ""; //yyyy_mm_dd

    RecyclerView recyclerView;
    RouteRecyclerAdapter adapter;
    List<Route_Data> Cur_Route_List;
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
        return inflater.inflate(R.layout.fragment_tourist_home_route, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.route_RecyclerView);
        adapter = new RouteRecyclerAdapter(m_Context,Cur_Route_List);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), Route_Detail_Activity.class);
                        intent.putExtra("Route_Index", Cur_Route_List.get(position).Route_Index);
                        getContext().startActivity(intent);

                    }
                })
        );
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        showRoute_Data();

        setSpinner(view);
    }


    public void setFilterDate(String date){
        Searched_Date = date;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void showRoute_Data(){
        Cur_Route_List = AppData.Route_Data_List;


        if(!AppData.KeywordData.equals("")){
            List <Route_Data> list = new ArrayList<>();
            for(int i=0; i<Cur_Route_List.size();i++){
                for(int j=0;j<Cur_Route_List.get(i).Route_Locations.size();j++){
                    if(Cur_Route_List.get(i).Route_Locations.get(j).Route_Title.equals(AppData.KeywordData)){
                        list.add(Cur_Route_List.get(i));
                    }
                }
            }
            Cur_Route_List = list;
        }


        adapter.setData(Cur_Route_List);
    }

    void setSpinner(View view){
        Spinner spinner = (Spinner)view.findViewById(R.id.route_Spinner);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(),R.array.route_spinner_item,R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

}
