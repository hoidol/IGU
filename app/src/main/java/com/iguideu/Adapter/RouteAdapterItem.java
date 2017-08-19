package com.iguideu.Adapter;

import android.graphics.drawable.Drawable;

/**
 * Created by Yuchan on 2017-08-13.
 */

public class RouteAdapterItem {


    int MarkerPosition ;

    String PlaceName , PlaceDetail;

    public void setMarkerPosition(int position)
    {
        this.MarkerPosition=position;
    }
    public void setPlaceName(String name)
    {
        this.PlaceName=name;
    }
    public void setPlaceDetail(String detail)
    {
        this.PlaceDetail=detail;
    }
    public int getMarkerPosition()
    {
        return MarkerPosition;
    }
    public String getPlaceName()
    {
        return PlaceName;
    }
    public String getPlaceDetail()
    {
        return PlaceDetail;
    }

}
