package com.iguideu.guide_mode.guide_history;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.Feed_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_feed.FeedRecyclerAdapter;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-11.
 */

public class HistoryRecyclerAdapter extends  RecyclerView.Adapter<HistoryRecyclerAdapter.History_Recycler_ViewHolder> {

    Context mContext;
    List<Route_Data> Route_Data_List;

    public HistoryRecyclerAdapter(Context context, List<Route_Data> list){
        this.mContext = context;
        Route_Data_List = list;
    }

    @Override
    public History_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);
        return new HistoryRecyclerAdapter.History_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(History_Recycler_ViewHolder holder, int position) {
        if(Route_Data_List == null)
            return;

        Route_Data data = Route_Data_List.get(position);
    }

    @Override
    public int getItemCount() {
        return Route_Data_List.size();
    }

    class History_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RelativeLayout history_Container;
        public ImageView history_node_ImageView;
        public RoundedImageView profile_ImageView;
        public TextView history_title_TextView;
        public TextView history_date_TextView;


        public History_Recycler_ViewHolder(View itemView) {
            super(itemView);
            history_Container = (RelativeLayout)itemView.findViewById(R.id.history_Container);
            history_node_ImageView= (ImageView) itemView.findViewById(R.id.history_node_ImageView);
            profile_ImageView= (RoundedImageView) itemView.findViewById(R.id.profile_ImageView);
            history_title_TextView= (TextView) itemView.findViewById(R.id.history_title_TextView);
            history_date_TextView= (TextView) itemView.findViewById(R.id.history_date_TextView);
        }
    }
}
