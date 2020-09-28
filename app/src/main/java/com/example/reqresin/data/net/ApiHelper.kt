package com.example.reqresin.data.net

import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val rest: ApiService
) {

    fun getUsers(page: Int) =
        rest
            .getUsers(page)

    fun getUserDetailInfo(id: Int) =
        rest
            .getUserDetailInfo(id)
}