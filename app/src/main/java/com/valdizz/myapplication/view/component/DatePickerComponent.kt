package com.valdizz.myapplication.view.component

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import com.valdizz.myapplication.model.entity.Question
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class DatePickerComponent(val question: Question) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val tv = TextView(ui.ctx)
        tv.textSize = 18f
        tv.textColor = Color.BLACK
        tv.setPadding(10, 24, 10, 24)
        if (question.value != null) {
            tv.text = question.value
        }
        else {
            tv.text = question.displayName
        }
        tv.isEnabled = question.editable == "1"
        tv.onClick {
            ui.ctx.alert {
                lateinit var datePicker: DatePicker
                customView {
                    verticalLayout {
                        datePicker = datePicker()
                    }
                }
                yesButton {
                    val simpleDateFormat = SimpleDateFormat(question.pattern, Locale.getDefault())
                    val parsedDate = simpleDateFormat.format(GregorianCalendar(datePicker.year, datePicker.month, datePicker.dayOfMonth).time)
                    //val parsedDate = "%d-%02d-%02d".format(datePicker.year, datePicker.month, datePicker.dayOfMonth)
                    tv.text = parsedDate
                }
                noButton { }
            }.show()
        }
        return tv
    }
}