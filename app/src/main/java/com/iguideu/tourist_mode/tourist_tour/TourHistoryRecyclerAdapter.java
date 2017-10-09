package com.iguideu.tourist_mode.tourist_tour;

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
import com.iguideu.data.Request_Data;
import com.iguideu.tourist_mode.tourist_tour.Rating_Route.RatingRouteActivity;

import java.util.List;

/**
 * Created by Hoyoung on 2017-10-05.
 */

public class TourHistoryRecyclerAdapter extends  RecyclerView.Adapter<TourHistoryRecyclerAdapter.TourHistory_Recycler_ViewHolder> {

    Context mContext;
    List<Request_Data> list;

    public TourHistoryRecyclerAdapter(Context context,List<Request_Data> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public TourHistory_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new TourHistoryRecyclerAdapter.TourHistory_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TourHistory_Recycler_ViewHolder holder, int position) {
        final Request_Data data = list.get(position);

        holder.history_title_TextView.setText(data.Route_Title);
        String[] date = data.Request_Date.split("_");//yyyy_MM_dd_HH_mm_ss
        holder.history_date_TextView.setText(date[0]+"년 " + date[1]+"월 "+ date[2]+"일");

        switch (data.Request_State){
            case 0: // 대기
                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("요청중");
                break;
            case 1: //수락
                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("수락");
                break;
            case 2: //거절

                holder.history_state_Btn.setEnabled(false);
                holder.history_state_Btn.setText("거절");
                break;
            case 3: // 별점 주기

                holder.history_state_Btn.setEnabled(true);
                holder.history_state_Btn.setText("별점 주기");
                holder.history_state_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, RatingRouteActivity.class);
                        intent.putExtra("Route_Owner_Index", AppData.StringReplace(data.Guider_User_ID));
                        intent.putExtra("Request_Index", data.Request_Index);
                        intent.putExtra("Route_Index",data.Route_Index);
                        mContext.startActivity(intent);
                    }
                });
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

    class TourHistory_Recycler_ViewHolder extends RecyclerView.ViewHolder{

        public RoundedImageView history_node_ImageView;
        public RoundedImageView profile_ImageView;
        public RelativeLayout history_contents_Container;
        public TextView history_title_TextView;
        public TextView history_date_TextView;
        public Button history_state_Btn;

        public TourHistory_Recycler_ViewHolder(View itemView) {
            super(itemView);
            history_node_ImageView= (RoundedImageView) itemView.findViewById(R.id.history_node_ImageView);
            profile_ImageView= (RoundedImageView) itemView.findViewById(R.id.profile_ImageView);
            history_contents_Container = (RelativeLayout)itemView.findViewById(R.id.history_contents_Container);
            history_title_TextView= (TextView) itemView.findViewById(R.id.history_title_TextView);
            history_date_TextView= (TextView) itemView.findViewById(R.id.history_date_TextView);
            history_state_Btn = (Button)itemView.findViewById(R.id.history_state_Btn);
        }
    }
}