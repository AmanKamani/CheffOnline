package jb.svb.cheffonline.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("_id")
    @Expose()
    val id: String,
    val name: String,
    val photo: String,
    val isAdmin: Boolean,
    val updatedAt: String,
    val token: String
)