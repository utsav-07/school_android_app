package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Student_Result_Web_View extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private WebView wv1;
    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__result__web__view);



        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //String url = "http://192.168.43.165/MyFold/Android/result_creation_view/studentbtn_view.php";
        String url = Constants.URL_STUDENT_RESULT_WEB_VIEW;
        // String url = "http://192.168.43.165/angularjs_insert_update_delete_new/HigerClass.php";
        // String url = "https://www.youtube.com/";
        wv1=(WebView)findViewById(R.id.web_view);
        wv1.getSettings().setOffscreenPreRaster(true);

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setBuiltInZoomControls(true);
        wv1.getSettings().setDisplayZoomControls(false);

        // wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);

        //wv1=(WebView)findViewById(R.id.web_view);
        wv1.setWebChromeClient(new MyBrowser());
        //wv1.setWebViewClient(new MyBrowser());
        //wv1.setWebViewClient(new WebViewClient() );

    }


    /* Log out section start */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager_Students.getInstance1(this).logout1();
                finish();
                startActivity(new Intent(this, Displaypage.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
    /* Log out section End */

    private class MyBrowser extends WebChromeClient {
        //  @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv1.canGoBack()) {
                        wv1.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item1:
                Toast.makeText(this, "About Us Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Displaypage.class));
                return true;
            case R.id.item2:
                Toast.makeText(this, "Teacher Login Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Teacherlogin.class));
                return true;
            case R.id.item3:
                Toast.makeText(this, "Student Login Is Selected", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));//student login
                return true;
            case R.id.item5:
                Toast.makeText(this, "Notification Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, NotificationDownload.class));

                return true;
            case R.id.item6:
                Toast.makeText(this, "Contact Us Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Contact_Us_Mail.class));

                return true;
            case R.id.item4:
                Toast.makeText(this, "Gallery is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Staffinfo.class));

                return true;

            case R.id.item9:
                Toast.makeText(this, "Our Staff ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ShowTeachers.class));

                return true;




        }
        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
