package com.valdizz.myapplication.view.component

import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import com.valdizz.myapplication.model.entity.Question
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

class AmountEditTextComponent(val question: Question) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val et = EditText(ui.ctx)
        et.hint = question.displayName
        et.inputType = InputType.TYPE_CLASS_NUMBER
        et.isEnabled = question.editable == "1"
        if (question.value != null) {
            et.setText(question.value)
        }
        return et
    }
}