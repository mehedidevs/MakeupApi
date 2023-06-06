package com.mehedi.makeupapi_2204


import com.google.gson.annotations.SerializedName

data class ProductColor(
    @SerializedName("colour_name")
    var colourName: String?,
    @SerializedName("hex_value")
    var hexValue: String?
)