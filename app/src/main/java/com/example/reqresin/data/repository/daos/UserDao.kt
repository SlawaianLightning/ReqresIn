package com.example.reqresin.data.repository.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reqresin.data.repository.record.UserRecord
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flowable<List<UserRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(actinonsRecord: List<UserRecord>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userRecord: UserRecord)

    @Query("SELECT * FROM users where id=:id")
    fun getUser(id: Int): Flowable<UserRecord>
}