package com.framgia.fbook.screen.notification.notificationUser

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framgia.fbook.R
import com.framgia.fbook.data.model.ItemNotification
import com.framgia.fbook.data.source.UserRepository
import com.framgia.fbook.data.source.remote.api.error.BaseException
import com.framgia.fbook.data.source.remote.api.response.NotificationResponse
import com.framgia.fbook.databinding.FragmentNotificationBinding
import com.framgia.fbook.screen.BaseFragment
import com.framgia.fbook.screen.approverequest.approvedetail.ApproveDetailActivity
import com.framgia.fbook.screen.bookdetail.BookDetailActivity
import com.framgia.fbook.screen.main.MainActivity
import com.framgia.fbook.screen.main.NotificationListener
import com.framgia.fbook.screen.notification.ItemNotificationClickListener
import com.framgia.fbook.screen.notification.ItemNotificationViewModel
import com.framgia.fbook.screen.notification.NotificationAdapter
import com.framgia.fbook.utils.Constant
import com.framgia.fbook.utils.navigator.Navigator
import com.fstyle.structure_android.widget.dialog.DialogManager
import javax.inject.Inject

/**
 * Notification Screen.
 */
class NotificationFragment : BaseFragment(), NotificationContract.ViewModel, ItemNotificationClickListener, NotificationUserListener, ReadAllNotification {

  @Inject
  internal lateinit var mPresenter: NotificationContract.Presenter
  @Inject
  internal lateinit var mDialogManager: DialogManager
  @Inject
  internal lateinit var mNotificationAdapter: NotificationAdapter
  @Inject
  internal lateinit var mNavigator: Navigator
  @Inject
  internal lateinit var mUserRepository: UserRepository
  private lateinit var mNotificationListener: NotificationListener
  private lateinit var mUpdateNotificationListener: NotificationListener.UpdateNotificationListener
  private lateinit var mBinding: FragmentNotificationBinding
  private var mIsLoadDataFirstTime = true
  val mIsVisibleLayoutNotData: ObservableField<Boolean> = ObservableField()
  val mIsRefresh: ObservableField<Boolean> = ObservableField(false)
  private var mPosition = 0

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    userVisibleHint = false
    DaggerNotificationComponent.builder()
        .mainComponent((activity as MainActivity).getMainComponent())
        .notificationModule(NotificationModule(this))
        .build()
        .inject(this)

    mBinding = DataBindingUtil.inflate<FragmentNotificationBinding>(inflater,
        R.layout.fragment_notification, container,
        false)
    mBinding.viewModel = this
    mNotificationAdapter.setItemNotificationClickListener(this)
    return mBinding.root
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
    if (context is MainActivity) {
      mNotificationListener = context
      mUpdateNotificationListener = context
      context.setNotificationUserListener(this)
      context.setReadAllNotificaitonOfUser(this)
    }
  }

  override fun onDismissProgressDialog() {
    mDialogManager.dismissProgressDialog()
  }

  override fun onShowProgressDialog() {
    mDialogManager.showIndeterminateProgressDialog()
  }

  override fun onError(error: BaseException) {
    mIsLoadDataFirstTime = false
    mDialogManager.dialogError(error.getMessageError())
    mIsRefresh.set(false)
  }

  override fun onItemNotificationClick(itemNotification: ItemNotification?, position: Int) {
    itemNotification?.let {
      if (itemNotification.viewed == 0) {
        mPosition = position
        mPresenter.updateNotification(itemNotification.id)
      }
      val bundle = Bundle()
      itemNotification.book?.id?.let {
        bundle.putInt(Constant.BOOK_DETAIL_EXTRA, it)
      }
      when (itemNotification.type) {
        ItemNotificationViewModel.WAITING, ItemNotificationViewModel.APPROVE_RETURNING,
        ItemNotificationViewModel.RETURNING, ItemNotificationViewModel.UNAPPROVE_WAITING
        -> {
          mNavigator.startActivity(ApproveDetailActivity::class.java, bundle)
        }
        ItemNotificationViewModel.APPROVE_WAITING, ItemNotificationViewModel.REVIEW -> {
          mNavigator.startActivity(BookDetailActivity::class.java, bundle)
        }
      }
    }
  }

  override fun getEnable(enable: Boolean) {
    if (enable && mIsLoadDataFirstTime) {
      mPresenter.getNotification()
    }
  }

  override fun onUpdateNotificationSuccess() {
    mNotificationAdapter.updateItem(mPosition)
    mNotificationListener.onUpdateNotificationSuccess()
    mIsRefresh.set(false)
  }

  override fun onGetNotificationSuccess(notificationResponse: NotificationResponse?) {
    mIsLoadDataFirstTime = false
    mIsRefresh.set(false)
    notificationResponse?.let {
      notificationResponse.notificationUser?.let {
        mNotificationAdapter.updateData(it.listNotification, TYPE_USER)
      }
      notificationResponse.notificationFollow?.let {
        mNotificationListener.getNotificationFollow(it)
      }
    }
    mUpdateNotificationListener.updateNotification()
  }

  override fun onReadAllNotificationSuccess() {
    mNotificationAdapter.notifyDataSetChanged()
    mPresenter.getNotification()
    mNotificationListener.onUpdateNotificationSuccess()
  }

  override fun readAllNotificationOfUser() {
    mPresenter.readAllNotificationOfUser()
  }

  override fun onIsNotRefresh(): Boolean {
    return mIsRefresh.get()
  }

  fun onRefresh() {
    mIsRefresh.set(true)
    mPresenter.getNotification()
  }


  companion object {

    private val TYPE_USER = 0
    val TAG: String = NotificationFragment::class.java.name

    fun newInstance(): NotificationFragment {
      return NotificationFragment()
    }
  }
}
