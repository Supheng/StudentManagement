
package kh.com.supheng.jetpack.compose.studentmanagement.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val DARK_MODE = booleanPreferencesKey("dark_mode")
        val NOTIFICATIONS = booleanPreferencesKey("notifications")
        val LANGUAGE = stringPreferencesKey("language")
    }

    // Read Dark Mode
    val darkMode: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[DARK_MODE] ?: false
        }

    // Read Notifications
    val notifications: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[NOTIFICATIONS] ?: true
        }

    // Read Language
    val language: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[LANGUAGE] ?: "English"
        }

    // Update Dark Mode
    suspend fun updateDarkMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE] = enabled
        }
    }

    // Update Notifications
    suspend fun updateNotifications(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATIONS] = enabled
        }
    }

    // Update Language
    suspend fun updateLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = language
        }
    }

    // Delete Dark Mode preference
    suspend fun clearDarkMode() {
        dataStore.edit { preferences ->
            preferences.remove(DARK_MODE)
        }
    }
}
