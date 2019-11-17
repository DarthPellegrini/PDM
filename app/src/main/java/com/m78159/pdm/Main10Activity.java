package com.m78159.pdm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Main10Activity extends AppCompatActivity {

    private JSONArray array;
    private double[] avg = {0.0,0.0,0.0,0.0,0.0};
    private TextView medias;
    private Main10ActivityAdapter adapter;
    private List<Map<String,Object>> dados = new ArrayList<>();
    private String[] from = {"temp", "hum", "orv", "pre", "vel", "dir" ,"data"};
    private int[] to = {R.id.aula10_lista_temperatura, R.id.aula10_lista_humidade, R.id.aula10_lista_orvalho, R.id.aula10_lista_pressao,
    R.id.aula10_lista_velocidade, R.id.aula10_lista_direcao, R.id.aula10_lista_datahora};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        medias = findViewById(R.id.mediaWebService);
        ListView medicoes = findViewById(R.id.listWebService);

        adapter = new Main10ActivityAdapter(this,dados,R.layout.activity_main10_list_layout,from,to);
        medicoes.setAdapter(adapter);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ghelfer.net/la/weather.json", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                try{
                    String data = new String(response, StandardCharsets.UTF_8);
                    array = new JSONObject(data).getJSONArray("weather");

                    for (int i = 0; i < array.length(); i++){
                        HashMap<String,Object> map = new HashMap<>();
                        System.out.println(array.get(i));
                        JSONObject json = array.getJSONObject(i);
                        map.put("temp",json.get("temperature"));
                        map.put("hum",json.get("humidity"));
                        map.put("orv",json.get("dewpoint"));
                        map.put("pre",json.get("pressure"));
                        map.put("vel",json.get("speed"));
                        map.put("dir",json.get("direction"));
                        map.put("data",json.get("datetime"));
                        avg[0] += Double.parseDouble((String)json.get("temperature"));
                        avg[1] += Double.parseDouble((String)json.get("humidity"));
                        avg[2] += Double.parseDouble((String)json.get("dewpoint"));
                        avg[3] += Double.parseDouble((String)json.get("pressure"));
                        avg[4] += Double.parseDouble((String)json.get("speed"));
                        dados.add(map);
                    }
                    adapter.notifyDataSetChanged();

                    for (int i = 0; i < avg.length; i++)
                        avg[i] = avg[i]/array.length();

                    medias.setText(String.format("Médias de:\nTemperatura: %s\nHumidade: %s\nOrvalho: %s\nPressão: %s\nVelocidade do vento: %s\n",
                                    avg[0], avg[1], avg[2], avg[3], avg[4]));

                }catch(Exception e){
                    Log.e("URL",e.toString());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
