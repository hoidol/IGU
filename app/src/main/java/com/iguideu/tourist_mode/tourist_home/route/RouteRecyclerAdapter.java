package com.iguideu.tourist_mode.tourist_home.route;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.SquareImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.route_detail.Route_Detail_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-22.
 */

public class RouteRecyclerAdapter extends  RecyclerView.Adapter<RouteRecyclerAdapter.Route_Recycler_ViewHolder> {

    Context mContext;

    public RouteRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public Route_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.touristmode_route_layout, parent, false);
        return new RouteRecyclerAdapter.Route_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Route_Recycler_ViewHolder holder, int position) {
        Route_Data data = AppData.Route_Data_List.get(position);

        Picasso.with(mContext).load(data.Route_Photo_URLs.get(0)).into(holder.route_ImageView);
        holder.route_title_TextView.setText(data.Route_Main_Title);

        int cur_Rating = data.Route_Rating_Num;

/*        List<String> list = AppData.getCur_User().getUser_Route_Favorites();

        holder.route_Favorite_Btn.setBackground(mContext.getDrawable(R.mipmap.no_favorite_icon));

        for(int i =0; i < list.size(); i++){
            if(data.getRoute_Index() == list.get(i)){
                holder.route_Favorite_Btn.setBackground(mContext.getDrawable(R.mipmap.favorite_icon));
            }
        }*/


        setStar(holder, cur_Rating);

    }

    void setStar(Route_Recycler_ViewHolder holder,int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                holder.rating_star_ImageView[i].setBackground(mContext.getDrawable(R.mipmap.star_solid));
            }else{
                holder.rating_star_ImageView[i].setBackground(mContext.getDrawable(R.mipmap.star_blank));
            }
        }
    }

    @Override
    public int getItemCount() {
        return AppData.Route_Data_List.size();
    }

    class Route_Recycler_ViewHolder extends RecyclerView.ViewHolder{

        public CardView route_Container;
        public SquareImageView route_ImageView;
        public TextView route_title_TextView;
        public ImageView[] rating_star_ImageView = new ImageView[5];


        public Route_Recycler_ViewHolder(View itemView) {
            super(itemView);
            route_Container = (CardView)itemView.findViewById(R.id.route_Container);
            route_ImageView= (SquareImageView) itemView.findViewById(R.id.route_image);
            route_title_TextView = (TextView)itemView.findViewById(R.id.route_title_TextView);
            rating_star_ImageView[0] = (ImageView)itemView.findViewById(R.id.rating_star_0);
            rating_star_ImageView[1] = (ImageView)itemView.findViewById(R.id.rating_star_1);
            rating_star_ImageView[2] = (ImageView)itemView.findViewById(R.id.rating_star_2);
            rating_star_ImageView[3] = (ImageView)itemView.findViewById(R.id.rating_star_3);
            rating_star_ImageView[4] = (ImageView)itemView.findViewById(R.id.rating_star_4);

        }
    }
}
