package com.tdsoft.myapplication.di

import com.tdsoft.myapplication.network_data.AuthyApi
import com.tdsoft.myapplication.network_data.requests.LoginRequest
import com.tdsoft.myapplication.network_data.requests.RegisterRequest
import com.tdsoft.myapplication.network_data.response.RegisterResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(api: AuthyApi): AuthRepository = AuthRepository(api)

    class AuthRepository(api: AuthyApi) {
        fun register(request: RegisterRequest) {

        }

        fun login(request: LoginRequest) {

        }

    }
}