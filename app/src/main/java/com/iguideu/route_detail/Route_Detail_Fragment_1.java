package com.iguideu.route_detail;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hoyoung on 2017-07-30.
 */

public class Route_Detail_Fragment_1  extends Fragment implements OnMapReadyCallback {

    Context m_Context;
    Activity activity;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    private  GoogleMap googleMap;
    private MapFragment mapView;
    private boolean mapsSupported = true;
    MapFragment mapFragment;
    ScrollView scrollView;

    private Route_Data Cur_Route_Data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.m_Context = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public Route_Detail_Fragment_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_route_detail_1, container, false);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button button = (Button)view.findViewById(R.id.route_detail_Btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });




        if(Cur_Route_Data != null){
            setToolbar(view);
            setImage(view);
            setMap(view, savedInstanceState);
        }


    }

    void setImage(View view){
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.route_image_ViewPager);
        Route_Image_PagerAdapter adapter = new Route_Image_PagerAdapter(getContext(),getActivity().getLayoutInflater(),Cur_Route_Data.Route_Photo_URLs);
        viewPager.setAdapter(adapter);
    }

    void SetRoute_Data(Route_Data route_data){
        Cur_Route_Data = route_data;
    }


    void setMap(View view,Bundle savedInstanceState){
        scrollView = (ScrollView)view.findViewById(R.id.route_detail_ScrollView);

        mapView = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);

        mapView.onCreate(savedInstanceState);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        LatLng seoul_citihole = new LatLng(37.566734,126.978395);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul_citihole, 13));

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.map_mark_1); //마커에 사용될 아이콘 설정

        googleMap.addMarker(new MarkerOptions().position(seoul_citihole).icon(icon)); //마커 등록
        List<LatLng> list  = new ArrayList<>();
        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(37.566734,126.978395))
                .add(new LatLng(37.566734,126.978495))  // North of the previous point, but at the same longitude
                .add(new LatLng(37.566754,126.978495))  // Same latitude, and 30km to the west
                .add(new LatLng(37.566754,126.978395))  // Same longitude, and 16km to the south
                .add(new LatLng(37.566734,126.978395));

        Polyline polyline = googleMap.addPolyline(rectOptions);

        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition arg0) {
                LatLng location_center = arg0.target;
                Log.d(AppData.LOG_INDICATOR,"위도 : " + location_center.latitude + " 경도 : " + location_center.longitude);


            }
        });



    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("");

        ImageButton toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);
        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();;
            }
        });
    }

    void changeFragment(){
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Route_Detail_Fragment_2 fragment = new Route_Detail_Fragment_2();
        fragment.setFragmentData(fragment,Cur_Route_Data);
        fragmentTransaction.add(R.id.route_detail_FrameLayout,fragment);
        fragmentTransaction.commit();
    }

}
