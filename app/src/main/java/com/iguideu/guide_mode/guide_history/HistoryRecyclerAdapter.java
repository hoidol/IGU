package com.iguideu.guide_mode.guide_history;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.main_Profile.ProfileActivity;
import com.iguideu.tourist_mode.tourist_feed.FeedRecyclerAdapter;
import com.iguideu.tourist_mode.tourist_tour.Rating_Route.RatingRouteActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-11.
 */

public class HistoryRecyclerAdapter extends  RecyclerView.Adapter<HistoryRecyclerAdapter.History_Recycler_ViewHolder> {

    Context mContext;
    List<Request_Data> list;

    public HistoryRecyclerAdapter(Context context, List<Request_Data> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public History_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new HistoryRecyclerAdapter.History_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(History_Recycler_ViewHolder holder, int position) {
        final Request_Data data = list.get(position);

        String[] request_Dates = data.Request_Date.split("_");

        Picasso.with(mContext).load(data.Tourist_User_Profile_URL).into(holder.profile_ImageView);
        holder.profile_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileActivity.class);
                intent.putExtra("User_ID",data.Tourist_User_ID);
                mContext.startActivity(intent);
            }
        });

        holder.history_title_TextView.setText(data.Route_Title);
        holder.history_date_TextView.setText(request_Dates[0] +"년 "+request_Dates[1]+"월 "+ request_Dates[2]+"일");

        switch (data.Request_State){
            case 1: //수락
                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("수락");
                break;
            case 2: //거절

                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("거절");
                break;
            case 3: // 별점 주기
                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("평가중");
                break;
            case 4: // 완료 - 시간이 지나면 없어짐
                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("완료");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class History_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RoundedImageView profile_ImageView;
        public RelativeLayout history_contents_Container;
        public TextView history_title_TextView;
        public TextView history_date_TextView;
        public Button history_state_Btn;


        public History_Recycler_ViewHolder(View itemView) {
            super(itemView);
            profile_ImageView= (RoundedImageView) itemView.findViewById(R.id.profile_ImageView);
            history_contents_Container = (RelativeLayout)itemView.findViewById(R.id.history_contents_Container);
            history_title_TextView= (TextView) itemView.findViewById(R.id.history_title_TextView);
            history_date_TextView= (TextView) itemView.findViewById(R.id.history_date_TextView);
            history_state_Btn = (Button)itemView.findViewById(R.id.history_state_Btn);
        }
    }
}
