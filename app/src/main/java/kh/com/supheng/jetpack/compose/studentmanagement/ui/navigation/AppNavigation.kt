package kh.com.supheng.jetpack.compose.studentmanagement.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kh.com.supheng.jetpack.compose.studentmanagement.ui.course.CourseScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.dashboard.DashboardScreen
import kh.com.supheng.jetpack.compose.studentmanagement.ui.student.StudentScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    // Get the current screen
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {

                // Dashboard button
                NavigationBarItem(
                    selected = currentRoute == Screen.Dashboard.route,
                    onClick = {
                        navController.navigate(Screen.Dashboard.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Dashboard"
                        )
                    },
                    label = {
                        Text("Dashboard")
                    }
                )

                // Students button
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
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }

            composable(Screen.Students.route) {
                StudentScreen()
            }
            composable(Screen.Course.route){
                CourseScreen()
            }
        }
    }
}