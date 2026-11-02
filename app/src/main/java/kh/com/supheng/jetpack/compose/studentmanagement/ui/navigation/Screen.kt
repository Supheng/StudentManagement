package kh.com.supheng.jetpack.compose.studentmanagement.ui.navigation

sealed class Screen(val route: String) {

    object Dashboard : Screen("dashboard")

    object Students : Screen("students")
    object Course : Screen("courses")

}