package com.example.student_tasks.di

import android.content.Context
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.interfaces.authentication.RegisterInterface
import com.example.student_tasks.interfaces.content.StudentListInterface
import com.example.student_tasks.repository.LaunchAppRepository
import com.example.student_tasks.repository.LoginRepository
import com.example.student_tasks.repository.RegisterRepository
import com.example.student_tasks.repository.StudentListRepository
import com.example.student_tasks.security.PrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelsModule {

    @Provides
    fun provideStudentListRepository(@ApplicationContext context: Context) : StudentListInterface {
        return StudentListRepository(roomRepo = UserRepository(context))
    }

    @Provides
    fun provideRoomUsersRepository(@ApplicationContext context: Context) : UserRepository {
        return UserRepository(context = context)
    }

    @Provides
    fun provideRegisterRepository() : RegisterInterface {
        return RegisterRepository()
    }

    @Provides
    fun provideLoginRepository() : LoginInterface {
        return LoginRepository()
    }

    @Provides
    fun provideLaunchAppRepository() : LaunchAppInterface {
        return LaunchAppRepository()
    }

    @Provides
    fun providePrefHelper(@ApplicationContext context: Context) : PrefHelper {
        return PrefHelper(context = context)
    }
}