package cl.iocco.example.JSON;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cl.iocco.example.foursquareapi.app.MainActivity;

public class LoadJsonTask extends AsyncTask<String, String, JSONObject> {
    private ProgressDialog pDialog;
    String url = "https://api.foursquare.com/v2/venues/search?client_id=NERLG4IFIOWZLN1ZFDWJLARWKYD3TYEBJ4UERR5WDGQWILJW&client_secret=PGVTJJZ5UEDOK3G1MTFAKTUP32ZA5JD1FQELVE12XT3TVG5U&v=20130815&ll=40.7,-74&query=beer";
    MainActivity activity;
    public LoadJsonTask(MainActivity main){

        activity = main;

        // Getting JSON from URL
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        JSONParser jParser = new JSONParser();
        // Getting JSON from URL
        JSONObject json = jParser.getJSONFromUrl(url);
        return json;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        try {
            // Getting JSON Array
            JSONObject response = json.getJSONObject("response");
            JSONArray venues = response.getJSONArray("venues");
            activity.fillList(venues);
            //Set JSON Data in TextView

    } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}