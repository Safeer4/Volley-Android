package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);

        queue = Volley.newRequestQueue(this);
    }

    public void login(View view) {
        ResponseHandle responseHandle = new ResponseHandle();

        JSONObject loginData = new JSONObject();
        try {
            loginData.put("username", etUsername.getText().toString());
            loginData.put("password", etPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                "http://safeerullah.ninja/volley/login.php",
                loginData,
                responseHandle,
                responseHandle
                );

        queue.add(request);
    }

    private class ResponseHandle implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onResponse(JSONObject response) {
            try {
                int success = response.getInt("success");
                if (success == 1)
                {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String reason = response.getString("reason");
                    Toast.makeText(MainActivity.this, "Login Failed! "+reason, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    }
}