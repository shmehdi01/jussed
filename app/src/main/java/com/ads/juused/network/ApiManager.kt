package com.ads.juused.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class ApiManager {

//    private var apiEndpoint: ApiInterface
//
    companion object {
        private val instance = ApiManager()
//
        fun getInstance(): ApiManager {
            return instance
        }
    }

    constructor() {
//        httpClient = getHttpClient()
//        apiEndpoint = getAuthService()
    }

//    private fun getAuthService(): ApiInterface {
//        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
//            .baseUrl(BuildConfig.ENDPOINT)
//            .addConverterFactory(
//                GsonConverterFactory.create(
//                    GsonBuilder()
//                        .serializeNulls().create()
//                )
//            )
//        val retrofit = retrofitBuilder.client(httpClient.build()).build()
//        return retrofit.create(ApiInterface::class.java)
//    }
//
//    private var httpClient: OkHttpClient.Builder
//
//    private fun getHttpClient(): OkHttpClient.Builder {
//        val interceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val cacheSize = 5 * 1024 * 1024.toLong()
//        val myCache = Cache(JetApplication.getContext().cacheDir, cacheSize)
//
//        return OkHttpClient.Builder()
//            .cache(myCache)
//            .addInterceptor(interceptor)
//            .addInterceptor(ChuckInterceptor(JetApplication.getContext()))
//            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
//                val original = chain.request()
//                val requestBuilder: Request.Builder
//                requestBuilder = original.newBuilder()
//                    .method(original.method, original.body)
//                var request = requestBuilder.build()
//                if (AppUtils.isNetworkAvailable(JetApplication.getContext())) {
//                    request = request.newBuilder().header(
//                        "Cache-Control",
//                        "public, max-age=" + 5
//                    ).build();
//                } else {
//                    request = request.newBuilder().header(
//                        "Cache-Control",
//                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
//                    ).build();
//                }
//                // End of if-else statement
//                val response = chain.proceed(request)
//                Log.e("CertificateResponse =", response.toString())
//                response
//            }) //.authenticator(new CustomAuthenticator())
//            .readTimeout(120000, TimeUnit.MILLISECONDS)
//            .writeTimeout(120000, TimeUnit.MILLISECONDS)
//    }
//
//    fun articleData(page: Int): Call<ArrayList<ArticleResponse>> {
//        return apiEndpoint.getQuizData(page, 10);
//    }
}