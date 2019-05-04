package `in`.co.ankitarora.templatechooser.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RestClient {
    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.dmp.jimdo-server.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}