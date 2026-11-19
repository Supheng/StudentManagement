
package kh.com.supheng.jetpack.compose.studentmanagement

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kh.com.supheng.jetpack.compose.studentmanagement.ui.navigation.AppNavigation
import kh.com.supheng.jetpack.compose.studentmanagement.ui.theme.StudentManagementTheme
import kh.com.supheng.jetpack.compose.studentmanagement.viewmodel.ThemeViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()

            val darkMode by themeViewModel.darkMode
                .collectAsStateWithLifecycle()

            StudentManagementTheme(
                darkTheme = darkMode
            ) {
                AppNavigation()
            }
        }
    }
}
