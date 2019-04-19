package com.valdizz.myapplication.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.valdizz.myapplication.R
import com.valdizz.myapplication.model.entity.Questionnaire
import com.valdizz.myapplication.presenter.QuestionnairePresenter
import com.valdizz.myapplication.view.QuestionnaireView
import com.valdizz.myapplication.view.adapter.ExpandListAdapter
import kotlinx.android.synthetic.main.activity_questionnaire.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast

class QuestionnaireActivity : MvpAppCompatActivity(), QuestionnaireView {

    @InjectPresenter
    internal lateinit var questionnairePresenter: QuestionnairePresenter
    var questions: Questionnaire? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
        setSupportActionBar(toolbar);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.questionnaire_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_load -> {
                questionnairePresenter.onShowQuestionnaire()
                return true
            }
            R.id.action_send -> {
                if (questions != null) {
                    questionnairePresenter.onSendAnswers(questions)
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun showAlert(message: String) {
        alert(getString(R.string.msg_incorrectdata, message)){
            title = getString(R.string.msg_warning)
            okButton {  }
        }.show()
    }

    override fun showQuestionnaire(questionnaire: Questionnaire) {
        questions = questionnaire
        expandable_view.setAdapter(ExpandListAdapter(this, questionnaire))
    }

    override fun showAnswersSent() {
        toast(getString(R.string.msg_sentdata))
    }
}
