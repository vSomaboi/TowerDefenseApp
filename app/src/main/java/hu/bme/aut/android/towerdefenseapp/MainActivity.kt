package hu.bme.aut.android.towerdefenseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.towerdefenseapp.navigation.NavGraph
import hu.bme.aut.android.towerdefenseapp.ui.theme.TowerDefenseAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TowerDefenseAppTheme {
                NavGraph()
            }
        }
    }
}