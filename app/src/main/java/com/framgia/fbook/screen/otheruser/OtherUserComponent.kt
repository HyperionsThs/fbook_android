package com.framgia.fbook.screen.otheruser;

import android.content.Context
import com.framgia.fbook.AppComponent
import com.framgia.fbook.data.source.local.sharedprf.SharedPrefsApi
import com.framgia.fbook.data.source.remote.api.service.FBookApi
import com.framgia.fbook.utils.dagger.ActivityScope
import com.framgia.fbook.utils.rx.BaseSchedulerProvider
import dagger.Component

/**
 * This is a Dagger component. Refer to {@link com.framgia.fbook.MainApplication} for the list of Dagger components
 * used in this application.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
    modules = arrayOf(OtherUserModule::class))
interface OtherUserComponent {
  fun inject(otheruserActivity: OtherUserActivity)

  fun fBookApi(): FBookApi

  fun baseSchedulerProvider(): BaseSchedulerProvider

  fun sharedPrefsApi(): SharedPrefsApi

  fun applicationContext(): Context
}
