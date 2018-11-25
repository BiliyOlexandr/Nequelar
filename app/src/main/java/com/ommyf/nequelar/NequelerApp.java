package com.ommyf.nequelar;

import android.app.Application;
import android.content.Context;
import com.ommyf.nequelar.di.AppModule;
import com.ommyf.nequelar.di.DaggerNequelerComponent;
import com.ommyf.nequelar.di.NequelerComponent;

/**
 * Created by zayts on 2/26/2017.
 */

public class NequelerApp extends Application {
  private static NequelerApp instance;

  private NequelerComponent mComponent;

  public NequelerComponent getComponent() {
    return mComponent;
  }

  public Context getContext() {
    return instance.getApplicationContext();
  }

  protected NequelerComponent createComponent() {
    return DaggerNequelerComponent.builder().appModule(new AppModule(this)).build();
  }

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    mComponent = createComponent();
    mComponent.inject(this);
  }
}
