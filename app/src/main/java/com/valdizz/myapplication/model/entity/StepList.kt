package com.valdizz.myapplication.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StepList {

    @SerializedName("stepList")
    @Expose
    var step: List<Step>? = null
}
