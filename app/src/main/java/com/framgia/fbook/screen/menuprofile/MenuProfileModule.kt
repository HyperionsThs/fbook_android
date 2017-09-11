package com.framgia.fbook.screen.menuprofile

import android.support.v4.app.Fragment
import com.framgia.fbook.data.source.UserRepository
import com.framgia.fbook.data.source.UserRepositoryImpl
import com.framgia.fbook.data.source.local.UserLocalDataSource
import com.framgia.fbook.data.source.remote.UserRemoteDataSource
import com.framgia.fbook.utils.dagger.FragmentScope
import com.framgia.fbook.utils.navigator.Navigator
import com.fstyle.structure_android.widget.dialog.DialogManager
import com.fstyle.structure_android.widget.dialog.DialogManagerImpl
import dagger.Module
import dagger.Provides

/**
 * This is a Dagger module. We use this to pass in the View dependency to
 * the [MenuProfilePresenter].
 */
@Module
class MenuProfileModule(private val mFragment: Fragment) {

  @FragmentScope
  @Provides
  fun providePresenter(): MenuProfileContract.Presenter {
    val presenter = MenuProfilePresenter()
    presenter.setViewModel(mFragment as MenuProfileContract.ViewModel)
    return presenter
  }

  @FragmentScope
  @Provides
  fun provideNavigator(): Navigator {
    return Navigator(mFragment)
  }

  @FragmentScope
  @Provides
  fun provideMenuProfileAdapter(): MenuProfileAdapter {
    return MenuProfileAdapter(mFragment.context,
        mFragment.childFragmentManager)
  }

  @FragmentScope
  @Provides
  fun providerUserRepository(userRemoteDataSource: UserRemoteDataSource,
      userLocalDataSource: UserLocalDataSource): UserRepository {
    return UserRepositoryImpl(userRemoteDataSource, userLocalDataSource)
  }

  @FragmentScope
  @Provides
  fun providerDialogManager(): DialogManager {
    return DialogManagerImpl(mFragment.context)
  }
}
