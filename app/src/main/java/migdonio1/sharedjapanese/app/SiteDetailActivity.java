package migdonio1.sharedjapanese.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import static migdonio1.sharedjapanese.data.APIConstants.SITE;

import migdonio1.sharedjapanese.R;

public class SiteDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private String original;
    private String description;
    private String translates;
    private Double latitude;
    private Double longitude;

    private TextView siteTranslatesTextView;
    private TextView siteOriginalTextView;
    private TextView siteDescriptionTextView;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);
        Intent intent = getIntent();

        translates = intent.getStringExtra(SITE.TRANSLATES);
        original = intent.getStringExtra(SITE.ORIGINAL);
        description = intent.getStringExtra(SITE.DESCRIPTION);
        latitude = intent.getDoubleExtra(SITE.LATITUDE, 0);
        longitude = intent.getDoubleExtra(SITE.LONGITUDE, 0);

        siteTranslatesTextView = (TextView) findViewById(R.id.siteTranslatesTextView);
        siteOriginalTextView = (TextView) findViewById(R.id.siteOriginalTextView);
        siteDescriptionTextView = (TextView) findViewById(R.id.siteDescriptionTextView);

        siteTranslatesTextView.setText(translates);
        siteOriginalTextView.setText(original);
        siteDescriptionTextView.setText(description);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        moveMap();
    }

    private void moveMap() {
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18);
        map.moveCamera(camUpd1);
    }
}
