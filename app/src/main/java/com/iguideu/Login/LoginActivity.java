package com.iguideu.Login;

import android.content.Intent;
import android.content.res.Configuration;
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
import java.util.List;
import java.util.Locale;

/**
 * Created by Hoyoung on 2017-07-14.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Button Login,GoogleLogin;
    String InitType;



    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login=(Button)findViewById(R.id.login_btn_login);
        GoogleLogin=(Button)findViewById(R.id.login_btn_google);

        InitType=AppData.getApp_Language();
        if(InitType=="kr" || InitType==null) {
            changeConfigulation("kr");
        }
        if(InitType=="en")
        {
            changeConfigulation(InitType);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

    }
    public void changeConfigulation(String type) {
        Locale mLocale = new Locale(type);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getResources().updateConfiguration(config, null);
    }

    public void LoginClick(View v)
    {
        switch(v.getId())
        {
            case R.id.login_btn_login:
                InitImage();
                Login.setBackground(getResources().getDrawable(R.drawable.base_btn_click));
                Login.setTextColor(getResources().getColor(R.color.Color_Base_Point));
                startActivity(new Intent(this,LoginActivity2.class));
                finish();
                //preference 추가해야합니다.

                break;
            case R.id.login_btn_google:
                InitImage();
                GoogleLogin.setBackground(getResources().getDrawable(R.drawable.base_btn_click));
                GoogleLogin.setTextColor(getResources().getColor(R.color.Color_Base_Point));

                signIn();
                //역시 마찬가지로 preference 추가해야합니다.

                break;
        }
    }

    public void InitImage()
    {
        Login.setBackground(getResources().getDrawable(R.drawable.base_btn));
        Login.setTextColor(getResources().getColor(R.color.Color_White));
        GoogleLogin.setBackground(getResources().getDrawable(R.drawable.base_btn));
        GoogleLogin.setTextColor(getResources().getColor(R.color.Color_White));
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
        AppData.setApp_AutoLogin(true);
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
                                        List<String> Favorite_list = new ArrayList<>();
                                        Favorite_list.add("-1");
                                        User user_inf = new User(acct.getEmail(), "google",acct.getDisplayName(),acct.getPhotoUrl().toString(),false,"",0,Favorite_list);
                                        Log.d(AppData.LOG_INDICATOR,"호출 타이밍 체크 setDatas() ");
                                        AppData.myRef.child("users").child(user_key).setValue(user_inf);
                                        AppData.setCur_User(user_inf);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            };
                            AppData.myRef.addListenerForSingleValueEvent(listener);
                        }else{

                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
