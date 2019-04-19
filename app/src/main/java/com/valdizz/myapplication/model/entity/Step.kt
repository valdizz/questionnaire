package com.valdizz.myapplication.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Step {

    @SerializedName("groupList")
    @Expose
    var group: List<Group>? = null
    @SerializedName("visible")
    @Expose
    var visible: String? = null
}
