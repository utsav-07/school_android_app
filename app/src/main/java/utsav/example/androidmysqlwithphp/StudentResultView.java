package utsav.example.androidmysqlwithphp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Used for fetching the students's result after
 * giving the conditions .....
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

public class StudentResultView extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {

    private EditText edittxtclass_section,edittextrollno;
    private TextView textViewUsername, textViewUserEmail;
    private Button viewYourResult;
    private ProgressDialog progressDialog;
    private Spinner  spineryear,  spinersemester;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result_view);

        if(!SharedPrefManager_Students.getInstance1(this).isLoggedIn1()){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        viewYourResult = (Button)findViewById(R.id.btnSave);
        //------------------------------------------------------------------------------------------------

        edittxtclass_section = (EditText)findViewById(R.id.class_section);
        edittextrollno = (EditText)findViewById(R.id.rollno);
        spineryear = (Spinner)findViewById(R.id.spinner);
        spinersemester = (Spinner)findViewById(R.id.spinnersemester);
        progressDialog =new ProgressDialog(this);

        //.............................for semester..........................................


        // Spinner click listener
        spinersemester.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> semester_list = new ArrayList<String>();
        semester_list.add("FA_1");
        semester_list.add("FA_2");
        semester_list.add("SA_1");
        semester_list.add("SA_2");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdaptersemester = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semester_list);

        // Drop down layout style -
        dataAdaptersemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinersemester.setAdapter(dataAdaptersemester);

        //.............................end of  semester..........................................

        //.......................for year................................................

        // Spinner click listener
        spineryear.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> year_list = new ArrayList<String>();
        year_list.add("2020");
        year_list.add("2021");
        year_list.add("2022");
        year_list.add("2023");
        year_list.add("2024");
        year_list.add("2025");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year_list);

        // Drop down layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spineryear.setAdapter(dataAdapter);

        //.........................end of for year..............................................

        viewYourResult.setOnClickListener(this);


    }

    /*for viewing the rsult this function is start with shared prefrence manager student resultview*/
    private void studentresultview(){
        final String class_section = edittxtclass_section.getText().toString().trim();
        final String rollno        = edittextrollno.getText().toString().trim();
        final String year          = spineryear.getSelectedItem().toString();
        final String semester      = spinersemester.getSelectedItem().toString();


        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_VIEWRESULT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager_StudentsResultView.getInstanceviewresult(getApplicationContext())
                                        .studentresult(
                                                obj.getInt("id"),
                                                obj.getString("studentname"),
                                                obj.getString("class_section"),
                                                obj.getString("rollno"),
                                                obj.getString("year"),
                                                obj.getString("semester"),
                                                obj.getString("subj_math"),
                                                obj.getString("subj_science"),
                                                obj.getString("subj_ssc"),
                                                obj.getString("subj_hindi"),
                                                obj.getString("subj_english"),
                                                obj.getString("subj_sanskrit"),
                                                obj.getString("subj_computer"),
                                                obj.getString("subj_gk"),
                                                obj.getString("subj_drawing"),
                                                obj.getString("subj_faircopy"),
                                                obj.getString("subj_project")
                                        );
                                startActivity(new Intent(StudentResultView.this, viewYourResult.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("class_section", class_section);
                params.put("rollno", rollno);
                params.put("year", year);
                params.put("semester", semester);
                return params;
            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }



    /* end of the viewing the result function*/



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


    @Override
    public void onClick(View v) {
        studentresultview();
        //startActivity(new Intent(this, viewYourResult.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
       String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
