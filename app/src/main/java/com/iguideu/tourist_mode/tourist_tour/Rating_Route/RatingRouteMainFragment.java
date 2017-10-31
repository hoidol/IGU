package com.iguideu.tourist_mode.tourist_tour.Rating_Route;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class RatingRouteMainFragment extends Fragment {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    RecyclerView recyclerView;

    ImageView[] Route_Rating_ImageViews = new ImageView[5];
    ImageView[] Guider_Rating_ImageViews = new ImageView[5];
    Button complete_Btn;

    int Route_Rating_Value = 5;
    int Guider_Rating_Value = 5;

    String Route_Owner_Index;
    String Request_Index;
    String Route_Index;
    public RatingRouteMainFragment() {
        // Required empty public constructor
    }

    public void set_Rating_Data(String Route_Owner_Index,String Request_Index,  String Route_Index){
        this.Route_Owner_Index = Route_Owner_Index;
        this.Request_Index = Request_Index;
        this.Route_Index = Route_Index;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rating_route_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);
        setRating(view);
        setOnClickRating();
        setCompleteBtn(view);

    }
    void setToolbar(View view){
        TextView textView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        if(AppData.getApp_Language().equals("en")){
            textView.setText("Rating");
        }else{
            textView.setText("별점주기");
        }
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.history_Title));

        ImageButton button = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }
    void setRating(View view){
        Route_Rating_ImageViews[0] = (ImageView)view.findViewById(R.id.rating_route_star_0);
        Route_Rating_ImageViews[1] = (ImageView)view.findViewById(R.id.rating_route_star_1);
        Route_Rating_ImageViews[2] = (ImageView)view.findViewById(R.id.rating_route_star_2);
        Route_Rating_ImageViews[3] = (ImageView)view.findViewById(R.id.rating_route_star_3);
        Route_Rating_ImageViews[4] = (ImageView)view.findViewById(R.id.rating_route_star_4);

        Guider_Rating_ImageViews[0] = (ImageView)view.findViewById(R.id.rating_guider_star_0);
        Guider_Rating_ImageViews[1] = (ImageView)view.findViewById(R.id.rating_guider_star_1);
        Guider_Rating_ImageViews[2] = (ImageView)view.findViewById(R.id.rating_guider_star_2);
        Guider_Rating_ImageViews[3] = (ImageView)view.findViewById(R.id.rating_guider_star_3);
        Guider_Rating_ImageViews[4] = (ImageView)view.findViewById(R.id.rating_guider_star_4);
        setRouteStar(5);
        setGuiderStar(5);
    }

    int i, j;

    void setOnClickRating(){
        Route_Rating_ImageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Rating_Value =  1;
                setRouteStar(Route_Rating_Value);
            }
        });
        Route_Rating_ImageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Rating_Value = 2;
                setRouteStar(Route_Rating_Value);
            }
        });
        Route_Rating_ImageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Rating_Value = 3;
                setRouteStar(Route_Rating_Value);
            }
        });
        Route_Rating_ImageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Rating_Value = 4;
                setRouteStar(Route_Rating_Value);
            }
        });
        Route_Rating_ImageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route_Rating_Value = 5;
                setRouteStar(Route_Rating_Value);
            }
        });

        Guider_Rating_ImageViews[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guider_Rating_Value = 1;
                setGuiderStar(Guider_Rating_Value);
            }
        });
        Guider_Rating_ImageViews[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guider_Rating_Value = 2;
                setGuiderStar(Guider_Rating_Value);
            }
        });
        Guider_Rating_ImageViews[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guider_Rating_Value = 3;
                setGuiderStar(Guider_Rating_Value);
            }
        });
        Guider_Rating_ImageViews[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guider_Rating_Value = 4;
                setGuiderStar(Guider_Rating_Value);
            }
        });
        Guider_Rating_ImageViews[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guider_Rating_Value = 5;
                setGuiderStar(Guider_Rating_Value);
            }
        });
    }

    void setRouteStar(int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                Route_Rating_ImageViews[i].setBackground(getContext().getDrawable(R.mipmap.star_solid));
            }else{
                Route_Rating_ImageViews[i].setBackground(getContext().getDrawable(R.mipmap.star_blank));
            }
        }
    }

    void setGuiderStar(int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                Guider_Rating_ImageViews[i].setBackground(getContext().getDrawable(R.mipmap.star_solid));
            }else{
                Guider_Rating_ImageViews[i].setBackground(getContext().getDrawable(R.mipmap.star_blank));
            }
        }
    }

    void setCompleteBtn(View view){
        complete_Btn = (Button)view.findViewById(R.id.complete_Btn);
        complete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Route_Rating_Value == 0 || Guider_Rating_Value == 0){
                    // 아무것도 안누름...
                }else{

                    ValueEventListener listener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get Post object and use the values to update the USI
                            User guider = dataSnapshot.child("users").child(Route_Owner_Index).getValue(User.class);
                            Route_Data route = dataSnapshot.child("routes").child(Route_Index).getValue(Route_Data.class);
                            int guider_Rate =0;
                            if(guider.User_Guide_Rating != 0){
                                guider_Rate= guider.User_Guide_Rating + Guider_Rating_Value/2;
                            }else{
                                guider_Rate=  Guider_Rating_Value;
                            }
                            int route_Rate = 0;
                            if(route.Route_Rating_Num != 0){
                                route_Rate = route.Route_Rating_Num + Route_Rating_Value/2;
                            }else{
                                route_Rate = Route_Rating_Value;
                            }

                            AppData.myRef.child("users").child(Route_Owner_Index).child("User_Guide_Rating").setValue(guider_Rate);
                            AppData.myRef.child("routes").child(Route_Index).child("Route_Rating_Num").setValue(route_Rate);
                            AppData.myRef.child("requests").child(Request_Index).child("Request_State").setValue(4);

                            RatingRouteCompleteFragment fragment = new RatingRouteCompleteFragment();
                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            fragmentTransaction.replace(R.id.rating_route_FrameLayout,fragment);
                            fragmentTransaction.commit();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    };
                    AppData.myRef.addListenerForSingleValueEvent(listener);

                }
            }
        });
    }
}
