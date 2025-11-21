package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    mapId: Int
) {
    val activity = LocalContext.current as Activity

    val map = viewModel.map
    val state = viewModel.gameState?.collectAsState()

    var showTowerMenu by remember { mutableStateOf(false) }
    var selectedSpot by remember { mutableStateOf<TowerSpot?>(null) }

    var scaleX by remember { mutableStateOf(1f) }
    var scaleY by remember { mutableStateOf(1f) }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    LaunchedEffect(Unit) {
        viewModel.loadMap(mapId)
        viewModel.uiEvents.collect { event ->
            when(event) {
                is GameUiEvent.ShowTowerSelectionMenu -> {
                    selectedSpot = event.spot
                    showTowerMenu = true
                }
                else -> {}
            }
        }
    }

    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    if(map != null){
        GameMapRenderer(
            map,
            onMapTap = { offset ->
                viewModel.onMapTap(offset)
            },
            onScaleComputed = { sx, sy ->
                scaleX = sx
                scaleY = sy
            },
            onCanvasSizeComputed = { size ->
                canvasSize = size
            }
        )

        if (showTowerMenu && selectedSpot != null) {
            TowerSelectionMenuOverlay(
                spot = selectedSpot!!,
                scaleX = scaleX,
                scaleY = scaleY,
                canvasSize = canvasSize,
                onTowerSelected = { type ->
                    viewModel.onTowerSelected(selectedSpot!!, type)
                    showTowerMenu = false
                    selectedSpot = null
                },
                onDismiss = {
                    showTowerMenu = false
                    selectedSpot = null
                }
            )
        }
    }


}