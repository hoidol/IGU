package com.iguideu.tourist_mode.tourist_home.recommend;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.tourist_mode.tourist_home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-29.
 */

public class Recommend_AttractionRecyclerAdapter extends  RecyclerView.Adapter<Recommend_AttractionRecyclerAdapter.Recommend_Recycler_ViewHolder>  {


    Context mContext;


    public Recommend_AttractionRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public Recommend_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_cardview_layout, parent, false);
        return new Recommend_AttractionRecyclerAdapter.Recommend_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recommend_Recycler_ViewHolder holder, int position) {
        Picasso.with(mContext).load(AppData.Attraction_Image_URL_List.get(position)).into(holder.recommend_ImageView);
        holder.recommend_title_TextView.setText(AppData.Attraction_Keyword_List.get(position).Keyword);


    }

    @Override
    public int getItemCount() {
        int List_size = AppData.Attraction_Keyword_List.size();
        return (List_size <= 10) ? List_size:10;
    }

    class Recommend_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView recommend_ImageView;
        public TextView recommend_title_TextView;


        public Recommend_Recycler_ViewHolder(View itemView) {
            super(itemView);
            recommend_ImageView = (SquareImageView)itemView.findViewById(R.id.recommend_ImageView);
            recommend_title_TextView = (TextView)itemView.findViewById(R.id.recommend_title_TextView);
        }
    }
}
