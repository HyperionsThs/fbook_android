package com.framgia.fbook.utils

/**
 * Created by le.quang.dao on 10/03/2017.
 */

object Constant {

  val END_POINT_URL = "http://api-book.framgia.vn"

  val BREAK_LINE = "\n"
  val LIST_BOOK_EXTRA = "LIST_BOOK_EXTRA"
  val LATE = "latest"
  val VIEW = "view"
  val RATING = "rating"
  val WAITING = "waiting"
  val READ = "read"
  val PAGE = 1
  val EXTRA_COMMA = ","
  val EXTRA_EMTY = ""
  val EXTRA_ZERO = 0
  val APPROVE = "approve"
  val UNAPPROVE = "unapprove"

  val BOOK_DETAIL_EXTRA = "book_detail"
  val USER_BOOK_DETAIL_EXTRA = "user_book_detail"
  val USER_BOOK_DETAIL_PAGE_EXTRA = "user_book_detail_page"
  val BOOK_DETAIL_IN_USER_EXTRA = "book_detail_in_user_extra"
  val SHOW_MESSAGE_MAXIMUM = "99+"
  val SHOW_MESSAGE_MAXIMUM_INT = 99
  val MESSAGE_MAXIMUM = 100
  val KEY_OFFICE = "KEY_OFFICE"
  val KEY_TYPE = "KEY_TYPE"

  object Tab {
    val TAB_HOME = 0
    val TAB_MY_BOOK = 1
    val TAB_NOTIFICATION = 2
    val TAB_ACCOUNT = 3
  }

  object RequestCode {
    val TAB_MY_BOOK_REQUEST = 101
    val TAB_PROFILE_REQUEST = 102
    val BOOK_DETAIL_REQUEST = 103
    val TAB_CATEGORY_FAVORITE_USER = 104
    val TAB_NOTIFICATION = 105
    val TAB_MAIN = 106
    val APRROVE_BOOK_REQUEST = 10
  }

  object ResultCode {
    val APRROVE = 201
    val UNAPPROVE = 202
  }

  object TabUser {
    val TAB_USER_REVIEW = 0
    val TAB_USER_WAITING = 1
    val TAB_USER_READING = 2
    val TAB_USER_RETURNING = 3
    val TAB_USER_RETURNED = 4
  }

  object TabOtherInUser {
    val TAB_SHARING_BOOK = 0
    val TAB_READING_BOOK = 1
    val TAB_WAITING_BOOK = 2
    val TAB_RETURNED_BOOK = 3
    val TAB_REVIEW_BOOK = 4

  }

  object TabApproveDetail {
    val TAB_WAIT_AND_READ = 0
    val TAB_RETURNING_AND_RETURNED = 1
  }

  object RequestCodeBookInUser {
    val TAB_SHARING_BOOK = "sharing"
    val TAB_READING_BOOK = "reading"
    val TAB_WAITING_BOOK = "waiting"
    val TAB_RETURNED_BOOK = "returned"
    val TAB_REVIEWED_BOOK = "reviewed"
  }

  object TabNotification {
    val TAB_USER = 0
    val TAB_FOLLOW = 1
  }
}