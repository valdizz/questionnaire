package com.valdizz.myapplication.view.component

import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.valdizz.myapplication.model.entity.Question
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

class SwitchComponent(val question: Question) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val switch = Switch(ui.ctx)
        switch.text = question.displayName
        switch.isEnabled = question.editable == "1"
        if (question.value != null) {
            switch.isChecked = question.value == "true"
        }
        return switch
    }
}