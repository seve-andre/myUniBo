package com.mitch.my_unibo.datastore;

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * TODO
 *
 * @property context
 */
class UserDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userStatus")

        /**
         * Keeps track of the user-status on login authentication.
         * User will login only the first time and after that datastore will be able to identify if
         * he's logged in. At every startup, if the user is logged in, he'll be redirected
         * to the home screen. If not, to the login screen.
         *
         * NOTE: if the user clears the cache of the app, this status will be restored to the
         * default one (i.e. not logged in, so login screen again).
         */
        val USER_LOGGED_IN = booleanPreferencesKey("userLoggedIn")

        val USER_EMAIL = stringPreferencesKey("userId")
    }

    /**
     * Returns whether the user is logged in, checking if the user status is stored inside datastore.
     * If not, it returns false by default.
     *
     * @return user status on login authentication
     */
    suspend fun isUserLoggedIn(): Boolean = context.dataStore
        .data
        .map { preferences -> preferences[USER_LOGGED_IN] ?: false }
        .first()

    suspend fun login() {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGGED_IN] = true
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }

    suspend fun getEmail(): String {
        return context
            .dataStore
            .data
            .map { preferences -> preferences[USER_EMAIL] ?: "" }
            .first()
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGGED_IN] = false
        }
    }
}
