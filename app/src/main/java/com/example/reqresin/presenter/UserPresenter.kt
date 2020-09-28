package com.example.reqresin.presenter

import com.example.reqresin.data.UsecaseFactory
import com.example.reqresin.data.repository.record.UserRecord
import com.example.reqresin.ui.view.UsersView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserPresenter @Inject constructor(
    private val usecaseFactory: UsecaseFactory
) {
    private lateinit var view: UsersView
    private var page: Int = 2

    fun attach(view: UsersView) {
        this.view = view
    }

    fun nextPage() {
        if (page < 2) {
            ++page
            getUsers()
        }
    }

    fun previousPage() {
        if (page > 0) {
            --page
            getUsers()
        }
    }

    fun getUsers() {
        view.showProgress(true)
        usecaseFactory
            .getUsers(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showUsers(it)
                view.showProgress(false)
            }, {
                view.showError(it.message.toString())
                view.showProgress(false)
            })
    }

}