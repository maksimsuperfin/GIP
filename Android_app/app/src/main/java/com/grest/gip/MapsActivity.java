package com.grest.gip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/*import android.view.View;
import android.widget.EditText;*/

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.grest.gip.com.grest.gip.dao.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_maps);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = getIntent();
        mMap = googleMap;
        String message = intent.getStringExtra(SearchResults.EXTRA_MESSAGE);
        updateTitle(message);
        int limitCount = 10;
        AsyncTask<String, Void, String> response = new GetResponseClass().execute(
                new StringBuilder("https://partner-int-api.groupon.com/deals?").
                        append("tsToken=IL_AFF_0_209000_515_0&").
                        append("country_code=IL&").
                        append("limit=" + String.valueOf(limitCount) + "&").
                        append("filters%3Dcategory%3A=" + message).toString());
        try {
            List<Marker> markers = loadMarkersFromResponse(response.get(), limitCount);
            chooseZoom2SeeAllMarkers(markers);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void chooseZoom2SeeAllMarkers(List<Marker> markers) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMap.getCameraPosition().target,
                mMap.getCameraPosition().zoom - 0.25f));
        // to display zoom tool on the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private List<Marker> loadMarkersFromResponse(String response, int count) throws JSONException {
        List<Marker> result = new ArrayList<Marker>();
        System.out.println("RESPONSE: " + response);
        JSONObject rec = new JSONObject(response);
        JSONArray deals = new JSONArray(rec.getString("deals"));
        Map<Double, Double> positions = new HashMap<Double, Double>();
        for (int i = 0; i < count; i++) {
            String title = deals.getJSONObject(i).getString("announcementTitle");
            double latitude = Double.valueOf(new JSONObject(deals.getJSONObject(i).getString("division")).getString("lat"));
            double longitude = Double.valueOf(new JSONObject(deals.getJSONObject(i).getString("division")).getString("lng"));
            // this logic was added only because Groupon doesn't return individual coordinates for all deals
            if (positions.get(latitude) != null) {
                positions.put(latitude, longitude);
            } else {
                latitude = latitude + ThreadLocalRandom.current().nextDouble(0.0001, 0.01);
                longitude = longitude + ThreadLocalRandom.current().nextDouble(0.0001, 0.01);
            }
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                                .title("Marker in Israel: " + title).snippet("Snippet: text"));
            result.add(marker);
        }
        return result;
    }

    private void updateTitle(String message) {
        String categoryName = Categories.abbrevations2Categories.get(message);
        if (categoryName != null) {
            setTitle("Map: " + categoryName);
        }
    }

    private class GetResponseClass extends AsyncTask<String, Void, String> {
        private Exception exception;

        @Override
        protected String doInBackground(String... urls) {
            URL url = null;
            String response = "";
            try {
                url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    response = readStream(in);
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        private String readStream(InputStream inputStream) throws IOException {
            if (inputStream != null) {
                Writer writer = new StringWriter();
                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1024);
                    int n;
                    while ((n = reader.read(buffer)) !=-1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    inputStream.close();
                }
                return writer.toString();
            } else {
                return "";
            }
        }
    }
}
