package com.example.mitalk.vm.record_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.record.GetRecordDetailUseCase
import com.example.mitalk.mvi.RecordDetailSideEffect
import com.example.mitalk.mvi.RecordDetailState
import com.example.mitalk.mvi.toSateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class RecordDetailViewModel @Inject constructor(
    private val getRecordDetailUseCase: GetRecordDetailUseCase
) : ContainerHost<RecordDetailState, RecordDetailSideEffect>, ViewModel() {
    override val container =
        container<RecordDetailState, RecordDetailSideEffect>(RecordDetailState())

    fun getRecordDetail(recordId: String) = intent {
        viewModelScope.launch {
            getRecordDetailUseCase(recordId = recordId)
                .onSuccess {
                    reduce {
                        state.copy(
                            startAt = it.startAt,
                            customerName = it.customerName,
                            counsellorName = it.counsellorName,
                            messageRecords = it.messageRecords.map { it.toSateData() })
                    }
                }.onFailure {

                }
        }
    }

    fun setTotalFindResultList(list: List<Int>) = intent {
        reduce { state.copy(totalFindResultList = list) }
        postSideEffect(
            RecordDetailSideEffect.ChangeCurrentFindPosition(
                state.totalFindResultList,
                0
            )
        )
    }

    fun plusCurrentFindPosition() = intent {
        postSideEffect(
            RecordDetailSideEffect.ChangeCurrentFindPosition(
                state.totalFindResultList,
                state.currentFindPosition + 1
            )
        )
        reduce { state.copy(currentFindPosition = state.currentFindPosition + 1) }
    }

    fun minusCurrentFindPosition() = intent {
        postSideEffect(
            RecordDetailSideEffect.ChangeCurrentFindPosition(
                state.totalFindResultList,
                state.currentFindPosition - 1
            )
        )
        reduce { state.copy(currentFindPosition = state.currentFindPosition - 1) }
    }

    fun clearFindResult() = intent {
        reduce { state.copy(totalFindResultList = listOf(), currentFindPosition = 0) }
    }
}