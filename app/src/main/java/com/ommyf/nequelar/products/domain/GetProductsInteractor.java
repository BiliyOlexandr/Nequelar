package com.ommyf.nequelar.products.domain;

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

@Singleton public class GetProductsInteractor extends Interactor<Integer, Product> {

  @Inject GetProductsInteractor(@IoThread Scheduler subscribeOn, @UiThread Scheduler observeOn) {
    super(subscribeOn, observeOn);
  }

  @Override protected Observable<Product> createObservable(Integer categoryId) {
    // If categoryId == -1 we need to fetch all products
    return Observable.just(new Product(0, 238.23, "IPhone 7 Plus 128gb",
        "The Apple iPhone 7 Plus is powered by quad-core Apple A10 Fusion processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded. As far as the cameras are concerned, the Apple iPhone 7 Plus packs a 12-megapixel primary camera on the rear and a 7-megapixel front shooter for selfies.\n"
            + "\n"
            + "The Apple iPhone 7 Plus runs iOS 10 and is powered by a 2900mAh non removable battery. It measures 158.20 x 77.90 x 7.30 (height x width x thickness) and weigh 188.00 grams.\n"
            + "\n"
            + "The Apple iPhone 7 Plus is a single SIM (GSM) smartphone that accepts a Nano-SIM. Connectivity options include Wi-Fi, GPS, Bluetooth, 3G and 4G (with support for Band 40 used by some LTE networks in India). Sensors on the phone include Compass Magnetometer, Proximity sensor, Accelerometer, Ambient light sensor, Gyroscope and Barometer.",
        "https://images-na.ssl-images-amazon.com/images/I/71x3e0x%2BM2L._SX569_.jpg"));
  }
}
