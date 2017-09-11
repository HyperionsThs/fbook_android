package com.framgia.fbook.data.source

import com.framgia.fbook.data.model.Office
import com.framgia.fbook.data.model.User
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import com.framgia.fbook.data.source.remote.api.response.SignInResponse
import io.reactivex.Single

/**
 * Created by le.quang.dao on 10/03/2017.
 */

interface UserDataSource {
  /**
   * LocalData For User
   */
  interface LocalDataSource {
    fun saveUser(user: User?)

    fun getUserLocal(): User?

    fun clearData()

  }

  /**
   * RemoteData For User
   */
  interface RemoteDataSource {
    fun login(email: String?, password: String?): Single<SignInResponse>

    fun getUser(): Single<BaseResponse<User>>

    fun getOffices(): Single<BaseResponse<List<Office>>>
  }
}
