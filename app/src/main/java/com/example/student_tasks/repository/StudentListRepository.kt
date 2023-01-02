package com.example.student_tasks.repository

import com.example.student_tasks.data.model.UsersListResponse
import com.example.student_tasks.data.room.UserRepository
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.interfaces.content.StudentListInterface
import com.example.student_tasks.network.AuthService
import retrofit2.Response
import javax.inject.Inject

class StudentListRepository @Inject constructor(
    private val roomRepo: UserRepository,
    private val authService: AuthService,
    private val users: Users? = null
) : StudentListInterface {
    override suspend fun getRemoteUsersList(token: String): Response<UsersListResponse> {
        return authService.updateUsersList(token = token)
    }

    override fun getLocalList(): List<Users> {
        return roomRepo.getAllUsers()
    }

    override fun updateLocalList(list: List<String>?, size: Int?) {
        roomRepo.deleteAll()
        var i = 0
        if (size != null && list != null) {
            while (i < size - 1) {
                users?.let {
                    var user = Users(
                        userName = list[i],
                        userEmail = list[i+1]
                    )
                    roomRepo.updateUsers(user)
                } ?: kotlin.run {
                    val user = Users(
                        userName = list[i],
                        userEmail = list[i+1]
                    )
                    roomRepo.insertUsers(user)
                }
                i += 2
            }
        }
    }
}