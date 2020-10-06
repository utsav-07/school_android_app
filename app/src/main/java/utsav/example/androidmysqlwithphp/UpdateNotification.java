package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
//import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static utsav.example.androidmysqlwithphp.Constants.URL_DELETE;
import static utsav.example.androidmysqlwithphp.Constants.URL_NOTIFATION;

/**
 * This class is for displaying the uploaded files
 *and also has SWIPE functionality for deleting the records
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

public class UpdateNotification extends AppCompatActivity implements  RecyclerView.OnClickListener,NavigationView.OnNavigationItemSelectedListener {


    //Rest API for fetching all notification
    public static final String PDF_FETCH_URL = URL_NOTIFATION;
    public static final String PDF_DELETE_URL = URL_DELETE;

    ImageView imageView;


    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Image request code
    private int PICK_PDF_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;



    //Uri to store the image uri
    private Uri filePath;

    //for recycler veiw
    RecyclerView recyclerView;

    //button to fetch the intiate the fetching of pdfs.
    Button buttonFetch;

    //Progress bar to check the progress of obtaining pdfs
    ProgressDialog progressDialog;

    //an array to hold the different pdf objects
    ArrayList<Pdf> pdfList= new ArrayList<Pdf>();

    //pdf adapter

    UpdateNotificationAdapter updateNotificationAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdfforteacher);



        ///copy start
        //Requesting storage permission
        requestStoragePermission();


        recyclerView = (RecyclerView) findViewById(R.id.listingView);

        //initializing buttonFetch
        buttonFetch = (Button) findViewById(R.id.buttonFetchPdf);

        //initializing progressDialog

        progressDialog = new ProgressDialog(this);

        //Setting clicklistener

        buttonFetch.setOnClickListener(this);

        getPdfs();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //removeItem((int) viewHolder.itemView.getTag());

                removeItem(viewHolder.itemView.getTag().toString());
               // updateNotificationAdapter.notifyDataSetChanged();
                updateNotificationAdapter.removeItem(viewHolder.getAdapterPosition());

            }

            // below code added delete icon - to swipe left to right and vice-versa notification is deleted

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(UpdateNotification.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(UpdateNotification.this, R.color.colorAccent))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_sweep_black_24dp)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(UpdateNotification.this, R.color.colorPrimaryDark))
                        .addSwipeRightActionIcon(R.drawable.ic_delete_sweep_black_24dp)
                        .setActionIconTint(ContextCompat.getColor(recyclerView.getContext(), android.R.color.white))
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }









        }).attachToRecyclerView(recyclerView);

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



    //handling the ima chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

        }
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onClick(View v) {


        if(v==buttonFetch){

           // getPdfs(); //No need to call this function from here, its automatically uploaded when the page get loaded
        }
    }

    private void getPdfs() {

        progressDialog.setMessage("Fetching Pdfs... Please Wait");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PDF_FETCH_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(UpdateNotification.this,obj.getString("message"), Toast.LENGTH_SHORT).show();

                            JSONArray jsonArray = obj.getJSONArray("pdfs");

                            for(int i=0;i<jsonArray.length();i++){

                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                Pdf pdf  = new Pdf();
                                //String pdfName = jsonObject.getString("name");
                                //String pdfUrl = jsonObject.getString("url");
                                String pdfid = jsonObject.getString("id");

                                //changed is done here
                                String file_name = jsonObject.getString("file_name");
                                String file_name_url = jsonObject.getString("file_name_url");
                                pdf.setFile_name(file_name);
                                pdf.setFile_name_url(file_name_url);


                               // pdf.setName(pdfName);
                                //pdf.setUrl(pdfUrl);
                                pdf.setId(pdfid);
                                pdfList.add(pdf);

                            }

                            updateNotificationAdapter=new UpdateNotificationAdapter(UpdateNotification.this,R.layout.activity_updatenotificatonrecyclerview,pdfList);


                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(UpdateNotification.this));

                            recyclerView.setAdapter(updateNotificationAdapter);



                           updateNotificationAdapter.notifyDataSetChanged();

                            //recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);

        //copy copmlete

    }



    private void removeItem(final String id) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, PDF_DELETE_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(UpdateNotification.this,obj.getString("message"), Toast.LENGTH_SHORT).show();

                           // startActivity(new Intent(UpdateNotification.this, ProfileActivity.class));


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //updateNotificationAdapter.notifyDataSetChanged();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }



        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
             //   params.put("password", password);
                return params;
            }

        };

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);


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
                        Uri.parse("http://192.168.43.165/MyFold/index.php"));
                //Uri.parse("https://spiffier-farad.000webhostapp.com/MyFold/index.php"));
                startActivity(browserIntent);
                // startActivity(new Intent(this, Uploading_File.class));

                return true;
            case R.id.item9:
                Toast.makeText(this, "Delete Notification Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, UpdateNotification.class));

                return true;

            case R.id.item4:
                Toast.makeText(this, "Gallery Is Selected ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Staffinfo.class));

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
