package utsav.example.androidmysqlwithphp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class is for inserting the students result by teacher
 *
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

    public class Resultinsert extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener {
    private EditText edittextname,edittxtclass_section,edittextrollno, editextsemester,edittextsubj,edittextmarks;
    private Button btnupdate;
    private Spinner spineryear,spinersemester;
    private Spinner spinersubject;
    private ProgressDialog progressDialog;
    String item,text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultinsert);


        edittextname = (EditText)findViewById(R.id.name);
        edittxtclass_section = (EditText)findViewById(R.id.class_section);
        edittextrollno = (EditText)findViewById(R.id.rollno);
        edittextmarks = (EditText) findViewById(R.id.marks);
        spineryear = (Spinner)findViewById(R.id.spinner);
        spinersubject = (Spinner)findViewById(R.id.spinnersubject);
        spinersemester = (Spinner)findViewById(R.id.spinnersemester);

        //EditText etUserName = (EditText) findViewById(R.id.txtUsername);
      //  String strUserName = edittextname.getText().toString();



        btnupdate = (Button)findViewById(R.id.btnSave);

        progressDialog =new ProgressDialog(this);

        btnupdate.setOnClickListener(this);

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

        // Drop down layout style - list view with radio button
        dataAdaptersemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinersemester.setAdapter(dataAdaptersemester);

        //.............................end of  semester..........................................


        //.............................for subject..........................................

        // Spinner click listener
        spinersubject.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> subj_list = new ArrayList<String>();
        subj_list.add("subj_math");
        subj_list.add("subj_english");
        subj_list.add("subj_science");
        subj_list.add("subj_ssc");
        subj_list.add("subj_hindi");
        subj_list.add("subj_english");
        subj_list.add("subj_sanskrit");
        subj_list.add("subj_computer");
        subj_list.add("subj_gk");
        subj_list.add("subj_drawing");
        subj_list.add("subj_project");
        subj_list.add("subj_faircopy");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdaptersubject = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subj_list);

        // Drop down layout style - list view with radio button
        dataAdaptersubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinersubject.setAdapter(dataAdaptersubject);

        //.............................end of  subject..........................................

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

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spineryear.setAdapter(dataAdapter);

       // text1 = spineryear.getSelectedItem().toString();

       // edittextyear.setText(text1);

        //.........................end of for year..............................................

    }



    private void updateresult() {
        final String studentname = edittextname.getText().toString().trim();
        final String class_section = edittxtclass_section.getText().toString().trim();
        final String rollno = edittextrollno.getText().toString().trim();
        //final String year = edittextyear.getText().toString();//edittextyear.getText().toString().trim();
        final String year = spineryear.getSelectedItem().toString();
        //final String semester = editextsemester.getText().toString().trim();
        final String semester = spinersemester.getSelectedItem().toString();
        //final String subj = edittextsubj.getText().toString().trim();
        final String subj = spinersubject.getSelectedItem().toString();
        final String marks = edittextmarks.getText().toString().trim();

        /* Start Validation for blank field*/
        if(TextUtils.isEmpty(studentname)){
            edittextname.setError("blank not allowed");
            return;
        }
        if(TextUtils.isEmpty(class_section)){
            edittxtclass_section.setError("blank not allowed");
            return;
        }
        if(TextUtils.isEmpty(rollno)){
            edittextrollno.setError("blank not allowed");
            return;
        }
        if(TextUtils.isEmpty(marks)){
            edittextmarks.setError("blank not allowed");
            return;
        }
        /* Start Validation for blank field*/

        progressDialog.setMessage("updating result.........");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_INSERTRESULT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);


                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(),"result submitted",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("studentname", studentname);
                params.put("class_section", class_section);
                params.put("rollno", rollno);
                params.put("year", year);
                params.put("semester", semester);
                params.put("subj", subj);
                params.put("marks", marks);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



    }

    @Override
    public void onClick(View v) {
        if(v==btnupdate) {
            updateresult();
            //Toast.makeText(getApplicationContext(),"result submitted",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        // On selecting a spinner item
         item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   /* @Override
    public void onClick(View v) {
        if(v == btnupdate )
            updateresult();
    }*/
}
