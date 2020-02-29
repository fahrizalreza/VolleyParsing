package com.example.volleyparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    String name, description, linkedinUrl, youtubeUrl;
    String url = "http://192.168.1.107/jsonoutput.php";
    TextView nameTxt, descriptionTxt, linkedinUrlTxt, youtubeUrlTxtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        nameTxt = (TextView) findViewById(R.id.nameTxt);
        descriptionTxt = (TextView) findViewById(R.id.descTxt);
        linkedinUrlTxt = (TextView) findViewById(R.id.linkedinTxt);
        youtubeUrlTxtt = (TextView) findViewById(R.id.youtubeTxt);

        sendJsonRequest();
    }

    public void sendJsonRequest(){
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    name = response.getString("name");
                    description = response.getString("description");
                    linkedinUrl = response.getString("linkedin");
                    youtubeUrl = response.getString("youtube");

                    nameTxt.setText(name);
                    descriptionTxt.setText(description);
                    linkedinUrlTxt.setText(linkedinUrl);
                    youtubeUrlTxtt.setText(youtubeUrl);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
