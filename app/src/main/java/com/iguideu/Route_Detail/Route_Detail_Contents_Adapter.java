package com.iguideu.Route_Detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.Route_Pin_Data;

import java.util.List;

/**
 * Created by Hoyoung on 2017-09-24.
 */

public class Route_Detail_Contents_Adapter extends RecyclerView.Adapter<Route_Detail_Contents_Adapter.Route_Detail_Contents_ViewHolder> {

    Context mContext;
    List<Route_Pin_Data> list;


    int[] map_mark_Res = {R.mipmap.marker_1,R.mipmap.marker_2,R.mipmap.marker_3,R.mipmap.marker_4,R.mipmap.marker_5};


    public Route_Detail_Contents_Adapter(Context _Context, List<Route_Pin_Data> _list){
        mContext = _Context;
        list = _list;

    }

    @Override
    public Route_Detail_Contents_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_detail_content, parent, false);
        return new Route_Detail_Contents_Adapter.Route_Detail_Contents_ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Route_Detail_Contents_ViewHolder holder, int position) {
        Route_Pin_Data Cur_Data = list.get(position);

        holder.route_detail_MarkImageView.setImageDrawable(mContext.getDrawable(map_mark_Res[position]));
        holder.route_detail_Title_TextView.setText(Cur_Data.Route_Title);
        holder.route_detail_Content_TextView.setText(Cur_Data.Route_Content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Route_Detail_Contents_ViewHolder extends RecyclerView.ViewHolder{
        public ImageView route_detail_MarkImageView;
        public TextView route_detail_Title_TextView;
        public  TextView route_detail_Content_TextView;


        public Route_Detail_Contents_ViewHolder(View itemView) {
            super(itemView);
            route_detail_MarkImageView= (ImageView) itemView.findViewById(R.id.route_detail_MarkImageView);
            route_detail_Title_TextView =(TextView)itemView.findViewById(R.id.route_detail_Title_TextView);
            route_detail_Content_TextView = (TextView)itemView.findViewById(R.id.route_detail_Content_TextView);

        }
    }
}
