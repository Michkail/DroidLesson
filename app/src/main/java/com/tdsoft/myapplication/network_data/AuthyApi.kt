package com.tdsoft.myapplication.network_data

import com.tdsoft.myapplication.network_data.requests.LoginRequest
import com.tdsoft.myapplication.network_data.requests.RegisterRequest
import com.tdsoft.myapplication.network_data.response.LoginResponse
import com.tdsoft.myapplication.network_data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthyApi {
    companion object{
        const val REGISTER = "accounts/register/"
        const val LOGIN = "accounts/login/"
    }

    @POST(REGISTER)
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @POST(LOGIN)
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}