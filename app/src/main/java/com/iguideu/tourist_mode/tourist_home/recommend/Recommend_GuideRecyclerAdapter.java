package com.iguideu.tourist_mode.tourist_home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-29.
 */

public class Recommend_GuideRecyclerAdapter extends  RecyclerView.Adapter<Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder>  {

    Context mContext;
    List<User> User_Data_List;


    public Recommend_GuideRecyclerAdapter(Context context, List<User> list){
        this.mContext = context;
        User_Data_List = list;
    }

    @Override
    public Recommend_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_cardview_layout, parent, false);
        return new Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recommend_Recycler_ViewHolder holder, int position) {
        Picasso.with(mContext).load(User_Data_List.get(position).User_Profile_URL).into(holder.recommend_ImageView);

    }

    @Override
    public int getItemCount() {
        return User_Data_List.size();
    }

    class Recommend_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView recommend_ImageView;


        public Recommend_Recycler_ViewHolder(View itemView) {
            super(itemView);
            recommend_ImageView = (SquareImageView)itemView.findViewById(R.id.recommend_ImageView);
        }
    }
}
