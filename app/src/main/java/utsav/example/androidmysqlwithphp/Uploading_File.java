package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Uploading_File extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Button upload_file;
    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading__file);

        upload_file = (Button)findViewById(R.id.upload_file);

        upload_file.setOnClickListener(this);

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
    }

    @Override
    public void onClick(View v) {

        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://192.168.43.165/MyFold/index.php"));
                //Uri.parse("https://spiffier-farad.000webhostapp.com/MyFold/index.php"));
        startActivity(browserIntent);



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
                startActivity(new Intent(this, Uploading_File.class));

                return true;
            case R.id.item9:
                Toast.makeText(this, "Delete Notification Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UpdateNotification.class));

                return true;




        }
        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
