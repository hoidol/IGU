package com.iguideu.Signup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;

public class SignUpProgress1 extends Fragment {

    EditText first,last;
    TextView Progress1_Text;
    Context context;
    Button Progress1Btn;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    User cur_User;



    public  SignUpProgress1(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_progress1, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        first=(EditText)view.findViewById(R.id.edit_progress1_firstname);
        last=(EditText)view.findViewById(R.id.edit_progress1_secondname);
        Progress1_Text=(TextView)view.findViewById(R.id.progress1_text);
        Progress1Btn=(Button)view.findViewById(R.id.btn_progress1_next);

        Progress1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Progress1Clock(v);
            }
        });
    }

    public void Progress1Clock(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_progress1_next:




                if(first.getText().toString().equals("") || last.getText().toString().equals(""))
                {
                    Progress1_Text.setText("이름을 다시 입력하세요.");
                    Progress1_Text.setTextColor(getResources().getColor(R.color.Color_Login_Error));
                    Animation shake= AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    Progress1_Text.startAnimation(shake);
                }else{
                    cur_User.User_Name = first.getText().toString() + last.getText().toString();
                    Log.d(AppData.LOG_INDICATOR,"현재 이름 : " + cur_User.User_Name);
                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();

                    SignUpProgress2 fragment = new SignUpProgress2();
                    fragment.set_Cur_User(cur_User);

                    fragmentTransaction.replace(R.id.sign_up_framelayout,fragment);
                    fragmentTransaction.commit();
                }
                break;

        }
    }
    public void set_Cur_User(User user){
        this.cur_User = user;
    }

}
