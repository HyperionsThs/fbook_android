package com.framgia.fbook.screen.notification.notificationFollow

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framgia.fbook.R
import com.framgia.fbook.data.model.Notification
import com.framgia.fbook.databinding.FragmentNotificationfollowBinding
import com.framgia.fbook.screen.BaseFragment
import com.framgia.fbook.screen.main.MainActivity
import javax.inject.Inject

/**
 * NotificationFollow Screen.
 */
class NotificationFollowFragment : BaseFragment(), NotificationFollowContract.ViewModel, NotificationFollowListener {

  @Inject
  internal lateinit var mPresenter: NotificationFollowContract.Presenter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {

    DaggerNotificationFollowComponent.builder()
        .mainComponent((activity as MainActivity).getMainComponent())
        .notificationFollowModule(
            NotificationFollowModule(this))
        .build()
        .inject(this)

    val binding = DataBindingUtil.inflate<FragmentNotificationfollowBinding>(inflater,
        R.layout.fragment_notificationfollow, container,
        false)
    binding.viewModel = this
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

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if(context is MainActivity){
      context.setNotificationFollowListener(this)
    }
  }

  override fun getNotificationFollow(notification: Notification?) {
  }

  companion object {

    fun newInstance(): NotificationFollowFragment {
      return NotificationFollowFragment()
    }
  }
}