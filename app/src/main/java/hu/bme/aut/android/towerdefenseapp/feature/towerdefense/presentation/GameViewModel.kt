package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.MapRepository
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.Tower
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di.GameEngineFactory
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameEngine
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sqrt

@HiltViewModel
class GameViewModel @Inject constructor(
    private val mapRepository: MapRepository,
    private val engineFactory: GameEngineFactory
) : ViewModel() {
    private val _uiEvents = MutableSharedFlow<GameUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()
    private var engine: GameEngine? = null
    val gameState: StateFlow<GameState>?
        get() = engine?.state

    val map: GameMap?
        get() = engine?.map

    fun loadMap(mapId: Int) {
        val map = mapRepository.getMapById(mapId)
        engine = engineFactory.create(map)
    }

    fun onMapTap(mapTap: Offset) {
        val spot = map?.towerSpots?.firstOrNull { spot ->
            val dx = mapTap.x - spot.position.x
            val dy = mapTap.y - spot.position.y
            sqrt(dx*dx + dy*dy) < spot.radius
        }

        if (spot != null) {
            onTowerSpotClicked(spot)
        }
    }

    private fun onTowerSpotClicked(spot: TowerSpot){
        viewModelScope.launch {
            _uiEvents.emit(GameUiEvent.ShowTowerSelectionMenu(spot))
        }
    }

    fun onTowerSelected(spot: TowerSpot, towerType: Tower) {
        //TODO
    }
}

sealed class GameUiEvent {
    data class ShowTowerSelectionMenu(val spot: TowerSpot) : GameUiEvent()
    data class TowerPlaced(val spot: TowerSpot, val tower: Tower) : GameUiEvent()
}