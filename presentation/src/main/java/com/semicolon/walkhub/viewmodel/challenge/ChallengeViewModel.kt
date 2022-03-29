package com.semicolon.walkhub.viewmodel.challenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.domain.usecase.challenge.FetchChallengesUseCase
import com.semicolon.domain.usecase.challenge.FetchMyChallengesUseCase
import com.semicolon.walkhub.adapter.RecyclerViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val fetchChallengesUseCase: FetchChallengesUseCase,
    private val fetchMyChallengesUseCase: FetchMyChallengesUseCase
) : ViewModel() {

    private val _challengeItems = MutableLiveData<List<RecyclerViewItem>>()
    val challengeItems: LiveData<List<RecyclerViewItem>> = _challengeItems

    fun fetchChallenges() {
        viewModelScope.launch {
            kotlin.runCatching {
                val myChallenges = fetchMyChallengesUseCase.execute(Unit)
                val challenges = fetchChallengesUseCase.execute(Unit)

                myChallenges.zip(challenges) { my, all ->
                    _challengeItems.value = listOf(
                        my.map {
                            RecyclerViewItem(
                                
                            )
                        }
                    )
                }
            }

        }
    }
}