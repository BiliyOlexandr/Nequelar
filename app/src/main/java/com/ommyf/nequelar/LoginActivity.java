package com.ommyf.nequelar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.ommyf.nequelar.mainscreen.WorkPresenter;
import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

  @Inject WorkPresenter mWorkPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    ((NequelerApp) getApplicationContext()).getComponent().inject(this);

  }
}
