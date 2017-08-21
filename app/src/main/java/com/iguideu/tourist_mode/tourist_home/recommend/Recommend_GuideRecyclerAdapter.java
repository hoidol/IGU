package com.iguideu.tourist_mode.tourist_home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-29.
 */

public class Recommend_GuideRecyclerAdapter extends  RecyclerView.Adapter<Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder>  {

    Context mContext;


    public Recommend_GuideRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public Recommend_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_cardview_layout, parent, false);
        return new Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recommend_Recycler_ViewHolder holder, int position) {
        User data = AppData.Recommend_Guider_List.get(position);
        Picasso.with(mContext).load(data.User_Profile_URL).into(holder.recommend_ImageView);

    }

    @Override
    public int getItemCount() {
        int List_size = AppData.Recommend_Guider_List.size();
        return (List_size <= 10) ? List_size:10;
    }

    class Recommend_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView recommend_ImageView;


        public Recommend_Recycler_ViewHolder(View itemView) {
            super(itemView);
            recommend_ImageView = (SquareImageView)itemView.findViewById(R.id.recommend_ImageView);
        }
    }
}
