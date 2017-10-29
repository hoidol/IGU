package com.iguideu.main_Profile;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.iguideu.custom_view.RoundedImageView;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.main_Inbox.message.MessageActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Hoyoung on 2017-10-05.
 */

public class Profile_Main_Fragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    RoundedImageView profile_ImageView;
    TextView profile_Nick_TextView;
    ImageView[] rating_starts = new ImageView[5];

    Button show_route_Btn;
    Button send_Message_Btn;

    String Profile_User_Id;

    public void set_Profile_User_Id(String user_id){
        Profile_User_Id= user_id;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_profile_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // User 인덱스 - AppData.StringReplace(Profile_User_Id)
        setToolbar(view);
        setProfile(view);
    }

    void setToolbar(View view){
        TextView toolbar_title_TextView = (TextView)view.findViewById(R.id.toolbar_title_TextView);
        toolbar_title_TextView.setText("프로필");
        toolbar_title_TextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.sp_16_7));
        ImageButton toolbar_back_ImagmeView = (ImageButton)view.findViewById(R.id.toolbar_back_ImagmeView);

        toolbar_back_ImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    void setProfile(final View view){
        profile_ImageView = (RoundedImageView)view.findViewById(R.id.profile_ImageView);
        profile_Nick_TextView = (TextView)view.findViewById(R.id.profile_Nick_TextView);
        rating_starts[0] = (ImageView)view.findViewById(R.id.rating_star_0);
        rating_starts[1]= (ImageView)view.findViewById(R.id.rating_star_1);
        rating_starts[2] = (ImageView)view.findViewById(R.id.rating_star_2);
        rating_starts[3]= (ImageView)view.findViewById(R.id.rating_star_3);
        rating_starts[4]= (ImageView)view.findViewById(R.id.rating_star_4);


        send_Message_Btn = (Button)view.findViewById(R.id.send_Message_Btn);

        if(Profile_User_Id.equals(AppData.getCur_User().User_ID)){
            send_Message_Btn.setVisibility(View.GONE);
        }



        send_Message_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class);
                intent.putExtra("Other_User_Id",Profile_User_Id);
                getContext().startActivity(intent);
                getActivity().finish();
            }
        });

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                final User cur_user = dataSnapshot.child("users").child(AppData.StringReplace(Profile_User_Id)).getValue(User.class);
                Picasso.with(getContext()).load(cur_user.User_Profile_URL).into(profile_ImageView);
                setStar(rating_starts,cur_user.User_Guide_Rating);



                show_route_Btn = (Button)view.findViewById(R.id.show_route_Btn);
                if(cur_user.User_Guide){
                    show_route_Btn.setVisibility(View.VISIBLE);
                }else{
                    show_route_Btn.setVisibility(View.GONE);
                }
                show_route_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Profile_Route_List_Fragment fragment = new Profile_Route_List_Fragment();
                        fragment.set_Profile_User_Id(Profile_User_Id);
                        fragment.set_Profile_Main_Fragment(fragment);

                        fm = getFragmentManager();
                        fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.add(R.id.profile_FrameLayout,fragment);
                        fragmentTransaction.commit();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(postListener);
    }

    void setStar(ImageView[] rating_starts, int cur_Rating){
        for(int i =0;i<5;i++){
            if(i<cur_Rating) {
                rating_starts[i].setBackground(getContext().getDrawable(R.mipmap.star_solid));
            }else{
                rating_starts[i].setBackground(getContext().getDrawable(R.mipmap.star_blank));
            }
        }
    }
}
