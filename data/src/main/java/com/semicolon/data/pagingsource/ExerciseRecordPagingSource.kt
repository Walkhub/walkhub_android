package com.semicolon.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import javax.inject.Inject

class ExerciseRecordPagingSource @Inject constructor(

) : PagingSource<Int, ExerciseRecordEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ExerciseRecordEntity>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExerciseRecordEntity> {
        TODO("Not yet implemented")
    }
}