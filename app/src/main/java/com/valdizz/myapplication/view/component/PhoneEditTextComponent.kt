package com.valdizz.myapplication.view.component

import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.valdizz.myapplication.model.entity.Question
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class PhoneEditTextComponent(val question: Question) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val slots = PhoneNumberUnderscoreSlotsParser().parseSlots("+375 (__) ___-____")
        val mask = MaskImpl.createTerminated(slots)
        val watcher = MaskFormatWatcher(mask)
        val et = EditText(ui.ctx)
        watcher.installOn(et)
        et.hint = question.displayName
        et.inputType = InputType.TYPE_CLASS_PHONE
        et.isEnabled = question.editable == "1"
        if (question.value != null) {
            et.setText(question.value)
        }
        return et
    }
}