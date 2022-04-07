package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.usecase.exercise.FetchExerciseRecordListUseCase
import com.semicolon.domain.usecase.exercise.FetchExercisingUserListUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
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
    private val fetchExercisingUserListUseCase: FetchExercisingUserListUseCase
) : ViewModel() {

    private var _isDistance = MutableStateFlow(true)
    val isDistance = _isDistance.asStateFlow()

    private val _measureRecyclerItem = MutableLiveData<List<RecyclerViewItem>>()
    val measureRecyclerItem: LiveData<List<RecyclerViewItem>> = _measureRecyclerItem

    private var _startMeasure = MutableEventFlow<Unit>()
    val startMeasure = _startMeasure.asEventFlow()

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
                    exercisingUsers // item 넣기
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
}