package com.grest.gip;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
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
import com.grest.gip.com.grest.gip.dao.GrouponConstants;

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

import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.grest.gip.com.grest.gip.dao.GrouponDealObject;
import com.grest.gip.com.grest.gip.dao.GrouponDealOption;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class MapsActivity extends AppCompatActivity implements OnInfoWindowClickListener,
        OnMapReadyCallback {
    public static final String OFFSET_EXTRA_MESSAGE = "com.grest.gip.OFFSET_EXTRA_MESSAGE";

    private GoogleMap mMap;
    private Map<String, String> markers2Deals = new HashMap<String, String>();
    private Map<String, GrouponDealObject> dealsDetails;
    private Intent intent;
    String grouponCategory;
    String country;
    String tsToken;
    int offset = 0;
    int offset4CurrentPage = 0;
    int limitCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_maps);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button showMore = (Button) findViewById(R.id.mapShowMore);
        showMore.setOnClickListener(showMoreListener);
    }

    // Create a message handling object as an anonymous class.
    private View.OnClickListener showMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            displayDataOnMap();
        }
    };
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        intent = getIntent();
        mMap = googleMap;
        dealsDetails = new HashMap<String, GrouponDealObject>();
        grouponCategory = intent.getStringExtra(SearchResults.CATEGORY_EXTRA_MESSAGE);
        country = intent.getStringExtra(SearchResults.COUNTRY_EXTRA_MESSAGE);
        String offsetStr = intent.getStringExtra(MapsActivity.OFFSET_EXTRA_MESSAGE);
        if (offsetStr != null) {
            offset = Integer.valueOf(offsetStr);
        }
        updateTitle(grouponCategory);
        displayDataOnMap();
    }

    private void displayDataOnMap() {
        mMap.clear();
        markers2Deals = new HashMap<String, String>();
        offset4CurrentPage = offset;
        tsToken = country + "_AFF_0" + GrouponConstants.AFFILIATE_ID +
                GrouponConstants.countriesAbbr2Codes.get(country) + "_0";
        AsyncTask<String, Void, String> response = new GetResponseClass().execute(
                new StringBuilder("https://partner-int-api.groupon.com/deals?").
                        append("tsToken=" + tsToken + "&").
                        append("country_code=" + country + "&").
                        append("limit=" + String.valueOf(limitCount) + "&").
                        append("filters=category%3A" + grouponCategory + "&").
                        append("offset=" + offset).toString());
        try {
            List<Marker> markers = loadMarkersFromResponse(response.get(), limitCount);
            chooseZoom2SeeAllMarkers(markers);
            mMap.setOnInfoWindowClickListener(this);
            offset += limitCount;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void chooseZoom2SeeAllMarkers(List<Marker> markers) {
        if (markers.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();

            int padding = 0; // offset from edges of the map in pixels
            // http://stackoverflow.com/questions/25231949/add-bounds-to-map-to-avoid-swiping-outside-a-certain-region
            // That code added to fix error message:
            // java.lang.IllegalStateException: Error using newLatLngBounds(LatLngBounds, int): Map size can't be 0.
            // Most likely, layout has not yet occured for the map view. Either wait until layout has occurred or use
            // newLatLngBounds(LatLngBounds, int, int, int) which allows you to specify the map's dimensions.
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            padding = (int) (width * 0.12);
            //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            mMap.moveCamera(cu);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMap.getCameraPosition().target,
                    mMap.getCameraPosition().zoom - 0.2f));
        } else {
            Toast.makeText(this, "Not found any deals of category " +
                    GrouponConstants.abbrevations2Categories.get(grouponCategory),
                    Toast.LENGTH_LONG).show();
        }
        // to display zoom tool on the map
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private List<Marker> loadMarkersFromResponse(String response, int count) throws JSONException {
        List<Marker> result = new ArrayList<Marker>();
        System.out.println("RESPONSE: " + response);
        JSONObject rec = new JSONObject(response);
        JSONArray deals = new JSONArray(rec.getString("deals"));
        if (deals.length() == 0) {
            System.out.println("NOT FOUND ANY DEALS");
            return result;
        }
        //Map<Double, Double> positions = new HashMap<Double, Double>();
        if (count > deals.length()) {
            count = deals.length();
        }
        String countryFromResponse;
        boolean isCountryValid;
        double latitude, longitude;
        GrouponDealObject object;
        String announcementTitle, title, finePrint, imageURI, dealID, merchantName;
        List<GrouponDealOption> dealOptions;
        GrouponDealOption dealOption;
        for (int i = 0; i < count; i++) {
            isCountryValid = false;
            latitude = 0;
            longitude = 0;
            announcementTitle = deals.getJSONObject(i).getString("announcementTitle");
            title = deals.getJSONObject(i).getString("title");
            finePrint = deals.getJSONObject(i).getString("finePrint");
            imageURI = deals.getJSONObject(i).getString("largeImageUrl");
            merchantName = deals.getJSONObject(i).getJSONObject("merchant").getString("name");
            System.out.println("title: " + title + "\nfinePrint: " + finePrint);
            dealOptions = new ArrayList<GrouponDealOption>();
            JSONArray options = deals.getJSONObject(i).getJSONArray("options");
            JSONArray redemptionLocations = options.getJSONObject(0).getJSONArray("redemptionLocations");
            // some items aren't contain information about locations in redemptionLocations tag
            if (redemptionLocations.length() != 0) {
                JSONObject locationInfo = redemptionLocations.getJSONObject(0);
                try {
                    latitude = Double.valueOf(locationInfo.getString("lat"));
                    longitude = Double.valueOf(locationInfo.getString("lng"));
                    countryFromResponse = locationInfo.getString("country");
                    isCountryValid = (countryFromResponse.equals(country));
                } catch (JSONException ex) {
                    System.out.println(locationInfo + " doesn't contain any GEO positions for deal" +
                            " with title " + announcementTitle + " #" + i);
                }
            } else {
                // TODO: add logic for getting geo info
                System.out.println("Category " + getTitle() + " doesn't have needed info");
                JSONObject division = deals.getJSONObject(i).getJSONObject("division");
                latitude = Double.valueOf(division.getString("lat"));
                longitude = Double.valueOf(division.getString("lng"));
            }
            object = new GrouponDealObject();
            dealOptions = new ArrayList<GrouponDealOption>();
            for (int j = 0; j < options.length(); j++) {
                dealOption = new GrouponDealOption();
                dealOption.setBuyUrl(options.getJSONObject(j).getString("buyUrl"));
                dealOption.setDetails(options.getJSONObject(j).getJSONArray("details").getJSONObject(0).getString("description"));
                dealOption.setPrice(options.getJSONObject(j).getJSONObject("price").getString("formattedAmount"));
                dealOption.setTitle(options.getJSONObject(j).getString("title"));
                dealOptions.add(dealOption);
            }
            object.setOptions(dealOptions);
            System.out.println(i + " - " + latitude + " ::: " + longitude);
            //double latitude = Double.valueOf(new JSONObject(deals.getJSONObject(i).getString("division")).getString("lat"));
            //double longitude = Double.valueOf(new JSONObject(deals.getJSONObject(i).getString("division")).getString("lng"));
            // this logic was added only because Groupon doesn't return individual coordinates for all deals

            /*if (positions.get(latitude) != null) {
                positions.put(latitude, longitude);
            } else {
                latitude = latitude + ThreadLocalRandom.current().nextDouble(0.0001, 0.01);
                longitude = longitude + ThreadLocalRandom.current().nextDouble(0.0001, 0.01);
            }*/

            if (isCountryValid) {
                Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                        .title(announcementTitle).snippet(merchantName));
                dealID = deals.getJSONObject(i).getString("id");
                markers2Deals.put(marker.getId(), dealID);

                object.setId(dealID);
                object.setAnnouncementTitle(announcementTitle);
                object.setTitle(title);
                object.setFinePrint(finePrint);
                object.setGrid6ImageUrl(imageURI);
                dealsDetails.put(dealID, object);
                result.add(marker);
            }
        }
        return result;
    }

    private void updateTitle(String message) {
        String categoryName = GrouponConstants.abbrevations2Categories.get(message);
        if (categoryName != null) {
            setTitle("Map: " + categoryName);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(MapsActivity.this, DealDetails.class);
        String id = markers2Deals.get(marker.getId());
        intent.putExtra(SearchResults.CATEGORY_EXTRA_MESSAGE, grouponCategory);
        intent.putExtra(MapsActivity.OFFSET_EXTRA_MESSAGE, String.valueOf(offset4CurrentPage));
        intent.putExtra(GrouponDealObject.class.getCanonicalName(), dealsDetails.get(id));
        intent.putExtra(SearchResults.COUNTRY_EXTRA_MESSAGE, country);
        startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(MapsActivity.this, SearchResults.class);
                intent.putExtra(SearchResults.COUNTRY_EXTRA_MESSAGE, country);
                startActivity(intent);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
