package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation.overlays.TowerSelectionMenuOverlay
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation.overlays.TowerUpgradeMenuOverlay

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel(),
    mapId: Int
) {
    val activity = LocalContext.current as Activity

    val map = viewModel.map
    val state by viewModel.gameState.collectAsState()

    var showTowerMenu by remember { mutableStateOf(false) }
    var selectedSpot by remember { mutableStateOf<TowerSpot?>(null) }
    var showUpgradeMenu by remember { mutableStateOf(false) }
    var selectedTower by remember { mutableStateOf<Tower?>(null) }

    var scaleX by remember { mutableFloatStateOf(1f) }
    var scaleY by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(Unit) {
        viewModel.loadMap(mapId)
        viewModel.uiEvents.collect { event ->
            when(event) {
                is GameUiEvent.ShowTowerSelectionMenu -> {
                    selectedSpot = event.spot
                    showTowerMenu = true
                }
                is GameUiEvent.ShowTowerUpgradeMenu -> {
                    selectedSpot = event.spot
                    selectedTower = event.tower
                    showUpgradeMenu = true
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
            state.towers,
            onMapTap = { offset ->
                viewModel.onMapTap(offset)
            },
            onScaleComputed = { sx, sy ->
                scaleX = sx
                scaleY = sy
            }
        )

        if (showTowerMenu && selectedSpot != null) {
            TowerSelectionMenuOverlay(
                spot = selectedSpot!!,
                scaleX = scaleX,
                scaleY = scaleY,
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

        if(showUpgradeMenu && selectedSpot != null && selectedTower != null){
            TowerUpgradeMenuOverlay(
                spot = selectedSpot!!,
                tower = selectedTower!!,
                scaleX = scaleX,
                scaleY = scaleY,
                onLevelUpgraded = {
                    viewModel.onLevelUpgraded(selectedSpot!!)
                    showUpgradeMenu = false
                    selectedSpot = null
                    selectedTower = null
                },
                onSpecSelected = {},
                onDismiss = {
                    showUpgradeMenu = false
                    selectedSpot = null
                    selectedTower = null
                }
            )
        }

    }
}