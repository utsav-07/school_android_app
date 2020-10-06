package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


/**
 * This class is used after teacher's login functionality
 * This intent shoing 3 buttons.
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */
public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TextView textViewUsername, textViewUserEmail;
    private Button next;
    private Button uploadpdf;
    private Button viewpdfforteacher;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, Teacherlogin.class));
        }


    /*    next = (Button)findViewById(R.id.button);
        uploadpdf = (Button)findViewById(R.id.uploadpdfbtn);
        viewpdfforteacher = (Button)findViewById(R.id.viewpdfforteacher);*/

/*
        next.setOnClickListener(this);
        uploadpdf.setOnClickListener(this);
        viewpdfforteacher.setOnClickListener(this);*/

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

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, Displaypage.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }


  /*  @Override
    public void onClick(View v) {
        if(v==next)
            startActivity(new Intent(this, Resultinsert.class));
        if(v==uploadpdf)
            startActivity(new Intent(this, Uploading_File.class));
        if(v==viewpdfforteacher)
            startActivity(new Intent(this, UpdateNotification.class));
    }*/




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

            case R.id.item7:
                Toast.makeText(this, "Upload Student Result Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Result_Insert_Web_View.class));

                return true;
            case R.id.item8:
                Toast.makeText(this, "Send Notification Selected", Toast.LENGTH_SHORT).show();
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        //Uri.parse("http://192.168.43.165/MyFold/index.php"));
                Uri.parse(Constants.URL_SEND_NOTIFICATION));
                //Uri.parse("https://spiffier-farad.000webhostapp.com/MyFold/index.php"));
                startActivity(browserIntent);
               // startActivity(new Intent(this, Uploading_File.class));

                return true;
            case R.id.item9:
                Toast.makeText(this, "Delete Notification Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UpdateNotification.class));

                return true;

            case R.id.item4:
                Toast.makeText(this, "Gallery ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Staffinfo.class));

                return true;
            case R.id.item11:
                Toast.makeText(this, "upload event pics ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Upload_Event_Pics.class));

                return true;
            case R.id.item12:
                Toast.makeText(this, "Our Staff ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ShowTeachers.class));

                return true;

            case R.id.item13:
                Toast.makeText(this, "Take Attendence", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Take_student_attendance.class));

                return true;




        }
        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}