package com.ommyf.nequelar.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zayts on 1/2/2017.
 */

@Module public class AppModule {
  private final Application mApplication;

  public AppModule(Application mApplication) {
    this.mApplication = mApplication;
  }

  @Provides @Singleton Context provideContext() {
    return mApplication;
  }

  @Provides @UiThread Scheduler provideUiThread() {
    return AndroidSchedulers.mainThread();
  }

  @Provides @IoThread Scheduler provideIoThread() {
    return Schedulers.io();
  }

}
