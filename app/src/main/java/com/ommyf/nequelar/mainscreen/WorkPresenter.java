package com.ommyf.nequelar.mainscreen;

import com.ommyf.nequelar.R;
import javax.inject.Inject;
import javax.inject.Singleton;
import viper.Router;
import viper.ViewCallbacks;
import viper.ViperPresenter;

/**
 * Created by zayts on 2/26/2017.
 */

@Singleton public class WorkPresenter extends ViperPresenter<ViewCallbacks, Router> {

  private USER_TYPE mUserType;

  @Inject WorkPresenter() {
    // dagger
  }

  public USER_TYPE getUserType() {
    return mUserType;
  }

  public void setUserType(USER_TYPE mUserType) {
    this.mUserType = mUserType;
  }



  public enum USER_TYPE {
    USER(0, R.string.user),
    MERCHANDISER(1, R.string.merchandiser);

    int id;
    int textResource;

    USER_TYPE(int typeId, int textResource) {
      this.id = typeId;
      this.textResource = textResource;
    }
  }
}
