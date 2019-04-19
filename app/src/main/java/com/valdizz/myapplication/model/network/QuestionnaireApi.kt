package com.valdizz.myapplication.model.network

import com.valdizz.myapplication.model.entity.Questionnaire
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface QuestionnaireApi {

    @GET("/questionnaire")
    fun getQuestionnaire(): Observable<Questionnaire>

    @POST("/answers")
    fun sendAnswers(): Call<Boolean>

    companion object Factory {

        const val URL = "https://api.url/"

        fun create(): QuestionnaireApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .client(OkHttpClient().newBuilder().addInterceptor(FakeInterceptor()).build())
                .build()
            return retrofit.create(QuestionnaireApi::class.java)
        }
    }
}
