package com.iguideu.signup;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;

public class SignUpProgress3 extends Fragment {

    Context context;

    TextView pg3_text;
    EditText edit_pg3_email;
    Button btn_pg3_next;

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
        return inflater.inflate(R.layout.fragment_sign_up_progress3, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pg3_text = (TextView)view.findViewById(R.id.pg3_text);
        edit_pg3_email = (EditText)view.findViewById(R.id.edit_pg3_email);

        btn_pg3_next = (Button)view.findViewById(R.id.btn_pg3_next);
        btn_pg3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Progress3Clock(v);
            }
        });

    }



    public void Progress3Clock(View v) {
        switch (v.getId()) {
            case R.id.btn_pg3_next:
                String id = edit_pg3_email.getText().toString();
                if (id.equals("")) {
                    shakeUI(pg3_text, false,"이메일을 다시 입력하세요");
                }else if(!AppData.isEmailPattern(id)){
                    shakeUI(pg3_text, false,"이메일 양식으로 입력하세요");
                }
                else {
                    String email = edit_pg3_email.getText().toString();
                    checkID(email);
                    break;
                }
        }
    }

    void checkID(final String Id){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String user_key = AppData.StringReplace(Id);
                long count = dataSnapshot.child("users").child(user_key).getChildrenCount();
                if(count == 0){
                    shakeUI(pg3_text, true,null);
                    cur_User.User_ID = Id;
                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();

                    SignUpProgress4 fragment = new SignUpProgress4();
                    fragment.set_Cur_User(cur_User);

                    fragmentTransaction.replace(R.id.sign_up_framelayout, fragment);
                    fragmentTransaction.commit();
                }else{
                    //이미 아이디있음
                    shakeUI(pg3_text, false,"가입된 이메일입니다.");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        AppData.myRef.addListenerForSingleValueEvent(listener);
    }

    void shakeUI(final TextView textView, final boolean result, final String commnet)
    {
        Handler myHandler=new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(result == false){
                    textView.setText(commnet);
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
