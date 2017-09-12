package com.framgia.fbook.screen.mainpage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framgia.fbook.R
import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.source.remote.api.error.BaseException
import com.framgia.fbook.databinding.FragmentMainPageBinding
import com.framgia.fbook.screen.BaseFragment
import com.framgia.fbook.screen.main.MainActivity
import com.framgia.fbook.screen.mainpage.adapter.MainPageTopRatingAdapter
import com.framgia.fbook.screen.onItemRecyclerViewClickListener
import com.fstyle.structure_android.widget.dialog.DialogManager
import javax.inject.Inject
import javax.inject.Named

/**
 * MainPage Screen.
 */
class MainPageFragment : BaseFragment(), MainPageContract.ViewModel, onItemRecyclerViewClickListener {

  @Inject
  internal lateinit var mPresenter: MainPageContract.Presenter
  @Inject
  lateinit var mDialogManager: DialogManager

  @field:[Inject Named("AdapterLate")]
  lateinit var mMainPageAdapterLateBook: MainPageTopRatingAdapter
  @field:[Inject Named("AdapterRating")]
  lateinit var mMainPageAdapterRatingBook: MainPageTopRatingAdapter

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    DaggerMainPageComponent.builder()
        .mainComponent((activity as MainActivity).getMainComponent())
        .mainPageModule(MainPageModule(this))
        .build()
        .inject(this)

    val binding = DataBindingUtil.inflate<FragmentMainPageBinding>(inflater,
        R.layout.fragment_main_page, container, false)
    binding.viewModel = this
    mPresenter.getSectionListTopRating()
    mMainPageAdapterLateBook.setItemInternalBookListener(this)
    mMainPageAdapterRatingBook.setItemInternalBookListener(this)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    mPresenter.onStart()
  }

  override fun onStop() {
    mPresenter.onStop()
    super.onStop()
  }

  override fun onShowProgressDialog() {
    mDialogManager.showIndeterminateProgressDialog()
  }

  override fun onDismissProgressDialog() {
    mDialogManager.dismissProgressDialog()
  }

  override fun onError(error: BaseException) {
    mDialogManager.dialogError(error.getMessageError())
  }

  override fun onGetSectionListBookSuccess(typeBook: Int, listBook: List<Book>?) {
    when (typeBook) {
      TypeBook.LATE_BOOK -> mMainPageAdapterLateBook.updateDataLateBook(listBook,
          TypeBook.LATE_BOOK)
      TypeBook.RATING_BOOK -> mMainPageAdapterRatingBook.updateDataRatingBook(listBook,
          TypeBook.RATING_BOOK)
    }
  }

  override fun onItemClickListener(any: Any?) {
    //TODO edit later
  }

  companion object {

    val TAG: String = MainPageFragment::class.java.name

    fun newInstance(): MainPageFragment {
      return MainPageFragment()
    }
  }
}
