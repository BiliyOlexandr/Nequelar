package com.ommyf.nequelar.product;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.ommyf.nequelar.NequelerApp;
import com.ommyf.nequelar.R;
import com.ommyf.nequelar.databinding.ActivityProductBinding;
import com.ommyf.nequelar.product.data.Product;
import com.ommyf.nequelar.product.domain.GetProductInteractor;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;

public class ProductActivity extends AppCompatActivity implements OnMapReadyCallback {

  public static final String PRODUCT_ID = "product_id";

  private GoogleMap mMap;
  private static Product mProduct;
  private ActivityProductBinding mActivityBinding;
  @Inject GetProductInteractor mGetProductInteractor;

  public static void start(Context context, Product product) {
    Intent intent = new Intent(context, ProductActivity.class);
    mProduct = product;
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_product);
    ((NequelerApp) getApplicationContext()).getComponent().inject(this);

    // Setup home button
    ActionBar mActionBar = getSupportActionBar();
    if (mActionBar != null) {
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    mActivityBinding.title.setText(mProduct.getName());
    mActivityBinding.price.setText("$" + mProduct.getPrice());
    mActivityBinding.description.setText(mProduct.getDescription());

    // Fill image
    Picasso.with(this).load(mProduct.getImgUri()).into(mActivityBinding.productImage);

    mActivityBinding.map.onCreate(null);
    mActivityBinding.map.getMapAsync(this);
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onStop() {
    mMap.setMyLocationEnabled(false);
    super.onStop();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return false;
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    if (checkLocationPermission(this)) {
      mMap.setMyLocationEnabled(true);

      LocationManager locationManager =
          (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

      Location location = locationManager.getLastKnownLocation(
          locationManager.getBestProvider(new Criteria(), false));
      if (location != null) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
            new LatLng(location.getLatitude(), location.getLongitude()), 17));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
            new LatLng(location.getLatitude(),
                location.getLongitude()))      // Sets the center of the map to location user
            .zoom(17).build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
      }
    }
    mActivityBinding.map.onResume();
  }

  public static boolean checkLocationPermission(Context context) {
    int permissionAccessCoarseLocation =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
    int permissionAccessFineLocation =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);

    return permissionAccessCoarseLocation == PackageManager.PERMISSION_GRANTED
        || permissionAccessFineLocation == PackageManager.PERMISSION_GRANTED;
  }
}
