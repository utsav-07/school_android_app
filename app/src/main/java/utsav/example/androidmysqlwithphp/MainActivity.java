package utsav.example.androidmysqlwithphp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;


/**
 * Login class for Registration -- first time student registration
 * currently not used -- in this application---
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edittextusername,edittxtemail,edittextpassword;
    private Button btnregister;
    private ProgressDialog progressDialog;
    private TextView textViewLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, StudentResultView.class));
            return;
        }

        edittextusername = (EditText)findViewById(R.id.edittextusername);
        edittxtemail = (EditText)findViewById(R.id.edittextemail);
        edittextpassword = (EditText)findViewById(R.id.edittextuserpassword);
        textViewLogin = (TextView) findViewById(R.id.logintext);

        btnregister = (Button)findViewById(R.id.buttonregister);

        progressDialog =new ProgressDialog(this);

        btnregister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void registerUser(){
       final String email = edittxtemail.getText().toString().trim();
        final String username = edittextusername.getText().toString().trim();
        final String password = edittextpassword.getText().toString().trim();

        progressDialog.setMessage("register user.........");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject =new JSONObject(response);

                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("email",email);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        /*RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
    }

    @Override
    public void onClick(View v) {
        if(v == btnregister )
            registerUser();
        if(v == textViewLogin)
            startActivity(new Intent(this, LoginActivity.class));
    }
}
