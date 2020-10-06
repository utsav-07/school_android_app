package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Contact_Us_Mail extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
   // private EditText mEditTextTo;
    private EditText Message;
    //private Spinner spineritem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us__mail);

        //mEditTextTo =  (EditText)  findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);



        mEditTextMessage = (EditText) findViewById(R.id.itemlist);


        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Button buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

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
                Toast.makeText(this, "Gallery Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Staffinfo.class));

                return true;
            case R.id.item8:
                Toast.makeText(this, "admin section ", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(this, admin_panel.class));
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,

                        Uri.parse(Constants.URL_ADMIN_SECTION));

                startActivity(browserIntent);

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

    private void sendMail() {
        String recipientList = "utsavudit07@gmail.com";
      //  String recipientList = mEditTextTo.getText().toString();
       // mEditTextTo.setText("utsavudit07@gmail.com");
        String[] recipients = recipientList.split(",");
        String subject = mEditTextSubject.getText().toString();
        String Message = mEditTextMessage.getText().toString();
        if(TextUtils.isEmpty(subject)){
            mEditTextSubject.setError("Blank not allowed");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, Message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Please Choose Only Gmail Option"));

        Toast.makeText(this,"Please choose only gmail option while sending email, We will contact you shortly",Toast.LENGTH_SHORT).show();
        ClearTextItem();
    }

    private void ClearTextItem(){

        mEditTextSubject.setText("");
        mEditTextMessage.setText("");

    }

}


