package com.framgia.fbook.screen.mainpage

import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.source.remote.api.error.BaseException
import com.framgia.fbook.screen.BasePresenter
import com.framgia.fbook.screen.BaseViewModel

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainPageContract {
  /**
   * View.
   */
  interface ViewModel : BaseViewModel {
    fun onError(error: BaseException)

    fun onGetSectionListBookSuccess(typeBook: Int, listBook: List<Book>?)

    fun onShowProgressDialog()

    fun onDismissProgressDialog()

  }

  /**
   * Presenter.
   */
  interface Presenter : BasePresenter<ViewModel> {
    fun getSectionListTopRating()
  }
}
