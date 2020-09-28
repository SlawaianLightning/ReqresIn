package com.example.reqresin.data.net

import com.example.reqresin.data.net.responce.PageResponce
import com.example.reqresin.data.net.responce.UserDetailInfoResponce
import com.example.reqresin.data.net.responce.UserResponce
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.schedulers.Schedulers

interface ApiService {

    companion object {

        private const val URL = "https://reqres.in/"

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().setLenient().create()))
                .build()
                .create(ApiService::class.java)
        }

    }

    @GET("https://reqres.in/api/users?")
    fun getUsers(@Query("page")page: Int): Single<PageResponce>

    @GET("https://reqres.in/api/users/{id}")
    fun getUserDetailInfo(@Path("id")id: Int): Single<UserDetailInfoResponce>
}