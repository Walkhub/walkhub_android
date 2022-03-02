package com.semicolon.data.local.storage

import kotlinx.coroutines.flow.Flow

interface SpeedDataStorage {

    fun fetchCurrentSpeed(): Flow<Float>
}