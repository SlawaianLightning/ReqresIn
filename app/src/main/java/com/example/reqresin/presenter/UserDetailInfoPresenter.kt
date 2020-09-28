package com.example.reqresin.presenter

import com.example.reqresin.data.UsecaseFactory
import com.example.reqresin.data.repository.record.UserRecord
import com.example.reqresin.ui.view.UserDetailInfoView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailInfoPresenter @Inject constructor(
    private val usecaseFactory: UsecaseFactory
) {
    private lateinit var view: UserDetailInfoView

    fun attach(view: UserDetailInfoView) {
        this.view = view
    }

    fun getUserDetailInfo(id: Int) {
        view.showProgress(true)
        usecaseFactory
            .getUserDetailInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showUser(it)
                view.showProgress(false)
            }, {
                view.showError(it.message.toString())
                view.showProgress(false)
            })
    }

}