package com.iguideu.tourist_mode.tourist_feed;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iguideu.R;
import com.iguideu.data.Feed_Data;
import com.iguideu.feed_detail.FeedDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.iguideu.data.AppData.LOG_INDICATOR;

/**
 * Created by Hoyoung on 2017-07-19.
 */

public class FeedRecyclerAdapter extends  RecyclerView.Adapter<FeedRecyclerAdapter.Feed_Recycler_ViewHolder>{

    Context mContext;
    List<Feed_Data> feed_Data_List;

    public FeedRecyclerAdapter(Context context, List<Feed_Data> list, FragmentManager fm){
        this.mContext = context;
        feed_Data_List = list;

    }

    @Override
    public Feed_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_layout, parent, false);
        return new Feed_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Feed_Recycler_ViewHolder holder, int position) {
        final Feed_Data feed_data = feed_Data_List.get(position);

        Picasso.with(mContext).load(feed_data.Feed_Image_URL).into(holder.Feed_thumbnail_ImageView);

        holder.Feed_thumbnail_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, FeedDetailActivity.class);
                // startCount 값을 넣어줍니다.

                intent.putExtra("User_ID", feed_data.User_ID);
                intent.putExtra("User_Name", feed_data.User_Name);
                intent.putExtra("User_Profile_URL", feed_data.User_Profile_URL);
                intent.putExtra("Feed_Image_URL", feed_data.Feed_Image_URL);
                intent.putExtra("Feed_Content", feed_data.Feed_Contents);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feed_Data_List.size();
    }



    class Feed_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public ImageView Feed_thumbnail_ImageView;


        public Feed_Recycler_ViewHolder(View itemView) {
            super(itemView);
            Feed_thumbnail_ImageView= (ImageView) itemView.findViewById(R.id.feed_thumbnail_ImageView);
        }
    }
}
