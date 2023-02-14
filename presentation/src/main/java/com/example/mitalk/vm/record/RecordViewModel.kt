package com.example.mitalk.vm.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.record.GetRecordListUseCase
import com.example.mitalk.mvi.RecordSideEffect
import com.example.mitalk.mvi.RecordState
import com.example.mitalk.mvi.toStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getRecordListUseCase: GetRecordListUseCase
) : ContainerHost<RecordState, RecordSideEffect>, ViewModel() {
    override val container = container<RecordState, RecordSideEffect>(RecordState())

    fun getRecordList() = intent {
        viewModelScope.launch {
            getRecordListUseCase()
                .onSuccess {
                    reduce { state.copy(recordList = it.map { it.toStateData() }) }
                }.onFailure {

                }
        }
    }
}