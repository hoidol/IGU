package com.iguideu.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Hoyoung on 2017-07-19.
 */

public class AppData extends AppCompatActivity {

    private static AppData instance;

    private static User Cur_User;

    private AppData(){
    }

    public static AppData getAppDataInstance(){
        if(instance == null){
            instance = new AppData();
        }
        return  instance;
    }

    public static final String LOG_INDICATOR = "LOG";
    public static final int GOOGLE_SIGN_IN = 0001;
    public static final int REQUEST_CODE_GALLERY = 1001;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2001;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;

    private static SharedPreferences preferences;

    // 앱 설정 관련
    private static String app_Language = "NULL"; // "KR" ->한글, "EN" -> 영어 ,NULL -> 미선택(Defulat)
    private static Boolean app_AutoLogin = false; // Default = 자동 로그인 안함
    private static int app_Mode = 0; // 0 - Tourist 모드[디폴트] 1 - Guide 모드
    private static Boolean app_Alarm = true; // 알람 설정 true - 알람 울림[디폴트] false - 알람 안울림
    //private static Boolean app_OnAuth = false; // 자동 로그인과 로그아웃 차이를 확실히하기

    // 권한 관련
    private static Boolean app_Permission_Location;
    private static Boolean app_Permission_Storage;

    // 데이터 관련
    public static List<Route_Data> Route_Data_List;
    public static List<Feed_Data> Feed_Data_List;
    public static List<User> Guider_Data_List;
    public static List<Request_Data> Request_Data_List;
    public static ArrayList<LatLng> PinPointData =new ArrayList<>();
    public static List<ChattingRoom> ChattingRoom_Data_List;

    public static List<Route_Data> Attraction_Route_List;
    public static List<Route_Data> Recommend_Route_List;
    public static List<User> Recommend_Guider_List;
    public static ArrayList<EditText> ListEditId=new ArrayList<>();
    //Firebase 관련
    public static FirebaseAuth mAuth;
    public static FirebaseDatabase database;
    public static DatabaseReference myRef;
    public static FirebaseStorage storage;
    public static StorageReference storageRef;
    public static FirebaseAuth.AuthStateListener mAuthStateListener;


    public static String getCurTime(){
        Date cur_Date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss"); //년 월 일 시 분 초
        String Data_Format = sdf.format(cur_Date);
        return Data_Format;
    }
    public static String getCurTime(String dateFormat){
        Date cur_Date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat); //년 월 일 시 분 초
        String Data_Format = sdf.format(cur_Date);
        return Data_Format;
    }
    public static String getApp_Language(){
        String app_Language = AppData.preferences.getString("app_Language","NULL");
        return app_Language;
    }
    public static void setApp_Language(String language){
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putString("app_Language",language);
        editor.commit();
        app_Language = language;
    }
    public static void setApp_AutoLogin(Boolean bool){
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putBoolean("app_AutoLogin",bool);
        editor.commit();
        app_AutoLogin = bool;
    }
    public static Boolean getApp_AutoLogin(){
        Boolean app_AutoLogin = AppData.preferences.getBoolean("app_AutoLogin",false);
        return app_AutoLogin;
    }
    public static void setApp_Mode(int mode){
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putInt("app_Mode",mode);
        editor.commit();
        app_Mode = mode;
    }
    public static int getApp_Mode(){
        int app_Mode = AppData.preferences.getInt("app_Mode",0);
        return app_Mode;
    }
    /* public static Boolean getApp_OnAuth() {
         boolean app_OnAuth = AppData.preferences.getBoolean("app_OnAuth",false);
         return app_OnAuth;
     }
     public static void setApp_OnAuth(Boolean app_OnAuth) {
         SharedPreferences.Editor editor = AppData.preferences.edit();
         editor.putBoolean("app_OnAuth",app_OnAuth);
         editor.commit();
         AppData.app_OnAuth = app_OnAuth;
     }*/
    public static void setCur_User(User user){
        Cur_User = user;
    }
    public static User getCur_User(){
        return  Cur_User;
    }
    public static Boolean getApp_Alarm() {
        boolean app_Alarm = AppData.preferences.getBoolean("app_Alarm",true);
        return app_Alarm;
    }
    public static void setApp_Alarm(Boolean app_Alarm) {
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putBoolean("app_Alarm",app_Alarm);
        editor.commit();

        AppData.app_Alarm = app_Alarm;
    }
    public static SharedPreferences getPreferences() {
        return preferences;
    }
    public static void setPreferences(SharedPreferences preferences) {
        AppData.preferences = preferences;
    }
    public static String StringReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, " ");
        return str;
    }
    public static boolean isEmailPattern(String email){
        Pattern pattern= Pattern.compile("\\w+[@]\\w+\\.\\w+");
        Matcher match=pattern.matcher(email);
        return match.find();
    }
    public static Boolean getApp_Permission_Location() {
        boolean app_Permission_Location = AppData.preferences.getBoolean("app_Permission_Location",false);
        return app_Permission_Location;
    }
    public static void setApp_Permission_Location(Boolean app_Permission_Location) {
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putBoolean("app_Permission_Location",app_Permission_Location);
        editor.commit();
        AppData.app_Permission_Location = app_Permission_Location;
    }
    public static Boolean getApp_Permission_Storage() {
        boolean app_Permission_Storage = AppData.preferences.getBoolean("app_Permission_Storage",false);
        return app_Permission_Storage;
    }
    public static void setApp_Permission_Storage(Boolean app_Permission_Storage) {
        SharedPreferences.Editor editor = AppData.preferences.edit();
        editor.putBoolean("app_Permission_Storage",app_Permission_Storage);
        editor.commit();
        AppData.app_Permission_Storage = app_Permission_Storage;
    }

    void SetAppData(){

    }

    public static void SetFirebase(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://iguideu-4befb.appspot.com/");
    }

    public static void Test(){
        String s = "제발 되라";
    }



}
