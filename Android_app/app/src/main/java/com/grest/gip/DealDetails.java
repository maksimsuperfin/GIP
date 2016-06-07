package com.grest.gip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.grest.gip.com.grest.gip.dao.GrouponDealObject;
import com.grest.gip.com.grest.gip.dao.GrouponDealOption;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Maksim.Superfin on 6/1/2016.
 */
public class DealDetails extends AppCompatActivity {

    String category;
    String offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_details);
        Intent intent = getIntent();
        category = intent.getStringExtra(SearchResults.CATEGORY_EXTRA_MESSAGE);
        offset = intent.getStringExtra(MapsActivity.OFFSET_EXTRA_MESSAGE);
        GrouponDealObject object = intent.getParcelableExtra(GrouponDealObject.class.getCanonicalName());
        String title = object.getTitle();
        String finePrint = object.getFinePrint();
        String imageURI = object.getGrid6ImageUrl();
        List<GrouponDealOption> dealOptions = object.getOptions();
        System.out.println("title: " + title + "\nfinePrint: " + finePrint);
        //System.out.println("onCreate::: " + category + " " + id);
        setTitle("Deal details "/* + id*/);

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(title);
        ImageView ivPicture = (ImageView) findViewById(R.id.ivImage);
        TextView tvFinePrint = (TextView) findViewById(R.id.tvFinePrint);
        tvFinePrint.setText(Html.fromHtml(finePrint));
        TextView tvAdditionalInfo = (TextView) findViewById(R.id.tvAdditionalInfo);
        String additionalInfo = "Title:" + dealOptions.get(0).getTitle() +
                                //"\nDetails: " + dealOptions.get(0).getDetails() +
                                "\nPrice: " + dealOptions.get(0).getPrice() +
                                "\nBuyUrl: " + dealOptions.get(0).getBuyUrl();
        tvAdditionalInfo.setText(additionalInfo);

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

