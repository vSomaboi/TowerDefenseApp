package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import hu.bme.aut.android.towerdefenseapp.R

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    val map = viewModel.map
    val state = viewModel.gameState?.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMap(1)
    }

    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    if(map != null){
        GameMapRenderer(map)
    }
}