package com.iguideu.signup;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.User;

public class SignUpProgress2 extends Fragment {

    Context context;

    Spinner pg2_spinner_number;
    EditText edit_pg2_number;
    TextView pg2_text;
    Button btn_progress2_next;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;

    User cur_User;

    String start_Phone_Num;
    String Phone_Num;

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
        return inflater.inflate(R.layout.fragment_sign_up_progress2, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setSpinner(view);

        edit_pg2_number = (EditText)view.findViewById(R.id.edit_pg2_number);
        pg2_text = (TextView)view.findViewById(R.id.pg2_text);

        btn_progress2_next = (Button)view.findViewById(R.id.btn_progress2_next);
        btn_progress2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Progress2Clock(v);
            }
        });

    }

    void setSpinner(View view){
        pg2_spinner_number = (Spinner)view.findViewById(R.id.pg2_spinner_number);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(getContext(),R.array.phone_item,R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pg2_spinner_number.setAdapter(adapter);
        pg2_spinner_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String[] cur_Values = getResources().getStringArray(R.array.phone_item);
                Log.d(AppData.LOG_INDICATOR,"현재 앞 번호 : " + cur_Values[position]);
                start_Phone_Num = cur_Values[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }


    public void Progress2Clock(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_progress2_next:
                if(edit_pg2_number.getText().toString().equals("") || edit_pg2_number.getText().length() != 8){
                    pg2_text.setText("번호를 입력해주세요.");
                    pg2_text.setTextColor(getResources().getColor(R.color.Color_Login_Error));
                    Animation shake= AnimationUtils.loadAnimation(getContext(),R.anim.shake);
                    pg2_text.startAnimation(shake);
                }else{
                    fm = getFragmentManager();
                    fragmentTransaction = fm.beginTransaction();
                    String Phone_Num = start_Phone_Num + edit_pg2_number.getText().toString();
                    cur_User.User_Phone_Num = Phone_Num;

                    SignUpProgress3 fragment = new SignUpProgress3();
                    fragment.set_Cur_User(cur_User);

                    fragmentTransaction.replace(R.id.sign_up_framelayout,fragment);
                    fragmentTransaction.commit();
                }


                break;
        }
    }
}
