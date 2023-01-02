package com.example.student_tasks.di

import com.example.student_tasks.network.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
//            .addInterceptor(createAuthorizationInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(mOkHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(client : Retrofit) : AuthService {
        return client.create(AuthService::class.java)
    }

//    private fun createAuthorizationInterceptor(): Interceptor {
//        val prefHelper: PrefHelper? = null
//        return Interceptor { chain ->
//            val newBuilder = chain.request().newBuilder()
//            val token = prefHelper?.getAccessToken()
//            if (token != null) {
//                newBuilder.addHeader("Authorization", StringConstants.bearerHeader + token)
//            }
//            return@Interceptor chain.proceed(newBuilder.build())
//        }
//    }
}