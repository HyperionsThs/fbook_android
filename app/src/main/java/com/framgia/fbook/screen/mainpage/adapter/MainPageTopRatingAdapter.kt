package com.framgia.fbook.screen.mainpage.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.framgia.fbook.R
import com.framgia.fbook.data.model.Book
import com.framgia.fbook.databinding.FragmentMainpageItemBinding
import com.framgia.fbook.databinding.FragmentMainpageLatestbookItemBinding
import com.framgia.fbook.screen.BaseRecyclerViewAdapter
import com.framgia.fbook.screen.mainpage.TypeBook
import com.framgia.fbook.screen.onItemRecyclerViewClickListener

/**
 * Created by Hyperion on 9/11/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class MainPageTopRatingAdapter(
    context: Context) : BaseRecyclerViewAdapter<RecyclerView.ViewHolder>(
    context) {
  private lateinit var mItemClickListener: onItemRecyclerViewClickListener
  private val mListRatingBook = mutableListOf<Book>()
  private val mListLateBook = mutableListOf<Book>()
  private var mTypeBook: Int? = null

  fun updateDataRatingBook(listBook: List<Book>?, typeBook: Int) {
    mTypeBook = typeBook
    listBook?.let {
      mListRatingBook.addAll(listBook)
      notifyDataSetChanged()
    }
  }

  fun updateDataLateBook(listBook: List<Book>?, typeBook: Int) {
    mTypeBook = typeBook
    listBook?.let {
      mListLateBook.addAll(listBook)
    }
  }

  fun setItemInternalBookListener(itemInternalBookListener: onItemRecyclerViewClickListener) {
    mItemClickListener = itemInternalBookListener
  }

  override fun getItemCount(): Int {
    when (mTypeBook) {
      TypeBook.LATE_BOOK -> return mListLateBook.size
      TypeBook.RATING_BOOK -> return mListRatingBook.size
    }
    return 0
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
    if (mTypeBook == TypeBook.LATE_BOOK) {
      val binding = DataBindingUtil.inflate<FragmentMainpageLatestbookItemBinding>(
          LayoutInflater.from(parent?.context), R.layout.fragment_mainpage_latestbook_item, parent,
          false)
      return ItemViewHolderLateBook(binding, mItemClickListener)
    }
    val binding = DataBindingUtil.inflate<FragmentMainpageItemBinding>(
        LayoutInflater.from(parent?.context),
        R.layout.fragment_mainpage_item, parent, false)
    return ItemViewHolderRatingBook(binding, mItemClickListener)

  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
    if (mTypeBook == TypeBook.LATE_BOOK) {
      val itemViewHolder: ItemViewHolderLateBook = holder as ItemViewHolderLateBook
      itemViewHolder.binData(mListLateBook[position])
    }
    if (mTypeBook == TypeBook.RATING_BOOK) {
      val itemViewHolder: ItemViewHolderRatingBook = holder as ItemViewHolderRatingBook
      itemViewHolder.binData(mListRatingBook[position])
    }
  }

  /**
   * Item Latest Book
   */
  inner class ItemViewHolderLateBook(
      private val mBinding: FragmentMainpageLatestbookItemBinding,
      private val mItemClickListener: onItemRecyclerViewClickListener?) : RecyclerView.ViewHolder(
      mBinding.root) {
    fun binData(book: Book) {
      mBinding.viewModel = ItemMainPageViewModel(book, mItemClickListener)
      mBinding.executePendingBindings()
    }
  }

  /**
   * Item Rating Book
   */
  inner class ItemViewHolderRatingBook(
      private val mBinding: FragmentMainpageItemBinding,
      private val mItemClickListener: onItemRecyclerViewClickListener?) : RecyclerView.ViewHolder(
      mBinding.root) {
    fun binData(book: Book) {
      mBinding.viewModel = ItemMainPageViewModel(book, mItemClickListener)
      mBinding.executePendingBindings()
    }
  }
}
