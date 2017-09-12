package com.framgia.fbook.data.source.remote

import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.model.GoogleBook
import com.framgia.fbook.data.source.BookDataSource
import com.framgia.fbook.data.source.remote.api.request.SearchBookRequest
import com.framgia.fbook.data.source.remote.api.response.BaseBookRespone
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import com.framgia.fbook.data.source.remote.api.service.FBookApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Hyperion on 9/5/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
class BookRemoteDataSource @Inject constructor(nameApi: FBookApi) : BaseRemoteDataSource(
    nameApi), BookDataSource.BookRemoteDataSource {
  override fun getMyBook(userId: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return fbookApi.getMyBook(userId)
  }

  companion object {
    private val PARAM_BOOK_NAME = "q"
  }

  override fun searchBookWithGoogleApi(bookName: String?): Single<BaseResponse<List<GoogleBook>>> {
    val mapBookName: Map<String, String?> = hashMapOf(PARAM_BOOK_NAME to bookName)
    return fbookApi.searchBookWithGoogleApi(mapBookName)
  }

  override fun searchBook(
      keyword: String?, field: String?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    val searchData = SearchBookRequest.SearchBookData()
    val searchBookRequest = SearchBookRequest()
    searchData.keyWord = keyword
    searchData.field = field
    searchBookRequest.searchBookData = searchData
    return fbookApi.searchBook(searchBookRequest)
  }

  override fun getSectionListBook(field: String?,
      page: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return fbookApi.getSectionListBook(field, page)
  }

}
