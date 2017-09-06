package com.iguideu.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.iguideu.R;

/**
 * Created by Yuchan on 2017-08-13.
 */

public class RouteAdapterItem {

    int Marker;
    String Route_Title,Route_Detail;

     public  RouteAdapterItem(int marker, String Route_Title,String Route_Detail)
    {
        this.Marker=marker;
        this.Route_Title=Route_Title;
        this.Route_Detail=Route_Detail;
    }
}
