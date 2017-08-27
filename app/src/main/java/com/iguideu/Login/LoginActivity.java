package com.iguideu.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iguideu.MainActivity;
import com.iguideu.R;
import com.iguideu.data.AppData;
import com.iguideu.data.ChattingRoom;
import com.iguideu.data.Request_Data;
import com.iguideu.data.Route_Data;
import com.iguideu.data.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hoyoung on 2017-07-14.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Button Login,GoogleLogin;



    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login=(Button)findViewById(R.id.login_btn_login);
        GoogleLogin=(Button)findViewById(R.id.login_btn_google);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

    }


    public void LoginClick(View v)
    {
        switch(v.getId())
        {
            case R.id.login_btn_login:
                InitImage();
                Login.setBackground(getResources().getDrawable(R.mipmap.initsetting_activity_menubox_solid));
                Login.setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_text));
                startActivity(new Intent(this,LoginActivity2.class));
                finish();
                //preference 추가해야합니다.

                break;
            case R.id.login_btn_google:
                InitImage();
                GoogleLogin.setBackground(getResources().getDrawable(R.mipmap.initsetting_activity_menubox_solid));
                GoogleLogin.setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_text));

                signIn();
                //역시 마찬가지로 preference 추가해야합니다.

                break;
        }
    }

    public void InitImage()
    {
        Login.setBackground(getResources().getDrawable(R.mipmap.initsetting_activity_menubox_blank));
        Login.setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
        GoogleLogin.setBackground(getResources().getDrawable(R.mipmap.initsetting_activity_menubox_blank));
        GoogleLogin.setTextColor(getResources().getColor(R.color.Color_Init_Btn_Focus_none_Text));
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, AppData.GOOGLE_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == AppData.GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();

                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        AppData.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            ValueEventListener listener = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // Get Post object and use the values to update the UI

                                    String user_key = AppData.StringReplace(acct.getEmail());
                                   long count = dataSnapshot.child("users").child(user_key).getChildrenCount();

                                    if(count == 0){
                                        //★★★★★★★★★★★ Database에 유저 등록★★★★★★★★★★★
                                        User user_inf = new User(acct.getEmail(), "google",acct.getDisplayName(),acct.getPhotoUrl().toString(),false,"",0,"",new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());

                                        AppData.myRef.child("users").child(user_key).setValue(user_inf);
                                        AppData.setCur_User(user_inf);

                                        //메인으로 이동
                                        Handler myHandler=new Handler(){
                                            public void handleMessage(Message msg) {
                                                super.handleMessage(msg);
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        };
                                        myHandler.sendEmptyMessage(0);


                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            };
                            AppData.myRef.addListenerForSingleValueEvent(listener);
                            AppData.setApp_AutoLogin(true);


                        }else{

                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
