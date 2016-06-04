package com.grest.gip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Maksim.Superfin on 6/1/2016.
 */
public class DealDetails extends AppCompatActivity {

    String category;
    //String id;
    String offset;
    String title;
    String finePrint;
    String imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_details);
        Intent intent = getIntent();
        category = intent.getStringExtra(SearchResults.CATEGORY_EXTRA_MESSAGE);
        //id = intent.getStringExtra(MapsActivity.DEAL_ID_EXTRA_MESSAGE);
        offset = intent.getStringExtra(MapsActivity.OFFSET_EXTRA_MESSAGE);
        title = intent.getStringExtra(MapsActivity.TITLE_EXTRA_MESSAGE);
        finePrint = intent.getStringExtra(MapsActivity.FINE_PRINT_EXTRA_MESSAGE);
        imageURI = intent.getStringExtra(MapsActivity.IMAGE_URI_EXTRA_MESSAGE);
        System.out.println("title: " + title + "\nfinePrint: " + finePrint);
        //System.out.println("onCreate::: " + category + " " + id);
        setTitle("Deal details "/* + id*/);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        ImageView ivPicture = (ImageView) findViewById(R.id.ivImage);
        //ivPicture.setImageURI(Uri.parse(imageURI));
        TextView tvFinePrint = (TextView) findViewById(R.id.tvFinePrint);
        tvFinePrint.setText(Html.fromHtml(finePrint));

        // https://github.com/nostra13/Android-Universal-Image-Loader
        // that library was recommended on SOF
        // http://stackoverflow.com/questions/18210700/best-method-to-download-image-from-url-in-android
        // library has license http://www.apache.org/licenses/LICENSE-2.0
        // TODO: check if the license can be used in our commercial application
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
            .defaultDisplayImageOptions(defaultOptions)
            .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage(imageURI, ivPicture);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DealDetails.this, MapsActivity.class);
                intent.putExtra(SearchResults.CATEGORY_EXTRA_MESSAGE, category);
                intent.putExtra(MapsActivity.OFFSET_EXTRA_MESSAGE, offset);
                startActivity(intent);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

