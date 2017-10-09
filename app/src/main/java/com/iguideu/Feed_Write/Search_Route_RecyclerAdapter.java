package com.iguideu.Feed_Write;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-10-07.
 */

public class Search_Route_RecyclerAdapter extends  RecyclerView.Adapter<Search_Route_RecyclerAdapter.SearchRoute_Recycler_ViewHolder> {

    Context mContext;
    List<Request_Data> list;


    public Search_Route_RecyclerAdapter(Context nContext, List<Request_Data> list){
        this.mContext = nContext;
        this.list = list;

    }
    @Override
    public SearchRoute_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_layout, parent, false);

        return new Search_Route_RecyclerAdapter.SearchRoute_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SearchRoute_Recycler_ViewHolder holder, final int position) {
        holder.history_node_ImageView.setVisibility(View.GONE);
        holder.history_state_Container.setVisibility(View.GONE);

        Request_Data data = list.get(position);
        Picasso.with(mContext).load(data.Guider_Profile_URL).into(holder.profile_ImageView);
        holder.history_title_TextView.setText(data.Route_Title);
        String[] date = data.Request_Date.split("_");
        holder.history_date_TextView.setText(date[0]+"년 "+ date[1]+"월 "+date[2] +"일");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class SearchRoute_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView history_node_ImageView;
        RoundedImageView profile_ImageView;
        TextView history_title_TextView;
        TextView history_date_TextView;
        RelativeLayout history_state_Container;
        public SearchRoute_Recycler_ViewHolder(View itemView) {
            super(itemView);
            history_node_ImageView = (RoundedImageView)itemView.findViewById(R.id.history_node_ImageView);
            profile_ImageView =(RoundedImageView)itemView.findViewById(R.id.profile_ImageView);
            history_title_TextView = (TextView)itemView.findViewById(R.id.history_title_TextView);
            history_date_TextView = (TextView)itemView.findViewById(R.id.history_date_TextView);
            history_state_Container = (RelativeLayout)itemView.findViewById(R.id.history_state_Container);


        }
    }
}
