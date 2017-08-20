package com.iguideu.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.iguideu.R;
import com.iguideu.data.Route_Data;

import java.util.ArrayList;

/**
 * Created by Yuchan on 2017-08-13.
 */

public class RouteAddListAdapter extends BaseAdapter {
    private ArrayList<RouteAdapterItem> data = new ArrayList<>();

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context=parent.getContext();

        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.guide_route_add_list,parent,false);
        }

        ImageView marker=(ImageView)convertView.findViewById(R.id.marker_resource);
        EditText placename=(EditText)convertView.findViewById(R.id.edit_route_add_place_name);
        EditText placedetail=(EditText)convertView.findViewById(R.id.edit_route_add_place_detail);

        RouteAdapterItem mitem=data.get(position);

        placename.setHint(mitem.getPlaceName());
        placedetail.setHint(mitem.getPlaceDetail());

        placename.getBackground().setColorFilter(convertView.getResources().getColor(R.color.Color_Layout_Background), PorterDuff.Mode.SRC_IN);
        placedetail.getBackground().setColorFilter(convertView.getResources().getColor(R.color.Color_Layout_Background), PorterDuff.Mode.SRC_IN);

        return convertView;
    }

    public void addItem(int position,String hint,String hint2){

        RouteAdapterItem item=new RouteAdapterItem();

        item.setMarkerPosition(position);
        item.setPlaceName(hint);
        item.setPlaceDetail(hint2);

        data.add(item);
    }
}
