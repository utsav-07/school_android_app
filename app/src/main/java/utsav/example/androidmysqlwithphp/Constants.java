package utsav.example.androidmysqlwithphp;

/*
*  This is constant file containing the API's URL.
*  Author - Utsav Sinha /Shalabh Sinha
 */

public class Constants {

   // private static final String ROOT_URL = "http://192.168.43.165/MyFold/Android/v1/"; //connecting to local server/database
  private static final String ROOT_URL = "https://schlprj.000webhostapp.com/MyFold/Android/v1/";

    public static final String URL_REGISTER = ROOT_URL+"register.php";//not in use
    public static final String URL_LOGIN = ROOT_URL+"login.php";//for student
    public static final String URL_TEACHERLOGIN = ROOT_URL + "teacherlogin.php";//for teacher
    public static final String URL_INSERTRESULT = ROOT_URL + "insertresult.php";//not in use
    public static final String URL_VIEWRESULT = ROOT_URL + "viewstudentresult.php";//not in us
    public static final String URL_PDFUPLOAD = ROOT_URL +   "upload1.php";//not in use
    public static final String URL_NOTIFATION = ROOT_URL +  "Getpdf.php";//download notification
    public static final String URL_DELETE = ROOT_URL +  "deleteNotification.php";//delete notification

   /* public static final String URL_STUDENT_RESULT_WEB_VIEW = "http://192.168.43.165/MyFold/Android/result_creation_view/studentbtn_view.php";
    public static final String URL_SEND_NOTIFICATION = "http://192.168.43.165/MyFold/index.php";
    public static final String URL_RESULT_INSERT = "http://192.168.43.165/MyFold/Android/result_creation_view/ResultSectionPage.php";*/


    public static final String URL_STUDENT_RESULT_WEB_VIEW = "https://schlprj.000webhostapp.com/MyFold/Android/result_creation_view/studentbtn_view.php";
    public static final String URL_SEND_NOTIFICATION = "https://schlprj.000webhostapp.com/MyFold/index.php";
    public static final String URL_RESULT_INSERT = "https://schlprj.000webhostapp.com/MyFold/Android/result_creation_view/ResultSectionPage.php";
 // public static final String URL_RESULT_INSERT = "https://schlprj.000webhostapp.com/MyFold/Android/result_creation_view/ResultSectionPage.php";
    public  static final String URL_ADMIN_SECTION = "https://schlprj.000webhostapp.com/admin/admin_login.php";//for registeration of new student
  public  static final String URL_UPLOAD_EVENT_PIC = "https://schlprj.000webhostapp.com/phpScripts/uploadImage.php";//For uploding the event pic inupload_event_pic.java this url to be changed in upload_event_pics.java
    public  static final String URL_TAKE_STUDENT_ATTENDANCE =  "https://schlprj.000webhostapp.com/admin/student_attendance.php";
}


