package com.example.mitalk.vm.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.LoginParam
import com.example.domain.usecase.auth.AutoLoginUseCase
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.system.FetchLanguageUseCase
import com.example.mitalk.mvi.LoginSideEffect
import com.example.mitalk.mvi.LoginState
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val autoLoginUseCase: AutoLoginUseCase,
    private val fetchLanguageUseCase: FetchLanguageUseCase
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container = container<LoginState, LoginSideEffect>(LoginState())

    fun login(loginParam: LoginParam) = intent {
        viewModelScope.launch {
            loginUseCase(loginParam)
                .onSuccess {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }.onFailure {
                    when (it) {
                    }
                }
        }
    }

    fun autoLogin() = intent {
        viewModelScope.launch {
            autoLoginUseCase()
                .onSuccess {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }
                .onFailure {
                }
        }
    }

    fun fetchLanguage() = intent {
        viewModelScope.launch {
            fetchLanguageUseCase()
                .onSuccess {
                    postSideEffect(LoginSideEffect.SystemLanguage(it))
                }
        }
    }

    fun inPutResult(result: GoogleSignInAccount?) = intent {
        reduce { state.copy(result = result) }
    }
}