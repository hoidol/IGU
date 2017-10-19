package com.iguideu.guide_mode.guide_home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-08-07.
 */

public class Guide_HomeRecyclerAdapter extends  RecyclerView.Adapter<Guide_HomeRecyclerAdapter.Guide_Home_Recycler_ViewHolder> {

    Context mContext;
    List<Route_Data> Route_Data_List;

    public Guide_HomeRecyclerAdapter(Context context, List<Route_Data> list){
        this.mContext = context;
        Route_Data_List = list;

    }

    @Override
    public Guide_Home_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.guidemode_route_layout, parent, false);
        return new Guide_HomeRecyclerAdapter.Guide_Home_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Guide_Home_Recycler_ViewHolder holder, final int position) {
        final Route_Data data = Route_Data_List.get(position);

        Picasso.with(mContext).load(data.Route_Photo_URLs.get(0)).into(holder.profile_ImageView);
        holder.route_title_TextView.setText(data.Route_Main_Title);
        int cur_Rating = data.Route_Rating_Num;

        holder.route_delete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Data_List.remove(position);
                AppData.myRef.child("routes").child(data.Route_Index).removeValue();
                notifyDataSetChanged();
            }
        });

        setStar(holder, cur_Rating);
    }

    void setStar(Guide_HomeRecyclerAdapter.Guide_Home_Recycler_ViewHolder holder, int cur_Rating){
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
        return Route_Data_List.size();
    }

    class Guide_Home_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public CardView route_Container;
        public RoundedImageView profile_ImageView;
        public TextView route_title_TextView;
        public ImageView[] rating_star_ImageView = new ImageView[5];
        public Button route_delete_Btn;


        public Guide_Home_Recycler_ViewHolder(View itemView) {
            super(itemView);
            route_Container = (CardView)itemView.findViewById(R.id.route_Container);
            profile_ImageView= (RoundedImageView) itemView.findViewById(R.id.profile_ImageView);
            route_title_TextView = (TextView)itemView.findViewById(R.id.route_title_TextView);
            rating_star_ImageView[0] = (ImageView)itemView.findViewById(R.id.rating_star_0);
            rating_star_ImageView[1] = (ImageView)itemView.findViewById(R.id.rating_star_1);
            rating_star_ImageView[2] = (ImageView)itemView.findViewById(R.id.rating_star_2);
            rating_star_ImageView[3] = (ImageView)itemView.findViewById(R.id.rating_star_3);
            rating_star_ImageView[4] = (ImageView)itemView.findViewById(R.id.rating_star_4);

            route_delete_Btn = (Button)itemView.findViewById(R.id.route_delete_Btn);
        }
    }
}
