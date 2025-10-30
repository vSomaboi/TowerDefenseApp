package hu.bme.aut.android.towerdefenseapp.navigation

sealed class Screen(val route: String) {
    data object MainMenu : Screen("main_menu_screen")
    data object Login : Screen("login_screen")
    data object Register : Screen("register_screen")
}