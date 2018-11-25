package com.ommyf.nequelar.mainscreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.ommyf.nequelar.R;
import com.ommyf.nequelar.about.AboutActivity;
import com.ommyf.nequelar.categories.presentation.CategoriesActivity;
import com.ommyf.nequelar.map.MapActivity;
import com.ommyf.nequelar.products.presentation.ProductsActivity;
import com.ommyf.nequelar.shops.ShopsActivity;

import static com.ommyf.nequelar.product.ProductActivity.checkLocationPermission;

public class MainScreenActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

  private static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;
  private GoogleMap mMap;

  public static void start(Context context) {
    context.startActivity(new Intent(context, MainScreenActivity.class));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_screen);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ((MapView) findViewById(R.id.map)).onCreate(null);
    ((MapView) findViewById(R.id.map)).getMapAsync(this);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    ActivityCompat.requestPermissions(this,
        new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, MY_PERMISSIONS_REQUEST_LOCATION);

  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_categories) {
      CategoriesActivity.start(this);
    } else if (id == R.id.nav_products) {
      ProductsActivity.start(this, -1);
    } else if (id == R.id.nav_shops) {
      ShopsActivity.start(this);
    } else if (id == R.id.nav_map) {
      MapActivity.start(this);
    } else if (id == R.id.nav_about) {
      AboutActivity.start(this);
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;

    // Add a marker in Sydney and move the camera
    //LatLng sydney = new LatLng(-34, 151);
    //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

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
  }
}
