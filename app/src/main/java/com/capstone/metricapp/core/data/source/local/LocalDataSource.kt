package com.capstone.metricapp.core.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    fun getUserToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }
    }

    suspend fun deleteCache() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveUserDivision(division: String) {
        dataStore.edit { preferences ->
            preferences[USER_DIVISION_KEY] = division
        }
    }

    fun getUserDivision(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USER_DIVISION_KEY] ?: ""
        }
    }


    companion object {
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token_key")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email_key")
        private val USER_DIVISION_KEY = stringPreferencesKey("user_division_key")

    }
}