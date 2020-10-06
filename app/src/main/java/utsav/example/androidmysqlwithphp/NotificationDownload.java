package utsav.example.androidmysqlwithphp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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

import static utsav.example.androidmysqlwithphp.Constants.URL_NOTIFATION;


/**
 * Login class for view / download all notifications
 *
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */
public class NotificationDownload extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

        //Rest API for fetching all notification
        public static final String PDF_FETCH_URL = URL_NOTIFATION;

        ImageView imageView;

        //Image request code
       private int PICK_PDF_REQUEST = 1;

        //storage permission code
        private static final int STORAGE_PERMISSION_CODE = 123;

        //Uri to store the image uri
        private Uri filePath;

        //ListView to show the fetched Pdfs from the server
        ListView listView;

        //button to fetch the intiate the fetching of pdfs.
        Button buttonFetch;

        //Progress bar to check the progress of obtaining pdfs
        ProgressDialog progressDialog;

        //an array to hold the different pdf objects
        ArrayList<Pdf> pdfList= new ArrayList<Pdf>();

        //pdf adapter

        PdfAdapter pdfAdapter;

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_upload);

            //Requesting storage permission
            requestStoragePermission();

            //initializing ListView
            listView = (ListView) findViewById(R.id.listView);

            //initializing buttonFetch
            buttonFetch = (Button) findViewById(R.id.buttonFetchPdf);

            //initializing progressDialog
            progressDialog = new ProgressDialog(this);

            //Setting clicklistener
            buttonFetch.setOnClickListener(this);


            //setting listView on item click listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Pdf pdf = (Pdf) parent.getItemAtPosition(position);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    //intent.setData(Uri.parse(pdf.getUrl()));
                    intent.setData(Uri.parse(pdf.getFile_name()));
                    intent.setData(Uri.parse(pdf.getFile_name_url()));
                    startActivity(intent);

                }
            });


            getPdfs();

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

           // getPdfs();//used to get pdf but now it come automatically without using it
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
                            Toast.makeText(NotificationDownload.this,obj.getString("message"), Toast.LENGTH_SHORT).show();


                            JSONArray jsonArray = obj.getJSONArray("pdfs");

                            for(int i=0;i<jsonArray.length();i++){

                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                Pdf pdf  = new Pdf();
                               /* String pdfName = jsonObject.getString("name");
                                String pdfUrl = jsonObject.getString("url");*/
                               // String pdfUrl = jsonObject.getString("id");
                                String file_name = jsonObject.getString("file_name");
                                String file_name_url = jsonObject.getString("file_name_url");
                                //String file_id = jsonObject.getString("id");

                               /* pdf.setName(pdfName);
                                pdf.setUrl(pdfUrl);
*/                                pdf.setFile_name(file_name);
                                  pdf.setFile_name_url(file_name_url);
                                  //pdf.setId(file_id);
                                //  pdf.setId(pdfUrl);
                                pdfList.add(pdf);

                            }

                            pdfAdapter=new PdfAdapter(NotificationDownload.this,R.layout.activity_list_layout, pdfList);

                            listView.setAdapter(pdfAdapter);

                            pdfAdapter.notifyDataSetChanged();

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
}

