package com.ommyf.nequelar.categories.domain;

import com.ommyf.nequelar.categories.data.Category;
import com.ommyf.nequelar.di.IoThread;
import com.ommyf.nequelar.di.UiThread;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Scheduler;
import viper.Interactor;

/**
 * Created by zayts on 3/18/2017.
 */

@Singleton public class GetCategoriesInteractor extends Interactor<Integer, Category> {

  @Inject
  GetCategoriesInteractor(@IoThread Scheduler subscribeOn,
      @UiThread Scheduler observeOn) {
    super(subscribeOn, observeOn);
  }

  @Override protected Observable<Category> createObservable(Integer fromId) {
    return Observable.just(new Category(0, "Electronics",
        "Some electronic products. Made in China. For real people",
        "https://static.businessinsider.com/image/4f9eedcaeab8ead93e000004/image.jpg"));
  }
}
