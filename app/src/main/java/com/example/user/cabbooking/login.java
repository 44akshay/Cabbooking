package com.example.user.cabbooking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class login extends AppCompatActivity {
    TextView empid, emppsd;
    ProgressDialog p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        empid = (TextView) findViewById(R.id.empid);
        emppsd = (TextView) findViewById(R.id.emppsd);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        //first getting the values
        final String emp_id = empid.getText().toString();
        final String password = emppsd.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(emp_id)) {
            empid.setError("Please enter your username");
            emppsd.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            emppsd.setError("Please enter your password");
            emppsd.requestFocus();
            return;
        }
        class UserLogin extends AsyncTask<Void, Void, String> {



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ProgressDialog progress = new ProgressDialog(login.this);
                progress.setMessage("Loging in");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



                try {


                    //converting response to json object
                    JSONObject obj = new JSONObject(s);


                    //if no error in response
                    if (!obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();


                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");



                        //creating a new user object
                        User user = new User(
                                userJson.getString("emp_id"),
                                userJson.getString("emp_name"),
                                userJson.getString("designation"),
                                userJson.getString("phone_no"),
                                userJson.getString("jobband")
                        );


                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the main activity
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

//                        Toast.makeText(login.this,"your credential is correct",Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("emp_id", emp_id);
                params.put("password", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }

        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}

        //if everything is fine



