package com.example.student_tasks.di

import android.content.Context
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.repository.LoginRepository
import com.example.student_tasks.repository.RegisterRepository
import com.example.student_tasks.repository.StudentListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideStudentListRepository() : StudentListRepository {
        return StudentListRepository()
    }

    @Provides
    @Singleton
    fun provideRoomUsersRepository(@ApplicationContext context: Context) : UserRepository {
        return UserRepository(context = context)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository() : RegisterRepository {
        return RegisterRepository()
    }

    @Provides
    @Singleton
    fun provideLoginRepository() : LoginRepository {
        return LoginRepository()
    }
}