package com.example.student_tasks.di

import android.content.Context
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.interfaces.authentication.RegisterInterface
import com.example.student_tasks.interfaces.content.FacultyAndSpecialityListInterface
import com.example.student_tasks.interfaces.content.StudentListInterface
import com.example.student_tasks.network.AuthService
import com.example.student_tasks.repository.*
import com.example.student_tasks.security.PrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideStudentListRepository(@ApplicationContext context: Context, client : Retrofit) : StudentListInterface {
        return StudentListRepository(roomRepo = UserRepository(context),
            authService = client.create(AuthService::class.java))
    }

    @Provides
    @Singleton
    fun provideRoomUsersRepository(@ApplicationContext context: Context) : UserRepository {
        return UserRepository(context = context)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(client : Retrofit) : RegisterInterface {
        return RegisterRepository(authService = client.create(AuthService::class.java))
    }

    @Provides
    @Singleton
    fun provideLoginRepository(client : Retrofit) : LoginInterface {
        return LoginRepository(authService = client.create(AuthService::class.java))
    }

    @Provides
    @Singleton
    fun provideLaunchAppRepository(client : Retrofit) : LaunchAppInterface {
        return LaunchAppRepository(authService = client.create(AuthService::class.java))
    }

    @Provides
    @Singleton
    fun provideFacAndSpecListRepository() : FacultyAndSpecialityListInterface {
        return FacultyAndSpecialityListRepository()
    }

    @Provides
    @Singleton
    fun providePrefHelper(@ApplicationContext context: Context) : PrefHelper {
        return PrefHelper(context = context)
    }
}