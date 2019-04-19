package com.valdizz.myapplication.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question {

    @SerializedName("displayName")
    @Expose
    var displayName: String? = null
    @SerializedName("editable")
    @Expose
    var editable: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("mandatory")
    @Expose
    var mandatory: String? = null
    @SerializedName("pattern")
    @Expose
    var pattern: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("min")
    @Expose
    var min: String? = null
    @SerializedName("max")
    @Expose
    var max: String? = null
    @SerializedName("value")
    @Expose
    var value: String? = null
}
