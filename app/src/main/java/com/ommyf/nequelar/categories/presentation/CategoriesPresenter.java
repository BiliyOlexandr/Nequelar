package com.ommyf.nequelar.categories.presentation;

import com.ommyf.nequelar.categories.domain.GetCategoriesInteractor;
import javax.inject.Inject;
import javax.inject.Singleton;
import viper.ViperPresenter;

/**
 * Created by zayts on 3/18/2017.
 */

@Singleton class CategoriesPresenter
    extends ViperPresenter<CategoriesViewCallbacks, CategoriesRouter> {

  GetCategoriesInteractor mGetCategoriesInteractor;

  @Inject CategoriesPresenter(GetCategoriesInteractor getCategoriesInteractor) {
    mGetCategoriesInteractor = getCategoriesInteractor;
  }

  void fetchCategories(int from) {
    mGetCategoriesInteractor.execute(category ->
      getView().onNewCategory(category),
      throwable -> getView().onError(throwable),
      from);
  }
}
