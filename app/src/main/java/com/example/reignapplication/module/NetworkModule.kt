package com.example.reignapplication.module

import com.example.reignapplication.data.network.HitApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerDevelopeApiClient(retrofit: Retrofit): HitApiClient {
        return retrofit.create(HitApiClient::class.java)
    }
}