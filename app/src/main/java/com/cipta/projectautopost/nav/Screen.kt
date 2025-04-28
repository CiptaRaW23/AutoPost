package com.cipta.projectautopost.nav

sealed class Screen(val route: String) {
    object Main : Screen("main")
    object Post : Screen("post")
    object Schedule : Screen("schedule")
    object Setting : Screen("setting")
}
