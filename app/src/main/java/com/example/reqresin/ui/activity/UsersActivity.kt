package com.example.reqresin.ui.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reqresin.App
import com.example.reqresin.R
import com.example.reqresin.data.repository.record.UserRecord
import com.example.reqresin.presenter.UserPresenter
import com.example.reqresin.ui.adapter.UsersAdapter
import com.example.reqresin.ui.view.UsersView
import kotlinx.android.synthetic.main.activity_users.*
import javax.inject.Inject
import kotlin.properties.Delegates


class UsersActivity : AppCompatActivity(), UsersView {

    @Inject
    lateinit var presenter: UserPresenter
    private var usersAdapter: UsersAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        usersAdapter = UsersAdapter()
        App[this]?.component()?.inject(this)
        presenter.attach(this)
        presenter.getUsers()
        ib_previous.setOnClickListener {
            presenter.previousPage()
        }
        ib_next.setOnClickListener {
            presenter.nextPage()
        }
    }

    override fun showUsers(usersRecord: List<UserRecord>) {
        rv_users.adapter = usersAdapter
        rv_users.layoutManager = LinearLayoutManager(this)
        usersAdapter.items = usersRecord as MutableList<UserRecord>
        usersAdapter.setCallback { userRecord ->
            val intent = Intent(this, UserDetailInfoActivity::class.java)
            intent.putExtra(ID_USER, userRecord.id);
            startActivity(intent)
        }
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

    companion object {
        public const val ID_USER = "ID_USER"
    }
}