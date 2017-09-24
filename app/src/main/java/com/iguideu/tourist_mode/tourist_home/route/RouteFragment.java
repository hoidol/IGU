package com.iguideu.tourist_mode.tourist_home.route;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.route_detail.Route_Detail_Activity;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class RouteFragment extends Fragment {

    Context m_Context;

    String Searched_Keyword = "";
    CalendarDay Searched_Date = null;
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

        if(!Searched_Keyword.equals("")){
            // 검색된 키워드가 있을때
        }

        if(Searched_Date != null){
            // 검색한 날짜가 있을 때
            Toast.makeText(getContext(),"Searched_Date.toString() : " + Searched_Date.toString(),Toast.LENGTH_SHORT).show();
        }

        setSpinner(view);
        setRecycler(view);
    }

    public void setFilterKeyword(String Keyword){
        Searched_Keyword = Keyword;
    }

    public void setFilterDate(CalendarDay date){
        Searched_Date = date;
    }

    void setSpinner(View view){
        Spinner spinner = (Spinner)view.findViewById(R.id.route_Spinner);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(),R.array.route_spinner_item,R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    void setRecycler(View view){
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.route_RecyclerView);


        RouteRecyclerAdapter adapter = new RouteRecyclerAdapter(m_Context);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getContext(), Route_Detail_Activity.class);
                        intent.putExtra("Cur_Route_Position", position);
                        getContext().startActivity(intent);

                    }
                })
        );


        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);


    }
}
