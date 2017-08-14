package com.fstyle.fbook.screen

/**
 * BasePresenter

 * @param <T> class extend from BaseViewModel
</T> */

interface BasePresenter<T : BaseViewModel> {

  fun onStart()

  fun onStop()

  fun setViewModel(viewModel: T)
}
