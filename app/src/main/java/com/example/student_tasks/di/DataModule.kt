package com.example.student_tasks.di

import android.content.Context
import com.example.student_tasks.data.room.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRoomUsersRepository(@ApplicationContext context: Context) : UserRepository =
        UserRepository(context = context)
}