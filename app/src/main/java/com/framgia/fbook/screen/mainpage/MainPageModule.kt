package com.framgia.fbook.screen.mainpage

import android.support.v4.app.Fragment
import com.framgia.fbook.data.source.BookRepository
import com.framgia.fbook.data.source.BookRepositoryImpl
import com.framgia.fbook.data.source.remote.BookRemoteDataSource
import com.framgia.fbook.screen.mainpage.adapter.MainPageTopRatingAdapter
import com.framgia.fbook.utils.dagger.FragmentScope
import com.framgia.fbook.utils.rx.BaseSchedulerProvider
import com.fstyle.structure_android.widget.dialog.DialogManager
import com.fstyle.structure_android.widget.dialog.DialogManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * This is a Dagger module. We use this to pass in the View dependency to
 * the [MainPagePresenter].
 */
@Module
class MainPageModule(private val mFragment: Fragment) {


  @Provides
  fun providePresenter(schedulerProvider: BaseSchedulerProvider,
      bookRepository: BookRepository): MainPageContract.Presenter {
    val presenter = MainPagePresenter(bookRepository)
    presenter.setViewModel(mFragment as MainPageContract.ViewModel)
    presenter.setSchedulerProvider(schedulerProvider)
    return presenter
  }

  @FragmentScope
  @Provides
  fun provideBookRepository(bookRemoteDataSource: BookRemoteDataSource): BookRepository {
    return BookRepositoryImpl(bookRemoteDataSource)
  }

  @FragmentScope
  @Provides
  fun provideDialogManager(): DialogManager {
    return DialogManagerImpl(mFragment.context)
  }

  @FragmentScope
  @Provides
  @Named("AdapterRating")
  fun provideMainPageAdapter(): MainPageTopRatingAdapter {
    return MainPageTopRatingAdapter(mFragment.context)
  }

  @FragmentScope
  @Provides
  @Named("AdapterLate")
  fun provideMainPageAdapterLate(): MainPageTopRatingAdapter {
    return MainPageTopRatingAdapter(mFragment.context)
  }
}
