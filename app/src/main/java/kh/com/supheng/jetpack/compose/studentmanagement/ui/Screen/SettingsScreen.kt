
package kh.com.supheng.jetpack.compose.studentmanagement.ui.Screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kh.com.supheng.jetpack.compose.studentmanagement.R
import kh.com.supheng.jetpack.compose.studentmanagement.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var languageMenuExpanded by remember {
        mutableStateOf(false)
    }

    val selectedLanguage = when (uiState.language) {
        "Khmer" -> stringResource(R.string.khmer)
        else -> stringResource(R.string.english)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineMedium
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.dark_mode),
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = uiState.darkMode,
                onCheckedChange = viewModel::updateDarkMode
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.notifications),
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = uiState.notifications,
                onCheckedChange = viewModel::updateNotifications
            )
        }

        Text(
            text = stringResource(R.string.language),
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedButton(
            onClick = {
                languageMenuExpanded = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = selectedLanguage)
        }

        DropdownMenu(
            expanded = languageMenuExpanded,
            onDismissRequest = {
                languageMenuExpanded = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.english))
                },
                onClick = {
                    languageMenuExpanded = false
                    viewModel.updateLanguage("English")

                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("en")
                    )
                }
            )

            DropdownMenuItem(
                text = {
                    Text(stringResource(R.string.khmer))
                },
                onClick = {
                    languageMenuExpanded = false
                    viewModel.updateLanguage("Khmer")

                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags("km")
                    )
                }
            )
        }
    }
}
