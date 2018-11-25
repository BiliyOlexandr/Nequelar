package com.ommyf.nequelar.product.domain;

import com.ommyf.nequelar.di.IoThread;
import com.ommyf.nequelar.di.UiThread;
import com.ommyf.nequelar.product.data.Product;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Scheduler;
import viper.Interactor;

/**
 * Created by zayts on 3/19/2017.
 */

@Singleton public class GetProductInteractor extends Interactor<Integer, Product> {

  @Inject
  GetProductInteractor(@IoThread Scheduler subscribeOn, @UiThread Scheduler observeOn) {
    super(subscribeOn, observeOn);
  }

  @Override protected Observable<Product> createObservable(Integer productId) {
    return Observable.just(new Product(0, 540.23, "IPhone 7 Plus 128gb",
          "Cool iphone 7 plus with 128 gb of memory.",
          "https://images-na.ssl-images-amazon.com/images/I/71x3e0x%2BM2L._SX569_.jpg"));
}
}