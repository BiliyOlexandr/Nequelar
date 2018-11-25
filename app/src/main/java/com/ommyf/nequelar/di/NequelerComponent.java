package com.ommyf.nequelar.di;

import com.ommyf.nequelar.LoginActivity;
import com.ommyf.nequelar.NequelerApp;
import com.ommyf.nequelar.categories.presentation.CategoriesActivity;
import com.ommyf.nequelar.product.ProductActivity;
import com.ommyf.nequelar.products.presentation.ProductsActivity;
import com.ommyf.nequelar.shops.ShopsActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by zayts on 1/2/2017.
 */

@Singleton @Component(modules = {AppModule.class})
public interface NequelerComponent {

  void inject(NequelerApp nequelerApp);

  void inject(LoginActivity loginActivity);

  void inject(CategoriesActivity categoriesActivity);

  void inject(ProductsActivity productsActivity);

  void inject(ProductActivity productActivity);

  void inject(ShopsActivity shopsActivity);

}
