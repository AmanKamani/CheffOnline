package jb.svb.cheffonline.network.apis

import jb.svb.cheffonline.network.requests.LoginNetworkRequest
import jb.svb.cheffonline.network.responses.LoginNetworkResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("user/login")
    fun login(
        @Body login: LoginNetworkRequest
    ): Call<LoginNetworkResponse>
}