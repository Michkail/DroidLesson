package com.tdsoft.myapplication.domain.usecases

import com.tdsoft.myapplication.di.RepositoryModule
import com.tdsoft.myapplication.network_data.requests.RegisterRequest
import com.tdsoft.myapplication.network_data.response.RegisterResponse
import com.tdsoft.myapplication.util.Resource
import com.tdsoft.myapplication.viewmodel.RegisterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: RepositoryModule.AuthRepository){
    operator fun invoke(request: RegisterRequest): Flow<Resource<RegisterResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.register(request)
            emit(Resource.Success(response))
        }catch (e: HttpException){
            emit(Resource.Error("An error occurred"))
        }catch (e: IOException){
            emit(Resource.Error("Check internet connection"))
        }
    }

    fun register(registerRequest: RegisterRequest){
        registerUserUseCase(registerRequest).onEach { result->
            when(result){
                is Resource.Success<*> ->{
                    registerState.value = RegisterState(data = result.data)
                }
                is Resource.Loading<*> ->{
                    registerState.value = RegisterState(isLoading = true)
                }
                is Resource.Error<*> -> {
                    registerState.value = result.message?.let { RegisterState(error = it) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun RegisterState(error: Any): RegisterState {

    }

    private fun RegisterState(isLoading: Boolean): RegisterState {

    }

    private fun registerUserUseCase(registerRequest: RegisterRequest): Any {

    }
}

fun Any.onEach(any: Any): Any {

}
