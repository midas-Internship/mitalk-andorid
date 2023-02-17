package com.example.mitalk.vm.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.system.FetchLanguageUseCase
import com.example.domain.usecase.system.SaveLanguageUseCase
import com.example.mitalk.mvi.SettingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val saveLanguageUseCase: SaveLanguageUseCase,
    private val fetchLanguageUseCase: FetchLanguageUseCase
) : ContainerHost<SettingState, Unit>, ViewModel() {
    override val container = container<SettingState, Unit>(SettingState())

    fun saveLanguage(language: String) = intent {
        viewModelScope.launch {
            saveLanguageUseCase(language = language)
        }
    }

    fun fetchLanguage() = intent {
        viewModelScope.launch {
            fetchLanguageUseCase()
                .onSuccess {
                    reduce { state.copy(language = it) }
                }
        }
    }
}