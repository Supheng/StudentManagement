package kh.com.supheng.jetpack.compose.studentmanagement.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kh.com.supheng.jetpack.compose.studentmanagement.ui.Screen.SettingsScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.auth.LoginScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.auth.RegisterScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.course.CourseScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.dashboard.DashboardScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.student.StudentScreen
import kh.com.supheng.jetpack.compose.studentmanagement.R

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Get the current screen
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Show bottom navigation only on main app screens
    val showBottomBar = currentRoute in listOf(
        Screen.Dashboard.route,
        Screen.Students.route,
        Screen.Course.route,
        Screen.Settings.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {

                    // Dashboard
                    NavigationBarItem(
                        selected = currentRoute == Screen.Dashboard.route,
                        onClick = {
                            navController.navigate(Screen.Dashboard.route) {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Dashboard"
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.dashboard)
                            )
                        }
                    )

                    // Students
                    NavigationBarItem(
                        selected = currentRoute == Screen.Students.route,
                        onClick = {
                            navController.navigate(Screen.Students.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Students"
                            )
                        },
                        label = {
                            Text("Students")
                        }
                    )

                    // Courses
                    NavigationBarItem(
                        selected = currentRoute == Screen.Course.route,
                        onClick = {
                            navController.navigate(Screen.Course.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = "Courses"
                            )
                        },
                        label = {
                            Text("Courses")
                        }
                    )

                    // Settings
                    NavigationBarItem(
                        selected = currentRoute == Screen.Settings.route,
                        onClick = {
                            navController.navigate(Screen.Settings.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )
                        },
                        label = {
                            Text("Settings")
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AuthRoutes.LOGIN,
            modifier = Modifier.padding(innerPadding)
        ) {

            // Login
            composable(AuthRoutes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(AuthRoutes.LOGIN) {
                                inclusive = true
                            }
                        }
                    },
                    onRegisterClick = {
                        navController.navigate(AuthRoutes.REGISTER)
                    }
                )
            }

            // Register
            composable(AuthRoutes.REGISTER) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(AuthRoutes.LOGIN) {
                                inclusive = true
                            }
                        }
                    },
                    onLoginClick = {
                        navController.popBackStack()
                    }
                )
            }

            // Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onLogout = {
                        navController.navigate(AuthRoutes.LOGIN) {
                            popUpTo(Screen.Dashboard.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // Students
            composable(Screen.Students.route) {
                StudentScreen()
            }

            // Courses
            composable(Screen.Course.route) {
                CourseScreen()
            }

            // Settings
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
