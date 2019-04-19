package com.valdizz.myapplication.view.component

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.valdizz.myapplication.model.entity.Group
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent

class GroupTextViewComponent(val group: Group) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val tv = TextView(ui.ctx)
        tv.text = group.displayName
        tv.layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
        tv.setPadding(100, 24, 10, 24)
        tv.textSize = 18f
        return tv
    }
}