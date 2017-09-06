package com.iguideu.Signup;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.User;

public class SignUpProgress4 extends Fragment {

    Context context;

    TextView pg4_text;

    EditText edit_pg4_pass;
    EditText edit_pg4_pass_check;

    Button btn_pg4_next;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    User cur_User;

    public void set_Cur_User(User user){
        this.cur_User = user;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up_progress4, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         pg4_text = (TextView)view.findViewById(R.id.pg4_text);

         edit_pg4_pass = (EditText)view.findViewById(R.id.edit_pg4_pass);
         edit_pg4_pass_check = (EditText)view.findViewById(R.id.edit_pg4_pass_check);

        btn_pg4_next = (Button)view.findViewById(R.id.btn_pg4_next);
        btn_pg4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Progress4Clock(v);
            }
        });

    }


    public void Progress4Clock(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_pg4_next:
                if (edit_pg4_pass.getText().toString().equals("") || edit_pg4_pass_check.getText().toString().equals("")) {
                    shakeUI(pg4_text,false,"비밀번호를 모두 입력하세요");
                } else if(!edit_pg4_pass.getText().toString().equals(edit_pg4_pass_check.getText().toString())){
                    // 비밀번호와 확인 번호가 다름
                    shakeUI(pg4_text,false,"비밀번호가 다릅니다");

                }else {
                    shakeUI(pg4_text,true,null);

                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    String password = edit_pg4_pass.getText().toString();
                    cur_User.User_Password = password;

                    SignUpProgress5 fragment = new SignUpProgress5();
                    fragment.set_Cur_User(cur_User);

                    fragmentTransaction.replace(R.id.sign_up_framelayout, fragment);
                    fragmentTransaction.commit();
                    break;
                }

        }
    }

    void shakeUI(final TextView textView, final boolean result, final String comment)
    {
        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(result == false){
                    textView.setText(comment);
                    textView.setVisibility(View.VISIBLE);
                    textView.setTextColor(getResources().getColor(R.color.Color_Login_Error));
                    Animation shake= AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.shake);
                    textView.startAnimation(shake);
                }else{
                    textView.setVisibility(View.GONE);
                }
            }
        };
        myHandler.sendEmptyMessageDelayed(0,0);
    }
}
