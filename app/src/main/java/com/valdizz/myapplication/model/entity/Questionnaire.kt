package com.valdizz.myapplication.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Questionnaire {

    @SerializedName("questionnaire")
    @Expose
    var stepList: StepList? = null
}
