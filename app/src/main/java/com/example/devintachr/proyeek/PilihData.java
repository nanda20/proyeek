package com.example.devintachr.proyeek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class PilihData extends AppCompatActivity {
    TextView jurusan;
    TextView countAdmin,countDosen;
    private final String URL= "http://10.0.3.2/absendosen/Absen/api_absen";
//    private final String URL= "http://192.168.43.116/absendosen/Absen/api_absen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        jurusan = (TextView) findViewById(R.id.jurusan);
        //Typeface myCustom = Typeface.createFromAsset(getAssets(), "fonts/MuseoSans_500.otf");
        //t.setTypeface(myCustom);

        countAdmin = (TextView) findViewById(R.id.admincount);
        countDosen = (TextView) findViewById(R.id.dosencount);
        setCount();
        jurusan.setText(Identity.jurusan);
        countDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),DosenList.class);
                intent.putExtra("status","dosen");
                startActivity(intent);
            }
        });
        countAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),DosenList.class);
                intent.putExtra("status","admin");
                startActivity(intent);
            }
        });



    }

    private void setCount(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params= new RequestParams();
        params.put("id_jurusan",Identity.id_jurusan.toString());

        client.post(URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject object=response.getJSONObject("result");
                    countAdmin.setText(object.getString("count_admin").toString());
                    countDosen.setText(object.getString("count_dosen").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                super.onSuccess(statusCode, headers, response);
            }
        });

    };
}
