package com.iguideu.guide_mode.Write_Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;

import java.util.ArrayList;

public class RouteAddActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap gmap;
    LatLng locationLatLng;
    boolean AddBtnEnable=true;
    int MarkerPosition=0,bitmapMarkersPosition=0;

    Button RouteAddBtn;
    boolean checkDataEnable=false;

    BitmapDescriptor[] marker=new BitmapDescriptor[5];
    int MarkerCount=0;

    ImageView markerResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_add);


        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_route_add);
        mapFragment.getMapAsync(this);

        RouteAddBtn=(Button)findViewById(R.id.btn_route_add_check);
        markerResource=(ImageView)findViewById(R.id.marker_resource);


    }
    public void onMapReady(GoogleMap googleMap)
    {
        gmap=googleMap;

        LatLng seoul=new LatLng(37.52487, 126.92723);
        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul,13));

        gmap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener(){
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                locationLatLng=cameraPosition.target;
            }
        });

    }


    public void RouteSaveClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_route_add_check:
                LatLng point=new LatLng(locationLatLng.latitude,locationLatLng.longitude);
                AppData.PinPointData.add(point);
                MarkerOptions options=new MarkerOptions();
                options.position(point).icon(getMarker(MarkerCount));
                gmap.addMarker(options);

                MarkerCount++;
                setMarkerResource(MarkerCount);
                if(MarkerCount==5)
                {
                    RouteAddBtn.setBackgroundResource(R.color.Color_Login_Error);
                    RouteAddBtn.setClickable(false);
                }
                break;
            case R.id.btn_route_add_saved:
                if(checkDataEnable)
                {
                    Intent intent=new Intent();
                    setResult(0);
                }
                finish();
                break;
        }
    }
    public BitmapDescriptor getMarker(int position)
    {
        marker[0]= BitmapDescriptorFactory.fromResource(R.mipmap.marker_1);
        marker[1]= BitmapDescriptorFactory.fromResource(R.mipmap.marker_2);
        marker[2]= BitmapDescriptorFactory.fromResource(R.mipmap.marker_3);
        marker[3]= BitmapDescriptorFactory.fromResource(R.mipmap.marker_4);
        marker[4]= BitmapDescriptorFactory.fromResource(R.mipmap.marker_5);

        return marker[position];
    }
    public void setMarkerResource(int position)
    {
        switch (position)
        {
            case 0:
                markerResource.setImageResource(R.mipmap.marker_1);
                break;
            case 1:
                markerResource.setImageResource(R.mipmap.marker_2);
                break;
            case 2:
                markerResource.setImageResource(R.mipmap.marker_3);
                break;
            case 3:
                markerResource.setImageResource(R.mipmap.marker_4);
                break;
            case 4:
                markerResource.setImageResource(R.mipmap.marker_5);
                break;

        }
    }
}
