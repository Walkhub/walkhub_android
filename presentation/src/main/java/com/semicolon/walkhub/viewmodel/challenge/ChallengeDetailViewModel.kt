package com.semicolon.walkhub.viewmodel.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.usecase.challenge.FetchChallengeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeDetailViewModel @Inject constructor(
    private val fetchChallengeDetailUseCase: FetchChallengeDetailUseCase
) : ViewModel() {

    private val _challengeDetail = MutableLiveData<ChallengeDetailEntity>()
    val challengeDetail: LiveData<ChallengeDetailEntity> = _challengeDetail

    fun fetchChallengeDetail(challengeId: Long) {
        viewModelScope.launch {
            fetchChallengeDetailUseCase.execute(challengeId.toInt()).collect {
                _challengeDetail.value = it
            }
        }
    }
}