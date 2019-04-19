package com.valdizz.myapplication.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.valdizz.myapplication.model.entity.Questionnaire

@StateStrategyType(AddToEndSingleStrategy::class)
interface QuestionnaireView : MvpView {

    fun showQuestionnaire(questionnaire: Questionnaire)
    @StateStrategyType(SkipStrategy::class)
    fun showError(error: String)
    @StateStrategyType(SkipStrategy::class)
    fun showAlert(message: String)
    @StateStrategyType(SkipStrategy::class)
    fun showAnswersSent()
}