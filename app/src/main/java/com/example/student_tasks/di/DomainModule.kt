package com.example.student_tasks.di

import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetLocalUserList(userRepository: UserRepository) : List<Users> {
        return userRepository.getAllUsers()
    }
}