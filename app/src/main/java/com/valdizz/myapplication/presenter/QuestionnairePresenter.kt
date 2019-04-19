package com.valdizz.myapplication.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.google.gson.Gson
import com.valdizz.myapplication.App
import com.valdizz.myapplication.model.entity.Questionnaire
import com.valdizz.myapplication.model.network.QuestionnaireApi
import com.valdizz.myapplication.view.QuestionnaireView
import com.valdizz.myapplication.view.component.ComponentType
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class QuestionnairePresenter : MvpPresenter<QuestionnaireView>() {

    @Inject
    lateinit var api: QuestionnaireApi
    val subscriptions = CompositeDisposable()

    init {
        App.instance.getAppComponent().injectQuestionnairePresenter(this)
    }

    fun onShowQuestionnaire() {
        loadQuestionnaire()
    }

    fun onSendAnswers(questionnaire: Questionnaire?) {
        val answers = checkAnswers(questionnaire)
        if (answers == ANSWERS_OK) {
            Log.d(APP_TAG, Gson().toJson(questionnaire))
            viewState.showAnswersSent()
        } else {
            viewState.showAlert(answers)
        }
    }

    private fun loadQuestionnaire() {
        val subscribe = api.getQuestionnaire()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { questionnaire -> viewState.showQuestionnaire(questionnaire) },
                { error -> viewState.showError("An error occurred: ${error.localizedMessage}") }
            )
        subscriptions.add(subscribe)
    }

    private fun checkAnswers(questionnaire: Questionnaire?): String {
        val errorAnswers: StringBuilder = StringBuilder()
        val groups = questionnaire?.stepList?.step?.get(0)?.group?.filter { it.visible == "1" } ?: emptyList()
        for (group in groups) {
            for (question in group.question?.filter { it.mandatory == "1" } ?: emptyList()) {
                try {
                    if (question.value == null || question.value == "") {
                        errorAnswers.append("\n${question.displayName}: value is null")
                    } else {
                        if (question.min != null || question.min != "") {
                            if ((ComponentType.valueOf(question.type!!) == ComponentType.NUMBER && question.value!!.toInt() < question.min!!.toInt()) ||
                                (ComponentType.valueOf(question.type!!) == ComponentType.AMOUNT && question.value!!.toDouble() < question.min!!.toDouble()))
                            {
                                errorAnswers.append("\n${question.displayName}: ${question.value} < min: ${question.min}")
                            }
                            if (ComponentType.valueOf(question.type!!) == ComponentType.BOOLEAN && question.value!!.toString() != question.min!!.toString()) {
                                errorAnswers.append("\n${question.displayName}: ${question.value} <> min: ${question.min}")
                            }
                        }
                        if (question.max != null || question.max != "") {
                            if ((ComponentType.valueOf(question.type!!) == ComponentType.NUMBER && question.value!!.toInt() > question.max!!.toInt()) ||
                                (ComponentType.valueOf(question.type!!) == ComponentType.AMOUNT && question.value!!.toDouble() > question.max!!.toDouble()))
                            {
                                errorAnswers.append("\n${question.displayName}: ${question.value} > max: ${question.max}")
                            }
                            if (ComponentType.valueOf(question.type!!) == ComponentType.BOOLEAN && question.value!!.toString() != question.max!!.toString()) {
                                errorAnswers.append("\n${question.displayName}: ${question.value} <> max: ${question.max}")
                            }
                        }
                    }
                } catch (e: Exception) {
                    errorAnswers.append("\n${e.localizedMessage}")
                }
            }
        }
        return if (errorAnswers.isEmpty()) {
            ANSWERS_OK
        } else {
            errorAnswers.toString()
        }
    }

    override fun destroyView(view: QuestionnaireView?) {
        super.destroyView(view)
        subscriptions.dispose()
    }

    companion object {
        const val APP_TAG = "Questionnaire_TAG"
        const val ANSWERS_OK = "Answers_OK"
    }
}