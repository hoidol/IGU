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
import com.iguideu.data.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hoyoung on 2017-07-23.
 */

public class GuideRecyclerAdapter extends  RecyclerView.Adapter<GuideRecyclerAdapter.Guide_Recycler_ViewHolder>{

    Context mContext;
    List<User> User_List;
    FragmentManager fm;

    public GuideRecyclerAdapter(Context context, List<User> list, FragmentManager fm){
        this.mContext = context;
        User_List = list;
        this.fm = fm;

    }

    @Override
    public Guide_Recycler_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_layout, parent, false);
        return new GuideRecyclerAdapter.Guide_Recycler_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Guide_Recycler_ViewHolder holder, int position) {

        User data = User_List.get(position);

        Picasso.with(mContext).load(data.User_Profile_URL).into(holder.profile_ImageView);
        holder.profile_Nick_TextView.setText(data.User_Nick);
        int rating = data.User_Guide_Rating;

        switch (rating){
            case 0:
                holder.rating_star_0.setVisibility(View.GONE);
                holder.rating_star_1.setVisibility(View.GONE);
                holder.rating_star_2.setVisibility(View.GONE);
                holder.rating_star_3.setVisibility(View.GONE);
                holder.rating_star_4.setVisibility(View.GONE);
                break;
            case 1:
                holder.rating_star_0.setVisibility(View.VISIBLE);
                holder.rating_star_1.setVisibility(View.GONE);
                holder.rating_star_2.setVisibility(View.GONE);
                holder.rating_star_3.setVisibility(View.GONE);
                holder.rating_star_4.setVisibility(View.GONE);
                break;
            case 2:
                holder.rating_star_0.setVisibility(View.VISIBLE);
                holder.rating_star_1.setVisibility(View.VISIBLE);
                holder.rating_star_2.setVisibility(View.GONE);
                holder.rating_star_3.setVisibility(View.GONE);
                holder.rating_star_4.setVisibility(View.GONE);
                break;
            case 3:
                holder.rating_star_0.setVisibility(View.VISIBLE);
                holder.rating_star_1.setVisibility(View.VISIBLE);
                holder.rating_star_2.setVisibility(View.VISIBLE);
                holder.rating_star_3.setVisibility(View.GONE);
                holder.rating_star_4.setVisibility(View.GONE);
                break;
            case 4:
                holder.rating_star_0.setVisibility(View.VISIBLE);
                holder.rating_star_1.setVisibility(View.VISIBLE);
                holder.rating_star_2.setVisibility(View.VISIBLE);
                holder.rating_star_3.setVisibility(View.VISIBLE);
                holder.rating_star_4.setVisibility(View.GONE);
                break;
            case 5:
                holder.rating_star_0.setVisibility(View.VISIBLE);
                holder.rating_star_1.setVisibility(View.VISIBLE);
                holder.rating_star_2.setVisibility(View.VISIBLE);
                holder.rating_star_3.setVisibility(View.VISIBLE);
                holder.rating_star_4.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return User_List.size();
    }

    class Guide_Recycler_ViewHolder extends RecyclerView.ViewHolder{
        public RoundedImageView profile_ImageView;
        public TextView profile_Nick_TextView;
        public ImageView rating_star_0;
        public ImageView rating_star_1;
        public ImageView rating_star_2;
        public ImageView rating_star_3;
        public ImageView rating_star_4;





        public Guide_Recycler_ViewHolder(View itemView) {
            super(itemView);
            profile_ImageView = (RoundedImageView)itemView.findViewById(R.id.profile_ImageView);
            profile_Nick_TextView = (TextView)itemView.findViewById(R.id.profile_Nick_TextView);
            rating_star_0 =(ImageView)itemView.findViewById(R.id.rating_star_0);
            rating_star_1 =(ImageView)itemView.findViewById(R.id.rating_star_1);
            rating_star_2 =(ImageView)itemView.findViewById(R.id.rating_star_2);
            rating_star_3 =(ImageView)itemView.findViewById(R.id.rating_star_3);
            rating_star_4 =(ImageView)itemView.findViewById(R.id.rating_star_4);

        }
    }
}
