package com.example.devintachr.proyeek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    TextView t;
    private EditText nip,pass;
    private Button btn;
    private static String URL = "http://10.0.3.2/absendosen/login/api_cekLogin";
//    private static String URL = "http://192.168.43.116/absendosen/login/api_cekLogin";

    public int status;
    JSONObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        t = (TextView) findViewById(R.id.jurusan);
//        Typeface myCustom = Typeface.createFromAsset(getAssets(), "fonts/MuseoSans_500.otf");
//        t.setTypeface(myCustom);
//
        nip= (EditText)findViewById(R.id.nip);
        pass= (EditText)findViewById(R.id.pass);
        btn= (Button) findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Nip : " +nip.getText() +" Pass : "+ pass.getText(),Toast.LENGTH_SHORT).show();
                readJson(nip.getText().toString(),pass.getText().toString());
            }
        });

    }

    public void readJson(String nip,String pass){
        final Intent intent= new Intent(this,PilihData.class);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params= new RequestParams();
        params.put("nip",nip);
        params.put("pass",pass);


        client.post(URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject object= response.getJSONObject("data");

                    if(response.get("login").equals("true")){
                        status=1;
//                      Toast.makeText(getApplicationContext(),"Json OK",Toast.LENGTH_SHORT).show();
//                      Log.d("Status","Status "+String.valueOf(statusCode));
                        Log.d("Jurusan","id_Jurusan "+object.get("id_jurusan").toString());

                        Identity.id_jurusan =Integer.valueOf(object.getString("id_jurusan").toString());
                        Identity.jurusan=String.valueOf(object.getString("jurusan"));
                        startActivity(intent);
                    }else{
                        status=0;
                        Log.d("Login","0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("LOG", responseString);
                Toast.makeText(getApplicationContext(),"Json Fail ",Toast.LENGTH_SHORT).show();
//                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
}
