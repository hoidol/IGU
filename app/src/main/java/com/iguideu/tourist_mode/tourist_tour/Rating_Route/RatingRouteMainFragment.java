package com.iguideu.tourist_mode.tourist_tour.Rating_Route;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.tourist_mode.tourist_home.route.RouteRecyclerAdapter;

/**
 * Created by Hoyoung on 2017-08-30.
 */

public class RatingRouteMainFragment extends Fragment {

    Context m_Context;
    RecyclerView recyclerView;

    ImageView[] Route_Rating_ImageViews = new ImageView[5];
    ImageView[] Guider_Rating_ImageViews = new ImageView[5];

    int Route_Rating_Value = 0;
    int Guider_Rating_Value = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public RatingRouteMainFragment() {
        // Required empty public constructor
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
    }

    void setToolbar(View view){
        TextView textView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        textView.setText("별점주기");
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

    int i, j;

    void setOnClickRating(){
        for(i = 0; i < Route_Rating_ImageViews.length; i++){
            Route_Rating_ImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Route_Rating_Value = i + 1;
                    setRouteStar(Route_Rating_Value);
                }
            });
        }

        for(j = 0; j < Route_Rating_ImageViews.length; j++){
            Guider_Rating_ImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Guider_Rating_Value = i + 1;
                    setGuiderStar(Guider_Rating_Value);
                }
            });
        }
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
}
