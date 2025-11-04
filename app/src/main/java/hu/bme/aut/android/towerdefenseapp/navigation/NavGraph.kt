package hu.bme.aut.android.towerdefenseapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.bme.aut.android.towerdefenseapp.feature.authentication.login.LoginScreen
import hu.bme.aut.android.towerdefenseapp.feature.authentication.register.RegisterScreen
import hu.bme.aut.android.towerdefenseapp.feature.mainmenu.MainMenuScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route){
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToMainMenu = {
                    navController.navigate(Screen.MainMenu.route)
                }
            )
        }
        composable(Screen.Register.route){
            RegisterScreen(
                onNavigateToMainMenu = {
                    navController.navigate(Screen.MainMenu.route)
                }
            )
        }
        composable(Screen.MainMenu.route){
            MainMenuScreen()
        }

    }
}