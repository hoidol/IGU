package com.iguideu.Route_Detail;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iguideu.R;
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.Route_Data;
import com.iguideu.data.Route_Pin_Data;
import com.squareup.picasso.Picasso;

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

    ImageView favorite_ImageView;
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
        if(Cur_Route_Data.User_ID.equals(AppData.getCur_User().User_ID)){
            button.setVisibility(View.GONE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();
            }
        });

        if(Cur_Route_Data != null){
            setToolbar(view);
            setImage(view);
            setContents(view);
            setMap(view, savedInstanceState);
            setFavorite(view);
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

    int[] map_mark_Res = {R.mipmap.marker_1,R.mipmap.marker_2,R.mipmap.marker_3,R.mipmap.marker_4,R.mipmap.marker_5};

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

        List<Route_Pin_Data> Locations = Cur_Route_Data.Route_Locations;

        LatLng Init_Location = new LatLng(Locations.get(0).Route_Pin_Point_lat,Locations.get(0).Route_Pin_Point_long);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Init_Location, 13.5f));

        for(int i =0; i < Locations.size();i++){
            LatLng temp_LatLng = new LatLng(Locations.get(i).Route_Pin_Point_lat,Locations.get(i).Route_Pin_Point_long);
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(map_mark_Res[i]); //마커에 사용될 아이콘 설정
            googleMap.addMarker(new MarkerOptions().position(temp_LatLng).icon(icon)); //마커 등록
        }

    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("");

        ImageButton toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);
        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    TextView route_title_TextView;
    public ImageView[] rating_star_ImageView = new ImageView[5];
    RoundedImageView route_profile_image;
    TextView route_profile_name;
    TextView route_guide_possibility_Text;
    TextView route_guide_time_Text;
    TextView route_guide_guestNum_Text;

    ImageView route_map_indicator_Image;
    int[] map_indicator_Res = {R.mipmap.map_indicator_0,R.mipmap.map_indicator_1,R.mipmap.map_indicator_2,R.mipmap.map_indicator_3,R.mipmap.map_indicator_4};
    RecyclerView route_detail_Container_RecyclerView;

    void setContents(View view){
        // 메인 제목
        route_title_TextView = (TextView)view.findViewById(R.id.route_title_TextView);
        route_title_TextView.setText(Cur_Route_Data.Route_Main_Title);

        // 별점
        rating_star_ImageView[0] = (ImageView)view.findViewById(R.id.rating_star_0);
        rating_star_ImageView[1] = (ImageView)view.findViewById(R.id.rating_star_1);
        rating_star_ImageView[2] = (ImageView)view.findViewById(R.id.rating_star_2);
        rating_star_ImageView[3] = (ImageView)view.findViewById(R.id.rating_star_3);
        rating_star_ImageView[4] = (ImageView)view.findViewById(R.id.rating_star_4);

        int cur_Rating = Cur_Route_Data.Route_Rating_Num;
        setStar(cur_Rating);

        //프로필 사진

        route_profile_image = (RoundedImageView)view.findViewById(R.id.route_profile_image);
        Picasso.with(getContext()).load(Cur_Route_Data.User_Profile_URL).into(route_profile_image);

        //가이드 닉네임
        route_profile_name = (TextView)view.findViewById(R.id.route_profile_name);
        route_profile_name.setText(Cur_Route_Data.User_Name);

        //가이드 여부
        route_guide_possibility_Text = (TextView)view.findViewById(R.id.route_guide_possibility_Text);
        if(Cur_Route_Data.Route_Possibility){
            route_guide_possibility_Text.setText("Yes");
        }else{
            route_guide_possibility_Text.setText("No");
        }

        //가이드 가능 시간
        route_guide_time_Text = (TextView)view.findViewById(R.id.route_guide_time_Text);
        route_guide_time_Text.setText(Cur_Route_Data.Route_Available_Time+ " " + Cur_Route_Data.Route_Start_Time + " ~ " + Cur_Route_Data.Route_End_Time);

        // 수용 인원
        route_guide_guestNum_Text = (TextView)view.findViewById(R.id.route_guide_guestNum_Text);
        route_guide_guestNum_Text.setText(Cur_Route_Data.Route_Tourist_Num + "명");

        //route Location 개수를 통한 이미지 변경
        route_map_indicator_Image = (ImageView)view.findViewById(R.id.route_map_indicator_Image);
        route_map_indicator_Image.setImageDrawable(getContext().getDrawable(map_indicator_Res[Cur_Route_Data.Route_Locations.size()-1]));

        //route Detail Recycycler 설정
        route_detail_Container_RecyclerView = (RecyclerView)view.findViewById(R.id.route_detail_Container_RecyclerView);
        Route_Detail_Contents_Adapter adapter = new Route_Detail_Contents_Adapter(getContext(),Cur_Route_Data.Route_Locations);
        route_detail_Container_RecyclerView.setAdapter(adapter);
        route_detail_Container_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    void setStar(int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                rating_star_ImageView[i].setBackground(getContext().getDrawable(R.mipmap.star_solid));
            }else{
                rating_star_ImageView[i].setBackground(getContext().getDrawable(R.mipmap.star_blank));
            }
        }
    }

    void changeFragment(){
        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        Route_Detail_Fragment_2 fragment = new Route_Detail_Fragment_2();
        fragment.setFragmentData(fragment,Cur_Route_Data);
        fragmentTransaction.add(R.id.route_detail_FrameLayout,fragment);
        fragmentTransaction.commit();
    }

    boolean IsFavorited = false;

    void setFavorite(View view){
        favorite_ImageView = (ImageView)view.findViewById(R.id.favorite_ImageView);
        favorite_ImageView.setImageDrawable(getContext().getDrawable(R.mipmap.no_favorite_icon));

        for(int i= 0;i<AppData.getCur_User().User_Favorites_Route_List.size();i++){
            String index = AppData.getCur_User().User_Favorites_Route_List.get(i);
            if(Cur_Route_Data.Route_Index.equals(index)){
                favorite_ImageView.setImageDrawable(getContext().getDrawable(R.mipmap.favorite_icon));
                IsFavorited = true;
                break;
            }
        }

        favorite_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsFavorited){
                    IsFavorited = false;
                    favorite_ImageView.setImageDrawable(getContext().getDrawable(R.mipmap.no_favorite_icon));
                    AppData.getCur_User().User_Favorites_Route_List.remove(Cur_Route_Data.Route_Index);
                    AppData.myRef.child("users").child(AppData.StringReplace(AppData.getCur_User().User_ID)).setValue(AppData.getCur_User());
                }else{
                    IsFavorited = true;
                    favorite_ImageView.setImageDrawable(getContext().getDrawable(R.mipmap.favorite_icon));
                    AppData.getCur_User().User_Favorites_Route_List.add(Cur_Route_Data.Route_Index);
                    AppData.myRef.child("users").child(AppData.StringReplace(AppData.getCur_User().User_ID)).setValue(AppData.getCur_User());
                }

            }
        });
    }

}
