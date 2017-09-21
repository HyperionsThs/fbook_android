package com.framgia.fbook.screen.userinbookdetail;

import com.framgia.fbook.AppComponent;
import com.framgia.fbook.utils.dagger.ActivityScope;

import dagger.Component;

/**
 * This is a Dagger component. Refer to {@link com.framgia.fbook.MainApplication} for the list of Dagger components
 * used in this application.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(UserInBookDetailModule::class))
public interface UserInBookDetailComponent {
  fun inject(userinbookdetailActivity: UserInBookDetailActivity)
}
