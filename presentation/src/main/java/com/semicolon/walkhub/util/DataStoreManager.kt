package com.semicolon.walkhub.util

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreManager(private val context : Context) {

    private val Context.dataStore  by preferencesDataStore(name = "authLogin_dataStore")

    private val idKey = stringPreferencesKey("idKey")
    private val pwKey = stringPreferencesKey("pwKey")

    val getId : Flow<String> = getData(idKey)
    val getPw : Flow<String> = getData(pwKey)

    fun getData(b : Preferences.Key<String>) : Flow<String> = context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[b] ?: ""
            }

    suspend fun setData(id : String, pw: String){
        context.dataStore.edit { preferences ->
            preferences[idKey] = id
            preferences[pwKey] = pw
        }
    }
}