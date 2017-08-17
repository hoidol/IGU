package com.iguideu.tourist_mode.tourist_home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.Route_Data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-29.
 */

public class Recommend_AttractionRecyclerAdapter extends  RecyclerView.Adapter<Recommend_AttractionRecyclerAdapter.Recommend_Recycler_ViewHolder>  {

    Context mContext;
    List<Route_Data> Recommend_Data_List;


    public Recommend_AttractionRecyclerAdapter(Context context, List<Route_Data> list){
        this.mContext = context;
        Recommend_Data_List = list;
    }

    @Override
    public Recommend_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_cardview_layout, parent, false);
        return new Recommend_AttractionRecyclerAdapter.Recommend_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recommend_Recycler_ViewHolder holder, int position) {
        Picasso.with(mContext).load(Recommend_Data_List.get(position).Route_Photo_URLs.get(0)).into(holder.recommend_ImageView);
    }

    @Override
    public int getItemCount() {
        return Recommend_Data_List.size();
    }

    class Recommend_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView recommend_ImageView;


        public Recommend_Recycler_ViewHolder(View itemView) {
            super(itemView);
            recommend_ImageView = (SquareImageView)itemView.findViewById(R.id.recommend_ImageView);
        }
    }
}
