package com.framgia.fbook.data.source.remote.api.service

import com.framgia.fbook.data.model.*
import com.framgia.fbook.data.source.remote.api.request.SearchBookRequest
import com.framgia.fbook.data.source.remote.api.request.SignInRequest
import com.framgia.fbook.data.source.remote.api.response.BaseBookRespone
import com.framgia.fbook.data.source.remote.api.response.BaseResponse
import com.framgia.fbook.data.source.remote.api.response.SignInResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by le.quang.dao on 10/03/2017.
 */

interface FBookApi {
  @GET("/api/v0/home/")
  fun getHome(@Query("office_id") officeId: Int?): Single<BaseResponse<List<BookType>>>

  @POST("/api/v0/login")
  fun login(@Body signInRequest: SignInRequest?): Single<SignInResponse>

  @POST("/api/v0/search")
  fun searchBook(@Body searchBookRequest: SearchBookRequest): Single<BaseResponse<BaseBookRespone<List<Book>>>>

  @GET("api/v0/search-books")
  fun searchBookWithGoogleApi(@QueryMap map: Map<String, String?>): Single<BaseResponse<List<GoogleBook>>>

  @GET("/api/v0/user-profile")
  fun getUser(): Single<BaseResponse<User>>

  @GET("/api/v0/users/book/{user_id}/sharing")
  fun getMyBook(@Path("user_id") userId: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>>

  @GET("/api/v0/offices")
  fun getOffices(): Single<BaseResponse<List<Office>>>

  @GET("/api/v0/books/")
  fun getSectionListBook(@Query("field") field: String?, @Query(
      "page") page: Int?): Single<BaseResponse<BaseBookRespone<List<Book>>>>
}
