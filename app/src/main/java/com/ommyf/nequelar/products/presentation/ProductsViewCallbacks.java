package com.ommyf.nequelar.products.presentation;

import com.ommyf.nequelar.product.data.Product;
import viper.ViewCallbacks;

/**
 * Created by zayts on 3/19/2017.
 */

interface ProductsViewCallbacks extends ViewCallbacks {

  void onNewProduct(Product product);

  void onError(Throwable e);

}
