package com.example.reqresin.ui.view

import com.example.reqresin.data.repository.record.UserRecord

interface UserDetailInfoView : BaseView {
    fun showUser(userRecord: UserRecord)
}