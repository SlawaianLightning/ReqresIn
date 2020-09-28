package com.example.reqresin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.reqresin.App
import com.example.reqresin.R
import com.example.reqresin.data.repository.record.UserRecord
import com.example.reqresin.presenter.UserDetailInfoPresenter
import com.example.reqresin.presenter.UserPresenter
import com.example.reqresin.ui.activity.UsersActivity.Companion.ID_USER
import com.example.reqresin.ui.view.UserDetailInfoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail_info.*
import kotlinx.android.synthetic.main.activity_user_detail_info.pb
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject

class UserDetailInfoActivity : AppCompatActivity(), UserDetailInfoView {

    @Inject
    lateinit var presenter: UserDetailInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail_info)
        App[this]?.component()?.inject(this)
        presenter.attach(this)
        intent.extras?.getInt(ID_USER)?.let { presenter.getUserDetailInfo(it) }
    }

    override fun showUser(userRecord: UserRecord) {
        Picasso.with(this)
            .load(userRecord.avatar)
            .into(iv_avatar)
        tv_name.text = userRecord.firstName+" "+userRecord.lastName
        tv_email.text = userRecord.email
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress(isProgress: Boolean) {
        if (isProgress) {
            pb.visibility = View.VISIBLE
        } else {
            pb.visibility = View.INVISIBLE
        }
    }
}