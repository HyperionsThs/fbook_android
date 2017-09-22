package com.framgia.fbook.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.framgia.fbook.R
import com.framgia.fbook.screen.BaseFragment
import com.framgia.fbook.utils.navigator.Navigator

/**
 * Created by levutantuan on 9/21/17.
 */
class ProfileContainerFragment : BaseFragment() {

  private val mNavigator: Navigator by lazy { Navigator(this) }

  companion object {

    val EXTRA_TAB: String = "EXTRA_TAB"
    val PROFILE: Int = 0
    val CATEGORY_FAVORITE: Int = 1

    fun newInstance(position: Int): ProfileContainerFragment {
      var profileContainerFragment: ProfileContainerFragment = ProfileContainerFragment()
      var bundle: Bundle = Bundle()
      bundle.putInt(EXTRA_TAB, position)
      profileContainerFragment.arguments = bundle
      return profileContainerFragment
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val view: View? = inflater?.inflate(R.layout.fragment_menu_profile_container, container, false)
    var containerId = R.id.layout_menu_profile_container
    var page: Int = arguments.getInt(EXTRA_TAB)
    when (page) {
      PROFILE -> Toast.makeText(context, R.string.personal_infor, Toast.LENGTH_SHORT).show()
      CATEGORY_FAVORITE -> Toast.makeText(context, R.string.category_favorite,
          Toast.LENGTH_SHORT).show()
    }
    return view
  }
}
