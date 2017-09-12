package com.framgia.fbook.screen.mainpage

import com.framgia.fbook.data.source.BookRepository
import com.framgia.fbook.data.source.remote.api.error.BaseException
import com.framgia.fbook.utils.rx.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Listens to user actions from the UI ([MainPageFragment]), retrieves the data and updates
 * the UI as required.
 */
class MainPagePresenter(private val mBookRepository: BookRepository) : MainPageContract.Presenter {

  private lateinit var mViewModel: MainPageContract.ViewModel
  private lateinit var mSchedulerProvider: BaseSchedulerProvider
  private val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

  override fun onStart() {}

  override fun onStop() {
    mCompositeDisposable.clear()
  }

  override fun getSectionListTopRating() {
    val disposable: Disposable = mBookRepository.getSectionListBook(LATE, PAGE)
        .subscribeOn(mSchedulerProvider.io())
        .doOnSubscribe { mViewModel.onShowProgressDialog() }
        .doAfterTerminate { mViewModel.onDismissProgressDialog() }
        .flatMap { listBookLateResponse ->
          mViewModel.onGetSectionListBookSuccess(TypeBook.LATE_BOOK,
              listBookLateResponse.item?.data)
          mBookRepository.getSectionListBook(RATING, PAGE)
        }
        .observeOn(mSchedulerProvider.ui())
        .subscribe({ listBookRatingResponse ->
          mViewModel.onGetSectionListBookSuccess(TypeBook.RATING_BOOK,
              listBookRatingResponse.item?.data)
        }, { error ->
          mViewModel.onError(error as BaseException)
        })
    mCompositeDisposable.add(disposable)
  }

  override fun setViewModel(viewModel: MainPageContract.ViewModel) {
    mViewModel = viewModel
  }

  fun setSchedulerProvider(schedulerProvider: BaseSchedulerProvider) {
    mSchedulerProvider = schedulerProvider
  }

  companion object {
    private val TAG = MainPagePresenter::class.java.name

    private val LATE = "latest"
    private val VIEW = "view"
    private val RATING = "rating"
    private val WAITING = "waiting"
    private val PAGE = 1
  }
}
