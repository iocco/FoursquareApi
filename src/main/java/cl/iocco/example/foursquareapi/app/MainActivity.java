package cl.iocco.example.foursquareapi.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.iocco.example.JSON.LoadJsonTask;

public class MainActivity extends Activity implements View.OnClickListener{

    JSONArray points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadJsonTask(this).execute();


    }

    public void fillList(JSONArray lugares){

        points = lugares;
        int cantidad = lugares.length();
        int i=0;
        LinearLayout[] elementos = new LinearLayout[cantidad];
        TextView[] nombres = new TextView[cantidad];
        SpannableString[] name = new SpannableString[cantidad];
        SpannableString[] address = new SpannableString[cantidad];
        TextView[] direcciones = new TextView[cantidad];
        LinearLayout lista = (LinearLayout)findViewById(R.id.lista);
        lista.removeAllViews();
        String nombre;
        String direccion;
        for (i=0;i<cantidad;i++){

            elementos[i] = new LinearLayout(this);
            elementos[i].setBackgroundColor(Color.BLACK);
            elementos[i].setOnClickListener(this);
            elementos[i].setId(100 + i);
            elementos[i].setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            nombres[i] = new TextView(this);
            direcciones[i] = new TextView(this);
            try {
                nombre = lugares.getJSONObject(i).getString("name");

            } catch (JSONException e) {
                nombre="--";
                e.printStackTrace();
            }
            name[i] = new SpannableString(nombre);
            name[i].setSpan(new RelativeSizeSpan(2.5f), 0,nombre.length(), 0);
            name[i].setSpan(new ForegroundColorSpan(Color.WHITE), 0,nombre.length(), 0);

            nombres[i].setText(name[i], TextView.BufferType.SPANNABLE);
            try {
                direccion = lugares.getJSONObject(i).getJSONObject("location").getString("address");
            } catch (JSONException e) {
                direccion = "--";
                e.printStackTrace();
            }
            address[i] = new SpannableString(direccion);
            address[i].setSpan(new RelativeSizeSpan(1.5f), 0,direccion.length(), 0);
            address[i].setSpan(new ForegroundColorSpan(Color.WHITE), 0, direccion.length(), 0);
            direcciones[i].setText(address[i], TextView.BufferType.SPANNABLE);
            elementos[i].addView(nombres[i]);
            elementos[i].addView(direcciones[i]);

            lista.addView(elementos[i],params);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId() - 100;
        Log.d("Click","Elemento" + id);
        Intent i = new Intent(this,Mapa.class);
        try {

            i.putExtra("name", points.getJSONObject(id).getString("name"));
            i.putExtra("lat", Float.parseFloat(points.getJSONObject(id).getJSONObject("location").getString("lat")));
            i.putExtra("lon", Float.parseFloat(points.getJSONObject(id).getJSONObject("location").getString("lng")));
            startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
