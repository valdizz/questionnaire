package com.valdizz.myapplication.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Group {

    @SerializedName("displayName")
    @Expose
    var displayName: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("questionList")
    @Expose
    var question: List<Question>? = null
    @SerializedName("visible")
    @Expose
    var visible: String? = null
}
