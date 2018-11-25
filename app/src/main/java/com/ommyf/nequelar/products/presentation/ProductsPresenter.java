package com.ommyf.nequelar.products.presentation;

import com.ommyf.nequelar.products.domain.GetProductsInteractor;
import javax.inject.Inject;
import javax.inject.Singleton;
import viper.ViperPresenter;

/**
 * Created by zayts on 3/19/2017.
 */

@Singleton public class ProductsPresenter
    extends ViperPresenter<ProductsViewCallbacks, ProductsRouter> {

  GetProductsInteractor mGetProductsInteractor;

  @Inject ProductsPresenter(GetProductsInteractor getProductsInteractor) {
    mGetProductsInteractor = getProductsInteractor;
  }

  void fetchProducts(int categoryId) {
    mGetProductsInteractor.execute(product -> getView().onNewProduct(product),
        throwable -> getView().onError(throwable),
        categoryId);
  }
}
