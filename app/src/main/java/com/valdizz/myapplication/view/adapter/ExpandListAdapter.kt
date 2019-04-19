package com.valdizz.myapplication.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import com.valdizz.myapplication.model.entity.Group
import com.valdizz.myapplication.model.entity.Question
import com.valdizz.myapplication.model.entity.Questionnaire
import com.valdizz.myapplication.view.component.ViewComponentFactory
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.textChangedListener


class ExpandListAdapter(val context: Context, val questionnaire: Questionnaire) : BaseExpandableListAdapter() {


    private val groups = questionnaire.stepList?.step?.get(0)?.group?.filter { it.visible == "1" } ?: emptyList()
    private val questions: HashMap<Group, List<Question>> = HashMap()

    init {
        for (group in groups) {
            questions.put(group, group.question ?: emptyList())
        }
    }

    override fun getGroup(groupPosition: Int): Any {
        return groups.get(groupPosition)
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val group = getGroup(groupPosition) as Group
        return ViewComponentFactory().getGroup(group).createView(AnkoContext.Companion.create(context, parent!!, true))
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return questions.get(groups.get(groupPosition))?.size ?: 0
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return questions.get(groups.get(groupPosition))?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val question = getChild(groupPosition, childPosition) as Question
        val component = ViewComponentFactory().getComponent(question).createView(AnkoContext.Companion.create(context, parent!!, true))
        when (component) {
            is EditText -> {
                component.textChangedListener {
                    afterTextChanged { question.value = it.toString().trim() }
                }
            }
            is Switch -> {
                component.setOnClickListener { question.value = component.isChecked.toString() }
            }
            is TextView -> {
                component.textChangedListener {
                    afterTextChanged { question.value = it.toString().trim() }
                }
            }
        }
        return component
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return groups.size
    }
}