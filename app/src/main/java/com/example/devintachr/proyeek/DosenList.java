package com.example.devintachr.proyeek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DosenList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<DataDosen> arrayList=new ArrayList<>();
    private AdapterHandler adapter;
    private final String URL= "http://10.0.3.2/absendosen/Absen/api_absen";
//    private final String URL= "http://192.168.43.116/absendosen/Absen/api_absen";

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() !=0){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_list);
        recyclerView= (RecyclerView)findViewById(R.id.RecycleList);
        layoutManager= new LinearLayoutManager(this);
        String status= getIntent().getStringExtra("status");
        if(status.equals("dosen")){
            loadData("data_dosen");
            setTitle("Absen Dosen");
        }else{
            loadData("data_admin");
            setTitle("Absen Admin");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void loadData(final String status){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params= new RequestParams();
        params.put("id_jurusan",Identity.id_jurusan.toString());

        client.post(URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

//                    Log.d("LOG", String.valueOf(response));
                    JSONObject object=response.getJSONObject("result");
                    JSONArray data= object.getJSONArray(status);
//                    JSONObject object1=;
                    for (int i=0;i<data.length();i++){
                        DataDosen dosen= new DataDosen();
                        dosen.setNip_karyawan(data.getJSONObject(i).getString("nip_karyawan"));
                        dosen.setNama(data.getJSONObject(i).getString("nama_karyawan"));
                        dosen.setJam(data.getJSONObject(i).getString("jam_datang"));
                        arrayList.add(dosen);
                    }
//                    DataDosen pojoDosens= new Gson().fromJson(response.toString(),DataDosen.class);

                    adapter = new AdapterHandler(DosenList.this, arrayList);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);

//                    JSONObject object=response.getJSONObject("result");
//                    countAdmin.setText(object.getString("count_admin").toString());
//                    countDosen.setText(object.getString("count_dosen").toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                super.onSuccess(statusCode, headers, response);
            }
        });



    }

}



