package com.example.reqresin.data.repository

import com.example.reqresin.data.repository.record.UserRecord
import io.reactivex.Completable
import javax.inject.Inject

class Repository @Inject constructor(
    private val daoFactory: DaoFactory
) {

    fun saveUsers(list: List<UserRecord>) =
        daoFactory
            .userDao()
            .insertAll(list)


    fun saveUser(user: UserRecord) =
        daoFactory
            .userDao()
            .insert(user)

    fun getUsers() =
        daoFactory
            .userDao()
            .getUsers()

    fun getUser(id: Int) =
        daoFactory
            .userDao()
            .getUser(id)
}