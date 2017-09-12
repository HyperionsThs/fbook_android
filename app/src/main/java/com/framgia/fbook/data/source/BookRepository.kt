package com.framgia.fbook.data.source

import com.framgia.fbook.data.model.Book
import com.framgia.fbook.data.model.GoogleBook
import com.framgia.fbook.data.source.remote.BookRemoteDataSource
import com.framgia.fbook.data.source.remote.api.response.BaseBookRespone
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Hyperion on 9/5/2017.
 * Contact me thuanpx1710@gmail.com.
 * Thank you !
 */
interface BookRepository : BookDataSource.BookRemoteDataSource

class BookRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource) : BookRepository {
  override fun getMyBook(userId: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return bookRemoteDataSource.getMyBook(userId)
  }

  override fun searchBook(
      keyword: String?, field: String?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return bookRemoteDataSource.searchBook(keyword, field)
  }

  override fun searchBookWithGoogleApi(
      bookName: String?): Single<BaseResponse<List<GoogleBook>>> {
    return bookRemoteDataSource.searchBookWithGoogleApi(bookName)
  }

  override fun getSectionListBook(field: String?,
      page: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>> {
    return bookRemoteDataSource.getSectionListBook(field, page)
  }
}
