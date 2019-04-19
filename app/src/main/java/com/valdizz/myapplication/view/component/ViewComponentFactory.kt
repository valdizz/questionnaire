package com.valdizz.myapplication.view.component

import android.view.ViewGroup
import com.valdizz.myapplication.model.entity.Group
import com.valdizz.myapplication.model.entity.Question
import org.jetbrains.anko.AnkoComponent

class ViewComponentFactory {

    fun getComponent(question: Question): AnkoComponent<ViewGroup> {
        return when (ComponentType.valueOf(question.type ?: "none")) {
            ComponentType.TEXT -> EditTextComponent(question)
            ComponentType.DATE -> DatePickerComponent(question)
            ComponentType.PHONE -> PhoneEditTextComponent(question)
            ComponentType.NUMBER -> NumberEditTextComponent(question)
            ComponentType.AMOUNT -> AmountEditTextComponent(question)
            ComponentType.BOOLEAN -> SwitchComponent(question)
            else -> throw IllegalArgumentException("Illegal component type!")
        }
    }

    fun getGroup(group: Group): AnkoComponent<ViewGroup> {
        return GroupTextViewComponent(group)
    }
}