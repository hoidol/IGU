package com.iguideu.Feed_Write;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.ClickListener.RecyclerItemClickListener;
import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hoyoung on 2017-09-02.
 */

public class Feed_Write_Search_Route_Fragment extends Fragment {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    Feed_Write_Contents_Fragment Write_Contents_Fragment;
    Feed_Write_Search_Route_Fragment Cur_Fragment;
    RecyclerView recyclerView;

    List<Request_Data> completed_Request_Data;
    public Feed_Write_Search_Route_Fragment() {
        // Required empty public constructor
    }

    public void set_Feed_Write_Search_Route_Data(Feed_Write_Contents_Fragment fragment1,Feed_Write_Search_Route_Fragment fragment2){
        Write_Contents_Fragment =fragment1;
        Cur_Fragment= fragment2;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_feed_write_search_route, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        recyclerView = (RecyclerView)view.findViewById(R.id.search_route_RecyclerView);

        completed_Request_Data  = new ArrayList<>();

        for(int i =0 ; i< AppData.Request_Data_ForTourist_List.size();i++){
            Request_Data cur_data = AppData.Request_Data_ForTourist_List.get(i);
            if(cur_data.Request_State == 4){
                completed_Request_Data.add(cur_data);
            }
        }
        Search_Route_RecyclerAdapter adapter = new Search_Route_RecyclerAdapter(getContext(),completed_Request_Data);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Write_Contents_Fragment.set_Searched_Route(completed_Request_Data.get(position));

                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(Cur_Fragment);
                fragmentTransaction.commit();
            }
        }));

    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);

        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));
        toolbar_title_TextView.setText("");

        TextView toolbar_back_TextView = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);
        toolbar_back_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.remove(Cur_Fragment);
                fragmentTransaction.commit();
            }
        });

        TextView complete_Btn = (TextView)view.findViewById(R.id.complete_Btn);
        complete_Btn.setText("");
    }
}
