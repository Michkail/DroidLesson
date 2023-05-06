package com.tdsoft.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tdsoft.myapplication.domain.usecases.LoginUseCase
import com.tdsoft.myapplication.domain.usecases.RegisterUserUseCase
import com.tdsoft.myapplication.network_data.response.LoginResponse
import com.tdsoft.myapplication.network_data.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val registerState: MutableLiveData<RegisterState> = MutableLiveData()
    val _registerState: LiveData<RegisterState>
        get() = registerState

    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val _loginState: LiveData<LoginState>
        get() = loginState
}

class LoginState(error: Any) {
    var isLoading:Boolean = false,
    var data: LoginResponse? = null,
    var error: String = ""
}

class RegisterState(data: Any) {
    var isLoading:Boolean = false,
    var data: RegisterResponse? = null,
    var error: String = ""
}
