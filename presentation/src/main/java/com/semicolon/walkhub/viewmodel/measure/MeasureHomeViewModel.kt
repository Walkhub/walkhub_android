package com.semicolon.walkhub.viewmodel.measure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.usecase.exercise.FetchExerciseRecordListUseCase
import com.semicolon.walkhub.BR
import com.semicolon.walkhub.R
import com.semicolon.walkhub.adapter.RecyclerViewItem
import com.semicolon.walkhub.util.MutableEventFlow
import com.semicolon.walkhub.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeasureHomeViewModel @Inject constructor(
    private val fetchExerciseRecordListUseCase: FetchExerciseRecordListUseCase
) : ViewModel() {

    private var _isDistance = MutableStateFlow(true)
    val isDistance = _isDistance.asStateFlow()

    private val _measureRecyclerItem = MutableLiveData<List<RecyclerViewItem>>()
    val measureRecyclerItem  = _measureRecyclerItem

    fun fetchExerciseRecordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                fetchExerciseRecordListUseCase.execute(Unit).collect {
                    _measureRecyclerItem.value = ArrayList<RecyclerViewItem>().apply {
                        add(RecyclerViewItem(R.layout.item_measure_home_header, it.toRecyclerViewItem(), BR.records))
                    }
                }
            }.onFailure {
                it
            }
        }
    }

    private fun List<ExerciseRecordEntity>.toRecyclerViewItem() =
        map { RecyclerViewItem(R.layout.item_measure_home_header_record, it, BR.record) }

    fun setIsDistance() {
        viewModelScope.launch {
            _isDistance.emit(true)
        }
    }

    fun setIsNotDistance() {
        viewModelScope.launch {
            _isDistance.emit(false)
        }
    }
}