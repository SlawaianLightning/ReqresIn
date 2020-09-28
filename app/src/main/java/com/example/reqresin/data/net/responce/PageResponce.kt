package com.example.reqresin.data.net.responce

import com.google.gson.annotations.SerializedName

data class PageResponce(
    @SerializedName("data") var list: List<UserResponce>
)