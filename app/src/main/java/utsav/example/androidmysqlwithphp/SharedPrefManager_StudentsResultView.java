package utsav.example.androidmysqlwithphp;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager_StudentsResultView {

    private static SharedPrefManager_StudentsResultView mInstanceresultview;
    private static Context mCtxresultview;

    //private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String SHARED_PREF_NAME_RESUT_VIEW = "mysharedpref20";
    private static final String KEY_USER_ID                 = "id";
    private static final String KEY_STUDENTNAME             = "studentname";
    private static final String KEY_CLASS_SECTION           = "class_section";
    private static final String KEY_ROLLNO                  = "rollno";
    private static final String KEY_YEAR                    = "year";
    private static final String KEY_SEMESTER                = "semester";
    private static final String KEY_SUBJ_MATH               = "subj_math";
    private static final String KEY_SCIENCE                 = "subj_science";
    private static final String KEY_SSC                     = "subj_ssc";
    private static final String KEY_HINDI                   = "subj_hindi";
    private static final String KEY_ENGLISH                 = "subj_english";
    private static final String KEY_SNANKRIT                = "subj_sanskrit";
    private static final String KEY_COMPUTER                = "subj_computer";
    private static final String KEY_GK                      = "subj_gk";
    private static final String KEY_DRAWING                 = "subj_drawing";
    private static final String KEY_FAIRCOPY                = "subj_faircopy";
    private static final String KEY_PROJECT                 = "subj_project";


    private SharedPrefManager_StudentsResultView(Context context) {
        mCtxresultview = context;

    }

    public static synchronized SharedPrefManager_StudentsResultView getInstanceviewresult(Context context) {
        if (mInstanceresultview == null) {
            mInstanceresultview = new SharedPrefManager_StudentsResultView(context);
        }
        return mInstanceresultview;
    }




    //for student
    public boolean studentresult(int id, String studentname, String class_section , String rollno , String year , String semester , String subj_math, String subj_science , String subj_ssc , String subj_hindi , String subj_english , String subj_sanskrit, String subj_computer , String subj_gk , String subj_drawing , String subj_faircopy , String subj_project  ){

        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_STUDENTNAME, studentname);
        editor.putString(KEY_CLASS_SECTION, class_section);
        editor.putString(KEY_ROLLNO, rollno);
        editor.putString(KEY_YEAR, year);
        editor.putString(KEY_SEMESTER,semester);
        editor.putString(KEY_SUBJ_MATH, subj_math);
        editor.putString(KEY_SCIENCE, subj_science);
        editor.putString(KEY_SSC, subj_ssc);
        editor.putString(KEY_HINDI, subj_hindi);
        editor.putString(KEY_ENGLISH, subj_english);
        editor.putString(KEY_SNANKRIT, subj_sanskrit);
        editor.putString(KEY_COMPUTER, subj_computer);
        editor.putString(KEY_GK, subj_gk);
        editor.putString(KEY_DRAWING, subj_drawing);
        editor.putString(KEY_FAIRCOPY, subj_faircopy);
        editor.putString(KEY_PROJECT, subj_project);

        editor.apply();

        return true;
    }












    public String getStudentname(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STUDENTNAME, null);
    }

    public String getclass_sec(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CLASS_SECTION, null);
    }
    public String getrollno(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLLNO, null);
    }
    public String getyear(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_YEAR, null);
    }
    public String getsemester(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SEMESTER, null);
    }
    public String getmath(  ){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SUBJ_MATH, null);
    }
    public String getscience(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SCIENCE, null);
    }
    public String getssc(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SSC, null);
    }public String gethindi(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_HINDI, null);
    }
    public String getenglish(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ENGLISH, null);
    }
    public String getsanskrit(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SNANKRIT, null);
    }
    public String getcomputer(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COMPUTER, null);
    }
    public String getgk(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GK, null);
    }public String getdrawing(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DRAWING, null);
    }public String getfaircopy(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FAIRCOPY, null);
    }
    public String getproject(){
        SharedPreferences sharedPreferences = mCtxresultview.getSharedPreferences(SHARED_PREF_NAME_RESUT_VIEW, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PROJECT, null);
    }



}
