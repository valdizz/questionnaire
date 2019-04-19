package com.valdizz.myapplication.di

import com.valdizz.myapplication.di.module.NetworkModule
import com.valdizz.myapplication.presenter.QuestionnairePresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun injectQuestionnairePresenter(questionnairePresenter: QuestionnairePresenter)
}