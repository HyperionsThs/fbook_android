package com.framgia.fbook.utils.binding

import android.databinding.BindingAdapter
import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.design.widget.TextInputLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.framgia.fbook.utils.common.StringUtils


/**
 * Created by le.quang.dao on 20/03/2017.
 */

object BindingUtils {

  /**
   * setErrorMessage for TextInputLayout
   */
  @JvmStatic
  @BindingAdapter("errorText")
  fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
  }

  /**
   * Load image from url
   */
  @JvmStatic
  @BindingAdapter("imageUrl")
  fun loadImage(imageView: ImageView, url: String?) {
    var url = url
    if (StringUtils.isBlank(url)) {
      url = ""
    }
    val uri = Uri.parse(url)
    Glide.with(imageView.context)
        .load(uri)
        .dontAnimate()
        .into(imageView)
  }

  /**
   * setAdapter For RecyclerView
   */
  @JvmStatic
  @BindingAdapter("recyclerAdapter")
  fun setAdapterForRecyclerView(recyclerView: RecyclerView,
      adapter: RecyclerView.Adapter<*>) {
    recyclerView.adapter = adapter
  }

  /**
   * addItemDecoration For RecyclerView
   */
  @JvmStatic
  @BindingAdapter("addItemDecoration")
  fun setItemDecoration(recyclerView: RecyclerView, orientation: Int) {
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, orientation))
  }

  /**
   * setAdapter for viewpager
   */
  @JvmStatic
  @BindingAdapter("viewPageAdapter")
  fun setViewPageAdapter(viewPager: ViewPager, adapter: FragmentPagerAdapter) {
    viewPager.adapter = adapter
  }

  @JvmStatic
  @BindingAdapter("setViewPager")
  fun setUpViewPager(tabLayout: TabLayout, viewPager: ViewPager) {
    tabLayout.setupWithViewPager(viewPager)
  }
}
