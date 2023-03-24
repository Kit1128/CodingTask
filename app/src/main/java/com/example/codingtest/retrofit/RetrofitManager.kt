package com.example.codingtest.retrofit

import android.content.Context
import com.example.codingtest.retrofit.request.AlbumService
import okhttp3.Interceptor
import okhttp3.Response

class RetrofitManager private constructor(private val appContext: Context): Interceptor {

    private var apis: AlbumService? = null

    companion object {

        private var _SINGLETON: AlbumService? = null

        fun getInstance(context: Context): AlbumService {
            if (_SINGLETON == null) {
                _SINGLETON = RetrofitManager(context.applicationContext).apis
            }
            return _SINGLETON!!
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .build()
        return chain.proceed(request)
    }

}