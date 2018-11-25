package com.ommyf.nequelar.categories.presentation;

import com.ommyf.nequelar.categories.data.Category;
import viper.ViewCallbacks;

/**
 * Created by zayts on 3/18/2017.
 */

interface CategoriesViewCallbacks extends ViewCallbacks {

  void onNewCategory(Category category);

  void onError(Throwable e);

}
