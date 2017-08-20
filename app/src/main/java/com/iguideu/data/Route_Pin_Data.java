package com.iguideu.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Hoyoung on 2017-08-17.
 */
@IgnoreExtraProperties
public class Route_Pin_Data {

    public String Route_Title;
    public String Route_Content;
    public LatLng Route_Pin_Point;

    public Route_Pin_Data(String Route_Title,String Route_Content  ){
        this.Route_Title = Route_Title;
        this.Route_Content = Route_Content;
    }
    public void setLatLngData(LatLng Route_Pin_Point)
    {
        this.Route_Pin_Point = Route_Pin_Point;
    }
}
