package com.iguideu.Signup;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.List;

public class SignUpProgress6 extends Fragment {

    Context context;
    Activity activity;
    Button btn_pg6_next;

    User cur_User;

    public void set_Cur_User(User user){
        this.cur_User = user;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (SignUpActivity) activity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_progress6, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_pg6_next = (Button)view.findViewById(R.id.btn_pg6_next);
        btn_pg6_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Progress6Clock(v);
            }
        });
        saveUserData();
    }

    void saveUserData(){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                //★★★★★★★★★★★ Database에 유저 등록★★★★★★★★★★★

                String user_key = AppData.StringReplace(cur_User.User_ID);
                List<String> Favorite_list = new ArrayList<>();
                Favorite_list.add("-1");
                User user_inf = new User(cur_User.User_ID, cur_User.User_Password,cur_User.User_Name,cur_User.User_Profile_URL,false,null,0,Favorite_list);

                AppData.myRef.child("users").child(user_key).setValue(user_inf);
                AppData.setCur_User(user_inf);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }


    public void Progress6Clock(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_pg6_next:
                activity.finish();
                break;

        }
    }
}
