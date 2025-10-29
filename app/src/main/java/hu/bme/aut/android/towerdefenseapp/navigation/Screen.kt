package hu.bme.aut.android.towerdefenseapp.navigation

sealed class Screen(val route: String) {
    data object MainMenu : Screen("main_menu_screen")
}