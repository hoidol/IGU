package com.iguideu.Signup_Guider;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iguideu.R;

/**
 * Created by Hoyoung on 2017-09-03.
 */

public class SignUpGuider_Check_Fragment extends Fragment {

    Context m_Context;
    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    Button signup_Yes_Btn;
    Button signup_No_Btn;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.m_Context = context;
    }

    public SignUpGuider_Check_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_signup_guider_check, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signup_Yes_Btn =(Button)view.findViewById(R.id.signup_Yes_Btn);
        signup_No_Btn = (Button)view.findViewById(R.id.signup_No_Btn);

        signup_Yes_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = getFragmentManager();
                fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.signup_guider_FrameLayout,new SignUpGuider_Nick_Fragment());
                fragmentTransaction.commit();
            }
        });

        signup_No_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }

}
