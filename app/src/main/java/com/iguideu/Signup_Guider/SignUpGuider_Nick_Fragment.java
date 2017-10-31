package com.iguideu.Signup_Guider;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.LogoActivity;
import com.iguideu.MainActivity;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.guide_mode.Route_Add_Activity.Guide_Route_Add_Fragment;

/**
 * Created by Hoyoung on 2017-09-03.
 */

public class SignUpGuider_Nick_Fragment extends Fragment {


    Context m_Context;
    EditText signup_nick_EditText;
    Button signup_nick_Btn;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public SignUpGuider_Nick_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_signup_guider_nick, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);
        signup_nick_EditText = (EditText)view.findViewById(R.id.signup_nick_EditText);
        signup_nick_EditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        signup_nick_Btn = (Button)view.findViewById(R.id.signup_nick_Btn);
        signup_nick_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signup_nick_EditText.getText().toString().equals("")){
                    // 아무것도 입력안함
                }else{
                    //가이드로 전환 가능

                    AppData.myRef.child("users").child(AppData.StringReplace(AppData.getCur_User().User_ID)).child("User_Guide").setValue(true);
                    AppData.myRef.child("users").child(AppData.StringReplace(AppData.getCur_User().User_ID)).child("User_Nick").setValue(signup_nick_EditText.getText().toString());

                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get Post object and use the values to update the UI
                            User cur_user = dataSnapshot.child("users").child(AppData.StringReplace(AppData.getCur_User().User_ID)).getValue(User.class);
                            AppData.setCur_User(cur_user);

                            fm = getFragmentManager();
                            fragmentTransaction = fm.beginTransaction();
                            Guide_Route_Add_Fragment fragment = new Guide_Route_Add_Fragment();
                            fragment.SetIsFirstGuide(true);
                            fragmentTransaction.replace(R.id.signup_guider_FrameLayout,fragment);
                            fragmentTransaction.commit();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Getting Post failed, log a message
                            // ...
                        }
                    };

                    AppData.myRef.addListenerForSingleValueEvent(postListener);



                }
            }
        });
    }

    void setToolbar(View view){
        TextView textView = (TextView)view.findViewById(R.id.toolbar2_title_TexView);
        textView.setText("");
        textView.setTextColor(Color.WHITE);

        TextView button = (TextView)view.findViewById(R.id.toolbar2_Close_Btn);
        button.setTextColor(getResources().getColor(R.color.IGU_Point_Color));

        if(AppData.getApp_Language().equals("en")){
            button.setText("close");
        }else{
            button.setText("닫기");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView complete_Btn =(TextView)view.findViewById(R.id.complete_Btn);
        complete_Btn.setVisibility(View.GONE);

    }
}
