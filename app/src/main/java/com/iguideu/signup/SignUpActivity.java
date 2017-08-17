package com.iguideu.signup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iguideu.R;
import com.iguideu.data.User;

public class SignUpActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = new User();

        fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();

        SignUpProgress1 fragment = new SignUpProgress1();
        fragment.set_Cur_User(user);

        fragmentTransaction.add(R.id.sign_up_framelayout,fragment);
        fragmentTransaction.commit();
    }
}
