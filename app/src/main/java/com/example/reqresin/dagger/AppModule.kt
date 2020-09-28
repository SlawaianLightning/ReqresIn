package com.example.reqresin.dagger

import android.content.Context
import com.example.reqresin.data.net.ApiService
import com.example.reqresin.data.repository.DaoFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(context: Context) {

    val context: Context = context

    @Provides
    fun providesContext(): Context {
        return context
    }
    @Provides
    fun provideFactDao(): DaoFactory {
        return  DaoFactory.create(context)
    }

    @Provides
    fun providesApiService(): ApiService {
        return ApiService.create()
    }
}