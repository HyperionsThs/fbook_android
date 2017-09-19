package com.framgia.fbook.data.source.remote

import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.model.GoogleBook
import com.framgia.fbook.data.source.BookDataSource
import com.framgia.fbook.data.source.remote.api.request.BookRequest
import com.framgia.fbook.data.source.remote.api.request.SearchBookRequest
import com.framgia.fbook.data.source.remote.api.response.BaseBookRespone
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import com.framgia.fbook.data.source.remote.api.service.FBookApi
import com.framgia.fbook.utils.RetrofitUtils
import io.reactivex.Single
import okhttp3.RequestBody
import java.util.*
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
    private val PARAM_TITLE = "title"
    private val PARAM_AUTHOR = "author"
    private val PARAM_PUBLISH_DATE = "publish_date"
    private val PARAM_DESCRIPTION = "description"
    private val PARAM_OFFICE_ID = "office_id"
    private val PARAM_CATEGORY_ID = "category_id"
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

  override fun getSectionListBook(type: String?,
      page: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return fbookApi.getSectionListBook(type, page)
  }

  override fun getBookDetail(bookId: Int?): Single<BaseResponse<Book>> {
    return fbookApi.getBookDetail(bookId)
  }

  override fun addBook(bookRequest: BookRequest): Single<BaseResponse<Book>> {
    val params = HashMap<String, RequestBody?>()
    bookRequest.title?.let {
      params.put(PARAM_TITLE, RetrofitUtils.toRequestBody(it))
    }
    bookRequest.author?.let {
      params.put(PARAM_AUTHOR, RetrofitUtils.toRequestBody(it))
    }
    bookRequest.publishDate?.let {
      params.put(PARAM_PUBLISH_DATE, RetrofitUtils.toRequestBody(it))
    }
    bookRequest.description?.let {
      params.put(PARAM_DESCRIPTION, RetrofitUtils.toRequestBody(it))
    }
    bookRequest.officeId?.let {
      params.put(PARAM_OFFICE_ID, RetrofitUtils.toRequestBody(it.toString()))
    }
    bookRequest.categoryId?.let {
      params.put(PARAM_CATEGORY_ID, RetrofitUtils.toRequestBody(it.toString()))
    }
    return fbookApi.addBook(params)
  }
}
