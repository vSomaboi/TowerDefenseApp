package hu.bme.aut.android.towerdefenseapp.feature.towerdefense.presentation

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.GameMap
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.MapRepository
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.maps.TowerSpot
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.data.model.TowerType
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di.GameEngineFactory
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.di.TowerFactory
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameEngine
import hu.bme.aut.android.towerdefenseapp.feature.towerdefense.domain.logic.GameState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> get() = _gameState

    val map: GameMap?
        get() = engine?.map

    fun loadMap(mapId: Int) {
        val map = mapRepository.getMapById(mapId)
        engine = engineFactory.create(map)
        engine!!.start()
        viewModelScope.launch {
            engine!!.state.collect { state ->
                _gameState.value = state
            }
        }
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
        val hasTower = engine?.state?.value?.towers?.any{ it.spotId == spot.id }
        viewModelScope.launch {
            if(hasTower == true){
                _uiEvents.emit(GameUiEvent.ShowTowerUpgradeMenu(spot))
            }else{
                _uiEvents.emit(GameUiEvent.ShowTowerSelectionMenu(spot))
            }
        }
    }

    fun onTowerSelected(spot: TowerSpot, towerType: TowerType) {
        val tower = TowerFactory.create(towerType, spot)
        engine?.placeTower(tower)
    }
}

sealed class GameUiEvent {
    data class ShowTowerSelectionMenu(val spot: TowerSpot) : GameUiEvent()
    data class ShowTowerUpgradeMenu(val spot: TowerSpot) : GameUiEvent()
}