package com.iguideu.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.iguideu.MainActivity;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;
import com.iguideu.signup.SignUpActivity;

public class LoginActivity2 extends AppCompatActivity {

    Button LoginPrefrence;
    User user;
    boolean AutoCheck=false;
    EditText id,pass;
    TextView error;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        LoginPrefrence=(Button)findViewById(R.id.btn_login_pref_check);
        getWindow().setWindowAnimations(0);



        id=(EditText)findViewById(R.id.edit_login_id);
        pass=(EditText)findViewById(R.id.edit_login_pass);
        error=(TextView)findViewById(R.id.login2_error);
        AppData.setApp_AutoLogin(false);

        setFirebase();
    }

    void setFirebase(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public void Login2Click(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_login_check:
                if(id.getText().toString().equals("") || pass.getText().toString().equals(""))
                {
                    shakeUI(error, false, "아이디/비밀번호 확인해주세요.");
                }else {
                    error.setVisibility(View.GONE);
                    checkLogin(id.getText().toString(),pass.getText().toString());
                }
                    break;
            case R.id.btn_login_pref_check:
                if(AutoCheck==false)
                {
                    AutoCheck=true;

                    LoginPrefrence.setBackground(getResources().getDrawable(R.color.Color_Login_AutoLogin_CheckBox));
                }else{
                    AutoCheck=false;
                    LoginPrefrence.setBackground(getResources().getDrawable(R.mipmap.checkboxblank));
                }
                AppData.setApp_AutoLogin(AutoCheck);
                break;

            case R.id.text_login_sign_up:
                AppData.setApp_AutoLogin(false);
                startActivity(new Intent(this,SignUpActivity.class));
                break;
        }
    }


    void checkLogin(final String User_Id, final String User_Pass){

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the USI
                Iterable<DataSnapshot> iterable = dataSnapshot.child("users").getChildren();
                while (iterable.iterator().hasNext()){
                    DataSnapshot cur_Snapshot = iterable.iterator().next();
                    String id =  cur_Snapshot.child("User_ID").getValue().toString();
                    String pass = cur_Snapshot.child("User_Password").getValue().toString();

                    if(id == null || pass == null)
                        return;

                    if(id.equals(User_Id)){
                        //아이디가 존재함
                        if(pass.equals(User_Pass)){
                            User cur_User = cur_Snapshot.getValue(User.class);
                            AppData.setCur_User(cur_User);
                            Intent intent = new Intent(LoginActivity2.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            shakeUI(error, true, null);
                        }else{
                            shakeUI(error, false, "아이디/비밀번호 확인해주세요.");
                        }
                        break;

                    }else{
                        shakeUI(error, false, "아이디/비밀번호 확인해주세요.");
                    }
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
                    Animation shake= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
                    textView.startAnimation(shake);
                }else if(result == true){
                    Log.d(AppData.LOG_INDICATOR,"shakeUI 실행됨");
                    textView.setVisibility(View.GONE);
                }
            }
        };
        myHandler.sendEmptyMessageDelayed(0,0);
    }


}
