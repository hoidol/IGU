package com.iguideu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuchan on 2017-08-13.
 */

public class RouteAddListAdapter extends RecyclerView.Adapter<RouteAddListAdapter.ViewHolder> {

    Context mContext;
    View v;
    ArrayList<RouteAddListAdapter.ViewHolder> Route_List_Holder=new ArrayList<>();
    public RouteAddListAdapter(Context context){
        this.mContext = context;

    }

    @Override
    public RouteAddListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v=LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_route_add_list,parent,false);
        return new ViewHolder(v);
    }

    public View getView()
    {
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
         final Route_Pin_Data itemData = AppData.PinPointData.get(position);

        switch(position)
        {
            case 0:
                holder.Marker.setImageResource(R.mipmap.marker_1);
                break;
            case 1:
                holder.Marker.setImageResource(R.mipmap.marker_2);
                break;
            case 2:
                holder.Marker.setImageResource(R.mipmap.marker_3);
                break;
            case 3:
                holder.Marker.setImageResource(R.mipmap.marker_4);
                break;
            case 4:
                holder.Marker.setImageResource(R.mipmap.marker_5);
                break;
        }

        holder.Route_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.PinPointData.remove(position);
                AppData.AppPinPointData.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.Route_Title.setHint(itemData.Route_Title);
        holder.Route_Detail.setHint(itemData.Route_Content);

        Route_List_Holder.add(holder);
    }

    public ArrayList<RouteAddListAdapter.ViewHolder> getViewHolder()
    {
        return Route_List_Holder;
    }

    @Override
    public int getItemCount() {
        return AppData.PinPointData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView Marker;
        public EditText Route_Title,Route_Detail;
        public Button Route_delete,Route_Edit;

        public  ViewHolder(View v)
        {
            super(v);
            Marker=(ImageView)v.findViewById(R.id.custom_list_marker);
            Route_Title=(EditText)v.findViewById(R.id.edit_route_add_place_name);
            Route_Detail=(EditText)v.findViewById(R.id.edit_route_add_place_detail);
            Route_delete=(Button)v.findViewById(R.id.route_add_item_list_delete_Btn);
            Route_Edit=(Button)v.findViewById(R.id.route_add_item_list_edit_Btn);
        }
    }
}
