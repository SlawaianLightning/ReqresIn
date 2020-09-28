package com.example.reqresin.ui.view

import com.example.reqresin.data.repository.record.UserRecord

interface UsersView : BaseView {
    fun showUsers(userRecord: List<UserRecord>)
}