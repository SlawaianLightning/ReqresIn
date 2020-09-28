package com.example.reqresin.dagger

import com.example.reqresin.ui.activity.UserDetailInfoActivity
import com.example.reqresin.ui.activity.UsersActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: UsersActivity)
    fun inject(activity: UserDetailInfoActivity)
}