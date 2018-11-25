package com.ommyf.nequelar.shops.domain;

import android.location.Location;
import com.ommyf.nequelar.di.IoThread;
import com.ommyf.nequelar.di.UiThread;
import com.ommyf.nequelar.shops.data.Shop;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Scheduler;
import viper.Interactor;

/**
 * Created by zayts on 3/27/2017.
 */

@Singleton public class GetShopsInteractor extends Interactor<Integer, List<Shop>> {

  @Inject
  protected GetShopsInteractor(@IoThread Scheduler subscribeOn, @UiThread Scheduler observeOn) {
    super(subscribeOn, observeOn);
  }

  @Override protected Observable<List<Shop>> createObservable(Integer integer) {
    List<Shop> result = new ArrayList<>();
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    result.add(new Shop(0, "Ostin", "Super ostin 2000",
        "http://tcsviblovo.ru/images/store/ostin.jpg", new Location(""), 0));
    return Observable.just(result);
  }
}
