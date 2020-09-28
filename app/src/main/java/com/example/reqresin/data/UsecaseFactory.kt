package com.example.reqresin.data

import com.example.reqresin.data.net.ApiHelper
import com.example.reqresin.data.repository.Repository
import com.example.reqresin.data.repository.record.UserRecord
import io.reactivex.Single
import javax.inject.Inject


class UsecaseFactory @Inject constructor(
    private val repository: Repository,
    private val apiHelper: ApiHelper
) {
    fun getUsers(page: Int) =
        apiHelper
            .getUsers(page)
            .map {
                var usersRecord = it.list.map {
                    UserRecord(
                        id = it.id,
                        email = it.email,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        avatar = it.avatar
                    )
                }
                repository.saveUsers(usersRecord)
                usersRecord
            }
            .onErrorReturn {
                Single.just(repository.getUsers()).blockingGet().blockingFirst()
            }

    fun getUserDetailInfo(id: Int) =
        apiHelper
            .getUserDetailInfo(id)
            .map {
                var userRecord = UserRecord(
                    id = it.userResponce.id,
                    email = it.userResponce.email,
                    firstName = it.userResponce.firstName,
                    lastName = it.userResponce.lastName,
                    avatar = it.userResponce.avatar
                )
                repository.saveUser(userRecord)
                userRecord
            }
            .onErrorReturn {
                repository.getUser(id).blockingFirst()
            }
}