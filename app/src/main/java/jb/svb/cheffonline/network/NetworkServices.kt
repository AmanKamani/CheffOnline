package jb.svb.cheffonline.network

import jb.svb.cheffonline.network.apis.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServices {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    fun getAuthApi(): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    companion object{
        private const val BASE_URL = "http://68.183.244.90/mastercheff/api/v1.0/"
    }
}