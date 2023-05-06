package com.tdsoft.myapplication.domain.usecases

import androidx.constraintlayout.helper.widget.Flow
import com.tdsoft.myapplication.di.RepositoryModule
import com.tdsoft.myapplication.network_data.requests.LoginRequest
import com.tdsoft.myapplication.network_data.response.LoginResponse
import com.tdsoft.myapplication.util.Resource
import com.tdsoft.myapplication.viewmodel.LoginState
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: RepositoryModule.AuthRepository){
    operator fun invoke(request: LoginRequest): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.login(request)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("An error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("No internet connection"))
        }
    }

    fun login(loginRequest: LoginRequest){
        LoginUseCase(loginRequest).onEach { result ->
            when(result){
                is Resource.Success<*> ->{
                    loginState.value = LoginState(data = result.data)
                }

                is Resource.Loading<*> -> {
                    loginState.value = LoginState(isLoading = true, data = result.data)
                }

                is Resource.Error<*> -> {
                    loginState.value = result.message?.let { LoginState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun LoginState(isLoading: Boolean, data: Any): LoginState {

    }

}