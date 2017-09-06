package com.iguideu.tourist_mode.tourist_feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Feed_Data;
import com.squareup.picasso.Picasso;

/**
 * Created by Hoyoung on 2017-07-19.
 */

public class FeedRecyclerAdapter extends  RecyclerView.Adapter<FeedRecyclerAdapter.Feed_Recycler_ViewHolder>{

    Context mContext;

    public FeedRecyclerAdapter(Context context){
        this.mContext = context;

    }

    @Override
    public Feed_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_layout, parent, false);
        return new Feed_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Feed_Recycler_ViewHolder holder, int position) {
        final Feed_Data feed_data = AppData.Feed_Data_List.get(position);

        Picasso.with(mContext).load(feed_data.Feed_Image_URL).into(holder.Feed_thumbnail_ImageView);

    }

    @Override
    public int getItemCount() {
        return AppData.Feed_Data_List.size();
    }



    class Feed_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public ImageView Feed_thumbnail_ImageView;


        public Feed_Recycler_ViewHolder(View itemView) {
            super(itemView);
            Feed_thumbnail_ImageView= (ImageView) itemView.findViewById(R.id.feed_thumbnail_ImageView);
        }
    }
}
