package cl.iocco.example.foursquareapi.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends Activity {

    private GoogleMap map;
    private Float latitud;
    private Float longitud;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        latitud = getIntent().getExtras().getFloat("lat");
        longitud = getIntent().getExtras().getFloat("lon");
        name= getIntent().getExtras().getString("name");
        Log.d("Mapa", name);
        LatLng latLng = new LatLng(latitud,longitud);
        CameraUpdateFactory CameraUpdateFactory;
        CameraUpdate cameraUpdate = com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(latLng, 15);
        map.animateCamera(cameraUpdate);
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .title(name));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mapa, menu);
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

}
