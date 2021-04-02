package jb.svb.cheffonline.network.requests

import com.google.gson.annotations.SerializedName

data class LoginNetworkRequest(
    val email: String,
    val password: String
)