package com.iguideu.guide_mode.guide_schedule;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.Request_Data;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-11.
 */

public class ScheduleCheckRecyclerAdapter extends RecyclerView.Adapter<ScheduleCheckRecyclerAdapter.ScheduleCheck_Recycler_ViewHolder> {

    Context mContext;
    List<Request_Data> list;
    public ScheduleCheckRecyclerAdapter(Context mContext, List<Request_Data> list){
        this.mContext = mContext;
        this.list = list;
    }
    @Override
    public ScheduleCheck_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_layout, parent, false);
        return new ScheduleCheckRecyclerAdapter.ScheduleCheck_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleCheck_Recycler_ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ScheduleCheck_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public CardView inbox_CardView;
        public RoundedImageView profile_imageView;
        public TextView profile_Name_TextView;
        public TextView inbox_Date_TextView;


        public ScheduleCheck_Recycler_ViewHolder(View itemView) {
            super(itemView);
            inbox_CardView = (CardView)itemView.findViewById(R.id.inbox_CardView);
            profile_imageView= (RoundedImageView) itemView.findViewById(R.id.profile_imageView);
            profile_Name_TextView= (TextView) itemView.findViewById(R.id.profile_Name_TextView);
            inbox_Date_TextView= (TextView) itemView.findViewById(R.id.inbox_Date_TextView);
        }
    }
}
