package com.iguideu.tourist_mode.tourist_home.guide;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-23.
 */

public class GuideRecyclerAdapter extends  RecyclerView.Adapter<GuideRecyclerAdapter.Guide_Recycler_ViewHolder>{

    Context mContext;
    FragmentManager fm;

    public GuideRecyclerAdapter(Context context){
        this.mContext = context;
        this.fm = fm;

    }

    @Override
    public Guide_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_layout, parent, false);
        return new GuideRecyclerAdapter.Guide_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Guide_Recycler_ViewHolder holder, int position) {

        User data = AppData.Guider_Data_List.get(position);

        Picasso.with(mContext).load(data.User_Profile_URL).into(holder.profile_ImageView);
        holder.profile_Nick_TextView.setText(data.User_Nick);
        int cur_Rating = data.User_Guide_Rating;
        setStar(holder, cur_Rating);

    }

    @Override
    public int getItemCount() {
        return AppData.Guider_Data_List.size();
    }

    void setStar(GuideRecyclerAdapter.Guide_Recycler_ViewHolder holder, int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                holder.rating_star_ImageView[i].setBackground(mContext.getDrawable(R.mipmap.star_solid));
            }else{
                holder.rating_star_ImageView[i].setBackground(mContext.getDrawable(R.mipmap.star_blank));
            }
        }
    }
    class Guide_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RoundedImageView profile_ImageView;
        public TextView profile_Nick_TextView;
        public ImageView[] rating_star_ImageView = new ImageView[5];

        public Guide_Recycler_ViewHolder(View itemView) {
            super(itemView);
            profile_ImageView = (RoundedImageView)itemView.findViewById(R.id.profile_ImageView);
            profile_Nick_TextView = (TextView)itemView.findViewById(R.id.profile_Nick_TextView);
            rating_star_ImageView[0] = (ImageView)itemView.findViewById(R.id.rating_star_0);
            rating_star_ImageView[1] = (ImageView)itemView.findViewById(R.id.rating_star_1);
            rating_star_ImageView[2] = (ImageView)itemView.findViewById(R.id.rating_star_2);
            rating_star_ImageView[3] = (ImageView)itemView.findViewById(R.id.rating_star_3);
            rating_star_ImageView[4] = (ImageView)itemView.findViewById(R.id.rating_star_4);

        }
    }
}
