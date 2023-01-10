package com.example.student_tasks.di

import android.content.Context
import com.example.student_tasks.network.AuthService
import com.example.student_tasks.network.exceptions.NetworkResponseAdapterFactory
import com.example.student_tasks.network.utils.*
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val interceptorTypeSelector = InterceptorTypeSelector()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        prefHelper: PrefHelper
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(
                getChuckerInterceptor(context).activeForType(
                    InterceptorType.APPLICATION,
                    interceptorTypeSelector
                )
            )
            .addNetworkInterceptor(
                getChuckerInterceptor(context).activeForType(
                    InterceptorType.NETWORK,
                    interceptorTypeSelector
                )
            )
            .addInterceptor(createAuthorizationInterceptor(prefHelper))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(mOkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(client: Retrofit): AuthService {
        return client.create(AuthService::class.java)
    }
}