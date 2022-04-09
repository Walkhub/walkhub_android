package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.entity.exercise.ExercisingUserEntity
import com.semicolon.domain.usecase.exercise.FetchExerciseRecordListUseCase
import com.semicolon.domain.usecase.exercise.FetchExercisingUserListUseCase
import com.semicolon.domain.usecase.socket.CheeringUseCase
import com.semicolon.domain.usecase.socket.ConnectSocketUseCase
import com.semicolon.domain.usecase.socket.DisconnectSocketUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
import com.semicolon.walkhub.viewmodel.cheering.CheeringItemViewModel
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MeasureHomeViewModel @Inject constructor(
    private val fetchExerciseRecordListUseCase: FetchExerciseRecordListUseCase,
    private val fetchExercisingUserListUseCase: FetchExercisingUserListUseCase,
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase,
    private val cheeringUseCase: CheeringUseCase
) : ViewModel() {

    private var _isDistance = MutableStateFlow(true)
    val isDistance = _isDistance.asStateFlow()

    private val _measureRecyclerItem = MutableLiveData<List<RecyclerViewItem>>()
    val measureRecyclerItem: LiveData<List<RecyclerViewItem>> = _measureRecyclerItem

    private var _startMeasure = MutableEventFlow<Unit>()
    val startMeasure = _startMeasure.asEventFlow()

    init {
        viewModelScope.launch {
            connectSocketUseCase.execute(Unit)
        }
    }

    fun fetchExerciseRecordList() {
        viewModelScope.launch {
            try {
                fetchExerciseRecordListUseCase.execute(Unit).collect {
                    _measureRecyclerItem.value = ArrayList<RecyclerViewItem>().apply {
                        add(
                            RecyclerViewItem(
                                R.layout.item_measure_home_header,
                                it.toRecyclerViewItem(),
                                BR.records
                            )
                        )
                    }

                    fetchExercisingUserList()
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun fetchExercisingUserList() {
        viewModelScope.launch {
            fetchExercisingUserListUseCase.execute(Unit).collect { exercisingUsers ->
                _measureRecyclerItem.value = measureRecyclerItem.value.apply {
                    exercisingUsers.map {
                        it.toRecyclerViewItem()
                    }
                }
            }
        }

    }

    private fun List<ExerciseRecordEntity>.toRecyclerViewItem() =
        map { RecyclerViewItem(R.layout.item_measure_home_header_record, it, BR.record) }

    fun setIsDistance() {
        viewModelScope.launch {
            _isDistance.value = true
        }
    }

    fun setIsWalkCount() {
        viewModelScope.launch {
            _isDistance.value = false
        }
    }

    fun startMeasure() {
        viewModelScope.launch {
            _startMeasure.emit(Unit)
        }
    }

    inner class MeasureExercisingUserItemViewModel(id: Int, name: String, profileUrl: String) :
        CheeringItemViewModel(id, name, profileUrl) {

        override fun onClick() {
            viewModelScope.launch {
                cheeringUseCase.execute(id)
            }

        }
    }

    private fun ExercisingUserEntity.toRecyclerViewItem(): RecyclerViewItem =
        RecyclerViewItem(
            itemLayoutId = R.layout.item_cheering,
            variableId = BR.vm,
            data = this.toRecyclerviewItemViewModel()
        )

    private fun ExercisingUserEntity.toRecyclerviewItemViewModel() =
        MeasureExercisingUserItemViewModel(userId, name, profileImageUrl)

    override fun onCleared() {
        viewModelScope.launch {
            disconnectSocketUseCase.execute(Unit)
        }
        super.onCleared()
    }
}