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
import com.iguideu.data.AppData;
import com.iguideu.data.Request_Data;
import com.squareup.picasso.Picasso;

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
        Request_Data data = list.get(position);


        String[] request_Dates = data.Request_Date.split("_");

        Picasso.with(mContext).load(data.Tourist_User_Profile_URL).into(holder.profile_imageView);

        holder.profile_Name_TextView.setText(data.Tourist_User_Name);
        holder.route_Date_TextView.setText(request_Dates[0] +"년 "+request_Dates[1]+"월 "+ request_Dates[2]+"일");

        if(data.Request_State == 0){
            holder.profile_Name_TextView.setTextColor(mContext.getResources().getColor(R.color.Color_All_Primary_Text));
            holder.route_Date_TextView.setTextColor(mContext.getResources().getColor(R.color.Color_All_Primary_Text));
        }else{
            holder.profile_Name_TextView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            holder.route_Date_TextView.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ScheduleCheck_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RoundedImageView profile_imageView;
        public TextView profile_Name_TextView;
        public TextView route_Date_TextView;


        public ScheduleCheck_Recycler_ViewHolder(View itemView) {
            super(itemView);
            profile_imageView= (RoundedImageView) itemView.findViewById(R.id.profile_imageView);
            profile_Name_TextView= (TextView) itemView.findViewById(R.id.profile_Name_TextView);
            route_Date_TextView= (TextView) itemView.findViewById(R.id.inbox_Date_TextView);
        }
    }
}
