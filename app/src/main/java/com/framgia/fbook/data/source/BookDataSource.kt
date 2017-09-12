package com.framgia.fbook.data.source

import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.model.GoogleBook
import com.framgia.fbook.data.source.remote.api.response.BaseBookRespone
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import io.reactivex.Single

/**
 * Created by Hyperion on 9/5/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
interface BookDataSource {
  /**
   * RemoteData For Book
   */
  interface BookRemoteDataSource {
    fun searchBook(
        keyword: String?, field: String?): Single<BaseResponse<BaseBookRespone<List<Book>>>>

    fun getMyBook(userId: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>>

    fun searchBookWithGoogleApi(
        bookName: String?): Single<BaseResponse<List<GoogleBook>>>

    fun getSectionListBook(field: String?,
        page: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>>
  }
}
