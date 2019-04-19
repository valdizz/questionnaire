package com.valdizz.myapplication.di.module

import com.valdizz.myapplication.model.network.QuestionnaireApi
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideQuestionnaireApiService(): QuestionnaireApi {
        return QuestionnaireApi.create()
    }
}