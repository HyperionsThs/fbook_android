package com.framgia.fbook.screen.otheruser

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.framgia.fbook.R
import com.framgia.fbook.screen.otheruser.bookInUser.BookInUserFragment
import com.framgia.fbook.utils.Constant

/**
 * Created by levutantuan on 9/27/17.
 */
class OtherUserAdapter(private val mContext: Context, private val mUserId: Int?,
    fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager) {

  companion object {
    val TOTAL_TAB: Int = 5
  }

  override fun getItem(position: Int): Fragment {
    return when (position) {
      Constant.TabOtherInUser.TAB_SHARING_BOOK -> BookInUserFragment.newInstance(
          Constant.RequestCodeBookInUser.TAB_SHARING_BOOK, mUserId, position)
      Constant.TabOtherInUser.TAB_READING_BOOK -> BookInUserFragment.newInstance(
          Constant.RequestCodeBookInUser.TAB_READING_BOOK, mUserId, position)
      Constant.TabOtherInUser.TAB_WAITING_BOOK -> BookInUserFragment.newInstance(
          Constant.RequestCodeBookInUser.TAB_WAITING_BOOK, mUserId, position)
      Constant.TabOtherInUser.TAB_RETURNED_BOOK -> BookInUserFragment.newInstance(
          Constant.RequestCodeBookInUser.TAB_RETURNED_BOOK, mUserId, position)
      Constant.TabOtherInUser.TAB_REVIEW_BOOK -> BookInUserFragment.newInstance(
          Constant.RequestCodeBookInUser.TAB_REVIEWED_BOOK, mUserId, position)
      else -> {
        Fragment()
      }
    }
  }

  override fun getPageTitle(position: Int): CharSequence {
    when (position) {
      Constant.TabOtherInUser.TAB_SHARING_BOOK -> return mContext.getString(R.string.sharing_books)
      Constant.TabOtherInUser.TAB_READING_BOOK -> return mContext.getString(R.string.reading_books)
      Constant.TabOtherInUser.TAB_WAITING_BOOK -> return mContext.getString(R.string.waiting_books)
      Constant.TabOtherInUser.TAB_RETURNED_BOOK -> return mContext.getString(
          R.string.returned_books)
      Constant.TabOtherInUser.TAB_REVIEW_BOOK -> return mContext.getString(R.string.reviewed_books)
    }
    return Constant.EXTRA_EMTY
  }

  override fun getCount(): Int = OtherUserAdapter.TOTAL_TAB
}
