package utsav.example.androidmysqlwithphp;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager_Students {

    private static SharedPrefManager_Students mInstance1;
    private static Context mCtx1;

    //private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String SHARED_PREF_NAME1 = "mysharedpref11";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_USER_EMAIL = "useremail";
    private static final String KEY_USER_ID = "userid";


    private SharedPrefManager_Students(Context context) {
        mCtx1 = context;

    }

    public static synchronized SharedPrefManager_Students getInstance1(Context context) {
        if (mInstance1 == null) {
            mInstance1 = new SharedPrefManager_Students(context);
        }
        return mInstance1;
    }




    //for student
    public boolean userLogin1(int id, String username, String email){

        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USERNAME, username);

        editor.apply();

        return true;
    }










  /*  public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }
        return false;
    }*/

    //for student

    public boolean isLoggedIn1(){
        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME, null) != null){
            return true;
        }
        return false;
    }




    public boolean logout1(){
        SharedPreferences sharedPreferences = mCtx1.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


   /* public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);
    }*/
}
