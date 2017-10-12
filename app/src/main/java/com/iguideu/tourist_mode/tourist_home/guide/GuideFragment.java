package com.iguideu.tourist_mode.tourist_home.guide;

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

import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.main_Profile.ProfileActivity;
import com.iguideu.R;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-17.
 */

public class GuideFragment extends Fragment{

    Context m_Context;
    List<User> Cur_Guide_List;
    RecyclerView recyclerView;
    GuideRecyclerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public GuideFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_tourist_home_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.guide_RecyclerView);
        Cur_Guide_List = AppData.Guider_Data_List;

        adapter = new GuideRecyclerAdapter(getContext(),Cur_Guide_List);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("User_ID",Cur_Guide_List.get(position).User_ID);
                getContext().startActivity(intent);
            }
        }));



    }

}
