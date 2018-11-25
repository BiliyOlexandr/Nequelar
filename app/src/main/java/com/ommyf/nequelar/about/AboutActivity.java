package com.ommyf.nequelar.about;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.ommyf.nequelar.R;

public class AboutActivity extends AppCompatActivity {

  public static void start(Context context) {
    context.startActivity(new Intent(context, AboutActivity.class));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);

    try {
      ((TextView) findViewById(R.id.version)).setText("Nequelar v" + getPackageManager().
            getPackageInfo(getPackageName(), 0).versionName);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
      ((TextView) findViewById(R.id.version)).setText("Nequalar 1.0");
    }
    // Setup home button
    ActionBar mActionBar = getSupportActionBar();
    if (mActionBar != null) {
      mActionBar.setHomeButtonEnabled(true);
      mActionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return false;
  }

}
