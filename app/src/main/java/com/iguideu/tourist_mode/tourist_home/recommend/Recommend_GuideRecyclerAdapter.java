package com.iguideu.tourist_mode.tourist_home.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.KeywordData;
import com.iguideu.data.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-29.
 */

public class Recommend_GuideRecyclerAdapter extends  RecyclerView.Adapter<Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder>  {

    Context mContext;
    List<User> list;

    public Recommend_GuideRecyclerAdapter(Context context,List<User> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public Recommend_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_cardview_layout, parent, false);
        return new Recommend_GuideRecyclerAdapter.Recommend_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recommend_Recycler_ViewHolder holder, int position) {
        User data = list.get(position);
        holder.recommend_ImageView.setBackground(mContext.getResources().getDrawable(R.color.Color_White));
        Picasso.with(mContext).load(data.User_Profile_URL).into(holder.recommend_ImageView);

        holder.recommend_title_TextView.setText(data.User_Nick);
    }

    @Override
    public int getItemCount() {
        int List_size = list.size();
        return (List_size <= 10) ? List_size:10;
    }

    public void setData(List<User> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class Recommend_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public SquareImageView recommend_ImageView;
        public TextView recommend_title_TextView;


        public Recommend_Recycler_ViewHolder(View itemView) {
            super(itemView);
            recommend_ImageView = (SquareImageView)itemView.findViewById(R.id.recommend_ImageView);
            recommend_title_TextView =(TextView)itemView.findViewById(R.id.recommend_title_TextView);
        }
    }
}
